package com.example.lab2;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class PhoneListAdapter extends ListAdapter<Phone, PhoneViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    public PhoneListAdapter(@NonNull DiffUtil.ItemCallback<Phone> diffCallback, RecyclerViewInterface recyclerViewInterface) {
        super(diffCallback);
        this.recyclerViewInterface = recyclerViewInterface;
    }

     Phone getPhoneAtPosition(int position) {
        return getItem(position);
    }

    @Override
    public PhoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PhoneViewHolder.create(parent, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(PhoneViewHolder holder, int position) {
        Phone current = getItem(position);
        holder.bind(current.getProducer(), current.getModel());
    }

    static class PhoneDiff extends DiffUtil.ItemCallback<Phone> {

        @Override
        public boolean areItemsTheSame(@NonNull Phone oldItem, @NonNull Phone newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Phone oldItem, @NonNull Phone newItem) {
            return oldItem.getModel().equals(newItem.getModel());
        }
    }


}
