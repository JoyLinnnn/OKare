package com.example.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
        holder.tv_meditime.setText("用藥時間："+ da.getMeditime());
        holder.tv_mediname.setText("藥品及數量："+ da.getMediname());
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dara.child("Medicine").child(da.getCey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(activity, "資料刪除成功! ", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "資料刪除失敗! ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setMessage("您確定要刪除嗎?"+da.getMeditime());
                builder.show();
            }
        });

        holder.card_resul.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogForm2 dialog2 = new DialogForm2(
                        da.getMeditime(),
                        da.getMediname(),
                        da.getCey(),
                        "Change"
                );
                dialog2.show(manager,"form");
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mmList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_meditime, tv_mediname;
        ImageView btn_delete;
        CardView card_resul;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_meditime = itemView.findViewById(R.id.tv_meditime);
            tv_mediname = itemView.findViewById(R.id.tv_mediname);
            btn_delete = itemView.findViewById(R.id.hapus);
            card_resul = itemView.findViewById(R.id.card_resul);
        }
    }
}
