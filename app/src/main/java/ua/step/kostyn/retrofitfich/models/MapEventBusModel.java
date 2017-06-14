package ua.step.kostyn.retrofitfich.models;

import java.util.ArrayList;
import java.util.List;

import ua.step.kostyn.retrofitfich.models.DepartmentModel;

/**
 * Created by konstantin on 31.05.17.
 */

public class MapEventBusModel {
    public List<DepartmentModel> list = new ArrayList<>();

    public MapEventBusModel(List<DepartmentModel> list) {
        this.list = list;
    }

    public List<DepartmentModel> getList() {
        return list;
    }

    public void setList(List<DepartmentModel> list) {
        this.list = list;
    }
}
