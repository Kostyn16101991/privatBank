package ua.step.kostyn.retrofitfich;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by konstantin on 29.05.17.
 */

public class RetrofitConfig {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.privatbank.ua")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private APINetworck apiNetworck = retrofit.create(APINetworck.class);

    public APINetworck getApiNetworck(){
        return apiNetworck;
    }

}
