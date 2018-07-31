package com.hp.cmcc.bboss.bdc.handle.impl;

import com.hp.cmcc.bboss.bdc.handle.BaseValid;

public class RecordValid implements BaseValid {

	@Override
	public boolean primaryKeyIsUnique(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fieldCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dataCheck() {
		// TODO Auto-generated method stub
		return false;
	}

}
