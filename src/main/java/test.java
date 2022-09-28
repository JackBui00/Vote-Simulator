package main.java;

import java.util.Random;


class Main{
    public static void main(String[] args) { 
        Random rand = new Random(); 

        int students = rand.nextInt(25) + 1; 

        int questions = rand.nextInt(5) + 1;

        int multiQuestions = rand.nextInt(5) + 1;

        //loop amount of random questions 
        for (int i = 0; i < questions; i++){
            SimulationDriver run = new SimulationDriver(students, questions,multiQuestions); 
            int[] xsize = run.createAnswerQuestion(questions);
            run.displayQuestionAnswer(xsize, students);
        }
        //loop amount of random questions 
        for (int i = 0; i < multiQuestions; i++){
            SimulationDriver run = new SimulationDriver(students, questions,multiQuestions); 
            int[] xsize = run.createAnswerMultiQuestion(questions);
            run.displayQuestionAnswer(xsize, students);
        }
        
    }



}