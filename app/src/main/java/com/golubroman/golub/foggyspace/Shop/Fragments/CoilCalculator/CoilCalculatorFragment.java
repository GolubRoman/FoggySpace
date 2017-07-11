package com.golubroman.golub.foggyspace.Shop.Fragments.CoilCalculator;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.golubroman.golub.foggyspace.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 15.03.2017.
 */

public class CoilCalculatorFragment extends Fragment {

    @BindView(R.id.calculator_calculator) TextView calculator_calculator;
    @BindView(R.id.wires_in_coil_calculator) TextView wires_in_coil_calculator;
    @BindView(R.id.number_of_coils_calculator) TextView number_of_coils_calculator;
    @BindView(R.id.coil_type_calculator) TextView coil_type_calculator;
    @BindView(R.id.wire_diameter_calculator) TextView wire_diameter_calculator;
    @BindView(R.id.turns_number_calculator) TextView turns_number_calculator;
    @BindView(R.id.legs_length_calculator) TextView legs_length_calculator;
    @BindView(R.id.wire_type_calculator) TextView wire_type_calculator;
    @BindView(R.id.resistance_calculator) TextView resistance_calculator;
    @BindView(R.id.resistance_text_calculator) TextView resistance_text_calculator;
    @BindView(R.id.wires_in_coil_spinner_calculator) MaterialSpinner wires_in_coil_spinner_calculator;
    @BindView(R.id.number_of_coils_spinner_calculator) MaterialSpinner number_of_coils_spinner_calculator;
    @BindView(R.id.coil_type_spinner_calculator) MaterialSpinner coil_type_spinner_calculator;
    @BindView(R.id.wire_diameter_spinner_calculator) MaterialSpinner wire_diameter_spinner_calculator;
    @BindView(R.id.turns_number_spinner_calculator) MaterialSpinner turns_number_spinner_calculator;
    @BindView(R.id.legs_length_spinner_calculator) MaterialSpinner legs_length_spinner_calculator;
    @BindView(R.id.wire_type_spinner_calculator) MaterialSpinner wire_type_spinner_calculator;

    public static CoilCalculatorFragment newInstance() {

        Bundle args = new Bundle();
        CoilCalculatorFragment fragment = new CoilCalculatorFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coil_calculator, container, false);
        ButterKnife.bind(this, view);
        wires_in_coil_spinner_calculator.setItems(Arrays.asList(getResources().getStringArray(R.array.wiresInCoil)));
        number_of_coils_spinner_calculator.setItems(Arrays.asList(getResources().getStringArray(R.array.numberOfCoils)));
        coil_type_spinner_calculator.setItems(Arrays.asList(getResources().getStringArray(R.array.coilType)));
        wire_diameter_spinner_calculator.setItems(Arrays.asList(getResources().getStringArray(R.array.wireDiameter)));
        turns_number_spinner_calculator.setItems(Arrays.asList(getResources().getStringArray(R.array.turnsNumber)));
        legs_length_spinner_calculator.setItems(Arrays.asList(getResources().getStringArray(R.array.legsLength)));
        wire_type_spinner_calculator.setItems(Arrays.asList(getResources().getStringArray(R.array.wireType)));
        changeFont();
        return view;
    }
    private double countResistance(){
        double resistance = 0;

        return resistance;
    }
    private void changeFont(){
        Typeface segoe = Typeface.createFromAsset(this.getResources().getAssets(), "segoe.ttf");
        calculator_calculator.setTypeface(segoe);
        wires_in_coil_calculator.setTypeface(segoe);
        number_of_coils_calculator.setTypeface(segoe);
        coil_type_calculator.setTypeface(segoe);
        wire_diameter_calculator.setTypeface(segoe);
        turns_number_calculator.setTypeface(segoe);
        legs_length_calculator.setTypeface(segoe);
        wire_type_calculator.setTypeface(segoe);
        resistance_calculator.setTypeface(segoe);
        resistance_text_calculator.setTypeface(segoe);
    }

}
