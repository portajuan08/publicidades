package monkey.woodstock.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rol {
    @Id
    private String rol;

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}

