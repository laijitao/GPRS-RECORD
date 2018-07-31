package com.hp.cmcc.bboss.bdc.handle;

public interface BaseValid {
	
	//主键唯一性校验，检查接口数据中是否违反规范定义的唯一性约束
	boolean primaryKeyIsUnique(String key);
	//字段类型检查，检查各字段类型是否与规范定义一致
	boolean fieldCheck();
	//对数据的取值进行有效性检查
	boolean dataCheck();
}
