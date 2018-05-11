package fr.wilcodeschool.wildtweetv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import javax.microedition.khronos.egl.EGLDisplay;

public class TweetCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_create);

        Intent recupList = getIntent();

        Button bLastTweet = findViewById(R.id.button_la_tweet);
        bLastTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference tweetsRef = database.getReference("tweets");
                tweetsRef.orderByChild("datetime").limitToLast(1)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot tweetSnapshot : dataSnapshot.getChildren()){
                                    TweetModel tweetModel = tweetSnapshot.getValue(TweetModel.class);
                                    Toast.makeText(TweetCreateActivity.this, String.format("%s : %s",
                                                      tweetModel.getUsername(), tweetModel.getContent()),Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

            }
        });

        Button sendTweetButton = findViewById(R.id.button_send_tweet);
        sendTweetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userName = findViewById(R.id.edit_username);
                String userNameValue = userName.getText().toString();
                EditText content = findViewById(R.id.edit_content);
                String contentValue = content.getText().toString();

                if (userNameValue.isEmpty()|| contentValue.isEmpty()){
                    Toast.makeText(TweetCreateActivity.this, R.string.error_fill_form, Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseDatabase.getInstance();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference tweetsRef = database.getReference("tweets");

                    // ecriture dans la base
                    TweetModel tweetModel = new TweetModel(userNameValue,contentValue, new Date().getTime());
                    final String key = tweetsRef.push().getKey();
                    tweetsRef.child(key).setValue(tweetModel);

                    Intent goToList = new Intent(TweetCreateActivity.this,
                            ListTweetActivity.class);
                    Toast.makeText(TweetCreateActivity.this, R.string.Tweet_add, Toast.LENGTH_SHORT).show();
                    TweetCreateActivity.this.startActivity(goToList);


                }


            }
        });
    }
}
