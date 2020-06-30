
package com.springboot.tienda.app.modelos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *Clase de transferencia de objeto
 * Aca se guarda una entidad Cliente 
 * Patron de diseño Data Transfer Object.
 * 
 *
 * @author Daniel
 */
@Data
@AllArgsConstructor
public class ClienteDTO {
    
    private Long id ;
    private String nombre;
    private String apellido;
    private String email ;
    private String telefono;
    
}
