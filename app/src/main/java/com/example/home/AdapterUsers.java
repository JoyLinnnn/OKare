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

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyViewHolder> {
    private List<ModelUsers> mList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterUsers(List<ModelUsers>mList, Activity activity){
        this.mList = mList;
        this.activity = activity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelUsers data = mList.get(position);
        holder.tv_backhospitaltime.setText("日期及時間："+data.getBacktime());
        holder.tv_hospitalsite.setText("門診及編號："+data.getHossite());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_backhospitaltime, tv_hospitalsite;
        CardView card_hasil;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_backhospitaltime = itemView.findViewById(R.id.tv_backhospitaltime);
            tv_hospitalsite = itemView.findViewById(R.id.tv_hospitalsite);
            card_hasil = itemView.findViewById(R.id.card_hasil);
        }
    }
}

