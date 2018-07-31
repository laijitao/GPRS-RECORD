package com.hp.cmcc.bboss.bdc.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.bdc.pojo.HandleReturnPara;
import com.hp.cmcc.bboss.bdc.pojo.RequestParamter;
import com.hp.cmcc.bboss.bdc.threadHandle.RecordHandleMainThread;

@RestController
public class RecordControl {
	
	Logger L = LoggerFactory.getLogger(RecordControl.class);
	
	@RequestMapping(value = "/record/handle", method = RequestMethod.POST,consumes = "application/json")
	public HandleReturnPara threadHandle(@RequestBody RequestParamter requestParamter) {
		long time = System.currentTimeMillis();
		List<String> fileBody = requestParamter.getFileBody();
		List<BbdcTypeCdr> rule = requestParamter.getRule();
		String fileName = requestParamter.getFileName();
		RecordHandleMainThread mt = new RecordHandleMainThread();
		HandleReturnPara hrp = mt.recordHandle(fileBody, rule, fileName);
		L.warn("multiple threads handle time :"+(System.currentTimeMillis()-time)+"ms");
		return hrp;
	}

}
