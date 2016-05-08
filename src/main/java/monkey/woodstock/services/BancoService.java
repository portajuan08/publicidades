package monkey.woodstock.services;


import monkey.woodstock.domain.Banco;

public interface BancoService {
    Iterable<Banco> listAllBancos();

    Banco getBancoById(Integer id);

    Banco saveBanco(Banco banco);
    
    void deleteBanco(Banco banco);
}

