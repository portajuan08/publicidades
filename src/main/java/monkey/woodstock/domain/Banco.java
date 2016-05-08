package monkey.woodstock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BANCO_ID")
    private Integer id;
    
    @NotNull(message = "La descripcion no puede ser vacio.")
    @Length(min = 1, message = "La descripcion no puede ser vacio.")
    private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    public String toString(){
    	if (id != null)
    		return id.toString();
    	return null;
    }
}

