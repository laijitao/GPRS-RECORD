package com.hp.cmcc.bboss.gprs.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hp.cmcc.bboss.gprs.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.gprs.pojo.FieldObject;
import com.hp.cmcc.bboss.gprs.pojo.GprsRecFilePara;
import com.hp.cmcc.bboss.gprs.pojo.HandleReturnPara;
import com.hp.cmcc.bboss.gprs.utils.Tools;


@Service
public class RecordService {
	
	@Autowired
	RestTemplate rt;
	
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;
	
	Logger L = LoggerFactory.getLogger(RecordService.class);
	
	public String[] strToArr(String record){
		return record.split(",");
	}
	
	public List<FieldObject> createCdrData(List<BbdcTypeCdr> rule,String record,String hashCode,String fn){
		
		String[] S = strToArr(record);
//		String[] S = validation(record,cdr);//后期校验
		rule.sort((x,y) -> Integer.compare(x.getHinderIdx().intValue(), y.getHinderIdx().intValue()));
		List<FieldObject> list = new LinkedList<>();
		Map<String, BbdcTypeCdr> map = getRuleMap(rule);
		String key = getKeyWord(S,map); 
		for (Entry<String, BbdcTypeCdr> entry : map.entrySet()) { 
			BbdcTypeCdr cdr = entry.getValue(); 
			FieldObject fieldObject = new FieldObject();
			fieldObject.setFi(cdr.getHinderIdx().intValue());
			fieldObject.setFn(cdr.getFieldName());
			fieldObject.setSeptor(cdr.getDataSeparator());
			if(cdr.getFormerIdx() == -1L) {
				if("CREATE_DATE".equals(cdr.getFieldName().toUpperCase())){
					fieldObject.setfv(cdr.getDataFiller());
				}
//				if("File_Id".equals(cdr.getFieldName())){
//					fieldObject.setfv(fId.toString());
//				}
				if("FILE_NAME".equals(cdr.getFieldName().toUpperCase())){
					fieldObject.setfv(fn);
				}
				if("BDC_CODE".equals(cdr.getFieldName().toUpperCase())){
					fieldObject.setfv(cdr.getDataFiller());
				}
				if("OPER_SERIAL_NBR".equals(cdr.getFieldName().toUpperCase())){
//					fieldData.setfv(getOperSerialNbr(rule,S));
					String osn = null;
					try {
						osn = getOperSerialNbrByKey(key);
						fieldObject.setfv(osn);
					} catch (Exception e) {
						L.error("[the Oper_Serial_Nbr for KEY:"+key+" is null or not exist!]",e);
						fieldObject.setfv(osn);
						S[getErrCodeIndex(map)] = "F999";
					}
				}
				if("RECORD_HASH".equals(cdr.getFieldName().toUpperCase())){
					fieldObject.setfv(hashCode);
				}
			}else {
				fieldObject.setfv(S[cdr.getFormerIdx().intValue()]);
			}
			list.add(fieldObject);
		}
		return list;
	}
	
	public int getErrCodeIndex(Map<String, BbdcTypeCdr> map) {
		return map.get("ERR_CODE".toUpperCase()).getFormerIdx().intValue();
	}
	
	private String getOperSerialNbrByKey(String key) throws Exception{
		String s = jdbcTemplate1.queryForObject("select Oper_Serial_Nbr from import.bdc_gprs_011701_t "
					+ "where KEY='"+key+"'" , String.class);
		return s;
	}

	private String getKeyWord(String[] s,Map<String,BbdcTypeCdr> map) {
		String key = "";
		for (Entry<String, BbdcTypeCdr> entry : map.entrySet()) {
			if("KEY".equals(entry.getKey().toUpperCase())) {
				key = s[entry.getValue().getFormerIdx().intValue()];
			}
		}
		return key;
	}
	
	private String getKeyWordFromrecord(String S,List<BbdcTypeCdr> rule) {
		Map<String,BbdcTypeCdr> map = getRuleMap(rule);
		String key = "";
		String[] s = strToArr(S);
		for (Entry<String, BbdcTypeCdr> entry : map.entrySet()) {
			if("KEY".equals(entry.getKey().toUpperCase())) {
				key = s[entry.getValue().getFormerIdx().intValue()];
			}
		}
		return key;
	}

	private Map<String,BbdcTypeCdr> getRuleMap(List<BbdcTypeCdr> rule) {
		Map<String,BbdcTypeCdr> map = new HashMap<String,BbdcTypeCdr>();
		for(BbdcTypeCdr cdr : rule) {
			map.put(cdr.getFieldName(), cdr);
		}
		return map;
	}

	public String createOutRecord(List<FieldObject> D){
		StringBuffer sb = new StringBuffer();
		D.sort((x,y) -> Integer.compare(x.getFi(), y.getFi()));
		for(FieldObject d : D) {
			sb.append(d.getfv()+"','");
		}
		return "'"+sb.toString().substring(0, sb.toString().length()-3)+"'";
	}

	public Integer getErrNum(List<String> fileBody,List<BbdcTypeCdr> rule) {
		Integer errNum = 0;
		for(BbdcTypeCdr cdr : rule) {
			if("ERR_CODE".equals(cdr.getFieldName().toUpperCase())) {
				for(String s : fileBody) {
					if(s.split(",")//处理后的记录转化为数组
							[cdr.getHinderIdx().intValue()].trim()//根据错码下标获取错码
									.startsWith("'F")) {//判断是否为错码
						errNum++;
					}
				}
				break;
			}
		}
		return errNum;
	}

	public HandleReturnPara HandleRecord(GprsRecFilePara grfp) {
		if(grfp == null) {
			L.error("[request data is null, pls check!]");
			return new HandleReturnPara();
		}
		List<String> fb = grfp.getFileBody();
		List<BbdcTypeCdr> rule = grfp.getRule();
		String fn = grfp.getFileName();
//		Integer fId = grfp.getfileId();
		
		List<String> fileBody = new LinkedList<>();;
		for(String re : fb) {
			String hashCode = "";
			if((int)Math.random()*10 == 5) {
				hashCode = "";
			}else {
				hashCode = 
						"hash_record";
//						rt.getForObject("http://bdc-file-service/file/test",String.class);//调服务
			}
			if(Tools.IsBlank(hashCode)) {
				re = setErrCode(re,rule);
				L.error("[the RECORD_HASH for key:{} is null or not exist]", getKeyWordFromrecord(re,rule));
			}
			String record = createOutRecord(createCdrData(rule, re,hashCode,fn));
			fileBody.add(record);
		}
		Integer errNum = getErrNum(fileBody,rule);
		HandleReturnPara hrp = new HandleReturnPara(fileBody, errNum);
		return hrp;
	}

	private String setErrCode(String re, List<BbdcTypeCdr> rule) {
		Map<String, BbdcTypeCdr> map = getRuleMap(rule);
		String[] record = strToArr(re);
		record[map.get("ERR_CODE").getFormerIdx().intValue()] = "F998";
		return arrToRecord(record);
	}

	private String arrToRecord(String[] record) {
		StringBuffer re = new StringBuffer("");
		for(int i = 0;i < record.length;i++) {
			re.append(record[i]+",");
		}
		return re.toString().substring(0, re.length()-1);
	}
}
