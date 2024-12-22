package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private Context context;
    private List<Room> roomList;
    private OnRoomClickListener listener;

    // Interface for click handling
    public interface OnRoomClickListener {
        void onRoomClick(Room room);
    }

    public RoomAdapter(Context context, List<Room> roomList) {
        this.context = context;
        this.roomList = roomList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.roomName.setText(room.getName());
        holder.roomDescription.setText(room.getDescription());
        holder.roomPrice.setText("$" + room.getPrice());
        Glide.with(context).load(room.getImageUrl()).into(holder.roomImage);

        // Handle item click for room details
        holder.itemView.setOnClickListener(v -> listener.onRoomClick(room));

        // Handle "Shop Now" button click
        holder.shopNowButton.setOnClickListener(v -> {
            // Pass room details to another activity (Booking page or details page)
            Intent intent = new Intent(context, BookingDetailsActivity.class); // Replace with your activity name
            intent.putExtra("room_name", room.getName());
            intent.putExtra("room_price", room.getPrice());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView roomName, roomDescription, roomPrice;
        ImageView roomImage;
        Button shopNowButton;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.roomName);
            roomDescription = itemView.findViewById(R.id.roomDescription);
            roomPrice = itemView.findViewById(R.id.roomPrice);
            roomImage = itemView.findViewById(R.id.roomImage);
            shopNowButton = itemView.findViewById(R.id.shopNowButton);  // Button for "Shop Now"
        }
    }
}
