package com.example.anjaleegamage.employeeassistancesystem;

/**
 * Created by AnjaleeGamage on 5/3/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;


public class QAmain extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null){
            Log.v("QAmain", "not signed in");
            startActivity(new Intent(this, Login.class));
        } else {
            setContentView(R.layout.activity_qa_main);

            Button askBtn = (Button) findViewById(R.id.askBtn);
            askBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(QAmain.this,SubmitQuestionActivity.class));
                }
            });

            ListView listView = (ListView) findViewById(R.id.questionsLV);

            //get instance of database
            mDatabase = FirebaseDatabase.getInstance().getReference().child("questions");

            mAdapter = new FirebaseListAdapter<Question>(this, Question.class, android.R.layout.two_line_list_item, mDatabase) {
                @Override
                protected void populateView(View view, Question question, final int position) {
                    ((TextView)view.findViewById(android.R.id.text1)).setText(question.getTitle());
                    ((TextView)view.findViewById(android.R.id.text2)).setText(question.getContent());

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(QAmain.this, QuestionDetailActivity.class);
                            i.putExtra("question", (Serializable) mAdapter.getItem(position));
                            i.putExtra("question_key", mAdapter.getRef(position).getKey());
                            startActivity(i);
                        }
                    });



                }
            };

            listView.setAdapter(mAdapter);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }


}