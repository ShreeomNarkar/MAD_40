package com.example.nauka;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private List<BookingModel> bookingList;

    public BookingAdapter(List<BookingModel> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BookingModel booking = bookingList.get(position);


        holder.tvBoatName.setText(booking.boatName);
        holder.tvAmount.setText("Total: ₹" + booking.totalAmount);
        holder.tvDetails.setText("Guests: " + booking.guests + " | Duration: " + booking.nights + " Night(s)");
        holder.tvTime.setText("Slot: " + booking.departureTime);


        if (booking.bookingId != null && booking.bookingId.length() > 8) {
            holder.tvId.setText("#" + booking.bookingId.substring(0, 8).toUpperCase());
        } else {
            holder.tvId.setText("#NAUKA-BK");
        }
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBoatName, tvAmount, tvDetails, tvTime, tvId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBoatName = itemView.findViewById(R.id.bookBoatName);
            tvAmount = itemView.findViewById(R.id.bookAmount);
            tvDetails = itemView.findViewById(R.id.bookDetails);
            tvTime = itemView.findViewById(R.id.bookDetails);
            tvId = itemView.findViewById(R.id.bookId);
        }
    }
}