package com.hp.cmcc.bboss.gprs.pojo;

import java.io.Serializable;

public class OutFileRecord  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String bdc_code;        //1     局数据类型编码（定向流量统付为011701）
	private String oper_serial_nbr; //2     操作流水
	private String record_hash;     //3     记录haSh
	private String err_code;		//4     错误码
	private String proc_id;         //5     业务流水号
	private String oper_type;       //6     操作类型
	private String service_id;      //7     业务计费代码
	private String app_name;        //8     客户产品简称
	private String service_type;    //9     业务模式
	private String nf_limit;        //10   单用户封顶流量
	private String valid_province;  //11   局数据作用省
	private String valid_date;		//12   生效时间
	private String expire_date;		//13   失效时间
	private String time_stamp;		//14   时间戳
	private String service_range;   //15   业务地域范围
	private String unified_period;  //16   统付时段
	private String create_date;     //17   创建时间
	private String file_name;       //18   回执文件名
	private Integer file_name_id;    //19   回执文件名
	
	public OutFileRecord() {
		super();
	}

	public OutFileRecord(String err_code, String bdc_code, String oper_serial_nbr, String record_hash, String proc_id,
			String oper_type, String service_id, String app_name, String service_type, String nf_limit,
			String valid_province, String valid_date, String expire_date, String time_stamp, String service_range,
			String unified_period, String create_date, String file_name,Integer file_name_id) {
		super();
		this.err_code = err_code;
		this.bdc_code = bdc_code;
		this.oper_serial_nbr = oper_serial_nbr;
		this.record_hash = record_hash;
		this.proc_id = proc_id;
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
		this.create_date = create_date;
		this.file_name = file_name;
		this.file_name_id = file_name_id;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getBdc_code() {
		return bdc_code;
	}

	public void setBdc_code(String bdc_code) {
		this.bdc_code = bdc_code;
	}

	public String getOper_serial_nbr() {
		return oper_serial_nbr;
	}

	public void setOper_serial_nbr(String oper_serial_nbr) {
		this.oper_serial_nbr = oper_serial_nbr;
	}

	public String getRecord_hash() {
		return record_hash;
	}

	public void setRecord_hash(String record_hash) {
		this.record_hash = record_hash;
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

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Integer getFile_name_id() {
		return file_name_id;
	}

	public void setFile_name_id(Integer file_name_id) {
		this.file_name_id = file_name_id;
	}

	@Override
	public String toString() {
		return bdc_code + "," + oper_serial_nbr + "," + record_hash + "," + err_code + "," + proc_id + ","
				+ oper_type + "," + service_id + "," + app_name + "," + service_type + "," + nf_limit + ","
				+ valid_province + "," + valid_date + "," + expire_date + "," + time_stamp + "," + service_range
				+ "," + unified_period + "," + create_date + "," + file_name + "," + file_name_id;
	}
	
	
}
