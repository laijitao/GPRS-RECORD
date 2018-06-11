package com.hp.cmcc.bboss.gprs.control;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hp.cmcc.bboss.gprs.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.gprs.pojo.OutFileRecord;
import com.hp.cmcc.bboss.gprs.service.RecordService;

@RestController
public class RecordControl {
	
	@Autowired
	RestTemplate rt;
	@Autowired
	RecordService rs;
	
	@RequestMapping(value = "/recreord")
	@ResponseBody
	public List<String> handleRecord(@RequestParam("fileBody") List<String> fb,
			@RequestParam("rule") List<BbdcTypeCdr> rule,@RequestParam("fileName") String fn,
			@RequestParam("fileNameId") Integer fnId) {
		
		List<OutFileRecord> list = new LinkedList<OutFileRecord>();
		list.clear();
		for(String record : fb) {
			OutFileRecord of = rs.recordToFormat(record, rule,fn,fnId);
			String recordHash = rt.getForObject("http://xxxx",String.class);//调服务
			of.setRecord_hash(recordHash);
			list.add(of);
		}
		List<String> crl = rs.createRecordList(list,rule);
		return crl;
	}
	
	@RequestMapping(value = "test")
	@ResponseBody
	public String test() {
		return (String) rt.getForObject("http://bdc-gprs-file-service/gprs",List.class).get(5);
	}

}
