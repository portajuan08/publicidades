package monkey.woodstock.services;

import java.util.ArrayList;
import java.util.List;

import monkey.woodstock.domain.Rol;
import monkey.woodstock.repositories.RolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {
    private RolRepository rolRepository;

    @Autowired
    public void setRolRepository(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> listAllRoles() {
        return (ArrayList<Rol>)rolRepository.findAll();
        
    }

    @Override
    public Rol getRolByName(String name){
        return rolRepository.findOne(name);
    }

	@Override
	public Rol saveRol(Rol rol) {
		return rolRepository.save(rol);
	}
	
	@Override
	public void deleteRol(Rol rol){
		rolRepository.delete(rol);
	}
}

