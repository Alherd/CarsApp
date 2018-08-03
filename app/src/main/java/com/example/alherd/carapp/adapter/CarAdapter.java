package com.example.alherd.carapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.alherd.carapp.database.DatabaseHelperMethods;

import java.io.InputStream;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {
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
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor1 = sqLiteDatabase.rawQuery("select * from " + DatabaseHelper.TABLE_CAR_MODELS, null);
        cursor1.moveToFirst();
        if (!cursor1.move(position)) {
            return;
        }

        String titleModel = cursor1.getString(cursor1.getColumnIndex(DatabaseHelper.COLUMN_NAME_CAR_MODEL));
        holder.titleModel.setText(titleModel);

        String photoModel = cursor1.getString(cursor1.getColumnIndex(DatabaseHelper.COLUMN_PHOTO_CAR_MODEL));
        InputStream is = getClass().getClassLoader().getResourceAsStream(photoModel);
        Bitmap bm = BitmapFactory.decodeStream(is);
        holder.photoModel.setImageBitmap(bm);

        String idMark = cursor1.getString(cursor1.getColumnIndex(DatabaseHelper.COLUMN_ID_CAR_MODEL_MARK));
        holder.markAndCountryModel.setText(databaseHelperMethods.getNameMarkFromId(idMark) +
                " (" + databaseHelperMethods.getManufacturerFromIdMark(idMark) + ")");

        String costModel = cursor1.getString(cursor1.getColumnIndex(DatabaseHelper.COLUMN_COST_CAR_MODEL));
        holder.costModel.setText("$" + costModel);
        cursor1.close();

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class CarHolder extends RecyclerView.ViewHolder {
        public TextView titleModel;
        public ImageView photoModel;
        public TextView markAndCountryModel;
        public TextView costModel;

        public CarHolder(View itemView) {
            super(itemView);

            titleModel = itemView.findViewById(R.id.title_model);
            photoModel = itemView.findViewById(R.id.photo_model);
            markAndCountryModel = itemView.findViewById(R.id.mark_country_model);
            costModel = itemView.findViewById(R.id.cost_model);
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
