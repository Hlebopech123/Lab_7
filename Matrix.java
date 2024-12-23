public class Matrix {
    private static final int ROWS = 3; // Количество строк
    private static final int COLS = 3; // Количество столбцов
    private static int[][] matrix = {
            {1, 2, 3,},
            {5, 6, 7,},
            {9, 10, 11,}
    };

    public static void main(String[] args) {
        Thread[] threads = new Thread[ROWS];
        int[] maxElements = new int[ROWS];

        for (int i = 0; i < ROWS; i++) {
            final int rowIndex = i;
            threads[i] = new Thread(() -> {
                int maxInRow = Integer.MIN_VALUE;
                for (int j = 0; j < COLS; j++) {
                    if (matrix[rowIndex][j] > maxInRow) {
                        maxInRow = matrix[rowIndex][j];
                    }
                }
                maxElements[rowIndex] = maxInRow;
            });
            threads[i].start(); // Запуск потока
        }

        // Ожидание завершения всех потоков
        for (int i = 0; i < ROWS; i++) {
            try {
                threads[i].join(); // Ожидание завершения потока
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Поиск максимального элемента среди максимальных элементов строк
        int overallMax = Integer.MIN_VALUE;
        for (int max : maxElements) {
            if (max > overallMax) {
                overallMax = max;
            }
        }

        System.out.println("Наибольший элемент в матрице: " + overallMax);
    }
}