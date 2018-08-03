package com.example.alherd.carapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alherd.carapp.R;
import com.example.alherd.carapp.database.DatabaseHelper;

import java.io.InputStream;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {

    private Context context;
    private Cursor cursor;

    public CarAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        cursor.moveToFirst();
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
        if (!cursor.move(position)) {
            return;
        }
        String titleModel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_CAR_MODEL));
        holder.titleModel.setText(titleModel);
        String photoDoctor = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHOTO_CAR_MODEL));
        InputStream is = getClass().getClassLoader().getResourceAsStream(photoDoctor);
        Bitmap bm = BitmapFactory.decodeStream(is);
        holder.photoModel.setImageBitmap(bm);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class CarHolder extends RecyclerView.ViewHolder {
        public TextView titleModel;
        public ImageView photoModel;

        public CarHolder(View itemView) {
            super(itemView);

            titleModel = itemView.findViewById(R.id.title_model);
            photoModel = itemView.findViewById(R.id.photo_model);
        }
    }

    private void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
