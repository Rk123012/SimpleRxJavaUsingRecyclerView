package com.example.mohsiul.simplerxjava;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    Context context;
    Activity activity;
    private ArrayList<Animal_name>mExamplelist;

    public RecyclerViewAdapter(ArrayList<Animal_name>Examplelist){
        mExamplelist=Examplelist;
        this.context=context;
        this.activity=activity;

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_content,parent,false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

       // AtomicIntegerArray mExamplelist;
        final Animal_name currentName=mExamplelist.get(position);

        holder.textView.setText(currentName.getName());
    }

    @Override
    public int getItemCount() {
        return mExamplelist.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout layout;
        public TextView textView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textview1);
            layout=itemView.findViewById(R.id.linearLayout);
        }
    }
}
