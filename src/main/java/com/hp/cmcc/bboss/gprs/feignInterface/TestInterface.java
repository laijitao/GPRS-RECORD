package com.hp.cmcc.bboss.gprs.feignInterface;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "test-service")
public interface TestInterface {

	@RequestMapping(value = "/feign",method = RequestMethod.GET)
	@ResponseBody
    String sayHiFromClientOne(@RequestParam(value = "map") Map<Integer,String> map);
}
