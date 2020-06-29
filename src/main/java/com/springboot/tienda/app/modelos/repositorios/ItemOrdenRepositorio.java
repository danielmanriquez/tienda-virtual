/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.tienda.app.modelos.repositorios;

import com.springboot.tienda.app.modelos.entidades.ItemOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrdenRepositorio extends JpaRepository < ItemOrden , Long > {
    
    
    
}
