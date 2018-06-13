package com.hp.cmcc.bboss.gprs.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.hp.cmcc.bboss.gprs.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.gprs.pojo.FieldData;
import com.hp.cmcc.bboss.gprs.utils.PubData;
import com.hp.cmcc.bboss.gprs.utils.Tools;


@Service
public class RecordService {
	
	public String[] strToArr(String record){
		return record.split(",");
	}
	
	public String createAfterData(List<BbdcTypeCdr> cdr,String record,String hashCode,String fn,Integer fId){
		
		String[] S = strToArr(record);
//		String[] S = validation(record,cdr);//后期校验
		cdr.sort((x,y) -> Integer.compare(x.getHinderIdx().intValue(), y.getHinderIdx().intValue()));
		List<FieldData> list = new LinkedList<>();
		for(BbdcTypeCdr p : cdr) {
			FieldData fieldData = new FieldData();
			fieldData.setFi(p.getHinderIdx().intValue());
			fieldData.setFn(p.getFieldName());
			fieldData.setSeptor(p.getDataSeparator());
			if(p.getFormerIdx() == -1L) {
				if("create_date".equals(p.getFieldName())){
					fieldData.setfv(Tools.getNowTime(PubData.TMFMT14));
				}
				if("file_id".equals(p.getFieldName())){
					fieldData.setfv(fId+"");
				}
				if("file_name".equals(p.getFieldName())){
					fieldData.setfv(fn);
				}
				if("bdc_code".equals(p.getFieldName())){
					fieldData.setfv(p.getDataFiller());
				}
				if("oper_serial_nbr".equals(p.getFieldName())){
					fieldData.setfv("oper_serial_nbr");
				}
				if("record_hash".equals(p.getFieldName())){
					fieldData.setfv(hashCode);
				}
			}else {
				fieldData.setfv(S[p.getFormerIdx().intValue()]);
			}
			list.add(fieldData);
		}
		return createOutRecord(list);
	}
	
	public String createOutRecord(List<FieldData> D){
		StringBuffer sb = new StringBuffer();
		D.sort((x,y) -> Integer.compare(x.getFi(), y.getFi()));
		for(FieldData d : D) {
			sb.append(d.getfv()+d.getSeptor());
		}
		return sb.toString();
	}
}
