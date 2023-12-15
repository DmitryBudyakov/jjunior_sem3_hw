package jjunior.sem3.hw.task1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Student implements Serializable {

    static final long serialVersionUID = 1L;

    /**
     * Имя
     */
    private String name;

    /**
     * Возраст
     */
    private int age;

    /**
     * Средний балл
     */
    @JsonIgnore
    private transient double GPA;

    public Student(){

    }

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    /**
     * Выводит все поля transient
     * @return
     */
    public static String hasTransient(){
        Class<Student> studentClass = Student.class;
        int count = 0;
        StringBuilder sb = new StringBuilder("--- Поля Transient ---\n");
        for (Field field: studentClass.getDeclaredFields()){
            if (Modifier.isTransient(field.getModifiers())){
                sb.append("Поле: ").append(field.getName()).append("\n");
                count++;
            }
        }
        if (count == 0){
            sb.append("нет полей Transient").append("\n");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @JsonIgnore
    public double getGPA() {
        return GPA;
    }

    @JsonIgnore
    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

}
