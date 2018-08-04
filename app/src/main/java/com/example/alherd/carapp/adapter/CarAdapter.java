package com.example.alherd.carapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.example.alherd.carapp.utils.BitmapUtils;
import com.example.alherd.carapp.utils.StringUtils;

import java.io.FileNotFoundException;
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
        cursor.moveToFirst();
        if (!cursor.move(position)) {
            return;
        }

        String titleModel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_CAR_MODEL));
        holder.titleModel.setText(titleModel);

        String photoModel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHOTO_CAR_MODEL));
        if (StringUtils.foo(photoModel, "jpg")) {
            InputStream is = getClass().getClassLoader().getResourceAsStream(photoModel);
            Bitmap bm = BitmapFactory.decodeStream(is);
            holder.photoModel.setImageBitmap(bm);
        } else {
            Bitmap bm = BitmapUtils.convertToBitmap(photoModel);
            holder.photoModel.setImageBitmap(bm);
        }
        String idMark = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_CAR_MODEL_MARK));
        holder.markAndCountryModel.setText(databaseHelperMethods.getNameMarkFromId(idMark) +
                " (" + databaseHelperMethods.getManufacturerFromIdMark(idMark) + ")");

        String costModel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST_CAR_MODEL));
        holder.costModel.setText("$" + costModel);

        String powerModel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POWER_CAR_MODEL));
        holder.powerModel.setText(powerModel);

        String doorsNumberModel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DOORS_NUMBER_CAR_MODEL));
        holder.doorsNumberModel.setText(doorsNumberModel);

        String bodyTypeModel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_BODY_TYPE_CAR_MODEL));
        holder.bodyTypeModel.setText(bodyTypeModel);

        String seatsNumberModel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SEATS_NUMBER_CAR_MODEL));
        holder.seatsNumberModel.setText(seatsNumberModel);

        String startReleaseModel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RELEASE_START_CAR_MODEL));
        holder.startReleaseModel.setText(startReleaseModel);

        String endReleaseModel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RELEASE_END_CAR_MODEL));
        holder.endReleaseModel.setText(endReleaseModel);


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
        public TextView powerModel;
        public TextView doorsNumberModel;
        public TextView bodyTypeModel;
        public TextView seatsNumberModel;
        public TextView startReleaseModel;
        public TextView endReleaseModel;

        public CarHolder(View itemView) {
            super(itemView);

            titleModel = itemView.findViewById(R.id.title_model);
            photoModel = itemView.findViewById(R.id.photo_model);
            markAndCountryModel = itemView.findViewById(R.id.mark_country_model);
            costModel = itemView.findViewById(R.id.cost_model);
            powerModel = itemView.findViewById(R.id.power_model);
            doorsNumberModel = itemView.findViewById(R.id.doors_number_model);
            bodyTypeModel = itemView.findViewById(R.id.body_type_model);
            seatsNumberModel = itemView.findViewById(R.id.seats_number_model);
            startReleaseModel = itemView.findViewById(R.id.start_release_model);
            endReleaseModel = itemView.findViewById(R.id.end_release_model);
        }
    }

    private InputStream a(String photoModel) {
        InputStream is = null;
        try {
            is = context
                    .getContentResolver()
                    .openInputStream(Uri.parse(photoModel));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return is;
    }
}
