package CASINO.JUGADORES;

import CASINO.CASA.Banca;
import CASINO.CASA.Ruleta;
import java.util.Random;

public class Martingala extends Thread {
    private int saldo = 1000;
    private Ruleta ruleta;
    private Banca banca;

    public Martingala(String nombre, Ruleta ruleta, Banca banca) {
        setName(nombre);
        this.ruleta = ruleta;
        this.banca = banca;
    }

    @Override
    public void run() {

        // número aleatorio entre 1 y 36
        int numeroElegido = new Random().nextInt(36) + 1;

        int apuesta = 10;

        while (saldo >= apuesta && banca.getSaldo() > 0) {
            // estamos la apuesta al saldo del jugador
            saldo = saldo - apuesta;
            banca.sumarDinero(apuesta);

            System.out.println("ESTRATEGIA DE APUESTA MARTINGALA \n  " + getName() + " apuesta " + apuesta  + " € al número " + numeroElegido + "\n");

            // Gira la ruleta
            ruleta.esperarResultado();

            // Comprobamos si hemos ganado
            int ganador = ruleta.getNumeroGanador();

            if (ganador == numeroElegido) {

                // Calculamos el premio según la cantidad apostada
                int premio = apuesta * 36;

                if (banca.getSaldo() >= premio) {
                    banca.restarDinero(premio);
                    saldo = saldo + premio;

                    System.out.println("ESTRATEGIA DE APUESTA MARTINGALA \n  " + getName() + " GANA " + premio  + " €. Saldo actual: " + saldo + " €\n");

                    // volvemos a apostar 10 € SOLO SI GANAMOS
                    apuesta = 10;

                } else {
                    System.out.println("LA BANCA NO PUEDE PAGAR A "
                            + getName() + ". ¡FIN DEL JUEGO!");

                    banca.setSaldo(0);
                }

            } else {
                System.out.println("ESTRATEGIA DE APUESTA MARTINGALA \n" + getName() + " pierde. Saldo actual: " + saldo + " € \n");

                // Si perdemos, duplicamos la siguiente apuesta
                apuesta = apuesta * 2;
            }

            // Esperar al siguiente turno del crupier mientras que se cumplan las condiciones de arriba
            // Thread.sleep(600);
            // Como he implementado un nuevo metodo para esperar tunro sustituimos el Thread.sleep
            ruleta.esperarNuevoTurno();
        }
    }
}
