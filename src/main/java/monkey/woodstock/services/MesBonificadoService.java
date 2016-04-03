package monkey.woodstock.services;


import monkey.woodstock.domain.MesBonificado;

public interface MesBonificadoService {
    Iterable<MesBonificado> listAllMesesBonificados();

    MesBonificado getMesBonificadoById(Integer id);

    MesBonificado saveMesBonificado(MesBonificado mesBonificado);
    
    void deleteMesBonificado(MesBonificado mesBonificado);
}

