package com.hp.cmcc.bboss.gprs.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hp.cmcc.bboss.gprs.control.RestTemplateTestControl;
import com.hp.cmcc.bboss.gprs.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.gprs.pojo.FieldObject;
import com.hp.cmcc.bboss.gprs.utils.PubData;
import com.hp.cmcc.bboss.gprs.utils.Tools;


@Service
public class RecordService {
	
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
	
	public String createAfterData(List<BbdcTypeCdr> rule,String record,String hashCode,String fn,Integer fId){
		
		String[] S = strToArr(record);
//		String[] S = validation(record,cdr);//后期校验
		rule.sort((x,y) -> Integer.compare(x.getHinderIdx().intValue(), y.getHinderIdx().intValue()));
		List<FieldObject> list = new LinkedList<>();
		Map<String, BbdcTypeCdr> map = getRuleMap(rule);
		for (Entry<String, BbdcTypeCdr> entry : map.entrySet()) { 
			BbdcTypeCdr cdr = entry.getValue(); 
			FieldObject fieldObject = new FieldObject();
			fieldObject.setFi(cdr.getHinderIdx().intValue());
			fieldObject.setFn(cdr.getFieldName());
			fieldObject.setSeptor(cdr.getDataSeparator());
			if(cdr.getFormerIdx() == -1L) {
				if("Create_Date".equals(cdr.getFieldName())){
					fieldObject.setfv(cdr.getFieldValue());
				}
				if("File_Id".equals(cdr.getFieldName())){
					fieldObject.setfv(fId.toString());
				}
				if("File_Name".equals(cdr.getFieldName())){
					fieldObject.setfv(fn);
				}
				if("Bdc_Code".equals(cdr.getFieldName())){
					fieldObject.setfv(cdr.getDataFiller());
				}
				if("Oper_Serial_Nbr".equals(cdr.getFieldName())){
//					fieldData.setfv(getOperSerialNbr(rule,S));
					String osn = getOperSerialNbrByKey(getKeyWord(S,map));
					if(Tools.IsBlank(osn)) {
						fieldObject.setfv(null);
					}
					fieldObject.setfv(osn);
				}
				if("Record_Hash".equals(cdr.getFieldName())){
					fieldObject.setfv(hashCode);
				}
			}else {
				fieldObject.setfv(S[cdr.getFormerIdx().intValue()]);
			}
			list.add(fieldObject);
		}
		return createOutRecord(list);
	}
	
	private String getOperSerialNbrByKey(String[] keyWord) {
		String sid = keyWord[0];
		String vp = keyWord[1];
		String s = "";
		try {
			s = jdbcTemplate1.queryForObject("select Oper_Serial_Nbr from bdc_gprs_011701_t "
					+ "where SERVICE_ID='"+sid +"' and VALID_PROVINCE='"+vp+"';" , String.class);
		} catch (Exception e) {
			L.error("the Oper_Serial_Nbr for service_id:"+keyWord[0]+"and valid_province:"+keyWord[1]+"null or not exist!");
		}
		return s;
	}

	private String[] getKeyWord(String[] s,Map<String,BbdcTypeCdr> map) {
		String[] kw = {"SERVICE_ID","VALID_PROVINCE"};
		for (Entry<String, BbdcTypeCdr> entry : map.entrySet()) {
			if("SERVICE_ID".equals(entry.getKey().toUpperCase())) {
				kw[0] = s[entry.getValue().getFormerIdx().intValue()];
			}
			if("VALID_PROVINCE".equals(entry.getKey().toUpperCase())) {
				kw[1] = s[entry.getValue().getFormerIdx().intValue()];
			}
		}
		return kw;
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
			sb.append(d.getfv()+d.getSeptor());
		}
		return sb.toString();
	}

	public Integer getErrNum(List<String> fileBody,List<BbdcTypeCdr> rule) {
		Integer errNum = 0;
		for(BbdcTypeCdr cdr : rule) {
			if("Err_Code".equals(cdr.getFieldName())) {
				for(String s : fileBody) {
					if(s.split(cdr.getDataSeparator())//处理后的记录转化为数组
							[cdr.getHinderIdx().intValue()]//根据错码下标获取错码
									.startsWith("F")) {//判断是否为错码
						errNum++;
					}
				}
				break;
			}
		}
		return errNum;
	}
}
