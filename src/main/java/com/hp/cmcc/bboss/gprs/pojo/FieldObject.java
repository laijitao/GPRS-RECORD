package com.hp.cmcc.bboss.gprs.pojo;

public class FieldObject {
	
	private String fn;
	private String fv;
	private Integer fi;
	private String septor;
	
	public FieldObject() {
		super();
	}

	public FieldObject(String fn,String fv, Integer fi,String septor) {
		super();
		this.fn = fn;
		this.fv = fv;
		this.fi = fi;
		this.septor = septor;
	}

	public String getfv() {
		return fv;
	}

	public void setfv(String fv) {
		this.fv = fv;
	}

	public Integer getFi() {
		return fi;
	}

	public void setFi(Integer fi) {
		this.fi = fi;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getSeptor() {
		return septor;
	}

	public void setSeptor(String septor) {
		this.septor = septor;
	}

	
	public String getFv() {
		return fv;
	}

	public void setFv(String fv) {
		this.fv = fv;
	}

	@Override
	public String toString() {
		return "fn="+fn + ", " + "fv="+fv + ", " + "fi="+fi;
	}
	
	
}
