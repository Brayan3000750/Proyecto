/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectolaboratorio;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner datos = new Scanner(System.in);
        Juego game = new Juego();

        System.out.println("Bienvenido al Juego de Ghosts");
        System.out.print("Ingrese un usuario para jugador 1: ");
        String username = datos.nextLine();
        System.out.print("Password: ");
        String password = datos.nextLine();
        Jugador player1 = new Jugador(username, password);
        game.setJugador1(player1);

        boolean running = true;

        while (running) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Jugar Ghosts");
            System.out.println("2. Configuracion");
            System.out.println("3. Reportes");
            System.out.println("4. Mi Perfil");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            int option = datos.nextInt();
            datos.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    // Jugar Ghosts
                    System.out.print("Ingrese el usuario del jugador 2: ");
                    String opponentName = datos.nextLine();
                    
                    
                    Jugador player2 = new Jugador(opponentName, "default");
                    if (game.setJugador2(player2)) {
                        game.jugarJuego();
                        game.registrarResultado("Partida entre " + player1.getUsername() + " y " + opponentName + " finalizada.");
                    } else {
                        System.out.println("El jugador 2 no es válido.");
                    }
                    break;

                case 2:
                    // Configuración
                    configurarJuego(game, datos);
                    break;

                case 3:
                    // Reportes
                    mostrarReportes(game, datos);
                    break;

                case 4:
                    // Mi Perfil
                    manejarPerfil(game, datos);
                    break;

                case 5:
                    // Salir
                    running = false;
                    System.out.println("Adios!");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        }
        datos.close();
    }

    private static void configurarJuego(Juego game, Scanner datos) {
        boolean inConfig = true;
        while (inConfig) {
            System.out.println("\nConfiguracion:");
            System.out.println("a. Dificultad");
            System.out.println("b. Modo de Juego");
            System.out.println("c. Regresar al Menu Principal");
            System.out.print("Seleccione una opcion: ");
            char configOption = datos.next().charAt(0);

            switch (configOption) {
                case 'a':
                    System.out.println("Seleccione dificultad: (1) Normal (2) Expert (3) Genius");
                    int dificultad = datos.nextInt();
                    game.configurarDificultad(dificultad);
                    break;

                case 'b':
                    System.out.println("Seleccione modo: (1) Aleatorio (2) Manual");
                    int modo = datos.nextInt();
                    game.configurarModoJuego(modo == 1);
                    break;

                case 'c':
                    inConfig = false;
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private static void mostrarReportes(Juego game, Scanner datos) {
        boolean inReports = true;
        while (inReports) {
            System.out.println("\nReportes:");
            System.out.println("a. Descripcion de mis últimos 10 juegos");
            System.out.println("b. Ranking de Jugadores");
            System.out.println("c. Regresar al Menú principal");
            System.out.print("Seleccione una opcion: ");
            char reportOption = datos.next().charAt(0);

            switch (reportOption) {
                case 'a':
                    game.mostrarReportesJugadorActual();
                    break;

                case 'b':
                    game.mostrarRanking();
                    break;

                case 'c':
                    inReports = false;
                    break;

                default:
                    System.out.println("Opción invalida.");
            }
        }
    }

    private static void manejarPerfil(Juego game, Scanner datos) {
        boolean inProfile = true;
        while (inProfile) {
            System.out.println("\nMi Perfil:");
            System.out.println("a. Ver mis Datos");
            System.out.println("b. Cambiar Password");
            System.out.println("c. Eliminar mi cuenta");
            System.out.println("d. Regresar al Menu Principal");
            System.out.print("Seleccione una opción: ");
            char profileOption = datos.next().charAt(0);

            switch (profileOption) {
                case 'a':
                    System.out.println("Mis datos:");
                    System.out.println(game.getJugador1());
                    break;

                case 'b':
                    if (game.getJugador1() != null) {
                        System.out.print("Ingrese nueva contraseña: ");
                        String newPassword = datos.nextLine();
                        game.getJugador1().setPassword(newPassword);
                        System.out.println("Contraseña cambiada.");
                    } else {
                        System.out.println("Error: No hay un jugador activo.");
                    }
                    break;

                case 'c':
                    
                   
             
                    System.out.println("Cuenta eliminada.");
                    game.setJugador1(null);
                    inProfile = false; // Salir del perfil
                    break;

                case 'd':
                    inProfile = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}



























