package monkey.woodstock.Util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UtilTime {
	public static Timestamp crearFecha(int dia,int mes,int anio){
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, dia);// I might have the wrong Calendar constant...
		cal.set(Calendar.MONTH, mes - 1);// -1 as month is zero-based
		cal.set(Calendar.YEAR, anio);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(cal.getTimeInMillis());
	}
	
	public static Timestamp fechaActual(){
		Calendar cal = GregorianCalendar.getInstance();
		return new Timestamp(cal.getTime().getTime());
	}
	
	public static int getDiaActual(){
		Calendar cal = GregorianCalendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);		
	}
	
	public static int getMesActual(){
		Calendar cal = GregorianCalendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;		
	}
	
	public static int getAnioActual(){
		Calendar cal = GregorianCalendar.getInstance();
		return cal.get(Calendar.YEAR);		
	}
	
	public static String getMesAnioActual(){
		Calendar cal = GregorianCalendar.getInstance();
		return getMesAnio(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
	}
	
	public static String getMesAnioSiguiente(){
		Calendar cal = GregorianCalendar.getInstance();
		int iAnio = cal.get(Calendar.YEAR);
		int iMes = cal.get(Calendar.MONTH) + 2;
		if (iMes >= 12){
			iAnio = iAnio + 1;
			iMes = iMes - 12;
		}
		return getMesAnio(iAnio, iMes);
	}
	
	public static String getMesAnio(int iAnio, int iMes){
		String sAnio = Integer.toString(iAnio);
		String sMes = Integer.toString(iMes);
		if (sMes.length() == 1)
			sMes = "0" + sMes;
		return sAnio + "-" + sMes;
	}

}
