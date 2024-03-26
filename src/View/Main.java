package View;


import Controller.ThreadTransaction;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        int permissoes = 1;
        Semaphore semaforo = new Semaphore(permissoes);

        for(int idThread = 1; idThread <= 21; idThread++) {
            Thread tThread = new ThreadTransaction(idThread, semaforo);
            tThread.start();

        }

    }
}
