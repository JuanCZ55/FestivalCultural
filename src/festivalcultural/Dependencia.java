/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package festivalcultural;

import java.util.Objects;

/**
 *
 * @author juancz55
 */
public class Dependencia {
    
    private Taller tallerOne;
    private Taller tallerTwo;
    private Double distancia;

    public Dependencia(Taller tallerOne, Taller tallerTwo, Double distancia) {
        this.tallerOne = tallerOne;
        this.tallerTwo = tallerTwo;
        this.distancia = distancia;
    }
   

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Taller getTallerOne() {
        return tallerOne;
    }

    public void setTallerOne(Taller tallerOne) {
        this.tallerOne = tallerOne;
    }

    public Taller getTallerTwo() {
        return tallerTwo;
    }

    public void setTallerTwo(Taller tallerTwo) {
        this.tallerTwo = tallerTwo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.tallerOne);
        hash = 17 * hash + Objects.hashCode(this.tallerTwo);
        hash = 17 * hash + Objects.hashCode(this.distancia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dependencia other = (Dependencia) obj;
        if (!Objects.equals(this.tallerOne, other.tallerOne)) {
            return false;
        }
        if (!Objects.equals(this.tallerTwo, other.tallerTwo)) {
            return false;
        }
        return Objects.equals(this.distancia, other.distancia);
    }

    @Override
    public String toString() {
        return  "Taller necesario: " + tallerOne + ", para hacer : " + tallerTwo + ", distancia: " + distancia ;
    }
    

}
