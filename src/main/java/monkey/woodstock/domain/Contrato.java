package monkey.woodstock.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity(name="contrato")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTRATO_ID")
    private Integer id;

    @NotNull(message = "El vendedor no puede ser vacio.")
    @Length(min = 1, message = "El vendedor no puede ser vacio.")
    private String vendedor;
    
    @Min(0)
    private BigDecimal precio;
    
    private String fechaInicio;
    
    private String fechaFin;
    
    @NotNull
    @OneToOne
    @JoinColumn(name="CLIENTE_ID", unique=true)
    private Cliente cliente;
    
    @OneToMany
    @JoinTable (
                    name="CONTRATO_MES",
                    joinColumns={ @JoinColumn(name="CONTRATO_ID", referencedColumnName="CONTRATO_ID") },
                    inverseJoinColumns={ @JoinColumn(name="MES_ID", referencedColumnName="MES_ID", unique=true) } )
    private List<MesBonificado> mesesBonificados;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<MesBonificado> getMesesBonificados() {
		if (this.mesesBonificados == null)
			mesesBonificados = new ArrayList<MesBonificado>();
		return mesesBonificados;
	}

	public void setMesesBonificados(List<MesBonificado> mesesBonificados) {
		this.mesesBonificados = mesesBonificados;
	}
	
	public void setCliente(Cliente cliente){
		this.cliente = cliente;
	}
	
	public Cliente getCliente(){
		return cliente;
	}
    
    /*
    @ManyToMany
    @JoinTable (
                    name="CHOFER_AUTO",
                    joinColumns={ @JoinColumn(name="AUTO_ID", referencedColumnName="AUTO_ID") },
                    inverseJoinColumns={ @JoinColumn(name="CHOFER_ID", referencedColumnName="CHOFER_ID") } )
    private List<Chofer> choferes;*/
    
    
}

