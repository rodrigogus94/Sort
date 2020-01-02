package com.example.sort.model;




import com.example.sort.config.ConfiguracaoFireBase;
import com.example.sort.helper.Base64Custom;
import com.example.sort.helper.DataCustom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Movimentacao {

    private String data;
    private String categoria;
    private String descricao;
    private String tipo;
    private double valor;
    private String key;

    public Movimentacao() {
    }

    public void salvarMovimentacao(String dataEscolhida){

        FirebaseAuth autenticacao = ConfiguracaoFireBase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificaBase64(autenticacao.getCurrentUser().getEmail());
        String mesAno = DataCustom.mesAnoDataEscolhida( dataEscolhida );



        DatabaseReference firebase = ConfiguracaoFireBase.getFirebaseDatabase();
        firebase.child("movimentacao")
                .child( idUsuario )
                .child( mesAno )
                .push()
                .setValue(this);

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
