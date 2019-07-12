package com.gmail.profbird.simplerecyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.ContactViewHolder> {

    private final ArrayList<HashMap<String, String>> mValues;

    // Provide access to all the views for a data item
    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        // each data item is a HashMap containing three key-value pairs
        public TextView mFirstNameTextView;
        public TextView mLastNameTextView;
        public TextView mPhoneTextView;
        public View mLayout;

        public ContactViewHolder(View v) {
            super(v);
            mLayout = v;
            mFirstNameTextView = v.findViewById(R.id.firstNameTextView);
            mLastNameTextView = v.findViewById(R.id.lastNameTextView);
            mPhoneTextView = v.findViewById(R.id.phoneTextView);
        }
    }

    public ContactRecyclerViewAdapter(ArrayList<HashMap<String, String>> items) {
        mValues = items;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, final int position) {
        HashMap<String, String> map = mValues.get(position);
        holder.mFirstNameTextView.setText(map.get("FirstName"));
        holder.mLastNameTextView.setText(map.get("LastName"));
        holder.mPhoneTextView.setText(map.get("Phone"));

        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Row " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

