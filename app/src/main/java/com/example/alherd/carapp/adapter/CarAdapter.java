package com.example.alherd.carapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alherd.carapp.R;
import com.example.alherd.carapp.database.DatabaseHelperMethods;

public class CarAdapter extends RecyclerView.Adapter<CarHolder> {
    private Context context;
    private Cursor cursor;
    private DatabaseHelperMethods databaseHelperMethods;

    public CarAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;

        databaseHelperMethods = new DatabaseHelperMethods(context);
    }

    @NonNull
    @Override
    public CarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.car_item, parent, false);
        return new CarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarHolder holder, int position) {
        holder.setTextInViewHolder(position, context, cursor);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
