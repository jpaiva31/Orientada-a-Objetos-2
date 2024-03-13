import java.util.Random;

class ThreadCarro1 extends Thread {
    public void run() {
        Random random = new Random();
        Thread t = Thread.currentThread();
        int end = 10;
        for (int i = 1; i <= end; i++){
            try {
                int tempo = random.nextInt(501) + 500;
                Thread.sleep(tempo);
                System.out.println(t.getName() + " andou para a posicao " + i + " no tempo " + tempo);        
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        ThreadCarro1 thread1 = new ThreadCarro1();
        ThreadCarro1 thread2 = new ThreadCarro1();
        ThreadCarro1 thread3 = new ThreadCarro1();
        thread1.setName("Carro 1");
        thread2.setName("Carro 2");
        thread3.setName("Carro 3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

//todo: pegar o tempo da hora que ele chegou