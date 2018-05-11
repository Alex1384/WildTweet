package fr.wilcodeschool.wildtweet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wilder on 18/03/18.
 */

public class TweetAdapter extends ArrayAdapter<TweetModel>{

    public TweetAdapter(Context context, ArrayList<TweetModel> items) {
        super(context, 0, items);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_tweet, parent, false);

        }
        TextView username = convertView.findViewById(R.id.text_username);
        TextView content = convertView.findViewById(R.id.text_content);
        // position : ligne en cours
        TweetModel tweetModel = getItem(position);
        username.setText(tweetModel.getUsername());
        content.setText(tweetModel.getContent());
            return  convertView;



    }

}


