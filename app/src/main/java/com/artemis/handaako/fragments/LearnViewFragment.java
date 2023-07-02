package com.artemis.handaako.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.artemis.handaako.R;

public class LearnViewFragment extends Fragment {

    Context context;
    View view;
    String itemName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_learn_view, container, false);
        context = container.getContext();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getArguments() != null) {
            itemName = getArguments().getString("itemName");
        }


        String[] itemNames = {"Lindol", "Sunog", "Bagyo", "Pagbaha", "Pagputok ng Bulkan", "Landslide", "Tsunami"};
        String[] itemDescription = {
                "Ang lindol ay ang mahina hanggang sa malakas na pagyanig ng lupa dulot ng biglaang paggalaw ng mga bato sa ilalim nito.",
                "Ang sunog ay isang pangyayari dulot ng mabilis at hindi mapigilang pagkalat ng apoy.",
                "Ang Bagyo ay nagdadala ng mga malakas na hangin at mabigat na buhos ng ulan.",
                "Ang baha ay nagaganap kapag ang mga tubig mula sa ulan, ilog, o iba pang pinagmumulan ng tubig ay umaabot o tumataas nang labis sa normal na antas nito.",
                "Ang mga pagsabog ng bulkan ay mga pangyayaring nagaganap dahil sa mga deposito ng magma sa bituka ng lupa na itinutulak palabas ng mga high-pressure na gas",
                "Ang landslide ay ang paggalaw o pagguho ng lupa at bato pababa mula sa isang mataas na lugar na kadalasan ay dulot ng malakas na pag-ulan.",
                "Ang tsunami ay makasunod-sunod na napakalaking alon sa karagatan dulot ng mga lindol, pagguho ng lupa sa ilalim ng dagat, pagsabog ng bulkan o mga asteroid."
        };
        Drawable[] itemImage = {
                ContextCompat.getDrawable(context, R.drawable.earthquake),
                ContextCompat.getDrawable(context, R.drawable.sunog2),
                ContextCompat.getDrawable(context, R.drawable.typhoon2),
                ContextCompat.getDrawable(context, R.drawable.flood2),
                ContextCompat.getDrawable(context, R.drawable.volcano2),
                ContextCompat.getDrawable(context, R.drawable.landslide),
                ContextCompat.getDrawable(context, R.drawable.tsunano)
        };
        Drawable[] itemAddImage = {
                ContextCompat.getDrawable(context, R.drawable.earthquake_magnitude_scale),
                ContextCompat.getDrawable(context, R.drawable.causes_of_fire),
                ContextCompat.getDrawable(context, R.drawable.rainfall_level),
                ContextCompat.getDrawable(context, R.drawable.storm_surge_levek),
                ContextCompat.getDrawable(context, R.drawable.volcano_alert_level),
                ContextCompat.getDrawable(context, R.drawable.landslide_warning_signs),
                ContextCompat.getDrawable(context, R.drawable.tsunami_alerts)
        };
        int[] itemVideo = {
                R.raw.lindol_preparedness,
                R.raw.sunog_preparedness,
                R.raw.bagyo_baha_preparedness,
                R.raw.bagyo_baha_preparedness,
                R.raw.volcanic_preparedness,
                R.raw.landslide_preparedness,
                R.raw.tsunami_preparedness,
        };
        String[] itemReference = {
                "PHILVOCS",
                "DILG PH",
                "DOST PAGASA",
                "DOST PAGASA",
                "PHILVOCS",
                "DILG PH",
                "READY GOV"
        };
        ScrollView scrollView = view.findViewById(R.id.scrollview);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.smoothScrollTo(0, 0);
            }
        });
        VideoView videoView = view.findViewById(R.id.learn_view_video);
        TextView learn_view_title = view.findViewById(R.id.learn_view_title);
        TextView learn_view_description = view.findViewById(R.id.learn_view_description);
        ImageView learn_view_image = view.findViewById(R.id.learn_view_image);
        ImageView learn_view_add_image = view.findViewById(R.id.learn_view_add_image);
        TextView learn_video_title = view.findViewById(R.id.learn_video_title);
        TextView learn_video_reference = view.findViewById(R.id.learn_video_reference);

        int index = -1;

        for (int i = 0; i < itemNames.length; i++) {
            if (itemNames[i].equals(itemName)) {
                index = i;
                break;
            }
        }

        learn_view_title.setText(itemName);
        learn_view_description.setText(itemDescription[index]);
        learn_view_image.setImageDrawable(itemImage[index]);
        learn_view_add_image.setImageDrawable(itemAddImage[index]);
        learn_video_title.setText(String.format("Paano ba maghanda kapag may %s", itemName));
        learn_video_reference.setText(String.format("Mula sa %s at Knowledge Channel", itemReference[index]));

        MediaController mediaController = new MediaController(context);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" + itemVideo[index]));
    }
}