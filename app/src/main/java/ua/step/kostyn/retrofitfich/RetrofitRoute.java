package ua.step.kostyn.retrofitfich;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by konstantin on 03.06.17.
 */

public class RetrofitRoute {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private APINetworck retrofitArea = retrofit.create(APINetworck.class);

    public APINetworck getRetrofitArea() {
        return retrofitArea;
    }
}
