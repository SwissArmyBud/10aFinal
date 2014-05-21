package com.example.basicapp;

/**
 * Created by Allison on 4/23/2014.
 */
public class Record {

    private String vin = "VIN #";
    private String miles = "Miles";
    private String gasLevel = "Gas Level";
    private String gasPumped = "Gallons Pumped";
    private String employeeNumber = "Employee #";
    private String inspectionResults = "Inspection";
    private String smokeOrPets = "Smoke/Pets";
    private String notes = "Notes";

    public Record() {
    }
    
    public Record(String vin, String miles, String gasLevel, String gasPumped, String employeeNumber, String inspectionResults, String smokeOrPets, String notes) {
    	this.vin = vin;
    	this.miles = miles;
    	this.gasLevel = gasLevel;
    	this.gasPumped = gasPumped;
    	this.employeeNumber = employeeNumber;
    	this.inspectionResults = inspectionResults;
    	this.smokeOrPets = smokeOrPets;
    	this.notes = notes;
    }

    public Record(String vin, int miles, int gasLevel, double gasPumped, String employeeNumber, int inspectionResults, int smokeOrPets, String notes) {
    		this.vin = vin;
	    	this.miles = "" + miles;
	    	this.gasLevel = "" + gasLevel;
	    	this.gasPumped = "" + gasPumped;
	    	this.employeeNumber = employeeNumber;
	    	this.inspectionResults = inspectionResultConverter(inspectionResults);
	    	this.smokeOrPets = smokeOrPetsConverter(smokeOrPets);
	    	this.notes = notes;
	    }

	public String getVinRecord() {
        return vin;
    }

    public String getMilesRecord() {
        return miles;
    }

    public String getGasLevelRecord() {
        return gasLevel;
    }

    public String getGasPumpedRecord() {
        return gasPumped;
    }

    public String getEmployeeNumberRecord() {
        return employeeNumber;
    }

    public String getInspectionResultRecord() {
        return inspectionResults;
    }

    public String getSmokeOrPetsRecord() {
        return smokeOrPets;
    }

    public String getNotesRecord() {
        return notes;
    }
    
    public String inspectionResultConverter(int result) {
        if (result == 1) {
            return "Yes";
        }
        else if (result == 2) {
            return "No";
        }
        else if (result == 3) {
            return "DX";
        }
        else {return "?";}
    }

    public String gasLevelConverter(int level) {
        return level + "/8";
    }

    public String gasPumpedConverter(double pumped) {
        pumped = pumped * 100.0;
        pumped = (int) pumped;
        pumped = pumped / 100.0;
        return "" + pumped;
    }

    public String smokeOrPetsConverter(int result){
        if (result == 1) {
            return "OK";
        }
        else if (result == 2) {
            return "Smoke";
        }
        else if (result == 3) {
            return "Pet Hair";
        }
        else if (result == 4) {
            return "Smk & Pet";
        }
        else {return "?";}
    }


}