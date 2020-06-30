
package com.springboot.tienda.app.modelos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarroDTO {
    
    private Long id;
    private Long ordenId;
    private ClienteDTO clienteDTO;
    private String estado ;
    
    
}
