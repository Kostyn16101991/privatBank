package ua.step.kostyn.retrofitfich.models;

import java.util.ArrayList;
import java.util.List;

import ua.step.kostyn.retrofitfich.models.CurrencyModel;

/**
 * Created by konstantin on 29.05.17.
 */

public class EventBusModel {

        private List<CurrencyModel> list = new ArrayList<>();

    public EventBusModel(List<CurrencyModel> list) {
        this.list = list;
    }

    public List<CurrencyModel> getList() {
        return list;
    }

    public void setList(List<CurrencyModel> list) {
        this.list = list;
    }
}
