package org.example.ElectiveSytem;

public class Course {
    private String courseName;
    private String courseCode;
    private int availableSeats;

    public Course(String courseName, String courseCode, int availableSeats) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.availableSeats = availableSeats;
    }

    public String getCourseName() { return courseName; }
    public String getCourseCode() { return courseCode; }
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int seats) { this.availableSeats = seats; }
}