package com.vandenrobotics.functionfirst.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.vandenrobotics.functionfirst.views.NumberPicker;
import android.widget.ToggleButton;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.activities.MatchActivity;
import com.vandenrobotics.functionfirst.model.AutoData;

/**
 * Created by Programming701-A on 12/11/2014.
 */
public class AutoFragment extends Fragment {

    private MatchActivity mActivity;
    private boolean viewsAssigned = false;

    public AutoData mAutoData;

    private CheckBox hadAuto;
    private NumberPicker noodlesInTrough;
    private NumberPicker nooodlesInoGoal;
    private CheckBox endsOverBarrier;
    private CheckBox hadOther;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_auto, container, false);
        mActivity = (MatchActivity) getActivity();

        mAutoData = mActivity.mMatchData.mAutoData;

        if(viewsAssigned) loadData(mAutoData);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        if(viewsAssigned) loadData(mAutoData);
    }

    @Override
    public void onPause(){
        super.onPause();
        mAutoData = new AutoData(saveData());
        mActivity.mMatchData.mAutoData = mAutoData;
        viewsAssigned=false;
    }

    @Override
    public void onResume(){
        super.onResume();
        assignViews(getView());
        if(viewsAssigned) loadData(mAutoData);
    }

    public void loadData(final AutoData autoData){
        // take the autoData and assign it to each view
        hadAuto.setChecked(autoData.hadAuto);
        noodlesInTrough.setValue(autoData.noodlesInTrough);
        nooodlesInoGoal.setValue(autoData.noodlesInGoal);

        endsOverBarrier.setChecked(autoData.endsOverBarrier);
        hadOther.setChecked(autoData.hadOther);

        if(hadAuto.isChecked())
            enableAutoViews();
        else
            disableAutoViews();
    }

    public AutoData saveData(){
        AutoData autoData = new AutoData();
        if(viewsAssigned){
            autoData.hadAuto = hadAuto.isChecked();
            autoData.noodlesInTrough = noodlesInTrough.getValue();
            autoData.noodlesInGoal = nooodlesInoGoal.getValue();

            autoData.endsOverBarrier = endsOverBarrier.isChecked();
            autoData.hadOther = hadOther.isChecked();
        }

        return autoData;
    }

    private void assignViews(View view){
        try{
            // assign all the custom view info to their respective views in the xml
            hadAuto = (CheckBox)view.findViewById(R.id.cb_hadAuto);
            noodlesInTrough = (NumberPicker)view.findViewById(R.id.pickerNoodlesInTrough);
            nooodlesInoGoal = (NumberPicker)view.findViewById(R.id.pickerNoodlesInGoal);

            endsOverBarrier = (CheckBox)view.findViewById(R.id.cb_endInAuto);
            hadOther = (CheckBox)view.findViewById(R.id.cb_hadOther);

            hadAuto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(hadAuto.isChecked())
                        enableAutoViews();
                    else
                        disableAutoViews();
                }
            });

            noodlesInTrough.setMinValue(0);
            noodlesInTrough.setMaxValue(3);
            nooodlesInoGoal.setMinValue(0);
            nooodlesInoGoal.setMaxValue(3);

            viewsAssigned = true;
        } catch (Exception e){
            e.printStackTrace();
            viewsAssigned = false;
        }
    }

    private void enableAutoViews(){
        noodlesInTrough.setEnabled(true);
        nooodlesInoGoal.setEnabled(true);

        endsOverBarrier.setEnabled(true);
        hadOther.setEnabled(true);
    }

    private void disableAutoViews(){
        noodlesInTrough.setValue(0);
        noodlesInTrough.setEnabled(false);
        nooodlesInoGoal.setValue(0);
        nooodlesInoGoal.setEnabled(false);

        endsOverBarrier.setChecked(false);
        endsOverBarrier.setEnabled(false);
        hadOther.setChecked(false);
        hadOther.setEnabled(false);
    }
}
