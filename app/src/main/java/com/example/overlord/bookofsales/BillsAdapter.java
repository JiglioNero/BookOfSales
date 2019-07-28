package com.example.overlord.bookofsales;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.overlord.bookofsales.Data.DBRequester;
import com.example.overlord.bookofsales.Model.Bill;

import java.util.ArrayList;
import java.util.Collection;

public class BillsAdapter extends RecyclerView.Adapter<BillsAdapter.BillHolder> {

    private ArrayList<Bill> bills = new ArrayList<>();
    private Context context;
    private DBRequester requester;

    public BillsAdapter(Context context, DBRequester requester) {
        this.context = context;
        this.requester = requester;
    }

    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_preview, parent, false);
        return new BillHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder holder, int position) {
        holder.bind(bills.get(position));
    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    public void setItems(Collection<Bill> bills) {
        this.bills.addAll(bills);
        notifyDataSetChanged();
    }

    public void clearItems() {
        bills.clear();
        notifyDataSetChanged();
    }

    public class BillHolder extends RecyclerView.ViewHolder {

        private TextView number;
        private TextView from;
        private TextView to;
        private TextView cost;
        private TextView date;

        public BillHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            cost = itemView.findViewById(R.id.cost);
            date = itemView.findViewById(R.id.date);
        }

        public void bind(final Bill bill) {
            number.setText(bill.getNumber() + "");
            from.setText(bill.getProvider().getName());
            to.setText(bill.getCustomer().getName());
            cost.setText(bill.getCost() + "");
            date.setText(bill.getDate());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BillViewActivity.class);
                    intent.putExtra("bill", bill);
                    context.startActivity(intent);
                }
            });
        }
    }

}
