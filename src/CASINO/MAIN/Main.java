package CASINO.MAIN;

import CASINO.CASA.Banca;
import CASINO.CASA.Ruleta;
import CASINO.CASA.Crupier;
import CASINO.JUGADORES.ApNumero;
import CASINO.JUGADORES.Par_Impar;
import CASINO.JUGADORES.Martingala;

public class Main {

    public static void main(String[] args) {

        Banca banca = new Banca(50000);
        Ruleta ruleta = new Ruleta();

        // 4 jugadores que apuestan a un número
        ApNumero j1 = new ApNumero("Miguel", ruleta, banca);
        ApNumero j2 = new ApNumero("Sergio", ruleta, banca);
        ApNumero j3 = new ApNumero("Hugo", ruleta, banca);
        ApNumero j4 = new ApNumero("Jesus", ruleta, banca);

        // 4 jugadores que apuestan a par o impar
        Par_Impar j5 = new Par_Impar("Roberto", ruleta, banca);
        Par_Impar j6 = new Par_Impar("Joel", ruleta, banca);
        Par_Impar j7 = new Par_Impar("Víctor", ruleta, banca);
        Par_Impar j8 = new Par_Impar("Javier", ruleta, banca);

        // 4 jugadores que utilizan Martingala
        Martingala j9 = new Martingala("Virginia", ruleta, banca);
        Martingala j10 = new Martingala("Eva", ruleta, banca);
        Martingala j11 = new Martingala("Juan Manuel", ruleta, banca);
        Martingala j12 = new Martingala("Beatriz", ruleta, banca);


        // Creamos el crupier
        Crupier crupier = new Crupier(ruleta, banca);

        // Arrancamos el crupier
        crupier.start();

        // Arrancamos los 12 jugadores
        j1.start();
        j2.start();
        j3.start();
        j4.start();


        j5.start();
        j6.start();
        j7.start();
        j8.start();


        j9.start();
        j10.start();
        j11.start();
        j12.start();


    }
}

