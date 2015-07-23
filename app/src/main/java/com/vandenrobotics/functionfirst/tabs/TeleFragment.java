package com.vandenrobotics.functionfirst.tabs;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.vandenrobotics.functionfirst.dialogs.DeleteStackDialogFragment;
import com.vandenrobotics.functionfirst.dialogs.DeleteStepStackDialogFragment;
import com.vandenrobotics.functionfirst.dialogs.EditStackDialogFragment;
import com.vandenrobotics.functionfirst.dialogs.EditStepStackDialogFragment;
import com.vandenrobotics.functionfirst.model.Stack;
import com.vandenrobotics.functionfirst.model.StepStack;
import com.vandenrobotics.functionfirst.views.FieldDiagram;
import com.vandenrobotics.functionfirst.views.NumberPicker;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.activities.MatchActivity;
import com.vandenrobotics.functionfirst.model.TeleData;

/**
 * Created by Programming701-A on 12/11/2014.
 */
public class TeleFragment extends Fragment {

    private MatchActivity mActivity;
    private boolean viewsAssigned = false;

    private TeleData mTeleData;


    private NumberPicker noodlesFromAllianceStation;
    private NumberPicker noodlesFromGround;
    private NumberPicker noodlesScoredInGoal;
    private NumberPicker noodlesScoredInTrough;
    private RadioGroup balanceState;
    private int balanceStateButtonPressed;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_tele, container, false);
        mActivity = (MatchActivity) getActivity();

        mTeleData = mActivity.mMatchData.mTeleData;

        if(!viewsAssigned) assignViews(rootView);
        if(viewsAssigned) loadData(mTeleData);

        return rootView;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        if(viewsAssigned) loadData(mTeleData);
    }

    @Override
    public void onPause(){
        super.onPause();
        mTeleData = new TeleData(saveData());
        mActivity.mMatchData.mTeleData = mTeleData;
        viewsAssigned=false;
    }

    @Override
    public void onResume(){
        super.onResume();
        assignViews(getView());
        if(viewsAssigned) loadData(mTeleData);
    }

    public void loadData(final TeleData teleData){
        // take the teleData and assign it to each view
        noodlesFromAllianceStation.setValue(teleData.noodlesFromAllianceStation);
        noodlesFromGround.setValue(teleData.noodlesFromGround);
        noodlesScoredInGoal.setValue(teleData.noodlesScoredInGoal);
        noodlesScoredInTrough.setValue(teleData.noodlesScoredInTrough);
        balanceStateButtonPressed = teleData.balanceState;

    }

    public TeleData saveData(){
        TeleData teleData = new TeleData();

        teleData.noodlesFromAllianceStation = noodlesFromAllianceStation.getValue();
        teleData.noodlesFromGround = noodlesFromGround.getValue();
        teleData.noodlesScoredInGoal = noodlesScoredInGoal.getValue();
        teleData.noodlesScoredInTrough = noodlesScoredInTrough.getValue();
        switch(balanceState.getCheckedRadioButtonId()){
            case R.id.radioButtonS:
                balanceStateButtonPressed = 0;
                break;
            case R.id.radioButtonA:
                balanceStateButtonPressed = 1;
                break;
            case R.id.radioButtonNA:
                balanceStateButtonPressed = 2;
                break;
            default:
                balanceStateButtonPressed = 2;
        }
        teleData.balanceState = balanceStateButtonPressed;

        return teleData;
    }

    private void assignViews(View view){
        try{
            // assign all the custom view info to their respective views in the xml
            noodlesFromAllianceStation = (NumberPicker)view.findViewById(R.id.pickerNoodlesFromAllianceStation);
            noodlesFromGround = (NumberPicker)view.findViewById(R.id.pickerNoodlesFromGround);
            noodlesScoredInGoal = (NumberPicker)view.findViewById(R.id.pickerNoodlesScoredInGoal);
            noodlesScoredInTrough = (NumberPicker)view.findViewById(R.id.pickerNoodlesScoredInTrough);
            balanceState = (RadioGroup)view.findViewById(R.id.balancegroup);

            noodlesFromAllianceStation.setMinValue(0);
            noodlesFromAllianceStation.setMaxValue(60);
            noodlesFromGround.setMinValue(0);
            noodlesFromGround.setMaxValue(60);
            noodlesScoredInGoal.setMinValue(0);
            noodlesScoredInGoal.setMaxValue(60);
            noodlesScoredInTrough.setMinValue(0);
            noodlesScoredInTrough.setMaxValue(60);

            switch(balanceStateButtonPressed){
                case 0:
                    balanceState.check(R.id.radioButtonS);
                    break;
                case 1:
                    balanceState.check(R.id.radioButtonA);
                    break;
                case 2:
                    balanceState.check(R.id.radioButtonNA);
                    break;
                default:
                    balanceState.check(R.id.radioButtonNA);
                    break;

            }

            viewsAssigned = true;
        } catch (Exception e){
            e.printStackTrace();
            viewsAssigned = false;
        }
    }

}
