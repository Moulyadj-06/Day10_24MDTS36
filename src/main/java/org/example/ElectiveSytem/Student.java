package org.example.ElectiveSytem;

public class Student {
    private String name;
    private String regNo;
    private String courseCode;

    public Student(String name, String regNo, String courseCode) {
        this.name = name;
        this.regNo = regNo;
        this.courseCode = courseCode;
    }

    public String getName() { return name; }
    public String getRegNo() { return regNo; }
    public String getCourseCode() { return courseCode; }
}