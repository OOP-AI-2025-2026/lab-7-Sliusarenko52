package ua.opnu.lambda;

import java.util.*;
import java.util.function.*;

public class Main {

    // Завд. 1 - Predicate: перевірка, чи є число простим
    static Predicate<Integer> isPrime = (Integer n) -> {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    };

    // Клас Student
    static class Student {
        private String name;
        private String group;
        private Integer[] marks;

        public Student(String name, String group, Integer[] marks) {
            this.name = name;
            this.group = group;
            this.marks = marks;
        }

        public String getName() { return name; }
        public String getGroup() { return group; }
        public Integer[] getMarks() { return marks; }

        @Override
        public String toString() {
            return name + " (" + group + ") " + Arrays.toString(marks);
        }
    }

    // Завд. 2 - фільтрація студентів за предикатом
    static Student[] filter(Student[] students, Predicate<Student> p) {
        List<Student> res = new ArrayList<>();
        for (Student s : students) {
            if (p.test(s)) res.add(s);
        }
        return res.toArray(new Student[0]);
    }

    // Завд. 3 - фільтрація за двома умовами
    static <T> List<T> filterTwo(List<T> list, Predicate<T> p1, Predicate<T> p2) {
        List<T> res = new ArrayList<>();
        for (T el : list) {
            if (p1.and(p2).test(el)) res.add(el);
        }
        return res;
    }

    // Завд. 4 - consumer: вивід студентів
    static Consumer<Student> printFullName = s ->
            System.out.println(s.getName() + " " + s.getGroup());

    static void forEach(Student[] arr, Consumer<Student> c) {
        for (Student s : arr) c.accept(s);
    }

    // Завд. 5 - Predicate + Consumer
    static void doIf(Integer[] arr, Predicate<Integer> p, Consumer<Integer> c) {
        for (Integer i : arr) {
            if (p.test(i)) c.accept(i);
        }
    }

    // Завд. 6 - Function: обчислення 2^n
    static Function<Integer, Integer> powTwo = (Integer n) -> (int) Math.pow(2, n);

    static Integer[] processArray(Integer[] arr, Function<Integer, Integer> f) {
        Integer[] res = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = f.apply(arr[i]);
        }
        return res;
    }

    // Завд. 7 - Function: перетворення числа у слово
    static String stringify(Integer[] arr, Function<Integer, String> f) {
        StringBuilder sb = new StringBuilder();
        for (Integer n : arr) {
            sb.append(f.apply(n)).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        // Завд. 1
        System.out.println("Завдання 1");
        for (int i = 0; i < 20; i++) {
            if (isPrime.test(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println("\n");

        // Завд. 2
        System.out.println("Завдання 2");
        Student[] arr = {
                new Student("Данило", "AI-245", new Integer[]{
                        90, 85, 70
                }),
                new Student("Іван", "AI-241", new Integer[]{
                        30, 50, 40
                }),
                new Student("Олена", "AI-243", new Integer[]{
                        100, 80, 95
                })
        };

        Predicate<Student> hasDebt = s -> {
            for (Integer m : s.getMarks())
                if (m < 60) return false;
            return true;
        };

        Student[] passed = filter(arr, hasDebt);
        for (Student s : passed) System.out.println(s);
        System.out.println();

        // Завд. 3
        System.out.println("Завдання 3");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> more3 = n -> n > 3;
        System.out.println(filterTwo(nums, even, more3));
        System.out.println();

        // Завд. 4
        System.out.println("Завдання 4");
        forEach(arr, printFullName);
        System.out.println();

        // Завд. 5
        System.out.println("Завдання 5");
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Predicate<Integer> odd = n -> n % 2 != 0;
        Consumer<Integer> show = n -> System.out.println("Непарне: " + n);
        doIf(a, odd, show);
        System.out.println();

        // Завд. 6
        System.out.println("Завдання 6");
        Integer[] b = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(processArray(b, powTwo)));
        System.out.println();

        // Завд. 7
        System.out.println("Завдання 7");
        Function<Integer, String> numToStr = n -> {
            int value = n.intValue();
            switch (value) {
                case 0: return "нуль";
                case 1: return "один";
                case 2: return "два";
                case 3: return "три";
                case 4: return "чотири";
                case 5: return "п’ять";
                case 6: return "шість";
                case 7: return "сім";
                case 8: return "вісім";
                case 9: return "дев’ять";
                default: return "?";
            }
        };

        Integer[] d = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(stringify(d, numToStr));
    }
}
