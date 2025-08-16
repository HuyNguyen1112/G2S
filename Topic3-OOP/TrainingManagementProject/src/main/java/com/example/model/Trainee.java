package com.example.model;

public class Trainee {
    private String id;
    private String name;
    private String gender;
    private byte age;

    public String getId() {
        return id;
    }

    public boolean setId(String id) {
        if (id.trim().isEmpty()) {
            System.out.println("ID không thể để trống!");
            return false;
        }
        //else if ()
        this.id = id;
        return true;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (name.trim().isEmpty()) {
            System.out.println("Tên không thể để trống!");
            return false;
        }
        this.name = name;
        return true;
    }

    public String getGender() {
        return gender;
    }

    public boolean setGender(String gender) {
        if (!"male".equals(gender) && !"female".equals(gender)) {
            System.out.println("Giới tính phải là \"male\" hoặc \"female\" !");
            return false;
        }
        this.gender = gender;
        return true;
    }

    public byte getAge() {
        return age;
    }

    public boolean setAge(byte age) {
        if (age < 6) {
            System.out.println("tuổi phải >=6!");
            return false;
        }
        this.age = age;
        return true;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "id='" + id + '\'' +
                ", 1.name='" + name + '\'' +
                ", 2.gender='" + gender + '\'' +
                ", 3.age=" + age +
                '}';
    }
}
