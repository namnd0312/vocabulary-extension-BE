package com.namnd.englishvocabextension.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.namnd.englishvocabextension.enums.EProcess;
import org.apache.commons.lang3.StringUtils;

public class Util {

	public final static String SLASH_DATE_FORMAT = "dd/MM/yyyy";

	public final static DateTimeFormatter SLASH_DATE_FORMATTER = DateTimeFormatter.ofPattern(SLASH_DATE_FORMAT)
			.withZone(ZoneId.from(ZoneOffset.UTC));

	public static boolean validateStandardDateFormat(String sDate) {
		try {
			LocalDate.from(SLASH_DATE_FORMATTER.parse(sDate));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Long stringToLong(String v) {
		try {
			return Long.parseLong(v);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static String longToString(Long l) {
		try {
			return String.valueOf(l);
		} catch (NumberFormatException e) {
			return null;
		}

	}


	public static String instantToString(Instant instant) {
		if (instant == null) {
			return null;
		}
		return SLASH_DATE_FORMATTER.format(instant);
	}
	
	
    public static Instant stringToInstant(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }
        return Instant.from(SLASH_DATE_FORMATTER.parse(strDate));
    }

	/**
	 * Hàm này sẽ trả ra giai đoạn tiếp theo của việc học từ vựng
	 * @param process
	 * @return
	 */
	public static String getScheduleForFuture(EProcess process){
		switch (process){
			case SECOND_DAY:
				return  EProcess.A_WEEK.name();

			case A_WEEK:
				return EProcess.TWO_WEEK.name();

			case TWO_WEEK:
				return EProcess.A_MONTH.name();

			case A_MONTH:
				return EProcess.TWO_MONTH.name();

			case TWO_MONTH:
				return EProcess.FINISH.name();

			default: return "";

		}
	}

}
