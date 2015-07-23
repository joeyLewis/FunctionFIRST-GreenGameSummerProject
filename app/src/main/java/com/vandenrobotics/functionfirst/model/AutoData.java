package com.vandenrobotics.functionfirst.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AutoData implements Parcelable {

    public boolean hadAuto;

    public int noodlesInTrough;
    public int noodlesInGoal;

    public boolean endsOverBarrier;
    public boolean hadOther;


    public AutoData(){
        hadAuto = false;
        noodlesInTrough = 0;
        noodlesInGoal = 0;

        endsOverBarrier = false;
        hadOther = false;
    }

    public AutoData(String string){
        this();
        try{
            String[] dataString = string.split(",");
            int[] data = new int[dataString.length];

            try{
                for(int i = 0; i < data.length; i++)
                    data[i] = Integer.parseInt(dataString[i]);
            } catch (NumberFormatException e){
                e.printStackTrace();
            }

            int index = 0;

            hadAuto = (data[index]==1);
            index += 1;
            noodlesInTrough = data[index];
            index += 1;
            noodlesInGoal = data[index];
            index += 1;

            endsOverBarrier = (data[index]==1);
            index += 1;
            hadOther = (data[index]==1);

        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public AutoData(AutoData autoData){
        this();
        hadAuto = autoData.hadAuto;
        noodlesInTrough = autoData.noodlesInTrough;
        noodlesInGoal = autoData.noodlesInGoal;

        endsOverBarrier = autoData.endsOverBarrier;
        hadOther = autoData.hadOther;
    }

    @Override
    public String toString(){
        int tempAuto = hadAuto? 1 : 0;
        int tempEndsOverBarrier = endsOverBarrier? 1 : 0;
        int tempOther = hadOther? 1 : 0;

        return tempAuto+","+noodlesInTrough+","+noodlesInGoal+","+
                tempEndsOverBarrier+","+tempOther;
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
        public AutoData createFromParcel(Parcel in){
            return new AutoData(in.readString());
        }

        public AutoData[] newArray(int size){
            return new AutoData[size];
        }
    };
}
