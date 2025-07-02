package org.example.ElectiveSytem;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
//import org.example.ElectiveSytem.Student;
//import org.example.ElectiveSytem.Course;

import java.util.Scanner;

public class ElectiveApp {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("ElectiveDB");
        MongoCollection<Document> studentColl = db.getCollection("RegisteredStudent");
        MongoCollection<Document> courseColl = db.getCollection("Courses");

        Scanner sc = new Scanner(System.in);
        int ch;

        do {
            System.out.println("\n1. Add Course\n2. View Courses\n3. Register Student\n4. View Students\n0. Exit\nOption: ");
            ch = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (ch) {
                case 1:
                    System.out.print("Course Name: ");
                    String cname = sc.nextLine();
                    System.out.print("Course Code: ");
                    String ccode = sc.nextLine();
                    System.out.print("Seats: ");
                    int seats = sc.nextInt();
                    courseColl.insertOne(new Document("courseName", cname)
                            .append("courseCode", ccode)
                            .append("availableSeats", seats));
                    System.out.println("Course added.");
                    break;

                case 2:
                    System.out.println("Available Courses:");
                    for (Document d : courseColl.find()) {
                        System.out.println(d.getString("courseName") + " - " +
                                d.getString("courseCode") + " (Seats: " + d.getInteger("availableSeats") + ")");
                    }
                    break;

                case 3:
                    System.out.print("Student Name: ");
                    String sname = sc.nextLine();
                    System.out.print("Reg No: ");
                    String reg = sc.nextLine();
                    System.out.print("Course Code: ");
                    String code = sc.nextLine();

                    Document course = courseColl.find(Filters.eq("courseCode", code)).first();
                    if (course != null && course.getInteger("availableSeats") > 0) {
                        studentColl.insertOne(new Document("name", sname).append("regNo", reg).append("courseCode", code));
                        courseColl.updateOne(Filters.eq("courseCode", code),
                                new Document("$inc", new Document("availableSeats", -1)));
                        System.out.println("Registration successful.");
                    } else {
                        System.out.println("Course full or not found.");
                    }
                    break;

                case 4:
                    System.out.println("Registered Students:");
                    for (Document d : studentColl.find()) {
                        System.out.println(d.getString("name") + " | " + d.getString("regNo") + " | " + d.getString("courseCode"));
                    }
                    break;
            }
        } while (ch != 0);

        sc.close();
        mongoClient.close();
    }
}
