package monkey.woodstock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import monkey.woodstock.domain.MesBonificado;
import monkey.woodstock.repositories.MesBonificadoRepository;

@Service
public class MesBonificadoServiceImpl implements MesBonificadoService {
    private MesBonificadoRepository mesBonficadoRepository;

    @Autowired
    public void setMesBonificadoRepository(MesBonificadoRepository MesBonificadoRepository) {
        this.mesBonficadoRepository = MesBonificadoRepository;
    }

    @Override
    public Iterable<MesBonificado> listAllMesesBonificados() {
        return mesBonficadoRepository.findAll();
    }

    @Override
    public MesBonificado getMesBonificadoById(Integer id) {
        return mesBonficadoRepository.findOne(id);
    }

	@Override
	public MesBonificado saveMesBonificado(MesBonificado mesBonificado) {
		return mesBonficadoRepository.save(mesBonificado);
	}
	
	@Override
	public void deleteMesBonificado(MesBonificado mesBonificado){
		mesBonficadoRepository.delete(mesBonificado);
	}
}

