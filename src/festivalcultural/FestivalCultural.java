/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package festivalcultural;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author juancz55
 */
public class FestivalCultural {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        Grilla gr = new Grilla();
        Persona susan = new Persona("Susan");
        Persona filipo = new Persona("Filipo");
        ArrayList<Persona> list = new ArrayList<>();
        list.add(susan);
        list.add(filipo);
        // Ejemplos de entrada de talleres
        gr.addTaller("Basico de Ceramica", LocalTime.of(10, 0), LocalTime.of(12, 0), "Carpa A");
        gr.addTaller("Avanzado de Ceramica", LocalTime.of(12, 30), LocalTime.of(14, 0), "Carpa B");
        gr.addTaller("Pintura en Ceramica", LocalTime.of(15, 0), LocalTime.of(17, 0), "Carpa C");
        gr.addTaller("Arte abstracta", LocalTime.of(9, 0), LocalTime.of(11, 0), "Carpa D");
        gr.addTaller("Vitrificacion", LocalTime.of(11, 30), LocalTime.of(13, 0), "Carpa F");
        gr.addTaller("Esculturas", LocalTime.of(13, 30), LocalTime.of(15, 45), "Carpa F");
        gr.addDependencia("Basico de Ceramica", "Avanzado de Ceramica",18.5 );
        gr.addDependencia("Avanzado de Ceramica", "Pintura en Ceramica",12.5);
        gr.addDependencia("Pintura en Ceramica", "Arte abstracta",6.4);
        gr.addDependencia("Pintura en Ceramica", "Vitrificacion",13.2);
        gr.addDependencia("Pintura en Ceramica", "Esculturas", 9.4);
        Scanner scan = new Scanner(System.in);
        int i = 0;
        int eleccion = 0;
        int var = 0;
        String resAdmin;

        do {
            do {
                System.out.println("Administracion <-1>\n");
                System.out.println("Eliga una persona");
                for (Persona persona : list) {
                    System.out.println("  <"+i + ">." + persona.getNombre());
                    i++;
                }
                System.out.println("\nSalir <"+i+">");
                eleccion = scan.nextInt();
                if (eleccion == i) {
                    System.out.println("bye bye bye");
                    return;
                }
                if (eleccion < -1 || eleccion > i) {
                    System.out.println("Ingrese un opcion valida");

                }
            } while (eleccion < -1 || eleccion > i);
                            i = 0;

            // Posible if para administracion.
            if (eleccion == -1) {
                do {
                    resAdmin = menu.menuAdmin(list, gr, scan);
                } while (resAdmin != "9");
            } else {
                do {
                    System.out.println("");

                    System.out.println("--------Eliga una opcion--------");
                    System.out.println("1.Inscribirse en un taller");
                    System.out.println("2.Consulta sobre 2 tallers");
                    System.out.println("3.Talleres Previos");
                    System.out.println("4.Talleres Siguientes");
                    System.out.println("5.Volver");
                    System.out.println("6.Salir");
                    var = scan.nextInt();
                    System.out.println("");
                    switch (var) {
                        case 1:
                            gr.menu1(list.get(eleccion), scan);
                            break;
                        case 2:
                            gr.caseDos(list.get(eleccion), gr.getTalleres(), scan);
                            break;
                        case 3:
                            gr.menu3(scan);
                            break;
                        case 4:
                             gr.menu4(scan);
                            break;
                        case 5:

                            break;
                        case 6:
                            return;
                        default:
                            System.out.println("Ingrese una opción válida.");
                    }
                } while (var != 5);
            }

        } while (true);

    }

}
