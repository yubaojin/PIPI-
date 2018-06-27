package com.pipi.utils.sql;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.pipi.utils.PIPIUtils;

public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public Query() {
	}

	public Query(Map<String, Object> params) throws ParseException {

		this.putAll(params);

		String s = (String) params.get("startDate");
		String e = (String) params.get("endDate");

		Date startDate = null;
		Date endDate = null;
		if (StringUtils.isNotBlank(s)) {
			startDate = PIPIUtils.getDate(s);
		} else {
			startDate = PIPIUtils.getCurrentDay();
		}
		if (StringUtils.isNotBlank(e)) {
			endDate = PIPIUtils.getDate(e);
			endDate = PIPIUtils.getNextDay(endDate);
		} else {
			endDate = PIPIUtils.getCurrentDay();
			endDate = PIPIUtils.getNextDay(endDate);
		}
		this.put("startDate", startDate);
		this.put("endDate", endDate);
	}

}