package monkey.woodstock.services;

import java.util.ArrayList;
import java.util.List;

import monkey.woodstock.Util.UtilTime;
import monkey.woodstock.domain.Cheque;
import monkey.woodstock.repositories.ChequeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChequeServiceImpl implements ChequeService {
    private ChequeRepository chequeRepository;

    @Autowired
    public void setChequeRepository(ChequeRepository chequeRepository) {
        this.chequeRepository = chequeRepository;
    }

    @Override
    public List<Cheque> listAllCheques() {
        return (ArrayList<Cheque>)chequeRepository.findAll();
        
    }

    @Override
    public Cheque getChequeById(Integer id) {
        return chequeRepository.findOne(id);
    }
    
    @Override
    public List<Cheque> getChequesByBanco(Integer bancoId) {
        return chequeRepository.findByBanco(bancoId);
    }

	@Override
	public Cheque saveCheque(Cheque cheque) {
		return chequeRepository.save(cheque);
	}
	
	@Override
	public void deleteCheque(Cheque cheque){
		chequeRepository.delete(cheque);
	}

	@Override
	public List<Cheque> getChequesEnVencimiento() {
		List<Cheque> cheques = chequeRepository.findByMostrarEnGadget();
		List<Cheque> chequesVencidos = new ArrayList<Cheque>();
		for(Cheque cheque : cheques)
			if (UtilTime.estaEnPeriodoVencimiento(cheque.getFechaCobro()))
				chequesVencidos.add(cheque);
		return chequesVencidos;
	}
}

