package com.hp.cmcc.bboss.bdc.threadHandle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.cmcc.bboss.bdc.config.BdcBeanFactory;
import com.hp.cmcc.bboss.bdc.handle.RecordHandle;
import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.bdc.pojo.HandleReturnPara;

public class RecordHandleMainThread {

	private Logger L = LoggerFactory.getLogger(RecordHandleMainThread.class);
	
	public HandleReturnPara recordHandle(List<String> fb, List<BbdcTypeCdr> rule, String fn) {
		HandleReturnPara hrp = new HandleReturnPara();
		List<String> records = new LinkedList<>();
		int corePoolSize = Runtime.getRuntime().availableProcessors();
	    ExecutorService executor  = Executors.newFixedThreadPool(corePoolSize);
	    List<Future<String>> resultList = new LinkedList<Future<String>>();
	    int size = fb.size();
	    for(int start = 0;start < size;start += corePoolSize) {
	    	int i = 0;
	    	while(start+i < size && i < corePoolSize){
	    		RecordHandleThread st = new RecordHandleThread(BdcBeanFactory.getBean("RecordHandle", RecordHandle.class),
	    				                           fb.get(start+i), rule, fn, start+i+2L);
	    		resultList.add(executor.submit(st));
	    		i++;
	    	}
	    }
	    
	    RecordHandle rs = BdcBeanFactory.getBean("RecordHandle", RecordHandle.class);
	    Map<String, BbdcTypeCdr> map = rs.getRuleMap(rule);
    	boolean flag = rs.isSpecialFile(fn);//判断业务类型是否为NOTIFY_INFO或NOTIFY_RESULT
    	int index = -1;
    	int errNum = 0;
		if(!flag) {
			BbdcTypeCdr cdr = map.get("ERR_CODE");
			index  = cdr.getHinderIdx().intValue();
    	}
		Iterator<Future<String>> iterator = resultList.iterator();
		try {
			while(iterator.hasNext()) {
				String record = iterator.next().get();
				records.add(record);
				if(!flag&&rs.isErrorRecord(record, index)) errNum++;
			}
		} catch (InterruptedException | ExecutionException e) {
			L.error("get handled record fail!");
		}
		
	    hrp.setRecords(records);
	    hrp.setErrNum(errNum);
	    return hrp;
	}
}
