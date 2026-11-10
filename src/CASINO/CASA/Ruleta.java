package CASINO.CASA;

import java.util.Random;

public class Ruleta {
    private int numeroGanador = -1;
    private boolean apuestasAbiertas = true;

    // Los jugadores entran y se quedan bloqueados esperando a que acabe la apuesta actual para poder apostar ellos
    public synchronized void esperarResultado() {
        while (apuestasAbiertas == true) { // Comprueba ANTES de dormir MUY MUY IMPORTANTE NO BUCLE DO-WHILE
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Con este metodo el crupier genera el número y avisa a los jugadores cuando este está generado
    public synchronized void girarRuleta() {
        apuestasAbiertas = false; // Cierra el tiempo de apuestas

        numeroGanador = new Random().nextInt(37); // Generamos número aleatorio
        System.out.println("¡No va más! El número ganador es: " + numeroGanador);
        notifyAll();
   }

    // Los jugadores esperan aquí después de cobrar hasta que el crupier abra el nuevo turno //IMPLENTADO GRACIAS AL ERROR VISTO EN PANTALLA
    public synchronized void esperarNuevoTurno() {
        while (apuestasAbiertas == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void nuevoTurno() {
        apuestasAbiertas = true;
        notifyAll();
        System.out.println("APUESTAS ABIERTAS");
    }

    public synchronized int getNumeroGanador() {
        return numeroGanador;
    }

    public synchronized boolean isApuestasAbiertas() {
        return apuestasAbiertas;
    }
}
