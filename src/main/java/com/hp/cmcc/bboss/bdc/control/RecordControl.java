package com.hp.cmcc.bboss.bdc.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.bdc.pojo.HandleReturnPara;
import com.hp.cmcc.bboss.bdc.service.RecordService;

@RestController
public class RecordControl {
	
	Logger L = LoggerFactory.getLogger(RecordControl.class);
	@Autowired
	RecordService rs;
	
	@RequestMapping(value = "/record/addField", method = RequestMethod.POST,consumes = "application/json")
	public HandleReturnPara fn(@RequestParam("fileBody") List<String> fileBody,  @RequestBody List<BbdcTypeCdr> rule, 
			@RequestParam("fileName") String fileName) {
		long time = System.currentTimeMillis();
//		rs.createLogForTest(L,fileBody,rule,fileName);
		HandleReturnPara hrp = rs.HandleRecord(fileBody, rule, fileName);
		L.warn("handle time :"+(System.currentTimeMillis()-time)+"ms");
		return hrp;
	}

//	@RequestMapping(value = "/test", method = RequestMethod.POST,consumes = "application/json")
//	public List<String> test(@RequestBody List<User> users,@RequestParam("name") String name
//			,@RequestParam("ct") Date ct) {
//		List<String> list = new LinkedList<>();
//		for(User u : users) {
//			u.setName(name);
//			u.setGender("男");
//			u.setAge(18);
//			u.setcT(ct);
//			list.add(u.toString());
//		}
//		return list;
//	}
//	
//	@RequestMapping(value = "/testSql", method = RequestMethod.POST,consumes = "application/json")
//	public String testSql(@RequestParam("sql") String sql) {
//		return rs.getDate(sql);
//	}
}
