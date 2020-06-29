package com.springboot.tienda.app.modelos.repositorios;

import com.springboot.tienda.app.modelos.entidades.Carro;
import com.springboot.tienda.app.modelos.entidades.EstadoCarro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


 @Repository
public interface CarroRepositorio extends JpaRepository <Carro , Long>{
    
    /**
     *Usando el mecanismo de Spring Data Query Builder 
     *creamos un metodo que nos devuelve una lista filtrada por el estado del carro.
     * 
     * @param estadoCarro
     * @return lista de carros
     */
    List<Carro> findByEstadoCarro(EstadoCarro estadoCarro);
     
    /**
     *Usando el mecanismo de Spring Data Query Builder 
     * creamos un metodo que nos devuelve una lista filtrada por el estado del carro y el id del cliente.
     * 
     * 
     * 
     * @param estadoCarro
     * @param idCliente
     * @return lista de carros
     */
    List<Carro> findByEstadoCarroAndIdCliente(EstadoCarro estadoCarro , Long idCliente);
     
     
}
