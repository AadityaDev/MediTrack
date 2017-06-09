package com.aditya.meditrack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aditya.meditrack.models.Medicine;
import com.aditya.meditrack.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHelper>{

    private List<Medicine> medicines;
    private Context context;

    public MedicineAdapter(@NonNull Context context,@NonNull List<Medicine> medicines){
        this.context=context;
        this.medicines = medicines;
    }

    @Override
    public MedicineViewHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.medicine_card,parent,false);
        return new MedicineViewHelper(view);
    }

    @Override
    public void onBindViewHolder(MedicineViewHelper holder, int position) {
        final Medicine medicine= medicines.get(position);
        if(medicine!=null){
//            if(StringUtils.isNotEmptyOrNull(movie.getPoster_path())){
//                Picasso.with(context).load(AppAPI.IMAGE_URL+movie.getPoster_path())
//                        .resize(point.x,point.x)
//                        .into(holder.background);
//            }
            if(StringUtils.isNotEmptyOrNull(medicine.getName())){
                holder.name.setText(medicine.getName());
            }
            if(StringUtils.isNotEmptyOrNull(medicine.getPriority())){
                holder.priority.setText(medicine.getPriority());
            }
            holder.dosage.setText(medicine.getDosage()+" in a day");
        }
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }

    @Override
    public long getItemId(int position) {
        return medicines.get(position).getId();
    }

    public Medicine getMovie(int position){
        return medicines.get(position);
    }

    public Bitmap getBitmapFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class MedicineViewHelper extends RecyclerView.ViewHolder{

        private ImageView background;
        private TextView name;
        private TextView priority;
        private TextView dosage;

        public MedicineViewHelper(@NonNull View item){
            super(item);
            name=(TextView)item.findViewById(R.id.medicine_name);
            priority=(TextView)item.findViewById(R.id.medicine_priority);
            dosage=(TextView)item.findViewById(R.id.medicine_dosage);
        }

    }

}
