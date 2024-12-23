public class SumCalculator {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Переменные для хранения результатов сумм
        final int[] sumResults = new int[2];

        // Создаем два потока для вычисления суммы по половинкам
        Thread thread1 = new Thread(() -> {
            int sum1 = 0;
            for (int i = 0; i < array.length / 2; i++) {
                sum1 += array[i];
            }
            sumResults[0] = sum1; // Сохраняем результат в массив
            System.out.println("Сумма первой половины: " + sum1);
        });

        Thread thread2 = new Thread(() -> {
            int sum2 = 0;
            for (int i = array.length / 2; i < array.length; i++) {
                sum2 += array[i];
            }
            sumResults[1] = sum2; // Сохраняем результат в массив
            System.out.println("Сумма второй половины: " + sum2);
        });

        // Запускаем потоки
        thread1.start();
        thread2.start();

        // Ждем завершения потоков
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Вычисляем общую сумму
        int totalSum = sumResults[0] + sumResults[1];
        System.out.println("Общая сумма: " + totalSum);
    }
}