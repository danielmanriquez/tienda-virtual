/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.tienda.app.modelos.repositorios;

import com.springboot.tienda.app.modelos.entidades.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevisionRepositorio  extends JpaRepository<Revision , Long>{
    
}
