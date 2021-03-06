package monkey.woodstock.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {
    @Id  
    private String username;
    
    private String password;
    
    private Boolean enabled;
    
    private String mail;
    
    @ManyToMany
    @JoinTable (
            name="USER_ROL",
            joinColumns={ @JoinColumn(name="USERNAME", referencedColumnName="USERNAME") },
            inverseJoinColumns={ @JoinColumn(name="ROL", referencedColumnName="ROL") } )
    private List<Rol> rol;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public List<Rol> getRol() {
		if (this.rol == null)
			rol = new ArrayList<Rol>();
		return rol;
	}

	public void setRol(List<Rol> rol) {
		this.rol = rol;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return username;
	}
	
	public void setMail(String mail){
		this.mail = mail;
	}
	
	public String getMail(){
		return mail;
	}

}

