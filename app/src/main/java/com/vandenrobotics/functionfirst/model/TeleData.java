package com.vandenrobotics.functionfirst.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Programming701-A on 1/31/2015.
 */
public class TeleData implements Parcelable {

    public int noodlesFromAllianceStation;
    public int noodlesFromGround;
    public int noodlesScoredInGoal;
    public int noodlesScoredInTrough;
    public int balanceState;

    public TeleData(){
        noodlesFromAllianceStation = 0;
        noodlesFromGround = 0;
        noodlesScoredInGoal = 0;
        noodlesScoredInTrough = 0;
        balanceState = 2;
    }

    public TeleData(String string){
        this();
        try{
            String[] dataString = string.split(",");
            int[] data = new int[dataString.length];

            for(int i = 0; i < data.length; i++){
                data[i]=Integer.parseInt(dataString[i]);
            }

            int index = 0;
            noodlesFromAllianceStation = data[index];
            index++;
            noodlesFromGround = data[index];
            index++;
            noodlesScoredInGoal = data[index];
            index++;
            noodlesScoredInTrough = data[index];
            index++;
            balanceState = data[index];


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public TeleData(TeleData teleData) {
        this();
        noodlesFromAllianceStation = teleData.noodlesFromAllianceStation;
        noodlesFromGround = teleData.noodlesFromGround;
        noodlesScoredInGoal = teleData.noodlesScoredInGoal;
        noodlesScoredInTrough = teleData.noodlesScoredInTrough;
        balanceState = teleData.balanceState;
    }

    @Override
    public String toString(){

        return noodlesFromAllianceStation+","+noodlesFromGround+","+
               noodlesScoredInGoal+","+noodlesScoredInTrough+","+
               balanceState;

    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel arg0, int arg1) {
        // TODO Auto-generated method stub
        arg0.writeString(this.toString());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public TeleData createFromParcel(Parcel in){
            return new TeleData(in.readString());
        }

        public TeleData[] newArray(int size){
            return new TeleData[size];
        }
    };
}
