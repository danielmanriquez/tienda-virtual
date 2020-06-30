
package com.springboot.tienda.app.modelos.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrdenDTO {
    
    private Long id ;
    private BigDecimal precioTotal;
    private String estadoOrden;
    private ZonedDateTime enviada;
    private PagoDTO pago;
    private DireccionDTO direccion ;
    private Set<ItemOrdenDTO> itemsOrden;
    
}
