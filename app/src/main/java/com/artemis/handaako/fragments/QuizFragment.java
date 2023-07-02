package com.artemis.handaako.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.artemis.handaako.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizFragment extends Fragment {

    Context context;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quiz, container, false);
        context = view.getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpQuiz();
    }

    private void setUpQuiz(){
        ImageView quiz_image = view.findViewById(R.id.quiz_image);
        CardView choice1, choice2, choice3, choice4;
        choice1 = view.findViewById(R.id.choice1_button);
        choice2 = view.findViewById(R.id.choice2_button);
        choice3 = view.findViewById(R.id.choice3_button);
        choice4 = view.findViewById(R.id.choice4_button);
        TextView choice1Text, choice2Text, choice3Text, choice4Text;
        choice1Text = view.findViewById(R.id.choice1_label);
        choice2Text = view.findViewById(R.id.choice2_label);
        choice3Text = view.findViewById(R.id.choice3_label);
        choice4Text = view.findViewById(R.id.choice4_label);

        Random random = new Random();
        int randomNumber = random.nextInt(6);

        String[] disasters = {"Lindol", "Sunog", "Bagyo", "Baha", "Pagputok ng Bulkan", "Landslide", "Tsunami"};

        String answer = disasters[randomNumber];

        @SuppressLint("DiscouragedApi") Drawable[] lindolImages = {
                ContextCompat.getDrawable(context, R.drawable.lindol1),
                ContextCompat.getDrawable(context, R.drawable.lindol2),
                ContextCompat.getDrawable(context, R.drawable.lindol3),
        };
        @SuppressLint("DiscouragedApi") Drawable[] sunogImages = {
                ContextCompat.getDrawable(context, R.drawable.sunog1),
                ContextCompat.getDrawable(context, R.drawable.sunogg2),
                ContextCompat.getDrawable(context, R.drawable.sunog3),
        };
        @SuppressLint("DiscouragedApi") Drawable[] bagyoImages = {
                ContextCompat.getDrawable(context, R.drawable.bagyo1),
                ContextCompat.getDrawable(context, R.drawable.bagyo2),
                ContextCompat.getDrawable(context, R.drawable.bagyo3),
        };
        @SuppressLint("DiscouragedApi") Drawable[] bahaImages = {
                ContextCompat.getDrawable(context, R.drawable.baha1),
                ContextCompat.getDrawable(context, R.drawable.baha2),
                ContextCompat.getDrawable(context, R.drawable.baha3),
        };
        @SuppressLint("DiscouragedApi") Drawable[] bulkanImages = {
                ContextCompat.getDrawable(context, R.drawable.bulkan1),
                ContextCompat.getDrawable(context, R.drawable.bulkan2),
                ContextCompat.getDrawable(context, R.drawable.bulkan3),
        };
        @SuppressLint("DiscouragedApi") Drawable[] landslideImages = {
                ContextCompat.getDrawable(context, R.drawable.landslide1),
                ContextCompat.getDrawable(context, R.drawable.landslide2),
                ContextCompat.getDrawable(context, R.drawable.landslide3),
        };
        @SuppressLint("DiscouragedApi") Drawable[] tsunamiImages = {
                ContextCompat.getDrawable(context, R.drawable.tsunami1),
                ContextCompat.getDrawable(context, R.drawable.tsunami2),
                ContextCompat.getDrawable(context, R.drawable.tsunami3),
        };

        int randomImage = new Random().nextInt(3);
        switch (answer) {
            case "Lindol":
                quiz_image.setImageDrawable(lindolImages[randomImage]);
                break;
            case "Sunog":
                quiz_image.setImageDrawable(sunogImages[randomImage]);
                break;
            case "Bagyo":
                quiz_image.setImageDrawable(bagyoImages[randomImage]);
                break;
            case "Baha":
                quiz_image.setImageDrawable(bahaImages[randomImage]);
                break;
            case "Pagputok ng Bulkan":
                quiz_image.setImageDrawable(bulkanImages[randomImage]);
                break;
            case "Landslide":
                quiz_image.setImageDrawable(landslideImages[randomImage]);
                break;
            case "Tsunami":
                quiz_image.setImageDrawable(tsunamiImages[randomImage]);
                break;
        }
        int randomPlace = new Random().nextInt(4);
        switch (randomPlace){
            case 0:
                choice1Text.setText(answer);
                break;
            case 1:
                choice2Text.setText(answer);
                break;
            case 2:
                choice3Text.setText(answer);
                break;
            case 3:
                choice4Text.setText(answer);
                break;
        }

        List<String> disasterList = new ArrayList<>(Arrays.asList(disasters));
        disasterList.remove(answer);
        Collections.shuffle(disasterList);

        // Get the remaining random values for the other TextViews
        for (int i = 0; i < 4; i++) {
            String randomDisaster = disasterList.get(i);

            // Assign the random disaster to the corresponding TextView
            switch (i) {
                case 0:
                    if (choice2Text.getText().toString().isEmpty()) {
                        choice2Text.setText(randomDisaster);
                    }
                    break;
                case 1:
                    if (choice3Text.getText().toString().isEmpty()) {
                        choice3Text.setText(randomDisaster);
                    }
                    break;
                case 2:
                    if (choice4Text.getText().toString().isEmpty()) {
                        choice4Text.setText(randomDisaster);
                    }
                    break;
                case 3:
                    if (choice1Text.getText().toString().isEmpty()) {
                        choice1Text.setText(randomDisaster);
                    }
                    break;
            }
        }
        final Dialog quizResult = new Dialog(context);
        quizResult.setContentView(R.layout.popup_quiz_result);
        quizResult.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        quizResult.setCanceledOnTouchOutside(false);
        quizResult.setCancelable(false);
        final TextView greeting_title = quizResult.findViewById(R.id.greeting_title);
        final TextView greeting_description = quizResult.findViewById(R.id.greeting_description);
        final LottieAnimationView greeting_animation = quizResult.findViewById(R.id.greeting_animation);
        greeting_animation.setMinAndMaxProgress(0f, 1.0f);
        greeting_animation.playAnimation();
        final CardView greeting_button = quizResult.findViewById(R.id.greeting_dismiss_button);

        greeting_description.setText(String.format("Ang sagot ay %s", answer));
        greeting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizResult.dismiss();
                setUpQuiz();
            }
        });
        choice1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                Toast.makeText(context, choice1Text.getText().toString() + answer, Toast.LENGTH_SHORT).show();
                if (choice1Text.getText().toString().equals(answer)){
                    greeting_title.setText("CONGRATULATIONS");
                    greeting_animation.setAnimation(R.raw.congratulations);
                }else{
                    greeting_title.setText("SUBUKAN NALANG ULI");
                    greeting_animation.setAnimation(R.raw.try_again);
                }
                quizResult.show();
            }
        });
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, choice2Text.getText().toString() + answer, Toast.LENGTH_SHORT).show();
                if (choice2Text.getText().toString().equals(answer)){
                    greeting_title.setText("CONGRATULATIONS");
                    greeting_animation.setAnimation(R.raw.congratulations);
                }else{
                    greeting_title.setText("SUBUKAN NALANG ULI");
                    greeting_animation.setAnimation(R.raw.try_again);
                }
                quizResult.show();
            }
        });
        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, choice3Text.getText().toString() + answer, Toast.LENGTH_SHORT).show();
                if (choice3Text.getText().toString().equals(answer)){
                    greeting_title.setText("CONGRATULATIONS");
                    greeting_animation.setAnimation(R.raw.congratulations);
                }else{
                    greeting_title.setText("SUBUKAN NALANG ULI");
                    greeting_animation.setAnimation(R.raw.try_again);
                }
                quizResult.show();
            }
        });
        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, choice4Text.getText().toString() + answer, Toast.LENGTH_SHORT).show();
                if (choice4Text.getText().toString().equals(answer)){
                    greeting_title.setText("CONGRATULATIONS");
                    greeting_animation.setAnimation(R.raw.congratulations);
                }else{
                    greeting_title.setText("SUBUKAN NALANG ULI");
                    greeting_animation.setAnimation(R.raw.try_again);
                }
                quizResult.show();
            }
        });
    }
}