
package com.springboot.tienda.app.modelos.repositorios;

import com.springboot.tienda.app.modelos.entidades.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente , Long> {
    
    List<Cliente> findAllByActivo(Boolean activo);
    
}
