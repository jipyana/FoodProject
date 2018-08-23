package com.example.kethompson.foodproject;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {

    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        listView = findViewById(R.id.listView);
        songAdapter songAdapter = new songAdapter(this);
        listView.setAdapter(songAdapter);
    }
}

class Song {
    Song(String title, int rating, int image) {
        this.title = title;
        this.rating = rating;
        this.image = image;
    }
    String title;
    int rating;
    int image;

}

class songAdapter extends BaseAdapter {


    ArrayList<Song> songs;
    Context c;
    songAdapter(Context c){
        this.c = c;
        songs = new ArrayList<>();
        Resources resources = c.getResources();
        int[] images= {R.drawable.soundofsilence, R.drawable.jinglebells};
        String[] titles = resources.getStringArray(R.array.songTitle);
        int[] ratings = resources.getIntArray(R.array.songRating);
        for (int i = 0; i < 2; i++) {
            songs.add(new Song(titles[i],ratings[i],images[i]));
        }
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.song_row,parent,false);
        ImageView imageView = view.findViewById(R.id.songImage);
        RatingBar ratingBar = view.findViewById(R.id.songRating);
        TextView textView = view.findViewById(R.id.songTitle);
        Song song = songs.get(position);
        imageView.setImageResource(song.image);
        ratingBar.setNumStars(song.rating);
        textView.setText(song.title);

        return view;
    }
}
