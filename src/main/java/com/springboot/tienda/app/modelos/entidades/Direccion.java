package com.springboot.tienda.app.modelos.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Embeddable // Anotacion para poder integrar esta clase dentro de otra entidad dentro del contexto de persistencia
public class Direccion {
   
    @Column (name = "direccion_1")
    private String direccion1;
    
    @Column(name = "direccion_2")
    private String direccion2;
    
    @Column(name = "ciudad")
    private String ciudad ;
    
    @NotNull
    @Size(max = 10)
    @Column (name = "codigo_postal" , length = 2 , nullable = false)
    private String codigoPostal;
    
    @NotNull
    @Size(max = 2) // para poder asignar CL , CO , PE , BR 
    @Column (name = "pais" , length = 2 , nullable = false)
    private String pais;
    
    
    
    
    
    
    
    
    
}
