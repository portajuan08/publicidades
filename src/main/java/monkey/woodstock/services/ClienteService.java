package monkey.woodstock.services;


import monkey.woodstock.domain.Cliente;

public interface ClienteService {
    Iterable<Cliente> listAllClientes();

    Cliente getClienteById(Integer id);

    Cliente saveCliente(Cliente cliente);
    
    void deleteCliente(Cliente cliente);
}

