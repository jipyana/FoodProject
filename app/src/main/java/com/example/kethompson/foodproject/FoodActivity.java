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

public class FoodActivity extends AppCompatActivity {

    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        listView = findViewById(R.id.listView);
        FoodAdapter foodAdapter = new FoodAdapter(this);
        listView.setAdapter(foodAdapter);
    }
}

class FoodAdapter extends BaseAdapter {

    ArrayList<Food> foodArrayList;
    Context c;
    FoodAdapter(Context c) {
        this.c = c;
        foodArrayList = new ArrayList<>();
        Resources resources = c.getResources();
        String[] titles = resources.getStringArray(R.array.foodTitle);
        int[] rank = resources.getIntArray(R.array.foodRank);
        int[] images = {R.drawable.fries,R.drawable.salad};
        for(int i=0;i<2;i++) {
            foodArrayList.add(new Food(titles[i], rank[i],images[i]));
        }
    }

    @Override
    public int getCount() {
        return foodArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //this is going to return the single row as an entire VIEW
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.food_row,parent,false);
        ImageView imageView = view.findViewById(R.id.foodView);
        TextView textView = view.findViewById(R.id.foodText);
        RatingBar ratingBar = view.findViewById(R.id.foodRating);
        Food food = foodArrayList.get(position);
        imageView.setImageResource(food.image);
        textView.setText(food.title);
        ratingBar.setNumStars(food.rank);
        return view;
    }

}