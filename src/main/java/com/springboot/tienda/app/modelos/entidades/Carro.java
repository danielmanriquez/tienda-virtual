package com.springboot.tienda.app.modelos.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity
@Table(name = "carro")
public class Carro extends EntidadAbstracta implements Serializable {
    
    private static final long serialVersionUID = 1L ;
    
    @OneToOne
    @JoinColumn(unique = true)
    private Orden orden ;
    
    @ManyToOne
    private Cliente cliente ;
    
    @NotNull // Valida que la entrada no sea null .
    @Enumerated(EnumType.STRING)
    private EstadoCarro estado ;
    
    
    public Carro (Cliente cliente) {
    
        this.cliente = cliente ;
        this.estado = EstadoCarro.NUEVO ;
        
    }
    
    
    
    
    
}
