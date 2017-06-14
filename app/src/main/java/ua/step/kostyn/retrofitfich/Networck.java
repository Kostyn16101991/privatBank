package ua.step.kostyn.retrofitfich;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.step.kostyn.retrofitfich.models.CurrencyModel;
import ua.step.kostyn.retrofitfich.models.DepartmentModel;
import ua.step.kostyn.retrofitfich.models.EventBusModel;
import ua.step.kostyn.retrofitfich.models.MapEventBusModel;

/**
 * Created by konstantin on 29.05.17.
 */

public class Networck {
    private APINetworck apiNetworck;


    public Networck() {
        apiNetworck = new RetrofitConfig().getApiNetworck();
    }



    public void getExchangeRate(){
        Call<List<CurrencyModel>> call = apiNetworck.getCurrencyModel(3);
        call.enqueue(new Callback<List<CurrencyModel>>() {
            @Override
            public void onResponse(Call<List<CurrencyModel>> call, Response<List<CurrencyModel>> response) {
                EventBus.getDefault().post(new EventBusModel(response.body()));
                //System.out.println(Arrays.asList(response.body()));
            }

            @Override
            public void onFailure(Call<List<CurrencyModel>> call, Throwable t) {

            }
        });
    }

    public void getAddressRate(String city, String address){
        apiNetworck.getDepartmentModel(city, address).enqueue(new Callback<List<DepartmentModel>>() {
            @Override
            public void onResponse(Call<List<DepartmentModel>> call, Response<List<DepartmentModel>> response) {
                System.out.println(Arrays.asList(response.body()));
                EventBus.getDefault().post(new MapEventBusModel(response.body()));
            }

            @Override
            public void onFailure(Call<List<DepartmentModel>> call, Throwable t) {

            }
        });
    }
}
