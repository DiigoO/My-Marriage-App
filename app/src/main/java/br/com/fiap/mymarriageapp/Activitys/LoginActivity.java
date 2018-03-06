package br.com.fiap.mymarriageapp.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.fiap.mymarriageapp.Api.UserApi;
import br.com.fiap.mymarriageapp.Model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etSenha;
    private Button btLogin;
    private TextView tvMensagemStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = findViewById(R.id.etLogin);
        etSenha = findViewById(R.id.etSenha);
        btLogin = findViewById(R.id.btLogin);
        tvMensagemStatus = findViewById(R.id.tvMensagemStatus);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = etLogin.getText().toString();
                String senha = etSenha.getText().toString();

                UserApi api = getRetrofit().create(UserApi.class);

                User user = new User();
                user.setNome(login);
                user.setPassword(senha);

                Boolean res = api.salvar(user).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Não foi possivel completar o Login!", Toast.LENGTH_SHORT).show();
                    }
                });

                if(res){
                    //LOGADO :)
                }
            }
        });

    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://app-mymarriage.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
