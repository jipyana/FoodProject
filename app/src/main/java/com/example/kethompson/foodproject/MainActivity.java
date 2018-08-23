package com.example.kethompson.foodproject;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        GameAdapter gameAdapter = new GameAdapter(this);
        listView.setAdapter(gameAdapter);

    }
}
class Game {
    Game(String title, String desc, int image) {
        this.title = title;
        this.description = desc;
        this.image = image;
    }
    String title;
    String description;
    int image;
}

class GameAdapter extends BaseAdapter {

    ArrayList<Game> arrayList;
    Context c;

    GameAdapter(Context context) {
        c = context;
        arrayList = new ArrayList<>();
        Resources resources = context.getResources();
        String[] titles = resources.getStringArray(R.array.title);
        String[] desc = resources.getStringArray(R.array.description);
        int[] images = {R.drawable.halflife,R.drawable.pacman};
        for (int i=0;i<2;i++) {
            Game game = new Game(titles[i],desc[i],images[i]);
            arrayList.add(game);
        }
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.single_row,parent,false);
        TextView title = view.findViewById(R.id.title);
        TextView desc = view.findViewById(R.id.description);
        ImageView imageView = view.findViewById(R.id.images);
        Game g = arrayList.get(position);
        title.setText(g.title);
        desc.setText(g.description);
        imageView.setImageResource(g.image);

        return view;
    }
}