package com.artemis.handaako.adapter_list_emergency;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.artemis.handaako.R;
import com.artemis.handaako.adapter_list_home.HomeRecyclerAdapter;

import java.util.ArrayList;

public class EmergencyContactRecyclerAdapter extends RecyclerView.Adapter<EmergencyContactRecyclerAdapter.ViewHolder> {

    ArrayList<EmergencyContactItem> emergencyContactItems = new ArrayList<>();
    private static final int REQUEST_CALL_PHONE = 1;
    private String PHONE_NUMBER;
    private Fragment fragment;

    public EmergencyContactRecyclerAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public EmergencyContactRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emergency_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergencyContactRecyclerAdapter.ViewHolder holder, int position) {
        holder.emergency_contact_name.setText(emergencyContactItems.get(position).getContactName());
        holder.emergency_contact_number.setText(emergencyContactItems.get(position).getPhoneNumber());
        PHONE_NUMBER = emergencyContactItems.get(position).getPhoneNumber();
        holder.emergency_call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });
    }

    @Override
    public int getItemCount() {
        return emergencyContactItems.size();
    }

    public void setEmergencyContactItems(ArrayList<EmergencyContactItem> emergencyContactItems) {
        this.emergencyContactItems = emergencyContactItems;
    }

    private void makePhoneCall() {
        // Check if the CALL_PHONE permission is granted
        if (ContextCompat.checkSelfPermission(fragment.requireContext(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(fragment.requireActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE);
        } else {
            // Permission is already granted, make the phone call
            initiateCall();
        }
    }

    private void initiateCall() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + PHONE_NUMBER));
        fragment.startActivity(callIntent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout emergency_call_button;
        TextView emergency_contact_name, emergency_contact_number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            emergency_call_button = itemView.findViewById(R.id.emergency_call_button);
            emergency_contact_name = itemView.findViewById(R.id.emergency_contact_name);
            emergency_contact_number = itemView.findViewById(R.id.emergency_contact_number);
        }
    }
}
