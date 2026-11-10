package CASINO.CASA;

public class Banca {
    private int saldo = 50000;

    public Banca(int saldo) {
        this.saldo = saldo;
    }

    public synchronized void sumarDinero(int cantidad) {
        saldo = saldo + cantidad;
    }

    public synchronized void restarDinero(int cantidad) {
        saldo = saldo - cantidad;
    }

    public synchronized int getSaldo() {
        return saldo;
    }

    public synchronized void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
