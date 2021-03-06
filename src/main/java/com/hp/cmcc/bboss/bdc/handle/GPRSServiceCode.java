package com.hp.cmcc.bboss.bdc.handle;

public interface GPRSServiceCode {
	//该记录中包含的字段数与接口不符合，比实际接口字段多或少
	boolean fieldNum(int fieldNum,int checkNum);
	//当新增局数据时，有效日期交叉：该业务代码ID已经存在，而且已存在的业务代码ID的失效日期大于当前记录的生效日期
	boolean dateCheck();
	//当修改、删除未来生效或删除局数据时，该业务代码ID不存在
	boolean isNotExist(String serviceCode);
	//该关键字的当前记录已失效不能做修改、删除未来生效或删除局数据，即:当修改、删除未来生效或删除局数据时，
	//该业务代码ID存在，但已存在的业务代码ID的失效日期小于或等于当前记录的生效日期
	
	//当修改或删除局数据时，该业务代码ID存在，但已存在的业务代码ID的生效日期大于当前记录的生效日期
	
	//当删除未来生效局数据时，该记录不存在未来生效数据
	
	//系统中存在与该记录相同（关键字+操作类型）的数据处于待审批
	
	//同一份接口文件中，包含多条关键字相同的记录
	
	//操作类型合法的取值为：ADD（新增）MOD（修改）DEL（删除）DCF (删除未来生效)
	
	//字段长度超过规范定义的最大长度
	//当删除时，在删除操作的生效日期之后，该业务代码ID在定向统付GPRS业务内容计费代码局数据记录中仍存在生效的业务计费代码
	
	//生效日期格式错误，不是合法日期
	
	//生效日期必须大于等于系统当前日期+N天。N以移动集团相关规定为准，默认N为2天
	
	//失效日期格式错误，不是合法日期
	
	//失效日期应大于生效日期
	
	//字段长度超过规范定义的最大长度
	
	//合法的取值为：0：免费、1：收费
	
	//信息费不能包含非数字字符
	
	//当IS_CHARGE取值为0（免费）时，信息费只能为0

	//

}
