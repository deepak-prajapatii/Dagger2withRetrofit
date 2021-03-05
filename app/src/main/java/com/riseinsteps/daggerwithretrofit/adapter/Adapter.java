package com.riseinsteps.daggerwithretrofit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.riseinsteps.daggerwithretrofit.R;
import com.riseinsteps.daggerwithretrofit.model.Data;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Data> dataList;

    public Adapter() {
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(dataList.get(position).getId(), dataList.get(position).getEmail(),
                dataList.get(position).getFirst_name(), dataList.get(position).getLast_name(),
                dataList.get(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id, email, firstName, lastName;
        private ImageView avatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            email = itemView.findViewById(R.id.email);
            firstName = itemView.findViewById(R.id.firstname);
            lastName = itemView.findViewById(R.id.lastname);
            avatar = itemView.findViewById(R.id.avatar);
        }

        private void setData(final int id, final String email, final String firstname, final String lastname, final String url) {
            this.id.setText(String.valueOf(id));
            this.email.setText(email);
            this.firstName.setText(firstname);
            this.lastName.setText(lastname);
            Glide.with(itemView).load(url).into(avatar);
        }
    }
}
