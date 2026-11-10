package CASINO.JUGADORES;

import CASINO.CASA.Banca;
import CASINO.CASA.Ruleta;
import java.util.Random;

public class Par_Impar extends Thread {
    private int saldo = 1000;
    private Ruleta ruleta;
    private Banca banca;

    public Par_Impar(String nombre, Ruleta ruleta, Banca banca) {
        setName(nombre);
        this.ruleta = ruleta;
        this.banca = banca;
    }

    @Override
    public void run() {
        while (saldo >= 10 && banca.getSaldo() > 0) {
            // 1. Elegimos aleatoriamente PAR o IMPAR
            int numeroAleatorio = new Random().nextInt(36) + 1;
            boolean apuestaPar = (numeroAleatorio % 2 == 0);

            // 2. Pagamos los 10 € de la apuesta a la banca
            saldo = saldo - 10;
            banca.sumarDinero(10);

            if (apuestaPar == true) {
                System.out.println(getName() + " apuesta 10 € a PAR");
            } else {
                System.out.println(getName() + " apuesta 10 € a IMPAR");
            }

            // 3. Esperamos a que la ruleta gire
            ruleta.esperarResultado();

            // 4. Comprobamos si hemos ganado
            int ganador = ruleta.getNumeroGanador();

            if (ganador != 0) {

                if (apuestaPar == true && ganador % 2 == 0) {
                    if (banca.getSaldo() >= 20) {
                        banca.restarDinero(20);
                        saldo = saldo + 20;
                        System.out.println("APUESTA PAR O IMPAR\n  " + getName() + " GANA 20 €. Saldo actual: " + saldo + " € \n");
                    } else {
                        System.out.println("La banca cayó en BANCA ROTA  \n ¡FIN DEL JUEGO!\n");
                        banca.setSaldo(0);
                    }

                } else if (apuestaPar == false && ganador % 2 != 0) {
                    if (banca.getSaldo() >= 20) {
                        banca.restarDinero(20);
                        saldo = saldo + 20;
                        System.out.println("APUESTA PAR O IMPAR\n  " + getName() + " GANA 20 €. Saldo actual: " + saldo + " €\n");
                    } else {
                        System.out.println("La banca cayó en BANCA ROTA  \n ¡FIN DEL JUEGO!");
                        banca.setSaldo(0);
                    }
                } else {
                    System.out.println("APUESTA PAR O IMPAR\n " + getName() + " pierde. Saldo actual: " + saldo + " €\n");
                }
            } else {
                System.out.println("APUESTA PAR O IMPAR\n  " + getName() + " pierde porque ha salido el 0. Saldo actual: " + saldo + " €\n");
            }

            // Esperar al siguiente turno del crupier mientras que se cumplan las condiciones de arriba
            // Thread.sleep(600);
            // Como he implementado un nuevo metodo para esperar tunro sustituimos el Thread.sleep
            ruleta.esperarNuevoTurno();

        }
    }
}
