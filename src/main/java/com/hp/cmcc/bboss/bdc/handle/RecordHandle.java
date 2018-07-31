package com.hp.cmcc.bboss.bdc.handle;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.hp.cmcc.bboss.bdc.config.BdcBeanFactory;
import com.hp.cmcc.bboss.bdc.exception.ValidException;
import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.bdc.pojo.FieldObject;
import com.hp.cmcc.bboss.bdc.pojo.HandleReturnPara;
import com.hp.cmcc.bboss.bdc.utils.PubData;
import com.hp.cmcc.bboss.bdc.utils.Tools;
import com.hp.cmcc.bboss.bdc.valid.RecRecordValid;
import com.hp.cmcc.bboss.bdc.valid.impl.RecRecordValidImpl;

@Component("RecordHandle")
@EnableAsync
public class RecordHandle {
	
	RecRecordValid rv = new RecRecordValidImpl();
	
	private Logger L = LoggerFactory.getLogger(RecordHandle.class);
	
	/**
	 * @param 规则
	 * @param 文件记录
	 * @param 文件名
	 * @return 一条记录中处理后对应字段名，字段索引，字段值封装成的对象
	 */
	public List<FieldObject> createCdrData(List<BbdcTypeCdr> rule,String record,String fn,Long lineNum){
		
		String[] S = Tools.strToArr(record);
		Map<String,FieldObject> m = new HashMap<>();
		Map<String, BbdcTypeCdr> map = getRuleMap(rule);
		String bdcErrCode = "";
		//校验记录中的字段是否缺失
		try {
			rv.validFieldNum(S.length, Integer.parseInt(map.get("BDC_ERR_CODE").getDataPattern()));
		} catch (ValidException e) {
			bdcErrCode = e.getErrCode();
		}
		//记录拆分解析
		for (Entry<String, BbdcTypeCdr> entry : map.entrySet()) {
			BbdcTypeCdr cdr = entry.getValue();
			FieldObject fieldObject = new FieldObject();
			fieldObject.setFieldIndex(cdr.getHinderIdx().intValue());
			fieldObject.setFieldName(cdr.getFieldName());
			//需要添加的字段
			if(cdr.getFormerIdx() == -1L) {
				//创建时间
				if("CREATE_DATE".equalsIgnoreCase(cdr.getFieldName())){
					fieldObject.setFieldValue(cdr.getDataFiller());
				}
				//行号
				if("LINE_NUM".equalsIgnoreCase(cdr.getFieldName())){
					fieldObject.setFieldValue(lineNum+"");
				}
				//文件名
				if("FILE_NAME".equalsIgnoreCase(cdr.getFieldName())){
					fieldObject.setFieldValue(fn);
				}
				//各省网元通知加载状态文件和发布结果通知文件不需要操作流水和bdc编码
				if(!isSpecialFile(cdr.getValName())) {
					//bdc编码
					if("BDC_CODE".equalsIgnoreCase(cdr.getFieldName())){
						fieldObject.setFieldValue(cdr.getDataFiller());
					}
					//操作流水
					if("OPER_SERIAL_NBR".equalsIgnoreCase(cdr.getFieldName())){
						String osn = "";
						try {
							osn = getOperSerialNbrByKey(record, map,lineNum);
						} catch (ValidException e) {
							L.error(e.getErrMsg()+",Original record:["+record+"],fileName:"+fn+",lineNum:"+lineNum);
//							L.error(e.getErrMsg()+",Original record:["+record+"],fileName:"+fn+",lineNum:"+lineNum,e);
							fieldObject.setFieldValue(osn);
							if(Tools.IsBlank(bdcErrCode)) {
								bdcErrCode = e.getErrCode();
							}
						}
						fieldObject.setFieldValue(osn);
					}
				}
			}else {//原纪录中有的字段直接获取，否则填“”
				fieldObject.setFieldValue(getValue(S,cdr.getFormerIdx()));
			}
			m.put(fieldObject.getFieldName(),fieldObject);
		}
		//设置内部检验码
		if(Tools.IsBlank(bdcErrCode)) {//标识
			//校验通过
			m.get("BDC_ERR_CODE").setFieldValue(map.get("BDC_ERR_CODE").getDataFiller());
		}else {
			//校验未通过
			m.get("BDC_ERR_CODE").setFieldValue(bdcErrCode);
		}
		return Tools.mapToList(m);
	}
	
	public void setBdcErrCode(Map<String, BbdcTypeCdr> map,String bdcErrCode) {
		
	}
	
	private String getValue(String[] s,Long index) {
		return index < s.length ? s[index.intValue()] : "";
	}

	/**
	 * @param s：记录转化后的数组
	 * @param map：解析规则
	 * @return 查询操作流水的sql语句
	 * @throws ValidException 
	 */
	private String getSql(String record, Map<String, BbdcTypeCdr> map,long lineNum) throws ValidException {
		String key = getKeyWord(record, map,lineNum);
		String sql = map.get("ORDER_ID").getDataFiller();
		return sql.trim().substring(0,sql.length()-1)+"'"+key+"'";
	}

	/**
	 * @param 记录
	 * @return 操作流水
	 * @throws ValidException 
	 */
	private String getOperSerialNbrByKey(String record, Map<String, BbdcTypeCdr> map,long lineNum) throws ValidException{
		String s = "";
		try {
			String sql = getSql(record, map,lineNum);
			JdbcTemplate jdbcTemplate = BdcBeanFactory.getBean("mysqlJdbcTemplate", JdbcTemplate.class);
//			JdbcTemplate jdbcTemplate = BdcBeanFactory.getBean("primaryJdbcTemplate", JdbcTemplate.class);
			s = jdbcTemplate.queryForObject(sql, String.class);
		} catch (ValidException e1) {
			throw new ValidException(e1.getErrCode(),e1.getErrMsg());
		}catch (Exception e2) {
			throw new ValidException("F999","the OPER_SERIAL_NBR is null or not exist");
		}
		return s;
	}

	/**
	 * @param s:记录转化后的数组
	 * @param map:规则
	 * @return 关键字
	 * @throws ValidException 
	 */
	private String getKeyWord(String record,Map<String,BbdcTypeCdr> map,long lineNum) throws ValidException{
		String[] s = Tools.strToArr(record);
		String key = "";
		BbdcTypeCdr cdr = map.get("ORDER_ID");
		try {
			key = s[cdr.getFormerIdx().intValue()];
		} catch (Exception e) {
			throw new ValidException("F997","[get the keyWord failed, please check the number of record field],LineNum:"+lineNum+",Original record:["+record+"]");
		}
		return key;
	}
	
	/**
	 * @param rule:规则
	 * @return 以FIELD_NAME为key，规则为value的map
	 */
	public Map<String,BbdcTypeCdr> getRuleMap(List<BbdcTypeCdr> rule) {
		Map<String,BbdcTypeCdr> map = new HashMap<String,BbdcTypeCdr>();
		for(BbdcTypeCdr cdr : rule) {
			map.put(cdr.getFieldName().toUpperCase(), cdr);
		}
		return map;
	}

	/**
	 * @param 根据记录转化为对象的List
	 * @return 拼接后的字符串
	 */
	public String createOutRecord(List<FieldObject> D){
		StringBuffer sb = new StringBuffer();
		D.sort((x,y) -> Integer.compare(x.getFieldIndex(), y.getFieldIndex()));
		for(FieldObject d : D) {
			sb.append(setSqlFieldStr(d,"CREATE_DATE","LINE_NUM")+",");
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}

	/**
	 * @param 文件体
	 * @param 规则
	 * @return 错单数量
	 */
	public boolean isErrorRecord(String s,int index) {
		if(s.split(",",-1)//处理后的记录转化的数组
				[index]//根据错码下标获取错码
						.startsWith("'F")) {//判断是否为错码
			return true;
		}
		return false;
	}
	
	/**
	 * @param 文件体
	 * @param 规则
	 * @return 错单数量
	 */
	public Integer getErrNum(List<String> fileBody,List<BbdcTypeCdr> rule) {
		Map<String, BbdcTypeCdr> map = getRuleMap(rule);
		Integer errNum = 0;
		BbdcTypeCdr cdr = map.get("ERR_CODE");
		int index = cdr.getHinderIdx().intValue();
		for(String s : fileBody) {
			if(s.split(",",-1)//处理后的记录转化的数组
					[index]//根据错码下标获取错码
							.startsWith("'F")) {//判断是否为错码
				errNum++;
			}
		}
		return errNum;
	}

	/**
	 * @param 被调用时获取到的参数对象
	 * @return 处理后的传递参数对象
	 * @throws IOException 
	 */
	public HandleReturnPara HandleRecord(List<String> fb ,List<BbdcTypeCdr> rule,String fn ) {
		if(Tools.IsEmpty(fb)) {
			L.error("[request FileBody is null, pls check!]");
			return null;
		}
		if(Tools.IsEmpty(rule)) {
			L.error("[request validat rule is null, pls check!]");
			return null;
		}
		if(Tools.IsEmpty(fn)) {
			L.error("[request fileName is null, pls check!]");
			return null;
		}
		List<String> fileBody = new LinkedList<>();
		String re = null;
		
		for(Long i = 0L;i < fb.size();i++) {
			re = fb.get(i.intValue());
			if(Tools.IsBlank(re)) {
				continue;
			}
			String record = createOutRecord(createCdrData(rule, re ,fn,i+2));
			fileBody.add(record);
		}
		Integer errNum = getErrNum(fileBody,rule);
		HandleReturnPara hrp = new HandleReturnPara(fileBody, errNum);
		return hrp;
	}
	
	public String setSqlFieldStr(FieldObject fo,String... fieldName) {
		for(String fn : fieldName) {
			if(Tools.IsBlank(fo.getFieldValue())) {
				fo.setFieldValue("");
			}
			if(fn.equals(fo.getFieldName().toUpperCase())) {
				return fo.getFieldValue();
			} 
		}
		return "'"+fo.getFieldValue()+"'";
	}

	/**
	 * @param fileName
	 * @return 是不是需要对记录进行操作的业务类型
	 */
	public boolean isSpecialFile(String valName) {
		if(valName.startsWith(PubData.NOTIFY_INFO)) {
			return true;
		}else if(valName.startsWith(PubData.NOTIFY_RESULT)){
			return true;
		}
		return false;
	}
	
	public ListenableFuture<String> handleRecord(String record, List<BbdcTypeCdr> rule, String fileName,long lineNum) {
		ListenableFuture<String> future;
		List<FieldObject> list = createCdrData(rule,record,fileName,lineNum);
		String rec = createOutRecord(list);
		future = new AsyncResult<String>(rec);
		return future;
	}
	
}
