package com.example.basicapp;

import com.example.basicapp.HomeFragment.HomeFragmentInterface;
import com.example.basicapp.LogViewFragment.LogUpdateStatus;
import com.example.basicapp.LogViewMenuFrag.LogViewMenuFragInterface;
import com.example.basicapp.ProcessRecordEditMenuFrag.ProcessRecordEditMenuFragInterface;
import com.example.basicapp.ProcessRecordFragment.UpdateStatus;
import com.example.basicapp.ProcessRecordMenuFrag.ProcessRecordMenuFragInterface;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class MainActivity extends FragmentActivity implements ProcessRecordEditMenuFragInterface, HomeFragmentInterface, ProcessRecordMenuFragInterface, LogViewMenuFragInterface, UpdateStatus, LogUpdateStatus {

	private static final String LIFE = "LifeCycle";
    private static final String EVENT = "Event";
	private static Record[] washLog = {
		new Record("EP001654", 5600, 7, 0, "E863PB", 3, 4, "This car was fucked up. DX plus Smoke plus Pets."),
    	new Record("ER109301", 12387, 2, 24.85, "E863PB", 1, 1, "This car was inspected and passed and had no smoke or pets."),
    	new Record("S0F37110", 200, 8, 0, "E863PB", 2, 2, "This car was not damaged but did have smoke issues.")
		};
	
   /* _____________________________
    * 
    * LIFECYCLE CREATION PATH
    * _____________________________
    
    ***CREATION***
	onCreate
	onStart
	onRestoreInstanceState (Dynamic Transition From VM)
	onResume
	***EXECUTION***
	onPause
	onSaveInstanceState (Dynamic Transition from VM)
	onStop
	onDestroy
	***DESTRUCTION***

    */
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LIFE, "onCreate");
        
        //SETTING ROOT VIEW
        setContentView(R.layout.activity_main);
        
        //INJECTING FRAGMENTS INTO ROOT VIEW
        if (savedInstanceState != null) {
            return;
        }
        
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        HomeFragment homeFrag = new HomeFragment();	
        homeFrag.setArguments(getIntent().getExtras());	
        
        transaction.add(R.id.main_top_frame, homeFrag, "HomeFrag");
        transaction.commit();
    }

    protected void onStart() {
		super.onStart();
		Log.i(LIFE, "onStart");
		
	}
	
	protected void onResume() {
		super.onResume();
		Log.i(LIFE, "onResume");
		
	}
	
	// ***EXECUTION***
	
	protected void onPause() {
		super.onPause();
		Log.i(LIFE, "onPause");
		
	}
	
	protected void onStop() {
		super.onStop();
		Log.i(LIFE, "onStop");
		
	}
	
	protected void onRestart() { //CALLED BETWEEN onStop AND onStart WHEN RESTARTING FROM PAST onStop IN LIFECYCLE
		super.onRestart();
		Log.i(LIFE, "onRestart");
		
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.i(LIFE, "onDestroy");
		
	}

	protected void onSaveInstanceState(Bundle outState) {  //CALLED AFTER onStop USUALLY DURING ORIENTATION TRANSITION
		super.onSaveInstanceState(outState);
		Log.i(LIFE, "onSaveInstanceState");
		
		/*
		EditText textBox = (EditText) findViewById(R.id.main_text);
		CharSequence userText = textBox.getHint();
		outState.putCharSequence("savedText", userText);
		*/
		
	}
	
	protected void onRestoreInstanceState(Bundle savedState) {	//CALLED AFTER onStart USUALLY DURING ORIENTATION TRANSITION
		Log.i(LIFE, "onRestoreInstanceState");
		
		/*
		EditText textBox = (EditText) findViewById(R.id.main_text);
		CharSequence userText = savedState.getCharSequence("savedText");
		textBox.setHint(userText);
		*/

	}
    
   /* ____________________________________________
	*
	*	THIS AREA IS FOR HANDLING THE MENU SYSTEM
	* ____________________________________________
    */
	
	//THIS AREA SPECIFIES THE MENU LAYOUT FILE
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		//MENU INFLATER
		getMenuInflater().inflate(R.menu.main, menu);
		Log.i(EVENT, "MENU CREATED");
		
		return true;
	}

	//THIS AREA HANDLES MENU BUTTON CLICK EVENTS
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		//ADD CASES FOR DIFFERENT BUTTON IDS
		
		//THE "SETTINGS" BUTTON
	    case R.id.settings_button:
			Log.i(EVENT, "SETTINGS PRESSED");
	        break;
	        
		}
		
		return super.onOptionsItemSelected(item);
	}
	
   /* ____________________________________________
	*
	*	THIS AREA IS FOR ADDING SUPPORTING METHODS
	* ____________________________________________
	*/
	
	public void updateStatus(String string) {
		Log.i(EVENT, "MainActivity updateStatus() -->" + string);
		
        TextView statusText = (TextView) findViewById(R.id.current_status);
        statusText.setText(string);
	}

	@Override
	public void homeAddRecordButtonPush() {
		Log.i(EVENT, "MainActivity homeAddRecordButtonPush()");
		
		//SETUP THE TRANSACTION MANAGER
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
		//SETUP THE RECORD AND MENU FRAGMENTS
        ProcessRecordFragment recordFrag = new ProcessRecordFragment();	
        ProcessRecordMenuFrag recordMenuFrag = new ProcessRecordMenuFrag();	
        recordFrag.setArguments(getIntent().getExtras());	
        recordMenuFrag.setArguments(getIntent().getExtras());	
        
        //ADD THE RECORD FRAGMENT TO THE TOP FRAME AND THE RECORD MENU TO THE BOTTOM FRAME
        transaction.replace(R.id.main_top_frame, recordFrag, "RecordFrag");
        transaction.add(R.id.main_bottom_frame, recordMenuFrag, "RecordMenuFrag");
        transaction.addToBackStack(null);
        transaction.commit();
        getSupportFragmentManager().executePendingTransactions();
	}
	
	@Override
	public void homeViewLogButton() {
		Log.i(EVENT, "MainActivity homeViewLogButton()");
		
		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        LogViewFragment logFrag = new LogViewFragment();	
        LogViewMenuFrag logMenuFrag = new LogViewMenuFrag();	
        logFrag.setArguments(getIntent().getExtras());	
        logMenuFrag.setArguments(getIntent().getExtras());	
        
        transaction.replace(R.id.main_top_frame, logFrag, "LogFrag");
        transaction.add(R.id.main_bottom_frame, logMenuFrag, "LogMenuFrag");
        transaction.addToBackStack(null);
        transaction.commit();
        getSupportFragmentManager().executePendingTransactions();
	}

	@Override
	public void homeSaveLogButton() {
		Log.i(EVENT, "MainActivity homeSaveLogButton()");
		// TODO Auto-generated method stub
	}

	@Override
	public void commitRecordButtonPush() {
		Log.i(EVENT, "MainActivity commitRecordButtonPush()");
		
		//GET REFERENCES TO THE ProcessRecordFragment AND BEGIN EXTRACTING INFORMATION FROM IT
		ProcessRecordMenuFrag recordMenuFrag = (ProcessRecordMenuFrag) getSupportFragmentManager().findFragmentByTag("RecordMenuFrag");
		@SuppressWarnings("unused")
		ProcessRecordFragment recordFrag = (ProcessRecordFragment) getSupportFragmentManager().findFragmentByTag("RecordFrag");
		
		//
		//
		//TODO
		//THIS IS WHERE THE LOGIC FOR CREATING AND STORING THE RECORD INTO THE WASHLOG GOES
		//
		//
		//
		
		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        HomeFragment homeFrag = new HomeFragment();		
        homeFrag.setArguments(getIntent().getExtras());		
        
        transaction.replace(R.id.main_top_frame, homeFrag, "HomeFrag");
        transaction.remove(recordMenuFrag);
        transaction.addToBackStack(null);
        transaction.commit();
        getSupportFragmentManager().executePendingTransactions();
		
	}

	@Override
	public void logCancel() {
		Log.i(EVENT, "logCancel()");
		
		//FIND THE MENU FRAGMENT AND OBTAIN A REFERENCE SO IT CAN BE REMOVED IN THE FRAGMENT TRANSITION
		LogViewMenuFrag logViewMenuFrag = (LogViewMenuFrag) getSupportFragmentManager().findFragmentByTag("LogMenuFrag");
		
		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        HomeFragment homeFrag = new HomeFragment();		
        homeFrag.setArguments(getIntent().getExtras());		
        
        transaction.replace(R.id.main_top_frame, homeFrag, "HomeFrag");
        transaction.remove(logViewMenuFrag);
        transaction.addToBackStack(null);
        transaction.commit();
        getSupportFragmentManager().executePendingTransactions();
	}

	@Override
	public void recordCancel(boolean edit) {
		Log.i(EVENT, "recordCancel()");
		
		//FIND THE MENU FRAGMENT AND OBTAIN A REFERENCE SO IT CAN BE REMOVED IN THE FRAGMENT TRANSITION
		ProcessRecordMenuFrag recordMenuFrag = (ProcessRecordMenuFrag) getSupportFragmentManager().findFragmentByTag("RecordMenuFrag");
		
		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        HomeFragment homeFrag = new HomeFragment();		
        homeFrag.setArguments(getIntent().getExtras());		
        
        transaction.replace(R.id.main_top_frame, homeFrag, "HomeFrag");
        transaction.remove(recordMenuFrag);
        transaction.addToBackStack(null);
        transaction.commit();
        getSupportFragmentManager().executePendingTransactions();
	}
	
	@Override
	public void editCancel() {
		Log.i(EVENT, "recordCancel()");
		
		//FIND THE MENU FRAGMENT AND OBTAIN A REFERENCE SO IT CAN BE REMOVED IN THE FRAGMENT TRANSITION
		ProcessRecordEditMenuFrag recordMenuFrag = (ProcessRecordEditMenuFrag) getSupportFragmentManager().findFragmentByTag("RecordMenuFrag");
		
		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        HomeFragment homeFrag = new HomeFragment();		
        homeFrag.setArguments(getIntent().getExtras());		
        
        transaction.replace(R.id.main_top_frame, homeFrag, "HomeFrag");
        transaction.remove(recordMenuFrag);
        transaction.addToBackStack(null);
        transaction.commit();
        getSupportFragmentManager().executePendingTransactions();
	}
	
	public static Record[] getCurrentLog() {
		Log.i(EVENT, "MainActivity getCurrentLog()");
		
		//RETURN WHATEVER THE CURRENT WASH LOG IS
		return washLog;
	}
	
	public void addRecordToLog(Record record){
		Log.i(EVENT, "MainActivity addRecordToLog()");

		//GROW THE WASH LOG AND ADD THE SUPPLIED RECORD TO THE END
        Record[] newArray = new Record[washLog.length+1];
        System.arraycopy(washLog, 0, newArray, 0, washLog.length);
    	newArray[newArray.length - 1] = record;
    	washLog = newArray;        
    }

	@Override
	public void editSelectedRecord() {
		Log.i(EVENT, "MainActivity editSelectedRecord()");
			
			//FIND THE LOG FRAGMENT WHILE IT IS STILL RUNNING AND EXTRACT THE SELECTION VALUE
			LogViewFragment logViewFrag = (LogViewFragment) getSupportFragmentManager().findFragmentByTag("LogFrag");
			int selection = logViewFrag.hasSelection();
			
			//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
	        
	        ProcessRecordFragment recordFrag = new ProcessRecordFragment();		
	        ProcessRecordEditMenuFrag recordMenuFrag = new ProcessRecordEditMenuFrag();
	        
	        transaction.replace(R.id.main_top_frame, recordFrag, "RecordFrag");
	        transaction.replace(R.id.main_bottom_frame, recordMenuFrag, "RecordMenuFrag");
	        transaction.addToBackStack(null);
	        transaction.commit();
	        getSupportFragmentManager().executePendingTransactions();
	        
	        //SET THE FIELDS IN THE ProcessRecordFragment USING THE SELECTED ID FROM THE ListView
	        setFields(selection, washLog);
	}
	
	public void setFields(int selection, Record[] washLog) {
		Log.i(EVENT, "MainActivity setFields()");

		//ATTACHING INPUT HANDLES FOR FIELDS
		EditText vinInput = (EditText) findViewById(R.id.vin_input);
		EditText milesInput = (EditText) findViewById(R.id.miles_input);
		TextView gasText = (TextView) findViewById(R.id.gas_text_box);
		EditText pumpedInput = (EditText) findViewById(R.id.pumped_input);
		EditText eracInput = (EditText) findViewById(R.id.erac_input);
		EditText notesInput = (EditText) findViewById(R.id.notes_input);
		SeekBar seekbar = (SeekBar) findViewById(R.id.gas_seek_bar);
		
		//ASSIGNING VALUES TO ATTACHED FIELDS
		eracInput.setText(washLog[selection].getEmployeeNumberRecord());
		milesInput.setText(washLog[selection].getMilesRecord());
		vinInput.setText(washLog[selection].getVinRecord());
		gasText.setText(washLog[selection].getGasLevelRecord());
		pumpedInput.setText(washLog[selection].getGasPumpedRecord());
		notesInput.setText(washLog[selection].getNotesRecord());
		seekbar.setProgress(Integer.parseInt(washLog[selection].getGasLevelRecord()));
		
		Log.i(EVENT, "Starting to set radio fields");
		//SETTING RADIO FIELD AND CHECKBOX AREAS
		String inspectionResult = washLog[selection].getInspectionResultRecord();
		RadioButton radioButton; 
		if (inspectionResult.equalsIgnoreCase("yes")) {
			radioButton = (RadioButton) findViewById(R.id.inspection_yesOK);
			radioButton.setChecked(true);
		}
		else if (inspectionResult.equalsIgnoreCase("no")) {
			radioButton = (RadioButton) findViewById(R.id.inspection_no);
			radioButton.setChecked(true);
		}
		else if (inspectionResult.equalsIgnoreCase("dx")) {
			radioButton = (RadioButton) findViewById(R.id.inspection_dx);
			radioButton.setChecked(true);
		}
		
		String petsResult = washLog[selection].getSmokeOrPetsRecord();
		CheckBox checkBox; 
		Log.i(EVENT, "petsResult = " + petsResult);
		if (petsResult.equals("OK")) {
			checkBox = (CheckBox) findViewById(R.id.pets_confirm);
			checkBox.setChecked(false);
			checkBox = (CheckBox) findViewById(R.id.smoke_confirm);
			checkBox.setChecked(false);
        }
        else if (petsResult.equals("Smoke")) {
        	checkBox = (CheckBox) findViewById(R.id.smoke_confirm);
			checkBox.setChecked(true);
        }
        else if (petsResult.equals("Pet Hair")) {
        	checkBox = (CheckBox) findViewById(R.id.pets_confirm);
			checkBox.setChecked(true);
        }
        else if (petsResult.equals("Smk & Pet")) {
        	checkBox = (CheckBox) findViewById(R.id.smoke_confirm);
			checkBox.setChecked(true);
			checkBox = (CheckBox) findViewById(R.id.pets_confirm);
			checkBox.setChecked(true);
        }
		
		Log.i(EVENT, "setFields() complete");
	}

	@Override
	public void saveRecord(Record record, int selection){
		Log.i(EVENT, "MainActivity saveRecord()");

    	washLog[selection] = record;
    
	}
	
}
