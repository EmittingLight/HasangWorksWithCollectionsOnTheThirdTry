import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Main {

    // Количество продуктов
    private static final int NUMBER_OF_PRODUCTS = 100000;

    public static void main(String[] args) {
        // Создаем два списка продуктов
        ProductList list1 = new ProductList(NUMBER_OF_PRODUCTS);
        ProductList list2 = new ProductList(NUMBER_OF_PRODUCTS - 1000);
        // Заполняем списки данными
        initData(list1, list2);
        // Выводим размеры списков
        System.out.println(list1.size() + " " + list2.size());

        // Измеряем время выполнения метода поиска дубликатов и количество найденных дубликатов
        Instant start1 = Instant.now();
        List<Product> duplicates1 = list1.findDuplicates(list2);
        Instant end1 = Instant.now();
        long timeElapsed1 = Duration.between(start1, end1).toMillis();

        // Измеряем время выполнения метода быстрого поиска дубликатов и количество найденных дубликатов
        Instant start2 = Instant.now();
        List<Product> duplicates2 = list1.findDuplicatesFast(list2);
        Instant end2 = Instant.now();
        long timeElapsed2 = Duration.between(start2, end2).toMillis();

        // Выводим результаты
        System.out.println("Метод поиска дубликатов():");
        System.out.println("  затраченное время: " + timeElapsed1 + " ms");
        System.out.println("  количество дубликатов: " + duplicates1.size());
        System.out.println("Метод быстрого поиска дубликатов");
        System.out.println("  затраченное время: " + timeElapsed2 + " ms");
        System.out.println("  количество дубликатов: " + duplicates2.size());
    }

    // Заполняем списки данными
    public static void initData(ProductList list1, ProductList list2) {
        while (list1.size() < NUMBER_OF_PRODUCTS) {
            // Создаем случайный код для каждого продукта
            String internalCode = UUID.randomUUID().toString();
            // Создаем продукт с помощью конструктора и добавляем в первый список
            Product product = new ProductWithConstructor("Product" + (list1.size() + 1),
                    "Category" + ((list1.size() + 1) % 10), internalCode.getBytes());
            list1.add(product);
            // Если количество продуктов в первом списке меньше, чем NUMBER_OF_PRODUCTS - 1000, то добавляем продукт во второй список
            if (list1.size() <= NUMBER_OF_PRODUCTS - 1000) {
                list2.add(product);
            }
        }
        // Добавляем первые 1000 продуктов из первого списка во второй список, чтобы получить дубликаты
        for (int i = 0; i < 1000; i++) {
            list2.add(list1.get(i));
        }
    }
}

