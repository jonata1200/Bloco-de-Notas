package com.example.blocodenotasjava;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.blocodenotasjava.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String pastaAnotacao = "Anotacao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        binding.btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String anotacao = binding.editAnotacao.getText().toString();

                if (anotacao.isEmpty()){

                    Toast.makeText(MainActivity.this, "Preencha com algum Texto!", Toast.LENGTH_SHORT).show();

                }else {

                    salvarAnotacao(anotacao);
                    snackBar(v);

                }

            }
        });

    }

public void snackBar(View view){

    Snackbar snackbar = Snackbar.make(view, "Anotação salva!", Snackbar.LENGTH_INDEFINITE);
    snackbar.setAction("OK", new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    snackbar.setActionTextColor(getColor(R.color.blue));
    snackbar.setBackgroundTint(getColor(R.color.black));
    snackbar.setTextColor(getColor(R.color.white));
    snackbar.show();

}

public void salvarAnotacao(String anotacao){

    SharedPreferences preferencias = getSharedPreferences(pastaAnotacao, MODE_PRIVATE);
    SharedPreferences.Editor editor = preferencias.edit();
    editor.putString("TextoGuardado", anotacao);
    editor.apply();

}

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferencias = getSharedPreferences(pastaAnotacao, MODE_PRIVATE);
        String anotacaoRecuperada = preferencias.getString("TextoGuardado", "");


        if (!anotacaoRecuperada.equals("")){

            binding.editAnotacao.setText(anotacaoRecuperada);
        }
    }
}