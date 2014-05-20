package com.example.basicapp;

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
	    convertView = inflater.inflate(R.layout.record_list_row, parent, false);

	    //ASSIGN VIEW MANAGERS VIA VIEW IDS
	    TextView vinView = (TextView) convertView.findViewById(R.id.vin_slot);
	    TextView milesView = (TextView) convertView.findViewById(R.id.miles_slot);
	    TextView fuelLevelView = (TextView) convertView.findViewById(R.id.fuel_slot);
	    TextView pumpedView = (TextView) convertView.findViewById(R.id.pumped_slot);
	    TextView inspectionView = (TextView) convertView.findViewById(R.id.inspection_slot);
	    TextView notesView = (TextView) convertView.findViewById(R.id.notes_slot);

	    //ASSIGN ARRAY VALUES VIA VIEW MANAGERS
	    vinView.setText(washLog[position].getVinRecord());
	    milesView.setText(washLog[position].getMilesRecord());
	    fuelLevelView.setText(washLog[position].getGasLevelRecord());
	    pumpedView.setText(washLog[position].getGasPumpedRecord());
	    inspectionView.setText(washLog[position].getInspectionResultRecord());
	    notesView.setText(washLog[position].getNotesRecord());
	    
	    return convertView;
	}

	    
}