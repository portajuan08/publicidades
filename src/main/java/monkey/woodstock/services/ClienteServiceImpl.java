package monkey.woodstock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import monkey.woodstock.domain.Cliente;
import monkey.woodstock.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
    private ClienteRepository clienteRepository;

    @Autowired
    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Iterable<Cliente> listAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Integer id) {
        return clienteRepository.findOne(id);
    }

	@Override
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@Override
	public void deleteCliente(Cliente cliente){
		clienteRepository.delete(cliente);
	}
}

