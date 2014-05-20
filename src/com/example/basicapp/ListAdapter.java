package com.example.basicapp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//here's our beautiful adapter
 	public class ListAdapter extends ArrayAdapter<Record> {
 	 
 	    Context mContext;
 	    int layoutResourceId;
 	    Record[] washLog = null;
 	 
 	public ListAdapter(Context mContext, int layoutResourceId, Record[] data) {
 	        super(mContext, layoutResourceId, data);
 	        this.layoutResourceId = layoutResourceId;
 	        this.mContext = mContext;
 	        this.washLog = data;
 	    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	    washLog = MainActivity.getCurrentLog();

	    if(convertView==null){
	    	// inflate the layout
	    	LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    	convertView = inflater.inflate(R.layout.record_list_row, parent, false);
	    }


	    if (washLog != null) {

	        TextView vinView = (TextView) convertView.findViewById(R.id.vin_slot);
	        TextView milesView = (TextView) convertView.findViewById(R.id.miles_slot);
	        TextView fuelLevelView = (TextView) convertView.findViewById(R.id.fuel_slot);
	        TextView pumpedView = (TextView) convertView.findViewById(R.id.pumped_slot);
	        TextView inspectionView = (TextView) convertView.findViewById(R.id.inspection_slot);
	        TextView notesView = (TextView) convertView.findViewById(R.id.notes_slot);

	        vinView.setText("VIN");
	        milesView.setText("MILES");
	        fuelLevelView.setText(washLog[position].getGasLevelRecord());
	        pumpedView.setText(washLog[position].getGasPumpedRecord());
	        inspectionView.setText(washLog[position].getInspectionResultRecord());
	        notesView.setText(washLog[position].getNotesRecord());
	        }
	    
	    return convertView;
	}

	    
}