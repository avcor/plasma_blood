package com.example.plasmabloodd;

import android.app.LauncherActivity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private List<user_list_item>listItems;
    private Context context;
    private OnItemClickListener mlistener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
         mlistener = listener;
    }

    public myAdapter(List<user_list_item> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item,parent,false);
        return  new ViewHolder(v,mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        user_list_item listItem = listItems.get(position);
        holder.textView_username.setText(listItem.getUser_name());
        holder.textView_usercity.setText(listItem.getUser_city());
        holder.IV_avtar.setImageResource(listItem.getmImageResource());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView_username;
        public TextView textView_usercity;
        public ImageView IV_avtar;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textView_username = (TextView) itemView.findViewById(R.id.user_name);
            textView_usercity = (TextView) itemView.findViewById(R.id.user_city);
            IV_avtar = (ImageView) itemView.findViewById(R.id.avtar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
