package com.example.basicapp;

/**
 * Created by Allison on 4/23/2014.
 */
public class WashLog {

    private Record[] log;

    public WashLog() {
    }


    public String getVinRecord(int index) {
        return log[index].getVinRecord();
    }

    public String getMilesRecord(int index) {
        return log[index].getMilesRecord();
    }

    public String getGasLevelRecord(int index) {
        return log[index].getGasLevelRecord();
    }

    public String getGasPumpedRecord(int index) {
        return log[index].getGasPumpedRecord();
    }

    public String getEmployeeNumberRecord(int index) {
        return log[index].getEmployeeNumberRecord();
    }

    public String getInspectionResultRecord(int index) {
        return log[index].getInspectionResultRecord();
    }

    public String getSmokeOrPetsRecord(int index) {
        return log[index].getSmokeOrPetsRecord();
    }

    public String getNotesRecord(int index) {
        return log[index].getNotesRecord();
    }
}
