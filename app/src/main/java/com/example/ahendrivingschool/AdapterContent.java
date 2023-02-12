package com.example.ahendrivingschool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class AdapterContent extends FirebaseRecyclerAdapter<Model, AdapterContent.viewHolder> {
    FirebaseRecyclerOptions<Model> options;
    public AdapterContent(@NonNull FirebaseRecyclerOptions<Model> options) {

            super(options);
            options=this.options;

    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Model model) {
        holder.schoolName.setText(model.getS_name());
        holder.address.setText(model.getS_address());
        holder.phone.setText(model.getS_mobile());

        holder.profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new DescribeFragment(model.getS_address(), model.getS_mail(), model.getS_mobile(), model.getS_name(), model.getS_pincode(), model.getS_profile())).addToBackStack(null).commit();
            }
        });

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView profile_pic;
        TextView schoolName, address, phone;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile_pic = itemView.findViewById(R.id.profile_pic);
            schoolName = itemView.findViewById(R.id.schoolName);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);



        }
    }



}
