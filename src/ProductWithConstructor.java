public class ProductWithConstructor extends Product {
    // Определение конструктора класса с параметрами name, category и internalCode
    public ProductWithConstructor(String name, String category, byte[] internalCode) {
        // Вызов конструктора суперкласса Product
        super();
        // Установка значений полей через методы установки (setter methods)
        setName(name);
        setCategory(category);
        setInternalCode(internalCode);
    }
}
