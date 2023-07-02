package com.artemis.handaako.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artemis.handaako.MainActivity;
import com.artemis.handaako.R;
import com.artemis.handaako.adapter_list_home.HomeItem;
import com.artemis.handaako.adapter_list_home.HomeRecyclerAdapter;

import java.util.ArrayList;
import java.util.Objects;

import me.ibrahimsn.lib.SmoothBottomBar;

public class HomeFragment extends Fragment {

    Context context;
    View view;

    RecyclerView homeRecycler;
    SmoothBottomBar smoothBottomBar;


    public HomeFragment(SmoothBottomBar smoothBottomBar) {
        this.smoothBottomBar = smoothBottomBar;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = container.getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        homeRecycler = view.findViewById(R.id.home_recyclerview);
        InitializeRecyclerView();
    }
    private void InitializeRecyclerView(){
        ArrayList<HomeItem> homeItems = new ArrayList<>();

        String[] itemName = {"Matuto", "Pagsusulit", "Emergency"};
        String[] itemDescription = {"Pag aralan ang iba't ibang\nnatural na kalamidad", "Subukin ang iyong nalalaman\nat alamin ang iyong kakayahan", "Alamin kung paano ang \ndapat gawin pag may sakuna"};
        @SuppressLint("DiscouragedApi") Drawable[] images = {ContextCompat.getDrawable(context, R.drawable.dash_kids), ContextCompat.getDrawable(context, R.drawable.dash_quiz), ContextCompat.getDrawable(context, R.drawable.ewan) };
        @SuppressLint("DiscouragedApi") int[] bgColor = {ContextCompat.getColor(context, R.color.alizarin), ContextCompat.getColor(context, R.color.wisteria), ContextCompat.getColor(context, R.color.carrot)};
        for (int i =0; i<itemName.length; i++) {
            homeItems.add(new HomeItem(itemName[i], itemDescription[i], bgColor[i], images[i]));
        }

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        HomeRecyclerAdapter adapter = new HomeRecyclerAdapter(fragmentManager, smoothBottomBar);
        adapter.setHomeItems(homeItems);
        homeRecycler.setAdapter(adapter);
        homeRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }


}