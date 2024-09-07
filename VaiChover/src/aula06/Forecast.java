package aula06;

import java.util.Random;

public class Forecast {

    private static String message;

    private static class ForecastThread implements Runnable {
        private Random r = new Random();

        public void run() {
            try {
                Thread.sleep(r.nextInt(2000));
            } catch (InterruptedException e) { }

            // AQUI
            message = "Hoje vai chover.";
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Random rnd = new Random();

        message = "Hoje não vai chover.";
        Thread forecastThread = new Thread(new ForecastThread());
        forecastThread.start();
        Thread.sleep(rnd.nextInt(1000));

        // AQUI
        forecastThread.join();

        // Como garantir que sempre haverá chuva?
        System.out.println(message);
    }
}
