package ua.step.kostyn.retrofitfich;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import ua.step.kostyn.retrofitfich.fragments.DataFragment;
import ua.step.kostyn.retrofitfich.fragments.MapFragment;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(MapFragment.newInstance());

    }
    public void changeFragment(Fragment fragment){
        this.fragment = fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
