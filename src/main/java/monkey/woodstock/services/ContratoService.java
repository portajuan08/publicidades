package monkey.woodstock.services;


import monkey.woodstock.domain.Contrato;

public interface ContratoService {
    Iterable<Contrato> listAllContratos();

    Contrato getContratoById(Integer id);
    
    Contrato getContratoByCliente(Integer id);

    Contrato saveContrato(Contrato contrato);
    
    void deleteContrato(Contrato contrato);
}

