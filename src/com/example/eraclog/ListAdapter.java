package com.example.eraclog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<Record> {
 	 
	Context context;
 	int layoutResourceId;
 	Record[] washLog = null;
 	
 	public ListAdapter(Context context, int layoutResourceId, Record[] data) {
 	        super(context, layoutResourceId, data);
 	        this.layoutResourceId = layoutResourceId;
 	        this.context = context;
 	        this.washLog = data;
 	    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		//SET THE CURRENT WASH LOG TO THE LOCAL LOG INSTANCE
	    washLog = MainActivity.getCurrentLog();

	    //INFLATE THE VIEW AND CHOOSE THE LAYOUT FILE
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    convertView = inflater.inflate(R.layout.list_adapter, parent, false);

	    //ASSIGN VIEW MANAGERS VIA VIEW IDS
	    TextView vinView = (TextView) convertView.findViewById(R.id.list_adapter_vin_slot);
	    TextView milesView = (TextView) convertView.findViewById(R.id.list_adapter_miles_slot);
	    TextView fuelLevelView = (TextView) convertView.findViewById(R.id.list_adapter_fuel_slot);
	    TextView pumpedView = (TextView) convertView.findViewById(R.id.list_adapter_pumped_slot);
	    TextView inspectionView = (TextView) convertView.findViewById(R.id.list_adapter_inspection_slot);
	    TextView notesView = (TextView) convertView.findViewById(R.id.list_adapter_notes_slot);
	    
	    //ASSIGN A SIMPLE ANSWER FOR THE VIN RECORD
	    Record record = new Record();
	    String defaultString = record.getVinRecord();
	    boolean defaultMatch = washLog[position].getVinRecord().equalsIgnoreCase(defaultString);
	    boolean empty = washLog[position].getVinRecord().equalsIgnoreCase("");
	    String status = "";
	    if (defaultMatch == true | empty == true) {
	    	status = "none";
	    } else {status = washLog[position].getVinRecord();}
	    //ASSIGN THE ANSWER TO THE TEXT HANDLER
	    vinView.setText("VIN #: " + status);
	    
	    //ASSIGN A SIMPLE ANSWER FOR THE MILES RECORD
	    defaultString = record.getMilesRecord();
	    defaultMatch = washLog[position].getMilesRecord().equalsIgnoreCase(defaultString);
	    empty = washLog[position].getMilesRecord().equalsIgnoreCase("");
	    status = "";
	    if (defaultMatch == true | empty == true) {
	    	status = "n/a";
	    } else {status = washLog[position].getMilesRecord();}
	    //ASSIGN THE ANSWER TO THE TEXT HANDLER
	    milesView.setText("Miles: " + status);
	    
	    //ASSIGN A SIMPLE ANSWER FOR THE GAS LEVEL RECORD
	    defaultString = record.getGasLevelRecord();
	    defaultMatch = washLog[position].getGasLevelRecord().equalsIgnoreCase(defaultString);
	    empty = washLog[position].getGasLevelRecord().equalsIgnoreCase("");
	    status = "";
	    if (defaultMatch == true | empty == true) {
	    	status = "n/a";
	    } else {status = washLog[position].getGasLevelRecord() + "/8";}
	    //ASSIGN THE ANSWER TO THE TEXT HANDLER
	    fuelLevelView.setText("Gas Lvl: " + status);
	    
	    //ASSIGN A SIMPLE ANSWER FOR THE GAS PUMPED RECORD
	    defaultString = record.getGasPumpedRecord();
	    defaultMatch = washLog[position].getGasPumpedRecord().equalsIgnoreCase(defaultString);
	    empty = washLog[position].getGasPumpedRecord().equalsIgnoreCase("");
	    status = "";
	    if (defaultMatch == true | empty == true) {
	    	status = "0.00";
	    } 
	    else {status = washLog[position].getGasPumpedRecord();}
	    //ASSIGN THE ANSWER TO THE TEXT HANDLER
	    pumpedView.setText("Pumped: " + status);
	    
	    //ASSIGN A SIMPLE ANSWER FOR THE INSPECTION RECORD
	    defaultString = record.getInspectionResultRecord();
	    defaultMatch = washLog[position].getInspectionResultRecord().equalsIgnoreCase(defaultString);
	    empty = washLog[position].getInspectionResultRecord().equalsIgnoreCase("");
	    status = "";
	    if (defaultMatch == true || empty == true) {
	    	status = "n/a";
	    } 
	    else {status = washLog[position].getInspectionResultRecord();}
	    //ASSIGN THE ANSWER TO THE TEXT HANDLER
	    inspectionView.setText("Insp: " + status);
	    
	    //ASSIGN A SIMPLE ANSWER FOR THE NOTES RECORD
	    defaultString = record.getNotesRecord();
	    defaultMatch = washLog[position].getNotesRecord().equalsIgnoreCase(defaultString);
	    empty = washLog[position].getNotesRecord().equalsIgnoreCase("");
	    status = "";
	    if (defaultMatch == true | empty == true) {
	    	status = "No";
	    } else {status = "Yes";}
	    //ASSIGN THE ANSWER TO THE TEXT HANDLER
	    notesView.setText("Notes: " + status);
	    
	    //RETURN THE FINAL INDIVIDUALLY ADAPTED VIEW
	    return convertView;
	}

	    
}