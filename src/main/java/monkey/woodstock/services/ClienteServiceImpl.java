package monkey.woodstock.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javassist.bytecode.Descriptor.Iterator;
import monkey.woodstock.domain.Cliente;
import monkey.woodstock.repositories.ClienteRepository;

import org.junit.internal.ArrayComparisonFailure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {
    private ClienteRepository clienteRepository;

    @Autowired
    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listAllClientes() {
        ArrayList<Cliente> clientes = (ArrayList<Cliente>)clienteRepository.findAll();
        Collections.sort(clientes, new OrdenCliente());
        return clientes;
        
    }
    
    public class OrdenCliente implements Comparator<Cliente> {
        public int compare(Cliente cliente1, Cliente cliente2) {
            return cliente1.getNombre().compareTo(cliente2.getNombre());
        }
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

