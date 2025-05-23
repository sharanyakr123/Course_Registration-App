package com.example.CourseRegisteration;

public class LibraryModelClass {

    Integer id;
    String Name;
    String Course;
    String Fees;

    public LibraryModelClass(String Name, String Course, String Fees) {
        this.Name = Name;
        this.Course = Course;
        this.Fees = Fees;
    }


    public LibraryModelClass(Integer id, String Name, String Course, String Fees) {
        this.id = id;
        this.Name = Name;
        this.Course = Course;
        this.Fees = Fees;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        Name = Name;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String Course) {
        Course = Course;
    }

    public String getFees() {
        return Fees;
    }
}
