package monkey.woodstock.services;


import java.util.List;

import monkey.woodstock.domain.Contrato;
import monkey.woodstock.domain.FiltroBusqueda;

public interface ContratoService {
    List<Contrato> listAllContratos();
    
    List<Contrato> listAllContratos(FiltroBusqueda filtroBusqueda);

    Contrato getContratoById(Integer id);
    
    Contrato getContratoByCliente(Integer id);

    Contrato saveContrato(Contrato contrato);
    
    void deleteContrato(Contrato contrato);
}

