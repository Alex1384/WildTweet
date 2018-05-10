package fr.wilcodeschool.wildtweetv2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import static fr.wilcodeschool.wildtweetv2.MainActivity.EXTRA_FIRSTNAME;
import static fr.wilcodeschool.wildtweetv2.MainActivity.EXTRA_LASTNAME;

public class ListTweetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tweet);
        Intent intentFromMainActivity = getIntent();
        String login= intentFromMainActivity.getStringExtra(EXTRA_FIRSTNAME);
        String lastNameTweet = intentFromMainActivity.getStringExtra(EXTRA_LASTNAME);

        Toast.makeText(this, login+" "+ lastNameTweet, Toast.LENGTH_SHORT).show();

        ArrayList<TweetModel>tweetList =
                new ArrayList<>();

        tweetList.add(new TweetModel("pascal",
        "ce live coding est genial!!", new Date().getTime()));
        tweetList.add(new TweetModel("pablo","Tacos", new Date().getTime()));
        tweetList.add(new TweetModel("claire","Git je comprends trop ce que c'est lol",new Date().getTime()));

        TweetAdapter adapter = new TweetAdapter(this, tweetList);
        ListView listweet = findViewById(R.id.list_tweet);
        listweet.setAdapter(adapter);

        FloatingActionButton addButton = findViewById(R.id.action_button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTweetCreate = new Intent(ListTweetActivity.this, TweetCreateActivity.class
                );
                ListTweetActivity.this.startActivity(goToTweetCreate);
            }
        });

        Intent recupCreate = getIntent();

    }
}
