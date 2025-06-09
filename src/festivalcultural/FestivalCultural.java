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
        gr.addTaller("Pintura", LocalTime.of(10, 0), LocalTime.of(12, 0), "Aula 1");
        gr.addTaller("Danza", LocalTime.of(12, 30), LocalTime.of(14, 0), "Aula 2");
        gr.addTaller("Teatro", LocalTime.of(15, 0), LocalTime.of(17, 0), "Aula 3");
        gr.addTaller("Fotografía", LocalTime.of(9, 0), LocalTime.of(11, 0), "Aula 4");
        gr.addTaller("Música", LocalTime.of(11, 30), LocalTime.of(13, 0), "Aula 5");

        Scanner scan = new Scanner(System.in);
        int i = 0;
        int eleccion = 0;
        int var = 0;
        String resAdmin;

        do {
            do {
                System.out.println("-1 - Administracion");
                System.out.println("Eliga una persona");
                for (Persona persona : list) {
                    System.out.println(i + "-" + persona.getNombre());
                    i++;
                }
                System.out.println(i + "-Salir");
                eleccion = scan.nextInt();
                if (eleccion == i) {
                    System.out.println("bye bye bye");
                    return;
                }
                if (eleccion < -1 || eleccion > i) {
                    System.out.println("Ingrese un opcion valida");

                }
                i = 0;
            } while (eleccion < -1 || eleccion > i);
            // Posible if para administracion.
            if (eleccion == -1) {
                do {
                    resAdmin = menu.menuAdmin(list, gr, scan);
                } while (resAdmin != "7");
            } else {
                do {
                    System.out.println("--------------------------------------------------------------------");

                    System.out.println("Eliga una opcion");
                    System.out.println("1-Inscribirse en un taller");
                    System.out.println("2-Consulta sobre 2 tallers");
                    System.out.println("3-Talleres Previos");
                    System.out.println("4-Talleres Siguientes");
                    System.out.println("5-Volver");
                    System.out.println("6-Salir");
                    var = scan.nextInt();

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
                            // TODO talleres siguientes, vecindad derecha extendida
                            // gr.buscarTalleresSiguientesATaller(taller); falta terminar menu, y ver si
                            // funciona
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
