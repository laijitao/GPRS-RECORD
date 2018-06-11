package com.hp.cmcc.bboss.gprs.control;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hp.cmcc.bboss.gprs.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.gprs.pojo.GprsRecFilePara;
import com.hp.cmcc.bboss.gprs.pojo.HandleReturnPara;
import com.hp.cmcc.bboss.gprs.pojo.OutFileRecord;
import com.hp.cmcc.bboss.gprs.service.RecordService;

@RestController
public class RestTemplateTestControl {
	@Autowired
	RestTemplate rt;
	@Autowired
	RecordService rs;
	
	@RequestMapping(value = "/record/addField", method = RequestMethod.POST)
	public HandleReturnPara fn(@RequestBody GprsRecFilePara grfp) {
		List<String> fb = grfp.getFileBody();
		List<BbdcTypeCdr> rule = grfp.getRule();
		String fn = grfp.getFileName();
		Integer fnId = grfp.getFileNameId();
		
		List<OutFileRecord> list = new LinkedList<OutFileRecord>();
		list.clear();
		for(String record : fb) {
			OutFileRecord of = rs.recordToFormat(record, rule,fn,fnId);
			String recordHash = "test_hash_record";
//					rt.getForObject("http://xxxx",String.class);//调服务
			of.setRecord_hash(recordHash);
			list.add(of);
		}
		List<String> crl = rs.createRecordList(list,rule);
		Integer errNum = 25;
		HandleReturnPara hrp = new HandleReturnPara(crl, errNum);
		return hrp;
	}
}
