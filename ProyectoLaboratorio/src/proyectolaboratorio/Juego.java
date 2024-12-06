/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectolaboratorio;

import java.util.Random;
import java.util.Scanner;

public class Juego {
    private Jugador jugadorActual;
    private Jugador oponente;
    private final char[][] tablero;
    private int dificultad; // 8: Normal, 4: Expert, 2: Genius
    private boolean modoAleatorio; // True: Aleatorio, False: Manual
    private int buenosJugador1, malosJugador1, buenosJugador2, malosJugador2;

    public Juego() {
        tablero = new char[6][6];
        dificultad = 8; // Default: Normal
        modoAleatorio = true; // Default: Aleatorio
        inicializarTablero();
    }

    // Configuración de jugadores
    public void setJugador1(Jugador jugador1) {
        this.jugadorActual = jugador1;
        buenosJugador1 = dificultad / 2;
        malosJugador1 = dificultad / 2;
    }

    public boolean setJugador2(Jugador jugador2) {
        if (jugador2 != null && !jugador2.getUsername().equals(jugadorActual.getUsername())) {
            this.oponente = jugador2;
            buenosJugador2 = dificultad / 2;
            malosJugador2 = dificultad / 2;
            return true;
        }
        return false;
    }

    public Jugador getJugador1() {
        return jugadorActual;
    }

    // Configuración del juego
    public void configurarDificultad(int nivel) {
        if (nivel == 1) dificultad = 8; // Normal
        else if (nivel == 2) dificultad = 4; // Expert
        else if (nivel == 3) dificultad = 2; // Genius
        System.out.println("Dificultad configurada: " + (nivel == 1 ? "Normal" : nivel == 2 ? "Expert" : "Genius"));
    }

    public void configurarModoJuego(boolean aleatorio) {
        this.modoAleatorio = aleatorio;
        System.out.println("Modo de juego configurado: " + (aleatorio ? "Aleatorio" : "Manual"));
    }

    // Inicializar tablero
    private void inicializarTablero() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    // Colocar fantasmas
    public void colocarFantasmas() {
        if (modoAleatorio) {
            colocarFantasmasAleatorios();
        } else {
            colocarFantasmasManuales();
        }
    }

    private void colocarFantasmasAleatorios() {
        Random random = new Random();
        for (int i = 0; i < dificultad; i++) {
            while (true) {
                int fila = random.nextInt(2); // Jugador 1: primeras 2 filas
                int columna = random.nextInt(6);
                if (tablero[fila][columna] == '-') {
                    tablero[fila][columna] = (i < dificultad / 2) ? 'B' : 'M';
                    break;
                }
            }
            while (true) {
                int fila = 4 + random.nextInt(2); // Jugador 2: últimas 2 filas
                int columna = random.nextInt(6);
                if (tablero[fila][columna] == '-') {
                    tablero[fila][columna] = (i < dificultad / 2) ? 'b' : 'm';
                    break;
                }
            }
        }
        System.out.println("Fantasmas colocados aleatoriamente.");
    }

    private void colocarFantasmasManuales() {
        Scanner datos = new Scanner(System.in);
        System.out.println("Jugador 1: Coloque sus fantasmas (8 en total).");
        for (int i = 0; i < dificultad; i++) {
            System.out.println("Fantasma " + (i + 1) + ":");
            System.out.print("Ingrese fila (0-1): ");
            int fila = datos.nextInt();
            System.out.print("Ingrese columna (0-5): ");
            int columna = datos.nextInt();
            if (esPosicionValida(fila, columna)) {
                tablero[fila][columna] = (i < dificultad / 2) ? 'B' : 'M';
            } else {
                System.out.println("Posición inválida. Intente de nuevo.");
                i--; // Reintentar
            }
        }
        System.out.println("Jugador 2: Coloque sus fantasmas (8 en total).");
        for (int i = 0; i < dificultad; i++) {
            System.out.println("Fantasma " + (i + 1) + ":");
            System.out.print("Ingrese fila (4-5): ");
            int fila = datos.nextInt();
            System.out.print("Ingrese columna (0-5): ");
            int columna = datos.nextInt();
            if (esPosicionValida(fila, columna)) {
                tablero[fila][columna] = (i < dificultad / 2) ? 'b' : 'm';
            } else {
                System.out.println("Posición invalida. Intente de nuevo.");
                i--; // Reintentar
            }
        }
    }

    private boolean esPosicionValida(int fila, int columna) {
        return fila >= 0 && fila < 6 && columna >= 0 && columna < 6 && tablero[fila][columna] == '-';
    }

    // Jugar juego
    public void jugarJuego() {
         inicializarTablero();
    colocarFantasmas();

    boolean juegoActivo = true;
    boolean turnoJugador1 = true; //  el jugador 1 empieza

    Scanner scanner = new Scanner(System.in);

    while (juegoActivo) {
        mostrarTablero(turnoJugador1); // Mostrar el tablero según el turno actual
        Jugador jugador = turnoJugador1 ? jugadorActual : oponente;

        System.out.println("Turno de " + jugador.getUsername());

        int filaOrigen, columnaOrigen, filaDestino, columnaDestino;

        
        do {
            System.out.print("Ingrese la fila de origen del fantasma que desea mover (0-5): ");
            filaOrigen = scanner.nextInt();
            System.out.print("Ingrese la columna de origen del fantasma que desea mover (0-5): ");
            columnaOrigen = scanner.nextInt();

            if (!esCoordenadaValida(filaOrigen, columnaOrigen, turnoJugador1)) {
                System.out.println("Coordenadas invalidas o no pertenecen a un fantasma tuyo. Intente de nuevo.");
            }
        } while (!esCoordenadaValida(filaOrigen, columnaOrigen, turnoJugador1));

        
        do {
            System.out.print("Ingrese la fila de destino (0-5 o -1 para salir): ");
            filaDestino = scanner.nextInt();
            System.out.print("Ingrese la columna de destino (0-5 o -1 para salir): ");
            columnaDestino = scanner.nextInt();

            if (filaDestino == -1 && columnaDestino == -1) {
                System.out.println(jugador.getUsername() + " se retira. El otro jugador gana");
                juegoActivo = false;
                return; 
            }

            if (!esMovimientoValido(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {
                System.out.println("Movimiento inválido. Intente de nuevo.");
            }
        } while (!esMovimientoValido(filaOrigen, columnaOrigen, filaDestino, columnaDestino));

        // Realizar el movimiento
        moverFantasma(filaOrigen, columnaOrigen, filaDestino, columnaDestino, turnoJugador1);

        // Verificar victoria
        if (checarVictoria()) {
            System.out.println("¡Victoria para " + jugador.getUsername() + "!");
            juegoActivo = false;
        } else {
            turnoJugador1 = !turnoJugador1; // Cambiar el turno
        }
    }
}

private boolean esCoordenadaValida(int fila, int columna, boolean turnoJugador1) {
    if (fila < 0 || fila >= 6 || columna < 0 || columna >= 6) {
        return false; // Fuera de rango
    }

    char casilla = tablero[fila][columna];

    // Verificar si pertenece al jugador en turno
    if ((turnoJugador1 && (casilla == 'b' || casilla == 'm')) || 
        (!turnoJugador1 && (casilla == 'B' || casilla == 'M'))) {
        return true;
    }

    return false;
}

    private boolean esMovimientoValido(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        return filaDestino >= 0 && filaDestino < 6 && columnaDestino >= 0 && columnaDestino < 6 &&
                Math.abs(filaDestino - filaOrigen) <= 1 && Math.abs(columnaDestino - columnaOrigen) <= 1 &&
                tablero[filaDestino][columnaDestino] == '-';
    }

    private boolean esFantasmaContrario(int fila, int columna, boolean turnoJugador1) {
        char fantasma = tablero[fila][columna];
        return (turnoJugador1 && (fantasma == 'b' || fantasma == 'm')) || (!turnoJugador1 && (fantasma == 'B' || fantasma == 'M'));
    }

    private void capturarFantasma(char fantasma, boolean turnoJugador1) {
        if (turnoJugador1) {
            if (fantasma == 'b') buenosJugador2--;
            else malosJugador2--;
        } else {
            if (fantasma == 'B') buenosJugador1--;
            else malosJugador1--;
        }
    }

    private boolean checarVictoria() {
        return buenosJugador1 == 0 || malosJugador1 == 0 || buenosJugador2 == 0 || malosJugador2 == 0;
    }

    // Mostrar tablero
    public void mostrarTablero(boolean turnoJugador1) {
       System.out.println("\nTablero actual:");
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            char casilla = tablero[i][j];
            

            // Mostrar fantasmas propios del jugador en turno
            if ((turnoJugador1 && (casilla == 'b' || casilla == 'm')) || 
                (!turnoJugador1 && (casilla == 'B' || casilla == 'M'))) {
                System.out.print(casilla + " "); // Mostrar el tipo real del fantasma propio
            }
            // Tapar los fantasmas del oponente
            else if ((turnoJugador1 && (casilla == 'B' || casilla == 'M')) || 
                     (!turnoJugador1 && (casilla == 'b' || casilla == 'm'))) {
                System.out.print("X "); // Mostrar 'X' para los fantasmas del oponente
            } else {
                // Mostrar casillas vacías
                System.out.print(casilla + " ");
            }
        }
        System.out.println();
    }
}



void mostrarReportesJugadorActual() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void mostrarRanking() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void registrarResultado(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    private void moverFantasma(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, boolean turnoJugador1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }










}
    