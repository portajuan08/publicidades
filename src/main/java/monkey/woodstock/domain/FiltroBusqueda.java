package monkey.woodstock.domain;

import java.sql.Timestamp;
import java.util.List;

import org.aspectj.weaver.SignatureUtils;

import monkey.woodstock.Util.UtilTime;

public class FiltroBusqueda {
	
	private String mes;
	private Boolean venceMes;
	private Boolean bonificadoMes;
	
	public FiltroBusqueda(String mes, Boolean venceMes, Boolean bonificadoMes) {
		super();
		this.mes = mes;
		this.venceMes = venceMes;
		this.bonificadoMes = bonificadoMes;
	}
	
	public FiltroBusqueda(){
		this(UtilTime.getMesAnioActual(), true, true);
	}
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Boolean getVenceMes() {
		return venceMes;
	}
	public void setVenceMes(Boolean venceMes) {
		this.venceMes = venceMes;
	}
	public Boolean getBonificadoMes() {
		return bonificadoMes;
	}
	public void setBonificadoMes(Boolean bonificadoMes) {
		this.bonificadoMes = bonificadoMes;
	}
	
	public boolean muestroPorMesVencido(Timestamp tFinMes){
		if (venceMes)
			return true;
		else{
	        String[] sFecha = getMes().split("-");
	        Timestamp tFecha = UtilTime.crearFecha(1, Integer.parseInt(sFecha[1]), Integer.parseInt(sFecha[0]));
	        if (tFecha.equals(tFinMes))
	        	return false;
	        else
	        	return true;
		}
	}
	
	public boolean muestroPorMesBonificado(List<MesBonificado> mesesBonificados){
		if (bonificadoMes)
			return true;
		else{
	        String[] sFecha = getMes().split("-");
	        Timestamp tFecha = UtilTime.crearFecha(1, Integer.parseInt(sFecha[1]), Integer.parseInt(sFecha[0]));
	        for (MesBonificado oMesBonificado : mesesBonificados){
		        String[] sFechaAux = oMesBonificado.getMes().split("-");
		        Timestamp tFechaAux = UtilTime.crearFecha(1, Integer.parseInt(sFechaAux[1]), Integer.parseInt(sFechaAux[0]));
		        if (tFechaAux.equals(tFecha))
		        	return false;
	        }
	        return true;
		}
	}
	
	public String toString(){
		return "Mes : " + mes + " vence : " + venceMes + " bonifica " + bonificadoMes;
	}
}
