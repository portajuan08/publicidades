package monkey.woodstock.Util;

import java.util.ArrayList;
import java.util.List;

import monkey.woodstock.domain.Cheque;
import monkey.woodstock.domain.LabelCheque;

public class UtilCheque {
	
	public static List<LabelCheque> getLabelsCheque(List<Cheque> oCheques){
		List<LabelCheque> labels = new ArrayList<LabelCheque>();
		for (Cheque oCheque : oCheques){
			String sTexto = "Numero de cheque: " + oCheque.getnCheque() + " Fecha de Cobro: " + oCheque.getFechaCobro(); 
			LabelCheque oLabel = new LabelCheque(oCheque.getId(), sTexto);
			labels.add(oLabel);
		}
		return labels;
	}

}
