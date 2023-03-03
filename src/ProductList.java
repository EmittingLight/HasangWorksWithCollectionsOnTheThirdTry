import java.util.*;

public class ProductList {
    private List<Product> products; // список продуктов

    public ProductList(int numberOfProducts) { // конструктор класса
        Set<String> uniqueInternalCodes = new HashSet<>(); // множество для хранения уникальных внутренних кодов
        this.products = new ArrayList<>(numberOfProducts); // создание списка продуктов заданного размера
        while (uniqueInternalCodes.size() < numberOfProducts) { // пока размер множества меньше размера списка продуктов
            String internalCode = UUID.randomUUID().toString(); // создание случайного внутреннего кода
            if (uniqueInternalCodes.add(internalCode)) { // если множество не содержит сгенерированный код
                Product product = new Product(); // создание нового продукта
                product.setName("Product" + uniqueInternalCodes.size()); // установка имени продукта
                product.setCategory("Category" + uniqueInternalCodes.size() % 10); // установка категории продукта
                product.setInternalCode(internalCode.getBytes()); // установка внутреннего кода продукта
                products.add(product); // добавление продукта в список
            }
        }
    }

    public List<Product> getProducts() { // получение списка продуктов
        return products;
    }

    public int size() { // получение размера списка продуктов
        return products.size();
    }

    public void add(Product product) { // добавление продукта в список
        products.add(product);
    }

    public Product get(int index) { // получение продукта по индексу
        return products.get(index);
    }

    public List<Product> findDuplicates(ProductList otherProducts) { // поиск дубликатов продуктов в двух списках
        List<Product> duplicates = new ArrayList<>(); // создание списка для хранения дубликатов
        for (Product product1 : products) { // перебор продуктов из первого списка
            for (Product product2 : otherProducts.getProducts()) { // перебор продуктов из второго списка
                if (product1.getName().equals(product2.getName()) // если имена продуктов равны
                        && product1.getCategory().equals(product2.getCategory()) // и категории продуктов равны
                        && Arrays.equals(product1.getInternalCode(), product2.getInternalCode())) { // и внутренние коды продуктов равны
                    duplicates.add(product1); // добавление продукта в список дубликатов
                    break; // прерывание цикла
                }
            }
        }
        return duplicates; // возврат списка дубликатов
    }

    public List<Product> findDuplicatesFast(ProductList otherProducts) { // поиск дубликатов продуктов в двух списках с использованием хэш-таблицы
        Set<String> codes = new HashSet<>(); // создание множества для хранения внутренних кодов продуктов из первого списка
        for (Product product : products) { // перебор продуктов в первом списке
            codes.add(new String(product.getInternalCode())); // добавление внутреннего кода текущего продукта в множество
        }
        List<Product> duplicates = new ArrayList<>(); // создание списка для хранения найденных дубликатов
        for (Product product : otherProducts.getProducts()) { // перебор продуктов во втором списке
            if (codes.contains(new String(product.getInternalCode()))) { // проверка, содержит ли множество внутренний код текущего продукта из второго списка
                duplicates.add(product); // добавление продукта в список дубликатов, если он найден
            }
        }
        return duplicates; // возврат списка дубликатов
    }
}