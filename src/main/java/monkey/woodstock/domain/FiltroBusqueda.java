package monkey.woodstock.domain;

import java.sql.Timestamp;
import java.util.List;

import monkey.woodstock.Util.UtilTime;

public class FiltroBusqueda {
	
	private String mes;
	private Boolean venceMes;
	private Boolean bonificadoMes;
	private Boolean telefono;
	private Boolean direccion;
	private Boolean vendedor;
	private Boolean precio;
	private Boolean inicio;
	private Boolean fin;
	private Boolean esBonificado;
	private Boolean seVence;

	public FiltroBusqueda(){
		this.mes = UtilTime.getMesAnioActual();
		this.venceMes = true;
		this.bonificadoMes = true;
		this.telefono = false;
		this.direccion = false;
		this.vendedor = true;
		this.precio = true;
		this.inicio = true;
		this.fin = true;
		this.esBonificado = true;
		this.seVence = true;
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
	
	public Boolean getTelefono() {
		return telefono;
	}

	public void setTelefono(Boolean telefono) {
		this.telefono = telefono;
	}

	public Boolean getDireccion() {
		return direccion;
	}

	public void setDireccion(Boolean direccion) {
		this.direccion = direccion;
	}

	public Boolean getVendedor() {
		return vendedor;
	}

	public void setVendedor(Boolean vendedor) {
		this.vendedor = vendedor;
	}

	public Boolean getPrecio() {
		return precio;
	}

	public void setPrecio(Boolean precio) {
		this.precio = precio;
	}

	public Boolean getInicio() {
		return inicio;
	}

	public void setInicio(Boolean inicio) {
		this.inicio = inicio;
	}

	public Boolean getFin() {
		return fin;
	}

	public void setFin(Boolean fin) {
		this.fin = fin;
	}

	public Boolean getEsBonificado() {
		return esBonificado;
	}

	public void setEsBonificado(Boolean esBonificado) {
		this.esBonificado = esBonificado;
	}

	public Boolean getSeVence() {
		return seVence;
	}

	public void setSeVence(Boolean seVence) {
		this.seVence = seVence;
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
	
	public String getUrl(){
		StringBuffer oString = new StringBuffer();
		oString.append("_venceMes=on&");
		if (seVence)
			oString.append("venceMes=true&");
		oString.append("_bonificadoMes=on&");
		if (bonificadoMes)
			oString.append("bonificadoMes=true&");
		oString.append("mes=" + mes + "&");
		oString.append("_telefono=on&");
		if (telefono)
			oString.append("telefono=true&");
		oString.append("_direccion=on&");
		if (direccion)
			oString.append("direccion=true&");
		oString.append("_vendedor=on&");
		if (vendedor)
			oString.append("vendedor=true&");
		oString.append("_precio=on&");
		if (precio)
			oString.append("precio=true&");
		oString.append("_inicio=on&");
		if (inicio)
			oString.append("inicio=true&");
		oString.append("_fin=on&");
		if (fin)
			oString.append("fin=on&");
		oString.append("_esBonificado=true&");
		if (esBonificado)
			oString.append("esBonificado=true&");
		oString.append("_seVence=on&");
		if (seVence)
			oString.append("seVence=true&");
		return oString.toString();
	}
	
	public String getResultCampo(boolean bValor){
		if (bValor)
			return "true";
		else
			return "on";
	}
	
	public String toString(){
		return "Mes : " + mes + " se vence : " + venceMes + " bonifica mes : " + bonificadoMes;
	}
}