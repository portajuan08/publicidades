package monkey.woodstock.services;


import java.util.List;

import monkey.woodstock.domain.Cheque;

public interface ChequeService {
    Iterable<Cheque> listAllCheques();

    Cheque getChequeById(Integer id);

    Cheque saveCheque(Cheque cheque);
    
    List<Cheque> getChequesByBanco(Integer id);
    
    void deleteCheque(Cheque cheque);
    
    
}

