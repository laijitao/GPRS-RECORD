package com.hp.cmcc.bboss.gprs.pojo;

import java.io.Serializable;

public class InFileRecord  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String err_code;		//1     错误码
	private String proc_id;         //2     业务流水号
	private String oper_type;       //3     操作类型
	private String service_id;      //4     业务计费代码
	private String app_name;        //5     客户产品简称
	private String service_type;    //6     业务模式
	private String nf_limit;        //7     单用户封顶流量
	private String valid_province;  //8     局数据作用省
	private String valid_date;		//9      生效时间
	private String expire_date;		//10   失效时间
	private String time_stamp;		//11   时间戳
	private String service_range;   //12   业务地域范围
	private String unified_period;  //13   统付时段
	private int fieldNum ;
	
	
	
	public InFileRecord() {
		super();
	}

	public InFileRecord(String[] s) {
		super();
		this.err_code = s[0];
		this.proc_id = s[1];
		this.oper_type = s[2];
		this.service_id = s[3];
		this.app_name = s[4];
		this.service_type = s[5];
		this.nf_limit = s[6];
		this.valid_province = s[7];
		this.valid_date = s[8];
		this.expire_date = s[9];
		this.time_stamp = s[10];
		this.service_range = s[11];
		this.unified_period = s[12];
	}
	
	public InFileRecord(String err_code,String proc_id, String oper_type, String service_id, String app_name, 
			String service_type, String nf_limit,String valid_province, String valid_date, String expire_date, String time_stamp,
			String service_range,String unified_period,String file_name) {
		super();
		this.err_code = err_code;
		this.oper_type = oper_type;
		this.service_id = service_id;
		this.app_name = app_name;
		this.service_type = service_type;
		this.nf_limit = nf_limit;
		this.valid_province = valid_province;
		this.valid_date = valid_date;
		this.expire_date = expire_date;
		this.time_stamp = time_stamp;
		this.service_range = service_range;
		this.unified_period = unified_period;
	}
	
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getProc_id() {
		return proc_id;
	}
	public void setProc_id(String proc_id) {
		this.proc_id = proc_id;
	}
	public String getOper_type() {
		return oper_type;
	}
	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getNf_limit() {
		return nf_limit;
	}
	public void setNf_limit(String nf_limit) {
		this.nf_limit = nf_limit;
	}
	public String getValid_province() {
		return valid_province;
	}
	public void setValid_province(String valid_province) {
		this.valid_province = valid_province;
	}
	public String getValid_date() {
		return valid_date;
	}
	public void setValid_date(String valid_date) {
		this.valid_date = valid_date;
	}
	public String getExpire_date() {
		return expire_date;
	}
	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}
	public String getService_range() {
		return service_range;
	}
	public void setService_range(String service_range) {
		this.service_range = service_range;
	}
	public String getUnified_period() {
		return unified_period;
	}
	public void setUnified_period(String unified_period) {
		this.unified_period = unified_period;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getFieldNum() {
		return fieldNum;
	}

	public void setFieldNum(int fieldNum) {
		this.fieldNum = fieldNum;
	}
	
}
