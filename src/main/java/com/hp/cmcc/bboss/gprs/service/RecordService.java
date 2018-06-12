package com.hp.cmcc.bboss.gprs.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.hp.cmcc.bboss.gprs.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.gprs.pojo.FieldData;


@Service
public class RecordService {
	
	public String[] strToArr(String record){
		return record.split(",");
	}
	
	public String createAfterData(List<BbdcTypeCdr> cdr,String record,String fn,Integer fnId){
		String[] S = strToArr(record);
		cdr.sort((x,y) -> Integer.compare(x.getFieldBegIdx(), y.getFieldBegIdx()));
		List<FieldData> list = new LinkedList<>();
		int index = 0;
		for(BbdcTypeCdr p : cdr) {
			FieldData fieldData = new FieldData();
			fieldData.setFi(index);
			fieldData.setFn(p.getFieldName());
			fieldData.setSeptor(p.getDataSeparator());
			if("file_name".equals(fieldData.getFn())) {
				fieldData.setfv(fn);
			}
			if("file_name_id".equals(fieldData.getFn())) {
				fieldData.setfv(fnId+"");
			}
			if(index >= S.length) {
				fieldData.setfv("filtter");
			}else {
				fieldData.setfv(S[index]);
			}
			list.add(fieldData);
			index++;
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
