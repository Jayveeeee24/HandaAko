package com.artemis.handaako.adapter_list_learn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.artemis.handaako.R;
import com.artemis.handaako.fragments.EmergencyFragment;
import com.artemis.handaako.fragments.LearnFragment;
import com.artemis.handaako.fragments.LearnViewFragment;
import com.artemis.handaako.fragments.QuizFragment;

import java.util.ArrayList;

import me.ibrahimsn.lib.SmoothBottomBar;

public class LearnRecyclerAdapter extends RecyclerView.Adapter<LearnRecyclerAdapter.ViewHolder> {

    private ArrayList<LearnItem> learnItems = new ArrayList<>();
    private final FragmentManager fragmentManager;
    public LearnRecyclerAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public LearnRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learn_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnRecyclerAdapter.ViewHolder holder,  int position) {
        if (learnItems.get(position).getItemName().equals("Pagputok ng Bulkan")){
            float textSizeSP = 20;
            float textSizePX = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSizeSP, holder.item_learn_name.getResources().getDisplayMetrics());

            holder.item_learn_name.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePX);
        }else{
            float textSizeSP = 30;
            float textSizePX = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSizeSP, holder.item_learn_name.getResources().getDisplayMetrics());

            holder.item_learn_name.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePX);
        }
        holder.item_learn_name.setText(learnItems.get(position).getItemName());

        String itemName =learnItems.get(position).getItemName();
        holder.item_learn_image.setImageDrawable(learnItems.get(position).getItemImage());
        holder.item_learn_color.setBackgroundColor(learnItems.get(position).getItemColor());
        holder.card_learn_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new LearnViewFragment();
                Bundle bundle = new Bundle();
                bundle.putString("itemName", itemName);
                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return learnItems.size();
    }
    public void setLearnItems(ArrayList<LearnItem> learnItems) {
        this.learnItems = learnItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        CardView card_learn_item;
        TextView item_learn_name;
        RelativeLayout item_learn_color;
        ImageView item_learn_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_learn_name = itemView.findViewById(R.id.item_learn_title);
            item_learn_image = itemView.findViewById(R.id.item_learn_image);
            item_learn_color = itemView.findViewById(R.id.card_learn_color);
            card_learn_item = itemView.findViewById(R.id.card_learn_item);
        }
    }
}
