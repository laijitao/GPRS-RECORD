package com.hp.cmcc.bboss.gprs.valid;

import com.hp.cmcc.bboss.gprs.exception.ValidException;

public interface RecRecordValid {
	
	public void validFieldNum(int fieldNum,int validNum) throws ValidException ;
}
