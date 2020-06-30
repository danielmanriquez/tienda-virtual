package com.springboot.tienda.app.modelos.servicios;

import com.springboot.tienda.app.modelos.dtos.DireccionDTO;
import com.springboot.tienda.app.modelos.entidades.Direccion;


public class DireccionServicio {
    
    public static DireccionDTO mapearADto(Direccion direccion){
    
        if (direccion != null){
            return new DireccionDTO (direccion.getDireccion1() ,
                    direccion.getDireccion2() ,
                    direccion.getCiudad() ,
                    direccion.getCodigoPostal() ,
                    direccion.getPais()  );
        
        }
        
        return null ;
    }
}
