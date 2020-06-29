/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.tienda.app.modelos.repositorios;

import com.springboot.tienda.app.modelos.entidades.Orden;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepositorio extends JpaRepository<Orden , Long>{
    
    
     /**
     *Usando el mecanismo de Spring Data Query Builder 
     *creamos un metodo que nos devuelve una lista filtrada de los carros que tiene el cliente que pasamos por id
     * 
     * @param id
     * @return lista de carros del cliente.
     */
    List<Orden> findByCarroCliente_Id(Long id);
}
