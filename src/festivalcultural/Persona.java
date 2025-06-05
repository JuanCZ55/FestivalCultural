/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package festivalcultural;

import java.util.HashSet;

/**
 *
 * @author juancz55
 */
public class Persona {

    private HashSet<Taller> talleres;
    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
        talleres = new HashSet<>();
    }

    public HashSet<Taller> getTalleres() {
        return talleres;
    }

    public boolean addTaller(Taller taller) {
        return this.talleres.add(taller);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
