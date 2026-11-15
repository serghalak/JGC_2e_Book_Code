package org.jgcbook.chapter03.C_a_fruity_example;
import java.util.*;


public class MyTest {
    public static void main(String[] args) {
        Apple a1 = new Apple(1);
        Apple a2 = new Apple(2);
        Orange o3 = new Orange(3);
        Orange o4 = new Orange(4);

        List<Apple> apples = Arrays.asList(a1, a2);
        // Працює! Collections.max повертає Apple (а2)
        assert Collections.max(apples).equals(a2);

        List<Orange> oranges = Arrays.asList(o3, o4);
        // Працює! Collections.max повертає Orange (o4)
        assert Collections.max(oranges).equals(o4);

        List<Fruit> mixed = List.of(a1, o3);
        // ТЕПЕР СКОМПІЛЮЄТЬСЯ І ПРАЦЮЄ!
        // Collections.max(mixed) поверне об'єкт Orange (o3), бо його розмір 3 > 1
        Fruit maxFruit = Collections.max(mixed);
        assert maxFruit.equals(o3);

        System.out.println("Все асерти успішно пройшли!");
    }
}



    // 1. Додаємо implements Comparable<Fruit>
    abstract class Fruit implements Comparable<Fruit> {
        protected String name;
        protected int size;

        protected Fruit(String name, int size) {
            this.name = Objects.requireNonNull(name);
            this.size = size;
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (o instanceof Fruit that) {
                return this.name.equals(that.name) && this.size == that.size;
            } else return false;
        }

        public int hashCode() {
            return Objects.hash(name, size);
        }

        // 2. Змінюємо на public
        @Override
        public int compareTo(Fruit that) {
            return Integer.compare(this.size, that.size);
        }
    }

    // 3. Прибираємо специфічні compareTo з наслідників
    class Apple extends Fruit {
        public Apple(int size) { super("Apple", size); }
    }

    class Orange extends Fruit {
        public Orange(int size) { super("Orange", size); }
    }


