package training.entities;

import training.utils.Validator;

import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private String code;
    private String name;
    private boolean status;
    private short duration;
    private String flag;

    public Course() {
    }

    public Course(String code, String name, boolean status, short duration, String flag) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.duration = duration;
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public short getDuration() {
        return duration;
    }

    public void setDuration(short duration) {
        this.duration = duration;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void input(Scanner sc, ArrayList<Course> courses) {
        while (true) {
            System.out.print("Enter code (RAxxx): ");
            String code = sc.nextLine();
            if (Validator.validateCode(code) && !Validator.isDulicateCode(code, courses)) {
                this.code = code;
                break;
            }

            System.out.println("Invalid or duplicated code!");
        }

        System.out.print("Enter name: ");
        name = sc.nextLine();

        while (true) {
            System.out.print("Enter status(1: active/0: inactive): ");
            String status = sc.nextLine();
            if (Validator.validateStatus(status)) {
                this.status = Boolean.parseBoolean(status);
                break;
            }

            System.out.println("Invalid status!");
        }

        while (true) {
            try {
                System.out.print("Enter duration(>0): ");
                short duration = Short.parseShort(sc.nextLine());
                if (Validator.validateDuration(duration)) {
                    this.duration = duration;
                    break;
                }
            }
            catch (Exception e) {
                System.out.println("Invalid number!");
            }
        }

        while (true) {
            System.out.print("Enter flag (optional, prerequisite, N/A): ");
            String flag = sc.nextLine();
            if (Validator.validateFlag(flag)) {
                this.flag = flag;
                break;
            }

            System.out.println("Invalid flag!");
        }
    }

    @Override
    public String toString() {
        return "Course{" + "code='" + code + '\'' + ", name='" + name + '\'' + ", status=" + status + ", duration=" + duration + ", flag='" + flag + '\'' + '}';
    }
}
