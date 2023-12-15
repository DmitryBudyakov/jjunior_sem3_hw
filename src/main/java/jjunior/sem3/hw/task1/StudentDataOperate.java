package jjunior.sem3.hw.task1;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDataOperate {

    public static final String FILE_BIN = "students.bin";

    public static final String FILE_JSON = "students.json";

    public static final String FILE_XML = "students.xml";

    // для json-сериализации
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // для xml-сериализации
    private static final XmlMapper xmlMapper = new XmlMapper();

    /**
     * Сериализует список студентов в файл
     *
     * @param fileName имя фала
     * @param students список объектов Student
     */
    public static void saveToFile(String fileName, List<Student> students) {
        System.out.print("\nНачало сериализации... ");
        try {
            if (fileName.endsWith(".bin")) {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
                oos.writeObject(students);
                System.out.println("файл '" + FILE_BIN + "' сохранен!");
            } else if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), students);
                System.out.println("файл '" + FILE_JSON + "' сохранен!");
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                xmlMapper.writeValue(new File(fileName), students);
                System.out.println("файл '" + FILE_XML + "' сохранен!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Десериализует список студентов из файла
     *
     * @param fileName имя файла
     * @return возвращает список объектов Student
     */
    public static List<Student> loadFromFile(String fileName) {
        List<Student> students = new ArrayList<>();
        System.out.print("Начало десериализации... ");
        try {
            if (fileName.endsWith(".bin")) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
                students = (List<Student>) ois.readObject();
                System.out.println("загрузка из файла '" + FILE_BIN + "' выполнена!");
            } else if (fileName.endsWith(".json")) {
                students = objectMapper.readValue(new File(fileName), objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, Student.class));
                System.out.println("загрузка из файла '" + FILE_JSON + "' выполнена!");
            } else if (fileName.endsWith(".xml")) {
                students = xmlMapper.readValue(new File(fileName), xmlMapper.getTypeFactory()
                        .constructCollectionType(List.class, Student.class));
                System.out.println("загрузка из файла '" + FILE_XML + "' выполнена!");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }

    /**
     * Вывод в консоль списка студентов
     *
     * @param students список
     */
    public static void readStudentsList(List<Student> students) {
        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append("Студент: ").append(student.getName())
                    .append(", возраст: ").append(student.getAge())
                    .append(", ср.балл(GPA): ").append(student.getGPA()).append("\n");
        }
        System.out.println(sb);
    }

}
