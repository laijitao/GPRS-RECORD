package com.hp.cmcc.bboss.gprs.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.hp.cmcc.bboss.gprs.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.gprs.pojo.InFileRecord;
import com.hp.cmcc.bboss.gprs.pojo.OutFileRecord;
import com.hp.cmcc.bboss.gprs.utils.PubData;
import com.hp.cmcc.bboss.gprs.utils.Tools;

@Service
public class RecordService {
	
	Tools tools = new Tools();
	
	public OutFileRecord recordToFormat(String record,List<BbdcTypeCdr> rule,String fn,Integer fnId) {
		OutFileRecord of = new OutFileRecord();
		String[] s = record.split(",");
		InFileRecord inf = new InFileRecord(s); //将记录转化为对象
		
		of.setBdc_code("011701");//流量统付局数据编码
		of.setOper_serial_nbr("null");//操作流水
		of.setRecord_hash("null");//记录哈西
		of.setCreate_date(Tools.getNowTime(PubData.TMFMT14));//创建时间
		of.setFile_name(fn);//文件名
		of.setFile_name_id(fnId);
		
		of.setErr_code(inf.getErr_code());
		of.setApp_name(inf.getApp_name());
		of.setExpire_date(inf.getExpire_date());
		of.setNf_limit(inf.getNf_limit());
		of.setOper_type(inf.getOper_type());
		of.setProc_id(inf.getProc_id());
		of.setService_id(inf.getService_id());
		of.setService_range(inf.getService_range());
		of.setService_type(inf.getService_type());
		of.setTime_stamp(inf.getTime_stamp());
		of.setUnified_period(inf.getUnified_period());
		of.setValid_date(inf.getValid_date());
		of.setValid_province(inf.getValid_province());
		return of;
	}

	public List<String> createRecordList(List<OutFileRecord> list, List<BbdcTypeCdr> rule) {
		BbdcTypeCdr[] arr = sortByBeginIndex(rule);
		List<String> crl = new LinkedList<String>();
		crl.clear();
		for(OutFileRecord of : list) {
			crl.add(createRecord(of,arr).toString());
		}
		return crl;
	}
	
	public StringBuffer createRecord(OutFileRecord of, BbdcTypeCdr[] arr) {
		
		String record = of.toString(); //重写toString方法，使之用","分隔
		String[] S = record.split(",");
		StringBuffer field = new StringBuffer("");
		for(int i = 0;i < S.length;i++) {
			field.append(S[0]+arr[i].getDataSeparator());
		}
		return field;
	}
	
	public BbdcTypeCdr[] sortByBeginIndex(List<BbdcTypeCdr> rule){
		List<BbdcTypeCdr> list = new LinkedList<>();
		list.clear();
		BbdcTypeCdr[] a = new BbdcTypeCdr[list.size()];
		BbdcTypeCdr[] arr = rule.toArray(a);
		
		for(int i = 0;i < arr.length;i++) {
			for(int j = i;j < arr.length;j++) {
				if(arr[i].getFieldBegIdx() > arr[j].getFieldBegIdx()) {
					BbdcTypeCdr cdr = arr[i];
					arr[i] = arr[j];
					arr[j] = cdr;
				}
			}
		}
		
		return arr;
	}
}
