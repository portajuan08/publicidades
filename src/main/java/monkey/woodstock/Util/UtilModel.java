package monkey.woodstock.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import monkey.woodstock.domain.CambiarContraseña;
import monkey.woodstock.domain.LabelCheque;
import monkey.woodstock.services.ChequeService;

public class UtilModel {

	
	public static Map<String,Object> getModalsGenerales(ChequeService chequeService){
		Map<String,Object> oMapModal = new HashMap<String, Object>();
    	List<LabelCheque> cheques = UtilCheque.getLabelsCheque(chequeService.getChequesEnVencimiento());
    	oMapModal.put("vencidos",cheques.size());
    	oMapModal.put("chequesVencidos",cheques);
    	oMapModal.put("cambiarContra", new CambiarContraseña());
		return oMapModal;
	}
	
}
