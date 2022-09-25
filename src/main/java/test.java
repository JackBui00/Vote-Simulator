package main.java;
import java.util.LinkedList;
import java.util.Queue;


class Main{
    public static void main(String[] args) {
        Queue<Object> queueClassroom = new LinkedList<>(); 
        


        //System.out.println("Hello World");

        multipleChoiceQuestion question = new multipleChoiceQuestion(); 

        question.addAnswer("A");
        String [] answers = question.getAnswers();
        for(int i = 0; i < answers.length; i++){
            System.out.println(answers[i]);
        }
        //System.out.println(question.getAnswers());
        
        Student student = new Student();
        System.out.println(student.getId());
        queueClassroom.add(student);
        //System.out.println(queueClassroom.peek());
        //Object test = queueClassroom.peek(); 
        
        //System.out.print(test);
        //System.out.println(queueClassroom.isEmpty());

    }
}