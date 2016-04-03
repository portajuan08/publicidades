package monkey.woodstock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import monkey.woodstock.domain.Contrato;
import monkey.woodstock.repositories.ContratoRepository;

@Service
public class ContratoServiceImpl implements ContratoService {
    private ContratoRepository contratoRepository;

    @Autowired
    public void setContratoRepository(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    @Override
    public Iterable<Contrato> listAllContratos() {
        return contratoRepository.findAll();
    }

    @Override
    public Contrato getContratoById(Integer id) {
        return contratoRepository.findOne(id);
    }
    
    @Override
    public Contrato getContratoByCliente(Integer clienteId) {
        return contratoRepository.findByCliente(clienteId);
    }

	@Override
	public Contrato saveContrato(Contrato contrato) {
		return contratoRepository.save(contrato);
	}
	
	@Override
	public void deleteContrato(Contrato contrato){
		contratoRepository.delete(contrato);
	}
}

