
package main.java; 
import java.util.*;  
import java.util.Queue;

class Student{
    public String answer;  // Answers 
    public String sid; 
    private List<String> tempchoices = Arrays.asList("a","b","c","d","e");
    private String [] choices; 
    //shuffle the choices to be picked 
    protected String[] shuffle(){
        Collections.shuffle(tempchoices);
        String [] choices = new String[tempchoices.size()];
        choices = tempchoices.toArray(choices); 
        return choices;
    }
    // grab choices 
    public String[] getChoices(){
        return this.choices; 
    }
    //grab ID if necessary 
    public Student(int id ){
            this.sid = Integer.toString(id); 
            this.choices = shuffle(); 
        }
        // change student Id if necessary 
    public void setStudentID(String studentID){
        this.sid = studentID; 
    }
    // get student ID 
    public String getId(){
        return this.sid; 
    }
    
    //grab answer
    public String chooseAnswer(String[] choices){
        answer = choices[0]; 
        return answer; 
        }
        
}
// creation of single choice master class 
class singleChoiceQuestion{
    private String question; 
    private String[] answer; 
    private List<String> tempchoices;

    public singleChoiceQuestion(){
        this.answer = new String[1];
        this.tempchoices = Arrays.asList("a","b","c","d","e");
    }
    // function to shuffle 
    protected String[] shuffle(){
        Collections.shuffle(tempchoices);
        String [] choices = new String[tempchoices.size()];
        choices = tempchoices.toArray(choices); 
        return choices;
    }
    //shuffle answer choices 
    public void createQuestion(){
        String[] choices = shuffle();
        addAnswer(choices[0]);
        
    }

    public String question(){
        return this.question;
    }
    //add string question if wanted 
    public void addQuestion(String newQuestion){
        this.question = newQuestion; 
    }
    //get answer 
    public String[] getAnswer(){
        return this.answer;
    }
    // change answer 
    public void addAnswer(String newAnswer){
        answer[0] = newAnswer; 
    }

    
}
// sub class based on the single choice, but with multiple outcomes 
class multipleChoiceQuestion extends singleChoiceQuestion{
    String[] answer = new String[0];
    List<String> tempchoices = Arrays.asList("a","b","c","d","e");

    public multipleChoiceQuestion(){
        this.answer = new String[0];
    }
    //creation of a question with correct answer 
    public void createQuestion(){
        String[] choices = shuffle();
        Random random = new Random();
        int correctAnswer = (random.nextInt(4)+1);
        for (int i = 0; i < correctAnswer; i++){
            addAnswer(choices[i]);
        }
        
    }
    //grab question answer/s 
    public String[] getAnswer(){
        return this.answer;
    }
    //edit answer if necessary 
    public void addAnswer(String newAnswer){
        int size = answer.length; 
        String[] tempAnswers = new String[size + 1];
        for (int x = 0; x < size; x++){
            tempAnswers[x] = answer[x];
        }
        tempAnswers[size] = newAnswer;
        answer = tempAnswers; 
    }
    
    

}
    //create queue of random amount of studnets 
class SimulationDriver{
    Queue<Student> queueClassroom = new LinkedList<>();
    int students; 
    int questions; 
    int multiQuestions;
    SimulationDriver(int students, int questions, int multiQuestions){
        this.students = students; 
        this.questions = questions; 
        this.multiQuestions = multiQuestions; 
        createClassroom();
    }

    private void createClassroom(){
        for(int i = 0; i< this.students; i++){
            Student student = new Student(5); 
            queueClassroom.add(student); 
        }
    }
    //grab student number 
    public int getStudents(){
        return this.students; 
    }
    // return question amount 
    public int getQuestions(){
        return this.questions; 
    }
    //return multi question amount 
    public int getMultiQuestions(){
        return this.multiQuestions; 
    }
    // get the queue of students 
    public Queue<Student> getQueue(){
        return queueClassroom; 
    }
    // create the single question and grab answers calling the vote 
    public int[] createAnswerQuestion(int questions){ 
        //System.out.println(i);
        System.out.print("NEW QUESTION, ONE ANSWER\n");
        VotingService voteCounter = new VotingService(queueClassroom);
        return voteCounter.getAnswersSingle(); 
    }

    // create multi question with answer sfor vote 
    public int[] createAnswerMultiQuestion(int questions){
        System.out.print("NEW QUESTION, Multi ANSWER\n");
        VotingService voteCounter = new VotingService(queueClassroom);
        return voteCounter.getAnswersMulti(); 
    }

    //display the statistics of the answers of students 
    public void displayQuestionAnswer(int [] array,int students){
        System.out.print("------------------------------------------------------\n\n");
        System.out.print("Student Count:"+ students + "\n"); 
        System.out.println("\nA:" + array[0]);
        System.out.println("B:" + array[1]);
        System.out.println("C:" + array[2]);
        System.out.println("D:" + array[3]);
        System.out.println("E:" + array[4]);
        System.out.print("------------------------------------------------------\n\n");
    }


}

class VotingService{
    //String answers[];  
    int[] stats = new int[5];
    Queue<Student> classroom = new LinkedList<>();



    public VotingService(Queue<Student> classroom){
        this.classroom = classroom; 
    }

    //run the student objects and the the answers that the choos e
    public int[] getAnswersSingle(){
        int a,b,c,d,e; 
        List<String>  answers = new ArrayList<String>();
        while(classroom.peek() != null){
            Student student = classroom.remove(); 
            String choice = student.chooseAnswer(student.getChoices()); 
            answers.add(choice);
        }
        //filter through list to find quantatites of different answers 
        a = Collections.frequency(answers, "a");
        b = Collections.frequency(answers, "b");
        c = Collections.frequency(answers, "c");
        d = Collections.frequency(answers, "d");
        e = Collections.frequency(answers, "e");
        stats[0] = a; 
        stats [1] = b;
        stats [2] = c;
        stats [3] = d; 
        stats [4] = e; 
        return stats;
    }

    //same as single answer, but with the chance of multiple attemps at answering 
    public int[] getAnswersMulti(){
        int a,b,c,d,e; 
        List<String>  answers = new ArrayList<String>();
        while(classroom.peek() != null){
            Student student = classroom.remove(); 
            Random rand = new Random();
            int randomChoices = rand.nextInt(5); 
            for(int i = 0; i < randomChoices; i++){
                String choice = student.chooseAnswer(student.getChoices()); 
                answers.add(choice);
            }
        }
        //filter through choices to amount 
        a = Collections.frequency(answers, "a");
        b = Collections.frequency(answers, "b");
        c = Collections.frequency(answers, "c");
        d = Collections.frequency(answers, "d");
        e = Collections.frequency(answers, "e");
        stats[0] = a; 
        stats [1] = b;
        stats [2] = c;
        stats [3] = d; 
        stats [4] = e; 
        return stats;
    }
        
    
}




