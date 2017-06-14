package ua.step.kostyn.retrofitfich;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import ua.step.kostyn.retrofitfich.models.RequestRoutModel;
import ua.step.kostyn.retrofitfich.models.CurrencyModel;
import ua.step.kostyn.retrofitfich.models.DepartmentModel;

/**
 * Created by konstantin on 29.05.17.
 */

public interface APINetworck {
    @GET("p24api/pubinfo?json&exchange&")
    Call<List<CurrencyModel>> getCurrencyModel(@Query("coursid") int courcedId);

    @GET("p24api/pboffice?json")
    Call<List<DepartmentModel>> getDepartmentModel (@Query("city") String city, @Query("address") String address);

    @GET("maps/api/directions/json")
    Call<RequestRoutModel> getRequestRoutModel (@QueryMap Map<String, String> map);
}
