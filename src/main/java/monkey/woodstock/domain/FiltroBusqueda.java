package monkey.woodstock.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
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
	
	public int getCantidadColumnas(){
		int iCant = 1; //por la columa nombre
		if (telefono) iCant++;
		if (direccion) iCant++;
		if (vendedor) iCant++;
		if (precio) iCant++;
		if (inicio) iCant++;
		if (fin) iCant++;
		if (esBonificado) iCant++;
		if (seVence) iCant++;
		return iCant;
	}
	
	public List<String> getColumnas(){
		List<String> columnas = new ArrayList<String>();
		columnas.add("NOMBRE");
		if (telefono) columnas.add("TELEFONO");
		if (direccion) columnas.add("DIRECCION");
		if (vendedor) columnas.add("VENDEDOR");
		if (precio) columnas.add("PRECIO");
		if (inicio) columnas.add("INICIO");
		if (fin) columnas.add("FIN");
		if (esBonificado) columnas.add("ES BONIFICADO");
		if (seVence) columnas.add("SE VENCE");
		return columnas;
	}
	
	public String toString(){
		return "Mes : " + mes + " se vence : " + venceMes + " bonifica mes : " + bonificadoMes;
	}
}