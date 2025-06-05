/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package festivalcultural;

import java.time.LocalTime;
import java.util.ArrayList;
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
        Grilla gr = new Grilla();
        Persona susan = new Persona("Susan");
        Persona filipo = new Persona("Filipo");
        ArrayList<Persona> list = new ArrayList<>();
        list.add(susan);
        list.add(filipo);

        Scanner scan = new Scanner(System.in);
        int i = 0;
        int eleccion = 0;
        int var = 0;
        do {
            System.out.println("Eliga una persona");

            do {
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
                if (eleccion < 0 || eleccion > i) {
                    System.out.println("Ingrese un opcion valida");
                }
            } while (eleccion < 0 || eleccion > i);
            do {
                System.out.println("--------------------------------------------------------------------");

                System.out.println("Eliga una opcion");
                System.out.println("1-Inscribirse en un taller");
                System.out.println("2-Consulta sobre 2 tallers");
                System.out.println("3-Talleres Previos");
                System.out.println("4-Talleres Dependientes");
                System.out.println("5-Volver");
                System.out.println("6-Salir");
                var = scan.nextInt();

                switch (var) {
                    case 1:
                        gr.menu1(list.get(eleccion), scan);
                        break;
                    case 2:

                        break;
                    case 3:
                        gr.menu3(scan);
                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Ingrese una opción válida.");
                }
            } while (var != 5);

        } while (true);

    }

}
