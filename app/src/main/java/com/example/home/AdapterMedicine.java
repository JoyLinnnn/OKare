package com.example.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterMedicine extends RecyclerView.Adapter<AdapterMedicine.MyHolder> {
    private List<ModelMedicine> mmList;
    private Activity activity;
    DatabaseReference dara = FirebaseDatabase.getInstance().getReference();

    public AdapterMedicine(List<ModelMedicine>mmList, Activity activity){
        this.mmList = mmList;
        this.activity = activity;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewerItem = inflater.inflate(R.layout.layout_item2, parent, false);
        return new MyHolder(viewerItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final ModelMedicine da = mmList.get(position);
        holder.tv_meditime.setText("Time:"+ da.getMeditime());
        holder.tv_mediname.setText("Name:"+ da.getMediname());

    }

    @Override
    public int getItemCount() {
        return mmList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_meditime, tv_mediname;
        CardView card_resul;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_meditime = itemView.findViewById(R.id.tv_meditime);
            tv_mediname = itemView.findViewById(R.id.tv_mediname);
            card_resul = itemView.findViewById(R.id.card_resul);
        }
    }
}
