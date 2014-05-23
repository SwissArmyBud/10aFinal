package com.example.basicapp;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.basicapp.HomeFragment.HomeFragmentInterface;
import com.example.basicapp.LogViewFragment.LogUpdateStatus;
import com.example.basicapp.LogViewMenuFrag.LogViewMenuFragInterface;
import com.example.basicapp.ProcessRecordEditMenuFrag.ProcessRecordEditMenuFragInterface;
import com.example.basicapp.ProcessRecordFragment.ProcessRecordFragInterface;
import com.example.basicapp.ProcessRecordMenuFrag.ProcessRecordMenuFragInterface;
import com.example.basicapp.SaveLogFragment.SaveLogFragInterface;

import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class MainActivity extends FragmentActivity implements SaveLogFragInterface, ProcessRecordEditMenuFragInterface, HomeFragmentInterface, ProcessRecordMenuFragInterface, LogViewMenuFragInterface, ProcessRecordFragInterface, LogUpdateStatus {

	private static final String LIFE = "LifeCycle";
	private static final String EVENT = "Event";
	private static Record[] washLog = {
		//new Record("EP001654", 5600, 7, 0, "E863PB", 3, 4, "This car was fucked up. DX plus Smoke plus Pets."),
		//new Record("ER109301", 12387, 2, 24.85, "E863PB", 1, 1, "This car was inspected and passed and had no smoke or pets."),
		//new Record("S0F37110", 200, 8, 0, "E863PB", 2, 2, "This car was not damaged but did have smoke issues.")
	};
	private int lastSelection = -1;

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

		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		ProcessRecordFragment recordFrag = new ProcessRecordFragment();	
		ProcessRecordMenuFrag recordMenuFrag = new ProcessRecordMenuFrag();	
		recordFrag.setArguments(getIntent().getExtras());	
		recordMenuFrag.setArguments(getIntent().getExtras());	
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

		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		SaveLogFragment homeFrag = new SaveLogFragment();		
		homeFrag.setArguments(getIntent().getExtras());		
		transaction.replace(R.id.main_top_frame, homeFrag, "HomeFrag");
		transaction.addToBackStack(null);
		transaction.commit();
		getSupportFragmentManager().executePendingTransactions();
	}

	@Override
	public void saveRecordButtonPush() {
		Log.i(EVENT, "MainActivity saveRecordButtonPush()");

		//GET REFERENCES TO THE ProcessRecordFragment AND BEGIN EXTRACTING INFORMATION FROM IT
		ProcessRecordEditMenuFrag recordMenuFrag = (ProcessRecordEditMenuFrag) getSupportFragmentManager().findFragmentByTag("RecordMenuFrag");
		ProcessRecordFragment recordFrag = (ProcessRecordFragment) getSupportFragmentManager().findFragmentByTag("RecordFrag");

		//GET A RECORD FROM THE RUNNING ProcessRecordFragment AND ADD IT TO THE ARRAY IN THE POSITION IS WAS RECIEVED FROM (lastSelection SET IN editSelectedRecord)
		Record newRecord = recordFrag.getRecord();
		addRecordToLog(newRecord, lastSelection);

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
	public void commitRecordButtonPush() {
		Log.i(EVENT, "MainActivity commitRecordButtonPush()");

		//GET REFERENCES TO THE ProcessRecordFragment AND BEGIN EXTRACTING INFORMATION FROM IT
		ProcessRecordMenuFrag recordMenuFrag = (ProcessRecordMenuFrag) getSupportFragmentManager().findFragmentByTag("RecordMenuFrag");
		ProcessRecordFragment recordFrag = (ProcessRecordFragment) getSupportFragmentManager().findFragmentByTag("RecordFrag");

		//GET A RECORD FROM THE RUNNING ProcessRecordFragment AND ADD IT TO THE ARRAY IN A NEW POSITION (-1 SWITCH IN addRecordToLog METHOD)
		Record newRecord = recordFrag.getRecord();
		addRecordToLog(newRecord, -1);

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
	public void logCancelButtonPush() {
		Log.i(EVENT, "MainActivity logCancel()");

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
	public void recordCancel() {
		Log.i(EVENT, "MainActivity recordCancel()");

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
		Log.i(EVENT, "MainActivity recordCancel()");

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

	public void addRecordToLog(Record record, int selection){
		Log.i(EVENT, "MainActivity addRecordToLog(), selection --> " + selection);

		if (selection == -1) {
			//GROW THE WASH LOG AND ADD THE SUPPLIED RECORD TO THE END
			Record[] newArray = new Record[washLog.length+1];
			System.arraycopy(washLog, 0, newArray, 0, washLog.length);
			newArray[newArray.length - 1] = record;
			washLog = newArray; 
			return;
		}
		else if (selection > -1) {
			washLog[selection] = record;
		}
	}

	public void addHeadersToLog(){
		Log.i(EVENT, "MainActivity addHeadersToLog()");

		//GROW THE WASH LOG AND ADD A DEFAULT RECORD AT THE HEAD, THEREBY CREATING HEADERS FOR THE CSV FILE
		Record[] newArray = new Record[washLog.length+1];
		System.arraycopy(washLog, 0, newArray, 1, washLog.length);
		newArray[0] = new Record();
		washLog = newArray;        
	}

	@Override
	public void editSelectedRecord() {
		Log.i(EVENT, "MainActivity editSelectedRecord()");

		//FIND THE LOG FRAGMENT WHILE IT IS STILL RUNNING AND EXTRACT THE SELECTION VALUE
		LogViewFragment logViewFrag = (LogViewFragment) getSupportFragmentManager().findFragmentByTag("LogFrag");

		//SET THE LogView SELECTION TO THE lastSelection GLOBAL SELECTION VARIABLE
		lastSelection = logViewFrag.hasSelection();

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
		recordFrag.setFields(lastSelection, washLog);
	}

	@Override
	public int logHasSelection() {
		Log.i(EVENT, "MainActivity selectionTrue()");

		//FIND THE LOG FRAGMENT WHILE IT IS STILL RUNNING AND EXTRACT THE SELECTION VALUE
		LogViewFragment logViewFrag = (LogViewFragment) getSupportFragmentManager().findFragmentByTag("LogFrag");

		return logViewFrag.hasSelection();
	}

	public void saveLogInternalStorage () {
		Log.i(EVENT, "MainActivity saveLogInternalStorage()");

		int numberOfRecords = washLog.length;

		//CREATE THE BASE JSON OBJECT AND ADD A FEW VALUES
		JSONObject myJSON = new JSONObject();

		try {
			try {
				try {

					//SET ANY VALUES AS EXTRAS TO THE LOG
					myJSON.put("WASHLOG_LENGTH", numberOfRecords);

					//CREATE A JSON ARRAY FOR HOLDING THE WASHLOG
					JSONArray washLogJSON = new JSONArray();

					//PARSE ITEMS INTO THE JSON WASHLOG
					for (int i = 0; i < numberOfRecords; i++) {
						JSONObject record = new JSONObject();
						String selection = "" + i;
						record.put("SELECTION", selection);
						record.put("VIN", washLog[i].getVinRecord());
						record.put("MILES", washLog[i].getMilesRecord());
						record.put("GASLEVEL", washLog[i].getGasLevelRecord());
						record.put("GASPUMPED", washLog[i].getGasPumpedRecord());
						record.put("ERACNUMBER", washLog[i].getEmployeeNumberRecord());
						record.put("INSPECTION", washLog[i].getInspectionResultRecord());
						record.put("SMOKEORPETS", washLog[i].getSmokeOrPetsRecord());
						record.put("NOTES", washLog[i].getNotesRecord());
						washLogJSON.put(record);
					}

					//ADD THE WASHLOG JSON OBJECT TO THE MAIN JSON OBJECT
					myJSON.put("washLogJSON", washLogJSON);

					//GET REFERENCE TO WRITERS
					FileOutputStream fileStream = null; 
					OutputStreamWriter streamWriter = null;
					BufferedOutputStream bufferedStream = null;

					//SETUP THE DIRECTORY AND FILE FOR WRITING
					String root = Environment.getExternalStorageDirectory().toString();
					File directory = new File(root + "/JSON");
					String filename = "testJSON.json";
					directory.mkdirs();
					File file = new File (directory, filename);

					//EXECUTE THE JSON WRITE
					fileStream = new FileOutputStream(file);
					bufferedStream = new BufferedOutputStream(fileStream);  
					streamWriter = new OutputStreamWriter(bufferedStream); 
					streamWriter.write(myJSON.toString()); 
					bufferedStream.flush();
					streamWriter.flush(); 
					streamWriter.close();

				} catch (JSONException e) {e.printStackTrace();}
			} catch (FileNotFoundException e) {e.printStackTrace();}
		} catch (IOException e) {e.printStackTrace();}
	}

	public void loadLogInternalStorage () {
		Log.i(EVENT, "MainActivity loadLogInternalStorage()");
		JSONObject inputJSON = null;
		JSONArray washLogJSON = null;
		int numberOfRecordsInJSON = 0;
		Record[] tempLog = new Record[0];

		try {
			try {

				//SETUP THE DIRECTORY AND FILE FOR READING
				String root = Environment.getExternalStorageDirectory().toString();
				File directory = new File(root + "/JSON");
				String filename = "testJSON.json";
				File file = new File (directory, filename);

				//GET REFERENCES TO READER AND STRING BUILDER
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				String input = null;
				StringBuilder stringBuilder = new StringBuilder();

				//READ FILE WHILE MORE EXISTS
				while((input = bufferedReader.readLine()) != null) {
					stringBuilder.append(input);
				}

				//CLOSE THE READER AND CAST THE RESULTING STRING INTO A JSON OBJECT
				bufferedReader.close();
				inputJSON = new JSONObject(stringBuilder.toString());

				//SET THE NUMBER OF RECORDS IN THE JSON ARRAY VIA THE STORED KEY, AND PREPARE THE TEMPORARY LOG TO HANDLE THAT MANY RECORDS
				numberOfRecordsInJSON = inputJSON.getInt("WASHLOG_LENGTH");
				tempLog = new Record[numberOfRecordsInJSON];

				//GET A HANDLE FOR THE WASHLOG ARRAY INSIDE THE JSON OBJECT
				washLogJSON = (JSONArray) inputJSON.get("washLogJSON");

			} catch (JSONException e) {e.printStackTrace();}
		} catch (IOException e) {e.printStackTrace();}

		try {
			//PARSE THE JSON ARRAY INTO NEW RECORDS AND CREATE A WASH LOG
			for (int i=0; i < numberOfRecordsInJSON; i++) { 
				JSONObject JSONobj = washLogJSON.getJSONObject(i);
				// Pulling items from the array
				int selection = JSONobj.getInt("SELECTION");
				String vin = JSONobj.getString("VIN");
				String miles = JSONobj.getString("MILES");
				String gasLevel = JSONobj.getString("GASLEVEL");
				String gasPumped = JSONobj.getString("GASPUMPED");
				String employeeNumber = JSONobj.getString("ERACNUMBER");
				String inspection = JSONobj.getString("INSPECTION");
				String smokeOrPets = JSONobj.getString("SMOKEORPETS");
				String notes = JSONobj.getString("NOTES");
				tempLog[selection] = new Record(vin, miles, gasLevel, gasPumped, employeeNumber, inspection, smokeOrPets, notes);
			} 

		} catch (JSONException e) {e.printStackTrace();}

		//SAVE THE RESULTING ARRAY INTO THE CURRENT WASHLOG POSITION
		washLog = tempLog;
	}

	@Override
	public void saveLoadLogButton() {
		Log.i(EVENT, "MainActivity saveLoadLogButton()");
		loadLogInternalStorage();
	}

	@Override
	public void saveSaveLogButton() {
		Log.i(EVENT, "MainActivity saveSaveLogButton()");
		saveLogInternalStorage();
		saveLogToCSV();
	}

	@Override
	public void saveCancelButton() {
		Log.i(EVENT, "MainActivity saveCancelButton()");
		
		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		HomeFragment homeFrag = new HomeFragment();		
		homeFrag.setArguments(getIntent().getExtras());		
		transaction.replace(R.id.main_top_frame, homeFrag, "HomeFrag");
		transaction.addToBackStack(null);
		transaction.commit();
		getSupportFragmentManager().executePendingTransactions();
	}
	
	public void saveLogToCSV() {
		Log.i(EVENT, "MainActivity saveLogToCSV()");
		
		//GET REFERENCE TO WRITERS
		FileOutputStream fileStream = null; 
		OutputStreamWriter streamWriter = null;
		BufferedOutputStream bufferedStream = null;
		StringBuilder stringBuilder = new StringBuilder();

		//SETUP THE DIRECTORY AND FILE FOR WRITING
		String root = Environment.getExternalStorageDirectory().toString();
		File directory = new File(root + "/JSON");
		String filename = "testCSV.csv";
		directory.mkdirs();
		File file = new File (directory, filename);
			
		saveLogInternalStorage();
		addHeadersToLog();
		
		            //start at the beginning of the index and loop through until finished writing
		            for (int index = 0;index<washLog.length;index++) {

		                //set the data to be written
		            	stringBuilder.append(washLog[index].getVinRecord());
		            	stringBuilder.append(',');
		            	stringBuilder.append(washLog[index].getMilesRecord());
		            	stringBuilder.append(',');
		            	stringBuilder.append(washLog[index].getGasLevelRecord());
		            	stringBuilder.append(',');
		            	stringBuilder.append(washLog[index].getGasPumpedRecord());
		            	stringBuilder.append(',');
		            	stringBuilder.append(washLog[index].getEmployeeNumberRecord());
		            	stringBuilder.append(',');
		            	stringBuilder.append(washLog[index].getInspectionResultRecord());
		            	stringBuilder.append(',');
		            	stringBuilder.append(washLog[index].getSmokeOrPetsRecord());
		            	stringBuilder.append(',');
		            	stringBuilder.append(washLog[index].getNotesRecord());
		            	stringBuilder.append(',');
		            	stringBuilder.append('\n');

		            }
		            
		            try {
		            	try {
		            		
		            		//SET THE FILE STREAM
		            		fileStream = new FileOutputStream(file);
		            		bufferedStream = new BufferedOutputStream(fileStream);  
		            		streamWriter = new OutputStreamWriter(bufferedStream); 

		            		//WRITE THE STRING THEN FLUSH AND CLOSE THE WRITING STACK
		            		streamWriter.write(stringBuilder.toString());
		            		bufferedStream.flush();
		            		streamWriter.flush(); 
		            		streamWriter.close();
		            		
		            	} catch (FileNotFoundException e) {e.printStackTrace();}
		            } catch (IOException e) {e.printStackTrace();} 
		
		loadLogInternalStorage();
		
	}
}
