package monkey.woodstock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MesBonificado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MES_ID")
    private Integer id;
    
    private String mes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
}
