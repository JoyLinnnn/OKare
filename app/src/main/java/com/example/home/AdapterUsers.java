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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyViewHolder> {
    private List<ModelUsers> mList;
    private Activity activity;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    //DatabaseReference database = FirebaseDatabase.getInstance().getReference();

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
        holder.tv_newtime.setText("回診日期："+data.getnew1());
        holder.tv_backhospitaltime.setText("回診時間："+data.getBacktime());
        holder.tv_hospitalsite.setText("門診及編號："+data.getHossite());
        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth= FirebaseAuth.getInstance();
                FirebaseUser rUser=mAuth.getCurrentUser();
                assert rUser !=null;
                String userId=rUser.getUid();
                reference = FirebaseDatabase.getInstance().getReference("Remind").child(userId);
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        reference.child(data.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
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
                }).setMessage("您確定要刪除嗎?"+data.getBacktime());
                builder.show();
            }
        });

        holder.card_hasil.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogForm dialog = new DialogForm(
                        data.getnew1(),
                        data.getBacktime(),
                        data.getHossite(),
                        data.getKey(),
                        "change改變"
                );
                dialog.show(manager,"form");
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_backhospitaltime, tv_hospitalsite,tv_newtime;
        ImageView btn_hapus;
        CardView card_hasil;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_newtime=itemView.findViewById(R.id.tv_newtime);
            tv_backhospitaltime = itemView.findViewById(R.id.tv_backhospitaltime);
            tv_hospitalsite = itemView.findViewById(R.id.tv_hospitalsite);
            btn_hapus = itemView.findViewById(R.id.hapus);
            card_hasil = itemView.findViewById(R.id.card_hasil);
        }
    }
}
