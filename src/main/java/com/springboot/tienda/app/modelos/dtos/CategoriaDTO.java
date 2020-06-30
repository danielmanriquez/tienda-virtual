package com.springboot.tienda.app.modelos.dtos;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaDTO {
   
    private  Long id ;
    private String nombre ;
    private String descripcion ;
    private Set<ProductoDTO> productos ;
            
            
}
