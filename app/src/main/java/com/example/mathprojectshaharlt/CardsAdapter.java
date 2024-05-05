package com.example.mathprojectshaharlt;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathprojectshaharlt.mathproject.UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.MyviewHolder> {
    public interface OnitemClickListener {
        void OnItemClick(Card item);
    }

    private ArrayList<Card> Cards;
    private OnitemClickListener listener;

    public CardsAdapter(ArrayList<Card> cards, OnitemClickListener listener) {
        this.Cards = cards;
    }

    @NonNull
    @Override
    public CardsAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new CardsAdapter.MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsAdapter.MyviewHolder holder, int position) {
        holder.bind(Cards.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return Cards.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView Cardimg;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            Cardimg = itemView.findViewById(R.id.Cardimg);
        }

        public void bind(final Card item, final OnitemClickListener listener) {
            Cardimg.setImageBitmap(item.getCardbitmap());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick(item);
                }
            });

        }


    }
}

