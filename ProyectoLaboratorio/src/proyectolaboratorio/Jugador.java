/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectolaboratorio;

/**
 *
 * @author Usuario
 */
public class Jugador {
    
private String username;
    private String password;
    private int puntos;
    private String juego1 = "";
    private String juego2 = "";
    private String juego3 = "";
    private String juego4 = "";
    private String juego5 = "";
    private String juego6 = "";
    private String juego7 = "";
    private String juego8 = "";
    private String juego9 = "";
    private String juego10 = "";

    public Jugador(String username, String password) {
        this.username=username;
        this.password=password;
        this.puntos= 0;
    }

    public String getUsername() {
        return username;
    }

    public boolean comprobarPassword(String password) {
        return this.password.equals(password);
    }

    public void agregarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void Juegos(String log) {
        // Mantiene un registro de 10 partidas desplazando hacia atrás.
        juego10 = juego9;
        juego9 = juego8;
        juego8 = juego7;
        juego7 = juego6;
        juego6 = juego5;
        juego5 = juego4;
        juego4 = juego3;
        juego3 = juego2;
        juego2= juego1;
        juego1 = log;
    }

    public String getGameLogs() {
         return juego1 + "\n" + juego2 + "\n" + juego3 + "\n" + juego4 + "\n" + juego5 +
               "\n" + juego6 + "\n" + juego7 + "\n" + juego8 + "\n" + juego9 + "\n" + juego10;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    
@Override
    public String toString() {
        return "Username: " + username + ", Points: " + puntos;
       }



}
