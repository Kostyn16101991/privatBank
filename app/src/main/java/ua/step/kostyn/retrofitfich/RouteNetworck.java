package ua.step.kostyn.retrofitfich;

import ua.step.kostyn.retrofitfich.fragments.MapFragment;

/**
 * Created by konstantin on 03.06.17.
 */

public class RouteNetworck {
    private APINetworck apiNetworck;

    public RouteNetworck() {
        apiNetworck = new RetrofitRoute().getRetrofitArea();
    }

    public void getRequestRoutModel() {
        //System.out.println(MapFragment.routeArray.length);
    }
}
