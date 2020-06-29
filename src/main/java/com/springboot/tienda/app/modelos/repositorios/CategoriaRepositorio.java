package com.springboot.tienda.app.modelos.repositorios;

import com.springboot.tienda.app.modelos.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria , Long> {
    
    
}
