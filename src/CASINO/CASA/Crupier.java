package CASINO.CASA;

public class Crupier extends Thread {
    private Ruleta ruleta;
    private Banca banca;

    public Crupier(Ruleta ruleta, Banca banca) {
        this.ruleta = ruleta;
        this.banca = banca;
    }

    @Override
    public void run() {
        // Mientras la banca tenga dinero el juego sigue
        while (banca.getSaldo() > 0) {
            try {

                ruleta.nuevoTurno();
                Thread.sleep(3000);
                ruleta.girarRuleta();
                Thread.sleep(1000);
                ruleta.nuevoTurno();
                System.out.println("SALDO DE LA BANCA");
                System.out.println("Saldo actual de la banca: " + banca.getSaldo() + " €\n");
                // Es necesario ya que hace que los jugadores no se queden atrapados en el wait
                // Comprueben si han ganado y volver a apostar

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("La banca se ha quedado sin fondos. \n FIN DEL JUEGO");
    }
}