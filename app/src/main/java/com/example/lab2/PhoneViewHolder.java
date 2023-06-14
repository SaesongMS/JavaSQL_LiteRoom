package com.example.lab2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class PhoneViewHolder extends RecyclerView.ViewHolder {
    private final TextView phoneProducer;
    private final TextView phoneModel;

    private PhoneViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        phoneProducer = itemView.findViewById(R.id.producer);
        phoneModel = itemView.findViewById(R.id.model);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                recyclerViewInterface.onItemClicked(position);
            }
        });
    }

    public void bind(String producer, String model) {
        phoneProducer.setText(producer);
        phoneModel.setText(model);
    }

    static PhoneViewHolder create(ViewGroup parent, RecyclerViewInterface recyclerViewInterface) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PhoneViewHolder(view, recyclerViewInterface);
    }

}
