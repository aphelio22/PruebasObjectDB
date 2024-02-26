package org.example.domain.bicho;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.tuberculo.Tuberculo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Bicho implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String nombre;
    private String description;

    @ManyToOne
    private Tuberculo tuberculoid;

    @Override
    public String toString() {
        return "Bicho{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", description='" + description + '\'' +
                ", tuberculoid=" + tuberculoid.getId() + //Acuérdate de poner '.getId()' en el toString en el campo que hace referencia a 'Tuberculo'.
                '}';
    }
}
