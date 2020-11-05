package ru.anfy.java3.lesson2;

import java.io.*;
import java.sql.*;

public class Main {
    private static Connection conn;
    private static Statement stmt;
    private static final int STUDENTS_TABLE_COLUMNS_COUNT = 3;

    public static void main(String[] args) {
        connect();
        createTables();

        deleteStudents("%");

        try {
            File file = new File("test.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] lineArr = line.split(" ");
                if(lineArr == null || lineArr.length < STUDENTS_TABLE_COLUMNS_COUNT) continue;
                addStudent(lineArr[1], Integer.parseInt(lineArr[2]));
                System.out.println(line + " has been added");
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        disconnect();

    }

    private static void createTables(){
        try {
            stmt.executeUpdate("CREATE TABLE If NOT EXISTS Students (\n" +
                    "    ID    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    NAME  TEXT,\n" +
                    "    SCORE INTEGER\n" +
                    ");\n");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void dropTable(String name){
        try {
            stmt.executeUpdate(String.format("DROP TABLE IF EXISTS '%s'", name));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void addStudent(String name, int score){
        try {
            stmt.executeUpdate(String.format("INSERT INTO students (name, score) VALUES ('%s', %d)", name, score));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static ResultSet getStudents(String name){
        try {
            return stmt.executeQuery(String.format("SELECT * FROM students WHERE name LIKE '%s';", name));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private static void deleteStudents(String name){
        try {
            stmt.executeUpdate(String.format("DELETE FROM students WHERE name LIKE '%s';", name));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:main.db");
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void disconnect() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
