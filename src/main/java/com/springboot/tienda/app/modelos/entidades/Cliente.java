package com.springboot.tienda.app.modelos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data // Anotacion para generar Getters y Setters
@NoArgsConstructor // Anotacion para generar un constructor sin argumentos
@AllArgsConstructor // Anotacion para generar un constructor con todos los argumentos
@EqualsAndHashCode(callSuper = false) // Anotacion para generar metodo Equals y Hash , callSuper false para que no llame al metodo de la super clase
@Entity // Anotacion para mapear una clase con una tabla de la base de datos en el contexto de persistencia .
@Table(name = "cliente")
public class Cliente  extends EntidadAbstracta{
    
    @Column(name = "primer_nombre")
    private String primerNombre;
    
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    
    @Column(name = "email")
    private String email;
    
    @Column(name="telefono")
    private String telefono;
    
    @OneToMany(mappedBy="cliente")
    @JsonIgnore
    private Set<Carro> carros;
    
}
