package com.springboot.tienda.app.modelos.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data // Anotacion para generar Getters y Setters
@NoArgsConstructor // Anotacion para generar un constructor sin argumentos
@AllArgsConstructor // Anotacion para generar un constructor con todos los argumentos
@EqualsAndHashCode(callSuper = false) // Anotacion para generar metodo Equals y Hash , callSuper false para que no llame al metodo de la super clase
@Entity // Anotacion para mapear una clase con una tabla de la base de datos en el contexto de persistencia .
@Table(name = "pago")
public class Pago extends EntidadAbstracta {
    
    @Column(name = "paypal_pago_id")
    private String paypalPagoId;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status" , nullable = false)
    private EstadoPago estadoPago;
    
    @OneToOne
    @JoinColumn(unique=true)
    private Orden orden;
    
}
