package monkey.woodstock.services;


import monkey.woodstock.domain.Rol;

public interface RolService {
    Iterable<Rol> listAllRoles();

    Rol getRolByName(String name);

    Rol saveRol(Rol rol);
    
    void deleteRol(Rol rol);
}

