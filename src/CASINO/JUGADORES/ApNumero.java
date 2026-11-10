package CASINO.JUGADORES;

import CASINO.CASA.Banca;
import CASINO.CASA.Ruleta;
import java.util.Random;

public class ApNumero extends Thread {
    private int saldo = 1000;
    private Ruleta ruleta;
    private Banca banca;

    public ApNumero(String nombre, Ruleta ruleta, Banca banca) {
        setName(nombre); // Asigna el nombre al hilo (Apuntes 7.4)
        this.ruleta = ruleta;
        this.banca = banca;
    }

    @Override
    public void run() {
        // El jugador juega mientras le queden al menos 10 € y la banca tenga dinero
        while (saldo >= 10 && banca.getSaldo() > 0) {
            int numeroElegido = new Random().nextInt(36) + 1 ; // Generamos número aleatorio IMPORTANTE NO SE PUEDE EL 0 POR ESO SUMAMOS +1
            saldo = saldo - 10;
            banca.sumarDinero(10);
            System.out.println("APUESTA POR UN NÚMERO\n  " + getName() + " apuesta 10 € al número " + numeroElegido);

            ruleta.esperarResultado(); // Esperamos a que la ruleta gire

            //  Comprobamos si hemos ganado

            int ganador = ruleta.getNumeroGanador();
            if (ganador == numeroElegido) {
                if (banca.getSaldo() >= 360) {
                    banca.restarDinero(360);
                    saldo = saldo + 360;
                    System.out.println("APUESTA POR UN NÚMERO\n  " + getName() + " GANA 360 €. Saldo actual: " + saldo + " €");
                } else {
                    System.out.println("LA BANCA NO PUEDE PAGAR A " + getName() + ". ¡FIN DEL JUEGO!");
                    banca.setSaldo(0); // Ponemos el saldo a 0 para que todos los bucles while terminen
                }
            } else {
                System.out.println("APUESTA POR UN NÚMERO\n  " + getName() + " pierde. Saldo actual: " + saldo + " €");
            }

            // Esperar al siguiente turno del crupier mientras que se cumplan las condiciones de arriba
            // Thread.sleep(600);
            // Como he implementado un nuevo metodo para esperar tunro sustituimos el Thread.sleep
            ruleta.esperarNuevoTurno();

        }
    }
}