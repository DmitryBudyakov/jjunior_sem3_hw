package jjunior.sem3.hw.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 1. Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
 * Обеспечьте поддержку сериализации для этого класса.
 * Создайте объект класса Student и инициализируйте его данными.
 * Сериализуйте этот объект в файл.
 * Десериализуйте объект обратно в программу из файла.
 * Выведите все поля объекта, включая GPA, и обсудите, почему значение GPA не было сохранено/восстановлено.
 *
 * 2. Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).
 */
public class Program {
    public static void main(String[] args) {
        List<Student> students = initStudentList();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Исходный список студентов:");
        StudentDataOperate.readStudentsList(students);
        System.out.println(Student.hasTransient());

        while (true) {
            System.out.println("Выберите вариант сериализации/десериализации:");
            System.out.println("1. через BIN-файл");
            System.out.println("2. через JSON-файл");
            System.out.println("3. через XML-файл");
            System.out.println("4. Выход");
            System.out.print("Ваш выбор [1-4]: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    StudentDataOperate.saveToFile(StudentDataOperate.FILE_BIN, students);
                    students = StudentDataOperate.loadFromFile(StudentDataOperate.FILE_BIN);
                    break;
                case "2":
                    StudentDataOperate.saveToFile(StudentDataOperate.FILE_JSON, students);
                    students = StudentDataOperate.loadFromFile(StudentDataOperate.FILE_JSON);
                    break;
                case "3":
                    StudentDataOperate.saveToFile(StudentDataOperate.FILE_XML, students);
                    students = StudentDataOperate.loadFromFile(StudentDataOperate.FILE_XML);
                    break;
                case "4":
                    System.out.println("Пока!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Ошибка ввода. Повторите ввод.");
                    break;
            }
            System.out.println("Список студентов после десериализации:");
            StudentDataOperate.readStudentsList(students);
            System.out.println("Исходный список студентов:");
            StudentDataOperate.readStudentsList(initStudentList());
            System.out.println(Student.hasTransient());
        }

    }

    /**
     * Инициализация исходного списка студентов
     * @return список студентов
     */
    public static List<Student> initStudentList(){
        List<Student> students = new ArrayList<>();
        Student student1 = new Student("Дмитрий", 25, 4.5);
        Student student2 = new Student("Иван", 28, 4.9);
        Student student3 = new Student("Анна", 22, 4.75);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        return students;
    }


}
