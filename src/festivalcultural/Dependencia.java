/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package festivalcultural;

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


}
