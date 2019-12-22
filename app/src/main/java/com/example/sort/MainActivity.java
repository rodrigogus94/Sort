package com.example.sort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);


        addSlide(new FragmentSlide.Builder()
                .background(R.color.light_green)
                .fragment(R.layout.slider_1)
                .build()

        );

        addSlide(new FragmentSlide.Builder()
                .background(R.color.Light_blue)
                .fragment(R.layout.slider_2)
                .build()

        );

        addSlide(new FragmentSlide.Builder()
                .background(R.color.light_purple)
                .fragment(R.layout.slider_3)
                .build()

        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.Light_red)
                .fragment(R.layout.slider_4)
                .build()

        );

        addSlide(new FragmentSlide.Builder()
                .background(R.color.light_blue_bb)
                .fragment(R.layout.slider_cadastro)
                .canGoForward(false)
                .build()

        );

    }

    public void btCadastrar(View view){

    }

    public void btEntrar(View view){
       // startActivity(new Intent());
    }
}
