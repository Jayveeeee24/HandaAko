package com.artemis.handaako.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.airbnb.lottie.LottieAnimationView;
import com.artemis.handaako.LocalityData;
import com.artemis.handaako.MainActivity;
import com.artemis.handaako.R;
import com.artemis.handaako.SirenActivity;
import com.artemis.handaako.adapter_list_emergency.EmergencyContactItem;
import com.artemis.handaako.adapter_list_emergency.EmergencyContactRecyclerAdapter;
import com.artemis.handaako.adapter_list_home.HomeItem;
import com.artemis.handaako.adapter_list_home.HomeRecyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmergencyFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Context context;
    View view;
    LocalityData localityData;
    RecyclerView emergencyRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_emergency, container, false);
        context = view.getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        localityData = new LocalityData();
        emergencyRecycler = view.findViewById(R.id.emergency_recylerview);

        LottieAnimationView sirenAnimation = view.findViewById(R.id.siren_animation);
        sirenAnimation.setMinAndMaxProgress(0f, 1.0f);
        sirenAnimation.playAnimation();

        sirenAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SirenActivity.class);
                startActivity(intent);
            }
        });

        InitializeRecyclerView();

    }

    private void InitializeRecyclerView() {
        Spinner change_emergency_spinner = view.findViewById(R.id.change_emergency_spinner);


        List<String> localities = new ArrayList<>();
        Collections.addAll(localities, localityData.locality);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, localities);
        change_emergency_spinner.setAdapter(dataAdapter);
        change_emergency_spinner.setSelection(0);
        change_emergency_spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ArrayList<EmergencyContactItem> emergencyContactItems = new ArrayList<>();

        if (adapterView.getItemAtPosition(i).toString().equals("Kawit")){
            for (int j =0; j<7; j++) {
                emergencyContactItems.add(new EmergencyContactItem(localityData.kawitContactName[j], localityData.kawitContactNumber[j]));
            }
        }else if (adapterView.getItemAtPosition(i).toString().equals("Cavite City")){
            for (int j =0; j<2; j++) {
                emergencyContactItems.add(new EmergencyContactItem(localityData.caviteCityName[j], localityData.caviteCityNumber[j]));
            }
        }else if (adapterView.getItemAtPosition(i).toString().equals("Rosario")){
            for (int j =0; j<3; j++) {
                emergencyContactItems.add(new EmergencyContactItem(localityData.rosarioName[j], localityData.rosarioNumber[j]));
            }
        }else if (adapterView.getItemAtPosition(i).toString().equals("Tanza")){
            for (int j =0; j<6; j++) {
                emergencyContactItems.add(new EmergencyContactItem(localityData.tanzaName[j], localityData.tanzaNumber[j]));
            }
        }else if (adapterView.getItemAtPosition(i).toString().equals("Noveleta")){
            for (int j =0; j<3; j++) {
                emergencyContactItems.add(new EmergencyContactItem(localityData.noveletaName[j], localityData.noveletaNumber[j]));
            }
        }else if (adapterView.getItemAtPosition(i).toString().equals("Imus")){
            for (int j =0; j<5; j++) {
                emergencyContactItems.add(new EmergencyContactItem(localityData.imusName[j], localityData.imusNumber[j]));
            }
        }else if (adapterView.getItemAtPosition(i).toString().equals("Bacoor")){
            for (int j =0; j<2; j++) {
                emergencyContactItems.add(new EmergencyContactItem(localityData.bacoorName[j], localityData.bacoorNumber[j]));
            }
        }

        EmergencyContactRecyclerAdapter adapter = new EmergencyContactRecyclerAdapter(this);
        adapter.setEmergencyContactItems(emergencyContactItems);
        emergencyRecycler.setAdapter(adapter);
        emergencyRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}