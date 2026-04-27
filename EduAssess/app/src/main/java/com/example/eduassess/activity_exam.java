package com.example.eduassess;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class activity_exam extends AppCompatActivity {

    private TextView tvTimer, tvQuestion;
    private RadioGroup rgOptions;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btnSubmit;

    // 10 Technical Python Questions
    private String[] questions = {
            "1. Which function is used to get the length of a list?",
            "2. How do you create a variable with the numeric value 5?",
            "3. Which method can be used to remove any whitespace from both the beginning and the end of a string?",
            "4. Which operator is used to multiply numbers?",
            "5. How do you start a WHILE loop in Python?",
            "6. Which collection is ordered, changeable, and allows duplicate members?",
            "7. How do you write a list in Python?",
            "8. Which statement is used to stop a loop?",
            "9. What is the correct way to create a dictionary?",
            "10. Which operator can be used to compare two values?"
    };

    private String[][] options = {
            {"length()", "len()", "size()", "count()"},
            {"x = 5", "x : 5", "int x = 5", "x == 5"},
            {"trim()", "ptrim()", "len()", "strip()"},
            {"#", "x", "*", "%"},
            {"while x > y", "while (x > y):", "while x > y:", "while x > y then:"},
            {"List", "Tuple", "Dictionary", "Set"},
            {"(a, b)", "[a, b]", "{a, b}", "<a, b>"},
            {"exit", "stop", "break", "return"},
            {"x = {'a':1}", "x = ['a':1]", "x = ('a':1)", "x = <'a':1>"},
            {"=", "<>", "==", "><"}
    };

    // Correct Answer Indices (0 to 3)
    private int[] correctAnswers = {1, 0, 3, 2, 2, 0, 1, 2, 0, 2};

    private int currentQuestionIndex = 0;
    private int rawScore = 0; // Each correct answer = 1 point

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        tvTimer = findViewById(R.id.tvTimer);
        tvQuestion = findViewById(R.id.tvQuestion);
        rgOptions = findViewById(R.id.rgOptions);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        btnSubmit = findViewById(R.id.btnSubmit);

        loadQuestion();

        // 10-minute timer
        new CountDownTimer(600000, 1000) {
            public void onTick(long millisUntilFinished) {
                long minutes = (millisUntilFinished / 1000) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                tvTimer.setText(String.format("%02d:%02d", minutes, seconds));
            }
            public void onFinish() {
                submitFinalScore();
            }
        }.start();

        btnSubmit.setOnClickListener(v -> {
            int selectedId = rgOptions.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRB = findViewById(selectedId);
            int answerIndex = rgOptions.indexOfChild(selectedRB);

            if (answerIndex == correctAnswers[currentQuestionIndex]) {
                rawScore++;
            }

            currentQuestionIndex++;
            if (currentQuestionIndex < questions.length) {
                loadQuestion();
            } else {
                submitFinalScore();
            }
        });
    }

    private void loadQuestion() {
        rgOptions.clearCheck();
        tvQuestion.setText(questions[currentQuestionIndex]);
        rb1.setText(options[currentQuestionIndex][0]);
        rb2.setText(options[currentQuestionIndex][1]);
        rb3.setText(options[currentQuestionIndex][2]);
        rb4.setText(options[currentQuestionIndex][3]);

        if (currentQuestionIndex == questions.length - 1) {
            btnSubmit.setText("FINISH EXAM");
        }
    }

    private void submitFinalScore() {
        // Each question is 2 marks. Total marks = 10 questions * 2 = 20.
        int finalScore = rawScore * 2;
        int totalMarks = 20;

        Intent intent = new Intent(activity_exam.this, activity_result.class);
        intent.putExtra("SCORE", finalScore);
        intent.putExtra("TOTAL", totalMarks);
        startActivity(intent);
        finish();
    }
}