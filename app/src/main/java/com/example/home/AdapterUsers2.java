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

public class AdapterUsers2 extends RecyclerView.Adapter<AdapterUsers2.MyViewHolder> {
    private List<ModelUsers2> mList2;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterUsers2(List<ModelUsers2>mList, Activity activity){
        this.mList2 = mList2;
        this.activity = activity;
    }
    @NonNull
    @Override
    public AdapterUsers2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewitem2 = inflater.inflate(R.layout.layout_item2, parent, false);
        return new AdapterUsers2.MyViewHolder(viewitem2);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUsers2.MyViewHolder holder, int position) {
        final ModelUsers2 data = mList2.get(position);
        holder.tv_meditime.setText("Medicine Time : "+data.getMeditime());
        holder.tv_mediname.setText("Medicine Name : "+data.getMediname());

    }

    @Override
    public int getItemCount() {
        return mList2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_meditime, tv_mediname;
        CardView card_hasil2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_meditime = itemView.findViewById(R.id.tv_meditime);
            tv_mediname = itemView.findViewById(R.id.tv_mediname);
            card_hasil2 = itemView.findViewById(R.id.card_hasil2);
        }
    }
}
