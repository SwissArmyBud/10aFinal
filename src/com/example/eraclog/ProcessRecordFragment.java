package com.example.eraclog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ProcessRecordFragment extends Fragment implements OnSeekBarChangeListener {

    private static final String EVENT = "Event";
    private static final String LIFE = "LifeCycle";
    
	Record[] washLog;
	SeekBar seekbar;
	int selection;

    //THIS SECTION SETS BUTTON LISTENERS
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LIFE, "ProcessRecordFragment onCreateView");
        
		//INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.process_record_fragment, container, false);
        
        //SET SEEK LISTENER
        seekbar = (SeekBar) rootView.findViewById(R.id.gas_seek_input);
	    seekbar.setOnSeekBarChangeListener(this);
	    
	    //RETURN THE VIEW
        return rootView;
    }
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
    */
	
	//REQUIRED METHOD FROM SCROLL HANDLER
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		Log.i(EVENT, "seekBarListener update: " + progress);
		
		View rootView = getView().findViewById(R.id.gas_text_box);
		TextView textBox = (TextView) rootView.findViewById(R.id.gas_text_box);
		textBox.setText("" + progress + "/8");
		
	}
	
	//REQUIRED METHOD FROM SCROLL HANDLER
	@Override  
	public void onStartTrackingTouch(SeekBar seekBar) {
		Log.i(EVENT, "seekBarListener onStartTrackingTouch");
		//NO USE FOR THIS METHOD SO FAR
	}

	//REQUIRED METHOD FROM SCROLL HANDLER
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		Log.i(EVENT, "seekBarListener onStopTrackingTouch");
		//NO USE FOR THIS METHOD SO FAR
		
	}
	
	public void setFields(int selection, Record[] washLog) {
		Log.i(EVENT, "ProcessRecordFragment setFields()");

		this.selection = selection;
		
		//ATTACHING INPUT HANDLES FOR TEXT FIELDS
		EditText vinInput = (EditText) getView().findViewById(R.id.vin_input);
		EditText milesInput = (EditText) getView().findViewById(R.id.miles_input);
		TextView gasText = (TextView) getView().findViewById(R.id.gas_text_box);
		EditText pumpedInput = (EditText) getView().findViewById(R.id.pumped_input);
		EditText eracInput = (EditText) getView().findViewById(R.id.erac_input);
		EditText notesInput = (EditText) getView().findViewById(R.id.notes_input);
		
		//ATTACHING INPUT HANDLE FOR SEEK BAR
		SeekBar seekbar = (SeekBar) getView().findViewById(R.id.gas_seek_input);
		
		//ASSIGNING VALUES TO ATTACHED TEXT FIELDS
		eracInput.setText(washLog[selection].getEmployeeNumberRecord());
		milesInput.setText(washLog[selection].getMilesRecord());
		vinInput.setText(washLog[selection].getVinRecord());
		
		//SETTING GAS LEVEL TO 4/8 FOR DEFAULT VALUE, OTHERWISE SEEKBAR WILL UPDATE AS NEEDED
		gasText.setText("4/8");
		
		pumpedInput.setText(washLog[selection].getGasPumpedRecord());
		notesInput.setText(washLog[selection].getNotesRecord());
		
		//SETTING THE SEEKBAR
		seekbar.setProgress(Integer.parseInt(washLog[selection].getGasLevelRecord()));
		
		//SETTING RADIO FIELD AND CHECKBOX AREAS
		String inspectionResult = washLog[selection].getInspectionResultRecord();
		RadioButton radioButton; 
		if (inspectionResult.equalsIgnoreCase(Record.inspectionResultConverter(1))) {
			radioButton = (RadioButton) getView().findViewById(R.id.inspection_yesOK);
			radioButton.setChecked(true);
		}
		else if (inspectionResult.equalsIgnoreCase(Record.inspectionResultConverter(2))) {
			radioButton = (RadioButton) getView().findViewById(R.id.inspection_no);
			radioButton.setChecked(true);
		}
		else if (inspectionResult.equalsIgnoreCase(Record.inspectionResultConverter(3))) {
			radioButton = (RadioButton) getView().findViewById(R.id.inspection_dx);
			radioButton.setChecked(true);
		}
		
		String petsResult = washLog[selection].getSmokeOrPetsRecord();
		CheckBox checkBox; 
		if (petsResult.equals(Record.smokeOrPetsConverter(1))) {
			checkBox = (CheckBox) getView().findViewById(R.id.pets_confirm);
			checkBox.setChecked(false);
			checkBox = (CheckBox) getView().findViewById(R.id.smoke_confirm);
			checkBox.setChecked(false);
        }
        else if (petsResult.equals(Record.smokeOrPetsConverter(2))) {
			checkBox = (CheckBox) getView().findViewById(R.id.pets_confirm);
			checkBox.setChecked(false);
			checkBox = (CheckBox) getView().findViewById(R.id.smoke_confirm);
			checkBox.setChecked(true);
        }
        else if (petsResult.equals(Record.smokeOrPetsConverter(3))) {
			checkBox = (CheckBox) getView().findViewById(R.id.pets_confirm);
			checkBox.setChecked(true);
			checkBox = (CheckBox) getView().findViewById(R.id.smoke_confirm);
			checkBox.setChecked(false);
        }
        else if (petsResult.equals(Record.smokeOrPetsConverter(4))) {
        	checkBox = (CheckBox) getView().findViewById(R.id.smoke_confirm);
			checkBox.setChecked(true);
			checkBox = (CheckBox) getView().findViewById(R.id.pets_confirm);
			checkBox.setChecked(true);
        }
		
		Log.i(EVENT, "setFields() complete");
	}
	
	public Record getRecord() {
		Log.i(EVENT, "ProcessRecordFragment getRecord()");
		
				//ATTACHING INPUT HANDLES FOR TEXT FIELDS
				EditText vinInput = (EditText) getView().findViewById(R.id.vin_input);
				EditText milesInput = (EditText) getView().findViewById(R.id.miles_input);
				EditText pumpedInput = (EditText) getView().findViewById(R.id.pumped_input);
				EditText eracInput = (EditText) getView().findViewById(R.id.erac_input);
				EditText notesInput = (EditText) getView().findViewById(R.id.notes_input);
				
				//ATTACHING INPUT HANDLE FOR SEEK BAR
				SeekBar seekbar = (SeekBar) getView().findViewById(R.id.gas_seek_input);
				
				//GETTING VALUES FROM ATTACHED TEXT FIELDS
				String tempVIN = vinInput.getText().toString();
				String tempMiles = milesInput.getText().toString();
				String tempPumped = pumpedInput.getText().toString();
				String tempErac = eracInput.getText().toString();
				String tempNotes = notesInput.getText().toString();
				
				//GETTING DATA FROM THE SEEKBAR
				String tempGas = "" + seekbar.getProgress();
				
				//GETTING DATA FROM RADIO FIELD
				RadioButton radioButton; 
				String tempInspection = "";
				radioButton = (RadioButton) getView().findViewById(R.id.inspection_yesOK);
					if (radioButton.isChecked()) {
						tempInspection = Record.inspectionResultConverter(1);
					}
				radioButton = (RadioButton) getView().findViewById(R.id.inspection_no);
					if (radioButton.isChecked()) {
						tempInspection = Record.inspectionResultConverter(2);
					}
				radioButton = (RadioButton) getView().findViewById(R.id.inspection_dx);
					if (radioButton.isChecked()) {
						tempInspection = Record.inspectionResultConverter(3);
					}
				
				//GETTING DATA FROM THE CHECKBOXES
				CheckBox checkBoxPets = (CheckBox) getView().findViewById(R.id.pets_confirm);
				CheckBox checkBoxSmoke = (CheckBox) getView().findViewById(R.id.smoke_confirm);
				String tempPetsAndSmoke = "";
				if (checkBoxPets.isChecked() == false & checkBoxSmoke.isChecked() == false) {
					tempPetsAndSmoke = Record.smokeOrPetsConverter(1);
				}
		        else if (checkBoxPets.isChecked() == false & checkBoxSmoke.isChecked() == true) {
					tempPetsAndSmoke = Record.smokeOrPetsConverter(2);
				}
		        else if (checkBoxPets.isChecked() == true & checkBoxSmoke.isChecked() == false) {
					tempPetsAndSmoke = Record.smokeOrPetsConverter(3);
				}
		        else if (checkBoxPets.isChecked() == true & checkBoxSmoke.isChecked() == true) {
					tempPetsAndSmoke = Record.smokeOrPetsConverter(4);
				}
				
				Record record = new Record(tempVIN, tempMiles, tempGas, tempPumped, tempErac, tempInspection, tempPetsAndSmoke, tempNotes);
		
		return record;
	}
}
