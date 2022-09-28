
package main.java; 
import java.util.*;  
import java.util.Queue;

class Student{
    public String answer; 
    public String sid; 
    private List<String> tempchoices = Arrays.asList("a","b","c","d","e");
    private String [] choices; 

    protected String[] shuffle(){
        Collections.shuffle(tempchoices);
        String [] choices = new String[tempchoices.size()];
        choices = tempchoices.toArray(choices); 
        return choices;
    }

    public String[] getChoices(){
        return this.choices; 
    }
    public Student(int id ){
            this.sid = Integer.toString(id); 
            this.choices = shuffle(); 
        }

    public void setStudentID(String studentID){
        this.sid = studentID; 
    }
    public String getId(){
        return this.sid; 
    }
    
    
    public String chooseSingle(String[] choices){
        //choices to be picked by the student, based on the student ID 
        answer = choices[0]; 
        return answer; 
        }
        
}

class singleChoiceQuestion{
    private String question; 
    private String[] answer; 
    private List<String> tempchoices;

    public singleChoiceQuestion(){
        this.answer = new String[1];
        this.tempchoices = Arrays.asList("a","b","c","d","e");
    }

    protected String[] shuffle(){
        Collections.shuffle(tempchoices);
        String [] choices = new String[tempchoices.size()];
        choices = tempchoices.toArray(choices); 
        return choices;
    }

    public void createQuestion(){
        String[] choices = shuffle();
        addAnswer(choices[0]);
        
    }
    public String question(){
        return this.question;
    }

    public void addQuestion(String newQuestion){
        this.question = newQuestion; 
    }

    public String[] getAnswer(){
        return this.answer;
    }

    public void addAnswer(String newAnswer){
        answer[0] = newAnswer; 
    }

    
}

class multipleChoiceQuestion extends singleChoiceQuestion{
    String[] answer = new String[0];
    List<String> tempchoices = Arrays.asList("a","b","c","d","e");

    public multipleChoiceQuestion(){
        this.answer = new String[0];
    }
    
    public void createQuestion(){
        String[] choices = shuffle();
        Random random = new Random();
        int correctAnswer = (random.nextInt(4)+1);
        for (int i = 0; i < correctAnswer; i++){
            addAnswer(choices[i]);
        }
        
    }

    public String[] getAnswer(){
        return this.answer;
    }

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
    
    public int getStudents(){
        return this.students; 
    }

    public int getQuestions(){
        return this.questions; 
    }

    public int getMultiQuestions(){
        return this.multiQuestions; 
    }

    public Queue<Student> getQueue(){
        return queueClassroom; 
    }

    public int[] createAnswerQuestion(int questions){ 
        for (int i = 0; i < questions; i++){}
        //System.out.println(i);
        System.out.print("NEW QUESTION, ONE ANSWER\n");
        VotingService voteCounter = new VotingService(queueClassroom);
        return voteCounter.getAnswersSingle(); 
        
    }

    public int[] createAnswerMultiQuestion(int questions){
        System.out.print("NEW QUESTION, Multi ANSWER\n");
        VotingService voteCounter = new VotingService(queueClassroom);
        return voteCounter.getAnswersMulti(); 
    }


    public void displayQuestionAnswer(int [] array,int students){
        System.out.print("------------------------------------------------------\n\n");
        System.out.println("A:" + array[0]);
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


    public int[] getAnswersSingle(){
        int a,b,c,d,e; 
        List<String>  answers = new ArrayList<String>();
        while(classroom.peek() != null){
            Student student = classroom.remove(); 
            String choice = student.chooseSingle(student.getChoices()); 
            answers.add(choice);
        }
        
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


    public int[] getAnswersMulti(){
        int a,b,c,d,e; 
        List<String>  answers = new ArrayList<String>();
        while(classroom.peek() != null){
            Student student = classroom.remove(); 
            Random rand = new Random();
            int randomChoices = rand.nextInt(5); 
            for(int i = 0; i < randomChoices; i++){
                String choice = student.chooseSingle(student.getChoices()); 
                answers.add(choice);
            }
        }
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




