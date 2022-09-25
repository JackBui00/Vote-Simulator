package main.java;

class Main{
    public static void main(String[] args) {
        System.out.println("Hello World");


        Student student = new Student();
        student.setName("James");
        student.setStudentID(12345);
        System.out.println(student.Name());
    }
}