import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WarehouseTransfer {

    private static final int MAX_WEIGHT = 150;

    public static void main(String[] args) {
        List<Integer> goodsWeights = List.of(10, 20, 30, 50, 30, 60, 60, 10, 20);
        transferGoods(goodsWeights);
    }

    public static void transferGoods(List<Integer> goodsWeights) {
        CompletableFuture<Void> future = CompletableFuture.completedFuture(null);

        for (int i = 0; i < goodsWeights.size(); i += 3) {
            final int start = i;
            future = future.thenRunAsync(() -> {
                int totalWeight = 0;
                List<Integer> currentBatch = new ArrayList<>();

                for (int j = start; j < start + 3 && j < goodsWeights.size(); j++) {
                    if (totalWeight + goodsWeights.get(j) <= MAX_WEIGHT) {
                        totalWeight += goodsWeights.get(j);
                        currentBatch.add(goodsWeights.get(j));
                    }
                }

                if (!currentBatch.isEmpty()) {
                    System.out.println("Грузчики переносят: " + currentBatch + " (Суммарный вес: " + totalWeight + " кг)");
                    unloadGoods(currentBatch);
                }
            });
        }

        // Ожидание завершения всех задач
        future.join();
        System.out.println("Все товары перенесены.");
    }

    private static void unloadGoods(List<Integer> goods) {
        // Логика разгрузки товаров на другом складе
        System.out.println("Товары " + goods + " разгружены на другом складе.");
    }
}