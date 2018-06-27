package com.hp.cmcc.bboss.gprs.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hp.cmcc.bboss.gprs.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.gprs.pojo.GprsRecFilePara;
import com.hp.cmcc.bboss.gprs.pojo.HandleReturnPara;
import com.hp.cmcc.bboss.gprs.service.RecordService;

@RestController
public class RecordControl {
	
	Logger L = LoggerFactory.getLogger(RecordControl.class);
	@Autowired
	RecordService rs;
	
	@RequestMapping(value = "/record/addField", method = RequestMethod.POST,consumes = "application/json")
	public HandleReturnPara fn(@RequestParam("fileBody") List<String> fileBody, @RequestBody List<BbdcTypeCdr> rule, 
			@RequestParam("fileName") String fileName) {
		GprsRecFilePara grfp = new GprsRecFilePara(fileBody, rule, fileName);
		HandleReturnPara hrp = rs.HandleRecord(grfp);
		return hrp;
	}

}
