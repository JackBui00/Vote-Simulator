
package main.java; 
import java.util.Random;

class Student{

    Random random = new Random(); 
    private String id = Integer.toString(random.nextInt(76437));
    private String answer; 

    public void setStudentID(String studentID){
        this.id = studentID; 
    }
    public String getId(){
        return this.id; 
    }

    public void choose(){
        //Choice an answer, incorporate the difference between a multiple choice and a single choice 
    }

}

class Question{
    private String question; 
    private String answer; 


    public String question(){
        return this.question;
    }

    public void addQuestion(String newQuestion){
        this.question = newQuestion; 
    }

    public String getAnswer(){
        return this.answer;
    }

    public void addAnswer(String newAnswer){
        this.answer = newAnswer; 
    }

    
}

class multipleChoiceQuestion extends Question{
    String[] answers = new String[0];



    public String[] getAnswers(){
        return this.answers;
    }

    public void addAnswer(String newAnswer){
        int size = answers.length; 
        String[] tempAnswers = new String[size + 1];
        for (int x = 0; x < size; x++){
            tempAnswers[x] = answers[x];
        }
        tempAnswers[size] = newAnswer;
        answers = tempAnswers; 
    }

}

class singleChoiceQuestion extends Question{

}
class VotingService{

}

class SimulationDriver{

}


