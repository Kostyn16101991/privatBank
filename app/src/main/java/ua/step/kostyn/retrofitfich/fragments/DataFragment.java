package ua.step.kostyn.retrofitfich.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import ua.step.kostyn.retrofitfich.models.CurrencyModel;
import ua.step.kostyn.retrofitfich.models.EventBusModel;
import ua.step.kostyn.retrofitfich.R;

/**
 * Created by konstantin on 29.05.17.
 */

public class DataFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.sumEt)
    EditText sumEt;
    @BindView(R.id.fromSpinner)
    Spinner fromSpinner;
    @BindView(R.id.toSpinner)
    Spinner toSpinner;
    @BindView(R.id.resultEt)
    EditText resultEt;
    @BindView(R.id.buyRb)
    RadioButton buyRB;
    @BindView(R.id.saleRB)
    RadioButton saleRB;


    public static DataFragment newInstance() {
        return new DataFragment();
    }

    private ArrayList<String> listCcy = new ArrayList<>();
    private ArrayList<String> listBaseCcy = new ArrayList<>();
    private ArrayList<CurrencyModel> eventBus = new ArrayList<>();
    private boolean isChecked, sale;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.data_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buyRB.setOnClickListener(this);
        saleRB.setOnClickListener(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void fromEventBus(EventBusModel eventBusModel) {
        setInListCcy(eventBusModel, listCcy);
        setInBaseListCcy(eventBusModel, listBaseCcy);
        fromSpinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listCcy));
        toSpinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listBaseCcy));
        String from = fromSpinner.getSelectedItem().toString();
        for (int i = 0; i < eventBusModel.getList().size(); i++) {
            eventBus.add(eventBusModel.getList().get(i));
        }
    }

    @OnClick(R.id.doinBtn)
    public void ok() {
        if (isChecked) {
            translate(sale);
        } else {
            Toast.makeText(getActivity(), "make your choose", Toast.LENGTH_LONG).show();
        }
    }

    public void translate(boolean sale) {
        String from = fromSpinner.getSelectedItem().toString();
        String to = toSpinner.getSelectedItem().toString();
        Double b;
        for (int i = 0; i < eventBus.size(); i++) {
            if (from.equals(eventBus.get(i).getCcy())
                    && to.equals(eventBus.get(i).getBase_ccy())) {
                if (sale) {
                    b = Double.valueOf(eventBus.get(i).getSale());
                } else b = Double.valueOf(eventBus.get(i).getBuy());
                resultEt.setText(String.valueOf(Double.valueOf(sumEt.getText().toString()) * b));
            }
        }
    }

    public void setInListCcy(EventBusModel eventBusModel, ArrayList<String> arrayList) {
        for (int i = 0; i < eventBusModel.getList().size(); i++) {
            arrayList.add(eventBusModel.getList().get(i).getCcy());
        }
    }

    public void setInBaseListCcy(EventBusModel eventBusModel, ArrayList<String> arrayList) {
        for (int i = 0; i < eventBusModel.getList().size(); i++) {
            arrayList.add(eventBusModel.getList().get(i).getBase_ccy());
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.saleRB) {
            buyRB.setChecked(false);
            isChecked = true;
            sale = false;
        } else if (view.getId() == R.id.buyRb) {
            saleRB.setChecked(false);
            sale = true;
            isChecked = true;
        }
    }


}

