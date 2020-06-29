package com.springboot.tienda.app.modelos.repositorios;

import com.springboot.tienda.app.modelos.entidades.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepositorio extends JpaRepository < Pago , Long > {
    
}
