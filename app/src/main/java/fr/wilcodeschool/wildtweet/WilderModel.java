package fr.wilcodeschool.wildtweet;

import java.util.ArrayList;

/**
 * Created by wilder on 12/05/18.
 */

public class WilderModel extends UserModel {

    private ArrayList<TweetModel> tweets = new ArrayList<>();


    public WilderModel(String username, String password) {
        super(username, password);
    }



    @Override
    public void login() {
        //TODO: aller vers le mur de tweets
    }

    public ArrayList<TweetModel> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<TweetModel> tweets) {
        this.tweets = tweets;
    }
}

