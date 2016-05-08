package monkey.woodstock.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import monkey.woodstock.domain.Banco;
import monkey.woodstock.repositories.BancoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BancoServiceImpl implements BancoService {
    private BancoRepository bancoRepository;

    @Autowired
    public void setBancoRepository(BancoRepository bancoRepository) {
        this.bancoRepository = bancoRepository;
    }

    @Override
    public List<Banco> listAllBancos() {
        ArrayList<Banco> bancos = (ArrayList<Banco>)bancoRepository.findAll();
        Collections.sort(bancos, new OrdenBanco());
        return bancos;
        
    }
    
    public class OrdenBanco implements Comparator<Banco> {
        public int compare(Banco banco1, Banco banco2) {
            return banco1.getDescripcion().compareTo(banco2.getDescripcion());
        }
    }

    @Override
    public Banco getBancoById(Integer id) {
        return bancoRepository.findOne(id);
    }

	@Override
	public Banco saveBanco(Banco banco) {
		return bancoRepository.save(banco);
	}
	
	@Override
	public void deleteBanco(Banco banco){
		bancoRepository.delete(banco);
	}
}

