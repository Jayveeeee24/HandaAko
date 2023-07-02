package com.artemis.handaako.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artemis.handaako.CustomSpaceItemDecoration;
import com.artemis.handaako.R;
import com.artemis.handaako.adapter_list_learn.LearnItem;
import com.artemis.handaako.adapter_list_learn.LearnRecyclerAdapter;

import java.util.ArrayList;

public class LearnFragment extends Fragment {
    Context context;
    View view;

    RecyclerView learnRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_learn, container, false);
        context = container.getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        learnRecycler = view.findViewById(R.id.learn_recyclerview);
        InitializeRecyclerView();
    }

    private void InitializeRecyclerView() {
        ArrayList<LearnItem> learnItems = new ArrayList<>();

        String[] itemName = {"Lindol", "Bagyo", "Pagbaha", "Pagputok ng Bulkan", "Landslide", "Tsunami", "Sunog"};
        @SuppressLint("DiscouragedApi") Drawable[] images = {
                ContextCompat.getDrawable(context, R.drawable.earthqauke),
                ContextCompat.getDrawable(context, R.drawable.typhoon),
                ContextCompat.getDrawable(context, R.drawable.flood),
                ContextCompat.getDrawable(context, R.drawable.vocalno),
                ContextCompat.getDrawable(context, R.drawable.landslide_2),
                ContextCompat.getDrawable(context, R.drawable.tsunano2),
                ContextCompat.getDrawable(context, R.drawable.sunog),
        };
        @SuppressLint("DiscouragedApi") int[] bgColor = {
                ContextCompat.getColor(context, R.color.pumpkin),
                ContextCompat.getColor(context, R.color.green_sea),
                ContextCompat.getColor(context, R.color.peter_river),
                ContextCompat.getColor(context, R.color.wisteria),
                ContextCompat.getColor(context, R.color.sunflower),
                ContextCompat.getColor(context, R.color.pomegranate),
                ContextCompat.getColor(context, R.color.silver),
        };

        for (int i =0; i<itemName.length; i++) {
            learnItems.add(new LearnItem(itemName[i], images[i], bgColor[i]));
        }

        int numColumns = 2;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, numColumns);
        learnRecycler.setLayoutManager(gridLayoutManager);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        LearnRecyclerAdapter adapter = new LearnRecyclerAdapter(fragmentManager);
        adapter.setLearnItems(learnItems);
        learnRecycler.setAdapter(adapter);

        int spacing = 16;
        boolean includeEdge = true;
        learnRecycler.addItemDecoration(new CustomSpaceItemDecoration(numColumns, spacing, includeEdge));


    }
}