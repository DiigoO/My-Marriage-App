package br.com.fiap.mymarriageapp.Api;

import br.com.fiap.mymarriageapp.Model.Budget;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BudgetApi {

    @GET("/budget/nome/{nome}")
    Call<BudgetApi> findBudget(@Path("nome") String nome);

    @POST("/budget")
    Call<Void> salvar(@Body Budget budget);

}