package com.hp.cmcc.bboss.gprs.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hp.cmcc.bboss.gprs.service.RecordService;

@RestController
public class RecordControl {
	
	@Autowired
	RestTemplate rt;
	@Autowired
	RecordService rs;
	
	
	@RequestMapping(value = "test")
	@ResponseBody
	public String test() {
		return (String) rt.getForObject("http://bdc-gprs-file-service/gprs",List.class).get(5);
	}

}
