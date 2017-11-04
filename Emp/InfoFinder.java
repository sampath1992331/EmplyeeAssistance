package com.example.anjaleegamage.employeeassistancesystem;

/**
 * Created by AnjaleeGamage on 5/9/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InfoFinder extends AppCompatActivity {

    private RecyclerView mBlogList;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infofinder);

        //Recycler View
        mBlogList = (RecyclerView) findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        //Send a Query to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Data");


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<InfoFinderModelClass, BlogViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<InfoFinderModelClass, BlogViewHolder>(
                        InfoFinderModelClass.class,
                        R.layout.design_row,
                        BlogViewHolder.class,
                        myRef) {

                    @Override
                    protected void populateViewHolder(BlogViewHolder viewHolder, InfoFinderModelClass model, int position) {
                        viewHolder.setTpno(model.getTpno());
                        viewHolder.setFname(model.getFname());
                        viewHolder.setLname(model.getLname());
                        viewHolder.setEmail(model.getEmail());
                    }
                };
        mBlogList.setAdapter(firebaseRecyclerAdapter);



    }

    //View Holder For Recycler View

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

        }

        public void setFname(String fname){
            TextView post_fname = (TextView)mView.findViewById(R.id.fname);
            post_fname.setText(fname);
        }

        public void setTpno(String tpno) {
            TextView post_tpno = (TextView)mView.findViewById(R.id.tpno);
            post_tpno.setText(tpno);
        }

        public void setLname(String lname) {
            TextView post_lname = (TextView)mView.findViewById(R.id.lname);
            post_lname.setText(lname);
        }

        public void setEmail(String email) {
            TextView post_email = (TextView)mView.findViewById(R.id.email);
            post_email.setText(email);
        }

    }
}