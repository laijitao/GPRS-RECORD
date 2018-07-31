package com.hp.cmcc.bboss.bdc.threadHandle;

import java.util.List;
import java.util.concurrent.Callable;

import com.hp.cmcc.bboss.bdc.handle.RecordHandle;
import com.hp.cmcc.bboss.bdc.pojo.BbdcTypeCdr;
import com.hp.cmcc.bboss.bdc.pojo.FieldObject;

import lombok.Getter;
import lombok.Setter;

public class RecordHandleThread implements Callable<String>{
	
	@Setter
	@Getter
	private String record;
	@Setter
	@Getter
	private List<BbdcTypeCdr> rule;
	@Setter
	@Getter
	private String fn;
	@Setter
	@Getter
	private Long lineNum;
	@Setter
	@Getter
	private RecordHandle recordHandle;
	
	public RecordHandleThread(RecordHandle recordHandle,String record, List<BbdcTypeCdr> rule, String fn, Long lineNum) {
		super();
		this.recordHandle = recordHandle;
		this.record = record;
		this.rule = rule;
		this.fn = fn;
		this.lineNum = lineNum;
	}

	@Override
	public String call() throws Exception {
		List<FieldObject> list = recordHandle.createCdrData(rule, record, fn, lineNum);
		return recordHandle.createOutRecord(list);
	}

	
}
