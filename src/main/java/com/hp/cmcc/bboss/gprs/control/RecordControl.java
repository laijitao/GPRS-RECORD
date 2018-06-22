package com.hp.cmcc.bboss.gprs.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	@RequestMapping(value = "/record/addField", method = RequestMethod.POST)
	public HandleReturnPara fn(@RequestBody GprsRecFilePara grfp) {
		HandleReturnPara hrp = rs.HandleRecord(grfp);
		return hrp;
	}
	
	@RequestMapping(value = "/record/paramtest", method = RequestMethod.POST)
	public String paramTest(@RequestBody GprsRecFilePara grfp) {
		List<String> fileBody = grfp.getFileBody();
		List<BbdcTypeCdr> rule = grfp.getRule();
		String fileName = grfp.getFileName();
		if(fileBody != null && rule != null && fileName != null) {
			return "parameter receive success! record :"+fileBody.size()+"rule :"+rule.size()+"fileName:"+fileName;
		}
		return "parameter receive failed!";
	}
	
	@RequestMapping(value = "/record/test")
	@ResponseBody
	public String test() {
		return "connect success!";
	}

}
