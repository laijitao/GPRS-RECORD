package com.hp.cmcc.bboss.bdc.valid.impl;

import com.hp.cmcc.bboss.bdc.exception.ValidException;
import com.hp.cmcc.bboss.bdc.utils.Tools;
import com.hp.cmcc.bboss.bdc.valid.RecRecordValid;

public class RecRecordValidImpl implements RecRecordValid{
	
	public void validFieldNum(int fieldNum,int validNum) throws ValidException {
		if(Tools.IsEmpty(fieldNum) || fieldNum != validNum) {
			throw new ValidException("F998", "the fieldNum is wrong");
		}
	}
}
