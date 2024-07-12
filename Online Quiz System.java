import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
class QuizMain {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        Scanner scanner = new Scanner(System.in);

        while (quiz.hasNextQuestion()) {
            Question currentQuestion = quiz.getCurrentQuestion();
            if (currentQuestion != null) {
                System.out.println(currentQuestion.getQuestion());
                String[] options = currentQuestion.getOptions();
                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ". " + options[i]);
                }
                System.out.print("Enter your answer (1-" + options.length + "): ");
                
                int answer = scanner.nextInt();
                if (quiz.submitAnswer(answer - 1)) {
                    System.out.println("Correct!\n");
                } else {
                    System.out.println("Wrong!\n");
                }
                quiz.goToNextQuestion();
            }
        }

        System.out.println("Quiz completed!");
        scanner.close();
    }
}


class Question {
    private String question;
    private String[] options;
    private int correctOptionIndex;

    public Question(String question, String[] options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(int optionIndex) {
        return correctOptionIndex == optionIndex;
    }
}


 class Quiz {
    private List<Question> questions;
    private int currentQuestionIndex;

    public Quiz() {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
        // Add questions to the quiz
        initializeQuestions();
    }

    private void initializeQuestions() {
        // Sample questions and options
        String[] options1 = {"Option A", "Option B", "Option C", "Option D"};
        Question q1 = new Question("Sample Question 1?", options1, 1);
        
        String[] options2 = {"Option A", "Option B", "Option C", "Option D"};
        Question q2 = new Question("Sample Question 2?", options2, 2);
        
        // Add more questions as needed
        questions.add(q1);
        questions.add(q2);
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    public boolean hasNextQuestion() {
        return currentQuestionIndex < questions.size() - 1;
    }

    public void goToNextQuestion() {
        if (hasNextQuestion()) {
            currentQuestionIndex++;
        }
    }

    public boolean submitAnswer(int optionIndex) {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion != null) {
            return currentQuestion.isCorrect(optionIndex);
        }
        return false;
    }
}

 
