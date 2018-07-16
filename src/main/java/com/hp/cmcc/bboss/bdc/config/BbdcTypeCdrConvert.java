package com.hp.cmcc.bboss.bdc.config;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hp.cmcc.bboss.bdc.utils.Tools;

@Component
public class BbdcTypeCdrConvert implements Converter<String,Date>{
	
	private static final String dateFormat = "yyyy/MM/dd";

	@Override
	public Date convert(String source) {
		if (Tools.IsBlank(source)) {
            return null;
        }
		try {
            SimpleDateFormat formatter;
            formatter = new SimpleDateFormat(dateFormat);
            Date dtDate = (Date) formatter.parse(source);
            return dtDate;
            
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }
	}
	
}
