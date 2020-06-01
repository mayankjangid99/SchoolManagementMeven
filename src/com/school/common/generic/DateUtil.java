package com.school.common.generic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	//private static String _dateTimeFormat = "MM/dd/yyyy HH:mm";
	private static String _datePickerFormat = "dd-MM-yyyy";
	private static String _dateTimeAMPMPickerFormat = "dd-MM-yyyy hh:mm:ss a";
	private static String _dateTime24AMPMPickerFormat = "dd-MM-yyyy HH:mm:ss a";
	
	
	public static Date parse( String dateString) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat( _datePickerFormat );
			return sdf.parse( dateString );
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		return null;
	}
	
	

	public static String format( Date date) {
		try {
			if(date == null) return null;
			SimpleDateFormat sdf = new SimpleDateFormat( _datePickerFormat );
			return sdf.format(date);
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Date parse( String dateString, String formatString ) {
		try {
			if(dateString == null || dateString.equals("")) return null;
			if( formatString == null || formatString.isEmpty()) {
				formatString = _datePickerFormat;
			}
			SimpleDateFormat sdf = new SimpleDateFormat( formatString );
			return sdf.parse( dateString );
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String format( Date date, String formatString ) {
		try {
			if(date == null) return null;
			if( formatString == null || formatString.isEmpty()) {
				formatString = _datePickerFormat;
			}
			SimpleDateFormat sdf = new SimpleDateFormat( formatString );
			return sdf.format(date);
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
	public static String formatFromToAnother( String dateString, String formatFrom, String formatTo) {
		try {
			if(dateString == null || dateString.isEmpty())
				return null;
			
			if(formatFrom == null || formatFrom.isEmpty())
				formatFrom = "dd-MM-yyyy";
			if(formatTo == null || formatTo.isEmpty())
				formatTo = "yyyy-MM-dd";
			SimpleDateFormat fromFormat = new SimpleDateFormat(formatFrom);
			fromFormat.setLenient(false);
			SimpleDateFormat toFormat = new SimpleDateFormat(formatTo);
			toFormat.setLenient(false);
			Date date = fromFormat.parse(dateString);
			return toFormat.format(date);
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Date parseFromUIDate( String dateString) {
		try {
			if(dateString == null || dateString.isEmpty()) return null;
			SimpleDateFormat sdf = new SimpleDateFormat( _datePickerFormat );
			return sdf.parse( dateString );
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Date parseFromUIDateTimeAMPM( String dateString) {
		try {
			if(dateString == null || dateString.equals("")) return null;
			SimpleDateFormat sdf = new SimpleDateFormat( _dateTimeAMPMPickerFormat );
			return sdf.parse( dateString );
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Date parseFromUIDateTime24AMPM( String dateString) {
		try {
			if(dateString == null || dateString.equals("")) return null;
			SimpleDateFormat sdf = new SimpleDateFormat( _dateTime24AMPMPickerFormat );
			return sdf.parse( dateString );
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String formatForUIDate( Date date) {
		try {
			if(date == null) return null;
			SimpleDateFormat sdf = new SimpleDateFormat( _datePickerFormat );
			return sdf.format(date);
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String formatForUIDateTimeAMPM( Date date) {
		try {
			if(date == null) return null;
			SimpleDateFormat sdf = new SimpleDateFormat( _dateTimeAMPMPickerFormat );
			return sdf.format(date);
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String formatForUIDateTime24AMPM( Date date) {
		try {
			if(date == null) return null;
			SimpleDateFormat sdf = new SimpleDateFormat( _dateTime24AMPMPickerFormat );
			return sdf.format(date);
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
		return null;
	}
}
