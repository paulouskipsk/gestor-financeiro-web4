package br.edu.utfpr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {
	
	public static Date formatStringToDate(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
		Date date;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}
	
	public static Date formatDateToDate(Date ConvDate) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
		Date date = formatStringToDate(format.format(ConvDate));
		return date;
	}
}
