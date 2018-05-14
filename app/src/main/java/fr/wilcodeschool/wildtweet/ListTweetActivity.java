package fr.wilcodeschool.wildtweet;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import static fr.wilcodeschool.wildtweet.MainActivity.EXTRA_LOGIN;


public class ListTweetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tweet);

        Intent intentFromMainActivity = getIntent();
        String login = intentFromMainActivity.getStringExtra (EXTRA_LOGIN);


        FloatingActionButton addButton = findViewById(R.id.action_button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTweetCreate = new Intent(ListTweetActivity.this, TweetCreateActivity.class
                );
                startActivity(goToTweetCreate);
            }
        });

        final ArrayList<TweetModel>tweetList =
                new ArrayList<>();

        final TweetAdapter adapter = new TweetAdapter(this, tweetList);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tweetsRef = database.getReference("tweets");
        tweetsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tweetList.clear();
                        for(DataSnapshot tweetSnapshot : dataSnapshot.getChildren()){
                            TweetModel tweetModel = tweetSnapshot.getValue(TweetModel.class);
                            tweetList.add(tweetModel);
                        }
                        Collections.reverse(tweetList);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


        ListView listweet = findViewById(R.id.list_tweet);
        listweet.setAdapter(adapter);



        Intent recupCreate = getIntent();

    }
}
