package com.example.sort.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sort.R;
import com.example.sort.activity.CadastroActivity;
import com.example.sort.activity.LoginActivity;
import com.example.sort.config.ConfiguracaoFireBase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {


    private FirebaseAuth autenticacao;


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

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void btCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }

    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFireBase.getFirebaseAutenticacao();

        if(autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));

    }

}
