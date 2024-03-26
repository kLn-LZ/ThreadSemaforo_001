package Controller;

import java.util.concurrent.Semaphore;

public class ThreadTransaction extends Thread {

    private int idThread;
    private Semaphore semaforo;
    public ThreadTransaction(int idThread, Semaphore semaforo) {
            this.idThread = idThread;
            this.semaforo = semaforo;
    }

    public void run() {

        calc();

    }

    private void calc () {
        int tempo;

        if(idThread % 3 == 1){
            for(int i = 0; i < 1; i ++) {
                tempo = (int) ((Math.random() * 1001) + 200);
                try {
                    sleep(tempo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Tempo de cálculo da thread #" + idThread + " foi de " + (double) tempo / 1000 + " Segundos");

                try {
                    semaforo.acquire();
                    transaction(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    semaforo.release();
                }
            }
        }
        else if(idThread % 3 == 2) {
            for(int i = 0; i < 2; i ++) {
                tempo = (int) ((Math.random() * 1501) + 500);
                try {
                    sleep(tempo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Tempo de cálculo da thread #" + idThread + " foi de " + (double) tempo / 1000 + " Segundos");
                try {
                    semaforo.acquire();
                    transaction(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    semaforo.release();
                }
            }


        }
        else if(idThread % 3 == 0) {
            for(int i = 0; i < 2; i ++) {
                tempo = (int) ((Math.random() * 2001) + 1000);

                System.out.println("Começando o processamento dos cálculos da thread #" + idThread);
                try {
                    sleep(tempo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Tempo de cálculo da thread #" + idThread + " foi de " + (double) tempo / 1000 + " Segundos");
                try {
                    semaforo.acquire();
                    transaction(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    semaforo.release();
                }
            }

        }

    }

    private void transaction (double tempo) {

        System.out.println("Transação da thread #" + idThread + " começando");
        try {
            sleep((int) tempo);
            System.out.println("Transação da thread #" + idThread + " finalizada");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tempo de transação para o Banco de dados da thread #" + idThread + " foi de " +  tempo / 1000 + " Segundos");



    }



}
