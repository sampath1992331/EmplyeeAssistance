package com.example.anjaleegamage.employeeassistancesystem;

/**
 * Created by AnjaleeGamage on 5/3/2017.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuestionDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        Question question = (Question) getIntent().getSerializableExtra("question");
        loadQuestion(question);

        ListView listView = (ListView) findViewById(R.id.answersLV);

        String questionKey = getIntent().getStringExtra("question_key");

        //get instance of database
        DatabaseReference answersDatabase = FirebaseDatabase.getInstance().getReference()
                .child("answers").child(questionKey);

        FirebaseListAdapter answerAdapter = new FirebaseListAdapter<Answer>(this, Answer.class,
                android.R.layout.simple_list_item_1, answersDatabase) {
            @Override
            protected void populateView(View view, Answer answer, final int position) {
                ((TextView)view.findViewById(android.R.id.text1)).setText(answer.getText());
            }
        };

        listView.setAdapter(answerAdapter);

    }


    private void loadQuestion(Question question){
        TextView questionTextView = (TextView) findViewById(R.id.questionTV);
        questionTextView.setText(question.getTitle());

        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTV);
        descriptionTextView.setText(question.getContent());
    }

    public void submitAnswer(View view){
        Intent i = new Intent(this, SubmitAnswerActivity.class);
        i.putExtra("question_key", getIntent().getStringExtra("question_key"));
        startActivity(i);
    }

}
