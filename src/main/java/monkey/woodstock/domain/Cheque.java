package monkey.woodstock.domain;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name="cheque")
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CHEQUE_ID")
    private Integer id;
    
    @NotNull(message = "El emisor no puede ser vacio.")
    private String emisor;
    
    @ManyToOne
    @JoinColumn(name="BANCO_ID")
    private Banco banco;
    
    @NotNull(message = "El Numero de cheque no puede ser vacio.")
    private Long nCheque;
    
    @NotNull(message = "El importe no puede ser vacio.")
    private BigDecimal importe;
    
    @NotNull(message = "La fecha de emision no puede ser vacia.")
    private Date fechaEmision;
    
    @NotNull(message = "La fecha de cobro no puede ser vacia.")
    private Date fechaCobro;
    
    @NotNull(message = "El cliente no puede ser vacio.")
    private String cliente;
    
    private String estado;
    
    private Boolean yaAviso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Long getnCheque() {
		return nCheque;
	}

	public void setnCheque(Long nCheque) {
		this.nCheque = nCheque;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaCobro() {
		return fechaCobro;
	}

	public void setFechaCobro(Date fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Boolean getYaAviso(){
		return yaAviso;
	}
	
	public void setYaAviso(Boolean yaAviso){
		this.yaAviso = yaAviso;
	}
	
	public String toString(){
		return nCheque.toString();
	}
}

