import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

class Thread1 extends Thread {
    int multiplo;
    int[] lista;
    private AtomicInteger total;
    private AtomicInteger cont;

    public Thread1(int[] lista, AtomicInteger cont, int multiplo, AtomicInteger total){
        this.lista = lista;
        this.cont = cont;
        this.multiplo = multiplo;
        this.total = total;
    }
    public void run() {
        for (int i = 0; i < lista.length; i++) {
            if (this.lista[i] % this.multiplo == 0) {
                this.cont.incrementAndGet();
                this.total.incrementAndGet();
                System.out.println("Total" + this.total);
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        int tam = 5000000;
        int[] lista = new int[tam];
        AtomicInteger contador1 = new AtomicInteger(0);
        AtomicInteger contador2 = new AtomicInteger(0);
        AtomicInteger contador3 = new AtomicInteger(0);
        AtomicInteger contador4 = new AtomicInteger(0);
        AtomicInteger contador5 = new AtomicInteger(0);
        AtomicInteger total = new AtomicInteger(0);

        for(int i = 0; i < tam; i++) {
            lista[i] = i;
        }

        Thread1 thread1 = new Thread1(Arrays.copyOfRange(lista, 0, 999999), contador1, 3, total);
        Thread1 thread2 = new Thread1(Arrays.copyOfRange(lista, 1000000, 1999999), contador2, 4, total);
        Thread1 thread3 = new Thread1(Arrays.copyOfRange(lista, 2000000, 2999999), contador3, 7, total);
        Thread1 thread4 = new Thread1(Arrays.copyOfRange(lista, 3000000, 3999999), contador4, 9, total);
        Thread1 thread5 = new Thread1(Arrays.copyOfRange(lista, 4000000, 4999999), contador5, 11, total);

        thread1.setName("Thread1");
        thread2.setName("Thread2");
        thread3.setName("Thread3");
        thread4.setName("Thread4");
        thread5.setName("Thread5");


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total na Thread1: " + contador1.get());
        System.out.println("Total na Thread2: " + contador2.get());
        System.out.println("Total na Thread3: " + contador3.get());
        System.out.println("Total na Thread4: " + contador4.get());
        System.out.println("Total na Thread5: " + contador5.get());
        System.out.println("Total das threads: " + total.get());
    }
}