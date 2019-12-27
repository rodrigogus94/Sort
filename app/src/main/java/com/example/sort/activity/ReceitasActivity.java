package com.example.sort.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sort.R;
import com.example.sort.config.ConfiguracaoFireBase;
import com.example.sort.helper.Base64Custom;
import com.example.sort.helper.DataCustom;
import com.example.sort.model.Movimentacao;
import com.example.sort.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ReceitasActivity extends AppCompatActivity {


    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campovalor;
    private Movimentacao movimentacao;
    private DatabaseReference firebaseRef = ConfiguracaoFireBase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFireBase.getFirebaseAutenticacao();
    private Double receitaTotal, despesaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescricao);
        campovalor = findViewById(R.id.editValor);

        //preencher a data com a data atual
        campoData.setText(DataCustom.dataAtual());
        recuperaReceitaTotal();

    }

    public void salvarReceita(View view){

        if(validarCampoReceita()){

            movimentacao = new Movimentacao();
            String data = campoData.getText().toString();
            Double valorRecuperado = Double.parseDouble(campovalor.getText().toString());
            movimentacao.setValor(valorRecuperado);
            movimentacao.setData(data);
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setTipo("r");

            Double receitaAtualizado = receitaTotal + valorRecuperado;
            atualizarReceita(receitaAtualizado);
            movimentacao.salvarMovimentacao(data);

           /* if(despesaTotal >= 0){
                Double novaReceita = receitaTotal - despesaTotal;
                atualizarReceitaComDespesa(novaReceita);
            }
*/

        }
    }

    public boolean validarCampoReceita(){

        String textoData = campoData.getText().toString();
        String textoCategoria = campoCategoria.getText().toString();
        String textoDescricao = campoDescricao.getText().toString();
        String textoValor = campovalor.getText().toString();

        //Validar se os campos foram preenchidos
        if(textoData.isEmpty()){
            Toast.makeText(ReceitasActivity.this,
                    "Data não preenchida!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(textoCategoria.isEmpty()){
            Toast.makeText(ReceitasActivity.this,
                    "Categoria não preenchida!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(textoDescricao.isEmpty()){
            Toast.makeText(ReceitasActivity.this,
                    "Descricação não preenchida!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(textoValor.isEmpty()){
            Toast.makeText(ReceitasActivity.this,
                    "Valor não preenchodo!", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }

    }

    public void recuperaReceitaTotal(){

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificaBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                receitaTotal = usuario.getReceitaTotal();
               // despesaTotal = usuario.getDespesaTotal();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void atualizarReceita(Double receita){

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificaBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.child("receitaTotal").setValue(receita);
    }

    public void atualizarReceitaComDespesa(Double novaReceita){

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificaBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.child("receitaTotal").setValue(novaReceita);

    }

}
