package monkey.woodstock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENTE_ID")
    private Integer id;
    
    @NotNull(message = "El nombre no puede ser vacio.")
    @Length(min = 1, message = "El nombre no puede ser vacio.")
    private String nombre;
    
    @NotNull(message = "La direccion no puede ser vacio.")
    @Length(min = 1, message = "La direccion no puede ser vacio.")
    private String direccion;
    
    @NotNull(message = "El telefono no puede ser vacio.")
    @Length(min = 1, message = "El telefono no puede ser vacio.")
    private String telefono;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
    
    public String toString(){
    	if (id != null)
    		return id.toString();
    	return null;
    }
}

