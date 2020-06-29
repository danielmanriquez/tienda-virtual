
package com.springboot.tienda.app.modelos.entidades;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="categoria")
public class Categoria extends EntidadAbstracta{
    
    @NotNull
    @Column(name = "nombre" , nullable= false)
    private String nombre ;
    
    @NotNull
    @Column(name = "nombre" , nullable= false)
    private String descripcion ;
    
    @OneToMany(mappedBy="categoria")
    private Set<Producto> productos = new HashSet<>();
    
}
