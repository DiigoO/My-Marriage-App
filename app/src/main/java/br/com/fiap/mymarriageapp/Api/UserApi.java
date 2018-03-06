package br.com.fiap.mymarriageapp.Api;

import br.com.fiap.mymarriageapp.Model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    @GET("/user/search/{user}")
    Call<BudgetApi> findBudget(@Path("nome") String nome);

    @POST("/user/login/")
    Call<Boolean> salvar(@Body User user);
}