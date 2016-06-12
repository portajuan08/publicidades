package monkey.woodstock.domain;

public class LabelCheque {
	private Integer idCheque;
	private String textoCheque;

	public LabelCheque(Integer idCheque, String textoCheque )
	{
		this.idCheque = idCheque;
		this.textoCheque = textoCheque;
	}

	public Integer getIdCheque() {
		return idCheque;
	}

	public void setIdCheque(Integer idCheque) {
		this.idCheque = idCheque;
	}

	public String getTextoCheque() {
		return textoCheque;
	}

	public void setTextoCheque(String textoCheque) {
		this.textoCheque = textoCheque;
	}
	
																																																																											
}
