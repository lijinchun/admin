package com.bluesky.admin.common.utils.typeeditors;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期编辑器
 * 
 * 根据日期字符串长度判断是长日期还是短日期。只支持yyyy-MM-dd，yyyy-MM-dd HH:mm:ss两种格式。
 * 扩展支持yyyy,yyyy-MM日期格式
 * @author 13219
 */
public class DateTypeEditor extends PropertyEditorSupport {
	private DateFormat dfLong = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private DateFormat dfShort = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat dfYear = new SimpleDateFormat("yyyy");
	private DateFormat dfMonth = new SimpleDateFormat("yyyy-MM");
	/**
	 * 短类型日期长度
	 */
	private static final int SHORT_DATE = 10;

	private static final int YEAR_DATE = 4;

	private static final int MONTH_DATE = 7;

	@Override
	public void setAsText(String text){
		if(text == null){
			setValue(text);
			return;
		}
		text = text.trim();
		if (!StringUtils.hasText(text)) {
			setValue(null);
			return;
		}
		try {
			if (text.length() <= YEAR_DATE) {
				setValue(new Date(dfYear.parse(text).getTime()));
			}else  if (text.length() <= MONTH_DATE) {
				setValue(new Date(dfMonth.parse(text).getTime()));
			}else if (text.length() <= SHORT_DATE) {
				setValue(new Date(dfShort.parse(text).getTime()));
			} else {
				setValue(new Timestamp(dfLong.parse(text).getTime()));
			}
		} catch (ParseException ex) {
			IllegalArgumentException iae = new IllegalArgumentException(
					"Could not parse date: " + ex.getMessage());
			iae.initCause(ex);
			throw iae;
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? dfLong.format(value) : "");
	}
}
