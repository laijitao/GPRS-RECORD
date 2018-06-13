package com.hp.cmcc.bboss.gprs.control;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hp.cmcc.bboss.gprs.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.gprs.pojo.GprsRecFilePara;
import com.hp.cmcc.bboss.gprs.pojo.HandleReturnPara;
import com.hp.cmcc.bboss.gprs.service.RecordService;

@RestController
public class RestTemplateTestControl {
	Logger l = LoggerFactory.getLogger(RestTemplateTestControl.class);
	@Autowired
	RestTemplate rt;
	@Autowired
	RecordService rs;
	
	@RequestMapping(value = "/record/addField", method = RequestMethod.POST)
	public HandleReturnPara fn(@RequestBody GprsRecFilePara grfp) {
		if(grfp == null) {
			l.error("request data is null, pls check!");
			return new HandleReturnPara();
		}
		List<String> fb = grfp.getFileBody();
		List<BbdcTypeCdr> rule = grfp.getRule();
		String fn = grfp.getFileName();
		Integer fId = grfp.getfileId();
		
		List<String> fileBody = new LinkedList<>();;
		for(String re : fb) {
			String hashCode = "hash_record";
//					rt.getForObject("http://xxxx",String.class);//调服务
			String record = rs.createAfterData(rule, re,hashCode,fn,fId);
			fileBody.add(record);
		}
		Integer errNum = 25;
		HandleReturnPara hrp = new HandleReturnPara(fileBody, errNum);
		return hrp;
	}
}
