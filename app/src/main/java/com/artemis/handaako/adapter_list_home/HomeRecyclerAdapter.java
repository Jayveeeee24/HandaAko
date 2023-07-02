package com.artemis.handaako.adapter_list_home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.artemis.handaako.MainActivity;
import com.artemis.handaako.R;
import com.artemis.handaako.fragments.EmergencyFragment;
import com.artemis.handaako.fragments.HomeFragment;
import com.artemis.handaako.fragments.LearnFragment;
import com.artemis.handaako.fragments.QuizFragment;

import java.util.ArrayList;

import me.ibrahimsn.lib.SmoothBottomBar;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {
    private ArrayList<HomeItem> homeItems = new ArrayList<>();
    private final FragmentManager fragmentManager;
    private final SmoothBottomBar smoothBottomBar;

    public HomeRecyclerAdapter(FragmentManager fragmentManager, SmoothBottomBar smoothBottomBar) {
        this.fragmentManager = fragmentManager;
        this.smoothBottomBar = smoothBottomBar;
    }

    @NonNull
    @Override
    public HomeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerAdapter.ViewHolder holder, int position) {
        holder.item_home_title.setText(homeItems.get(position).getName());
        holder.item_home_description.setText(homeItems.get(position).getDescription());
        holder.item_home_image.setImageDrawable(homeItems.get(position).getImageURL());
        holder.item_color.setBackgroundColor(homeItems.get(position).getBgColor());
        holder.cardHomeItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Fragment fragment;
                smoothBottomBar.setItemActiveIndex(holder.getAdapterPosition() + 1);
                if (holder.getAdapterPosition() == 0){
                    fragment = new LearnFragment();
                } else if (holder.getAdapterPosition() == 1) {
                    fragment = new QuizFragment();
                } else{
                    fragment = new EmergencyFragment();
                }
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame_layout, fragment);
                fragmentTransaction.commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return homeItems.size();
    }

    public void setHomeItems(ArrayList<HomeItem> homeItems) {
        this.homeItems = homeItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardHomeItem;
        TextView item_home_title, item_home_description;
        ImageView item_home_image;
        RelativeLayout item_color;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_home_title = itemView.findViewById(R.id.item_home_title);
            item_home_description = itemView.findViewById(R.id.item_home_description);
            item_home_image = itemView.findViewById(R.id.item_home_image);
            item_color = itemView.findViewById(R.id.card_color);
            cardHomeItem = itemView.findViewById(R.id.card_home_item);
        }
    }
}
