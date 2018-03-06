package br.com.fiap.mymarriageapp.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.mymarriageapp.Api.BudgetApi;
import br.com.fiap.mymarriageapp.Model.Budget;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SaveBudgetActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etLocalizacao;
    private EditText etValor;
    private EditText etServicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_budget);

        etNome = (EditText) findViewById(R.id.etNome);
        etLocalizacao = (EditText) findViewById(R.id.etLocalizacao);
        etValor = (EditText) findViewById(R.id.etValor);
        etServicos = (EditText) findViewById(R.id.etServicos);
    }

    public void salvar(View v){
        BudgetApi api = getRetrofit().create(Budget.class);

        Budget budget = new Budget();
        budget.setNome(etNome.getText().toString());
        budget.setLocalizacao(etLocalizacao.getText().toString());
        budget.setValor(etValor.getText().toString());
        budget.setServicos(etServicos.getText().toString());

        api.salvar(budget).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MainActivity.this, "Gravado com Sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi possivel Gravar!", Toast.LENGTH_SHORT).show();
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
