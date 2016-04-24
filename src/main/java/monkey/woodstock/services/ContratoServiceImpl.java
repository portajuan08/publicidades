package monkey.woodstock.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import monkey.woodstock.Util.UtilTime;
import monkey.woodstock.domain.Contrato;
import monkey.woodstock.domain.FiltroBusqueda;
import monkey.woodstock.repositories.ContratoRepository;

@Service
public class ContratoServiceImpl implements ContratoService {
    private ContratoRepository contratoRepository;

    @Autowired
    public void setContratoRepository(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    @Override
    public List<Contrato> listAllContratos() {
        return (List<Contrato>)contratoRepository.findAll();
    }
    
    @Override
    public List<Contrato> listAllContratos(FiltroBusqueda filtroBusqueda) {
        List<Contrato> contratos = listAllContratos();
        Iterator<Contrato> oIt = contratos.iterator();
        if (filtroBusqueda.getMes() == null)
        	filtroBusqueda.setMes(UtilTime.getMesAnioActual());
        String[] sFecha = filtroBusqueda.getMes().split("-");
        Timestamp tFecha = UtilTime.crearFecha(1, Integer.parseInt(sFecha[1]), Integer.parseInt(sFecha[0]));
        while (oIt.hasNext()){
        	Contrato contrato = oIt.next();
            String[] sFechaInicio = contrato.getFechaInicio().split("-");
            Timestamp tFechaInicio = UtilTime.crearFecha(1, Integer.parseInt(sFechaInicio[1]), Integer.parseInt(sFechaInicio[0]));
            String[] sFechaFin = contrato.getFechaFin().split("-");
            Timestamp tFechaFin = UtilTime.crearFecha(1, Integer.parseInt(sFechaFin[1]), Integer.parseInt(sFechaFin[0]));
        	if (tFecha.compareTo(tFechaInicio) >= 0 && tFecha.compareTo(tFechaFin) <= 0){
        		if (!filtroBusqueda.muestroPorMesVencido(tFechaFin))
        			oIt.remove();
        		if (!filtroBusqueda.muestroPorMesBonificado(contrato.getMesesBonificados()))
        			oIt.remove();
        	}else
        		oIt.remove();
        		
        }
        	
        return contratos;
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

