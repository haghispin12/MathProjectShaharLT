//package com.example.mathprojectshaharlt;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class MyFruirtsAdapter extends RecyclerView.Adapter<MyFruirtsAdapter.MyViewHolder> {
//    public interface OnItemClickListener{
//        void OnItemClickListener(UserFruit item);
//    }
//    private ArrayList<User>fruits;
//    private OnItemClickListerner listerner;
//
//    public MyFruirtsAdapter(ArrayList<User>fruits , OnItemClickListerner listerner) {
//        this.fruits = fruits;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist,parent,false);
//    return new MyViewHolder(view);
//    }
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//    holder.bind(fruits.get(position),listerner);
//    }
//    @Override
//    public int getItemCount() {
//        return fruits.size();
//    }
//    public interface OnItemClickListerner{
//        void onItemClick(Fruits item);
//    }
//
//
//
//public static class MyViewHolder extends RecyclerView.ViewHolder{
//TextView tvFruitName;
//ImageView ivFruitImg;
//
//    public MyViewHolder(@NonNull View itemView) {
//        super(itemView);
//        tvFruitName = itemView.findViewById(R.id.tvFruitName);
//        ivFruitImg = itemView.findViewById(R.id.ivFruitImg);
//    }
//    public void bind(final Fruits item, final OnItemClickListerner listerner){
//    tvFruitName.setText(item.getName());
//    ivFruitImg.setImageResource(item.getDrawable());
//    itemView.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//        listerner.onItemClick(item);
//        }
//    });
//    }
//
//}
//}