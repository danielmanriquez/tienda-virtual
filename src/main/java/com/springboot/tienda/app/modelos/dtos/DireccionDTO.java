
package com.springboot.tienda.app.modelos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DireccionDTO {
    
    private String direccion1 ;
    private String direccion2 ;
    private String ciudad ;
    private String codigoPostal ; 
    private String pais ;
    
}
