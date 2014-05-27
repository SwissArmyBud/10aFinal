package com.example.eraclog;

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

import com.example.eraclog.HomeFragment.HomeFragmentInterface;
import com.example.eraclog.LogSaveFragment.SaveLogFragInterface;
import com.example.eraclog.LogViewFragment.LogUpdateStatus;
import com.example.eraclog.LogViewMenuFrag.LogViewMenuFragInterface;
import com.example.eraclog.ProcessRecordEditMenuFrag.ProcessRecordEditMenuFragInterface;
import com.example.eraclog.ProcessRecordFragment.ProcessRecordFragInterface;
import com.example.eraclog.ProcessRecordMenuFrag.ProcessRecordMenuFragInterface;

import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class MainActivity extends FragmentActivity implements SaveLogFragInterface, ProcessRecordEditMenuFragInterface, HomeFragmentInterface, ProcessRecordMenuFragInterface, LogViewMenuFragInterface, ProcessRecordFragInterface, LogUpdateStatus {

	private static final String LIFE = "LifeCycle";
	private static final String EVENT = "Event";
	private static Record[] washLog = {};
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
		setContentView(R.layout.main_activity);

		//INJECTING FRAGMENTS INTO ROOT VIEW
		if (savedInstanceState != null) {
			return;
		}

		goToHomeScreen();
		
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

	//ALLOWS THE PROGRAM TO UPDATE THE STATUS TAB AT THE BOTTOM OF THE SCREEN
	public void updateStatus(String string) {
		Log.i(EVENT, "MainActivity updateStatus() -->" + string);

		TextView statusText = (TextView) findViewById(R.id.current_status);
		statusText.setText(string);
		
	}

	//RETURNS THE CURRENT washLog
	public static Record[] getCurrentLog() {
		Log.i(EVENT, "MainActivity getCurrentLog()");

		//RETURN WHATEVER THE CURRENT WASH LOG IS
		return washLog;
	}
	
	//THIS AREA IS FOR THE HOME SCREEN BUTTONS
	//_____________________________________
	//
	@Override
	public void homeAddRecordButtonPush() {
		Log.i(EVENT, "MainActivity homeAddRecordButtonPush()");

		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		ProcessRecordFragment recordFrag = new ProcessRecordFragment();	
		ProcessRecordMenuFrag recordMenuFrag = new ProcessRecordMenuFrag();	
		fragmentTransition(recordFrag, "RecordFrag", recordMenuFrag, "RecordMenuFrag");
		
		updateStatus("**NEW RECORD**");
	}

	@Override
	public void homeViewLogButton() {
		Log.i(EVENT, "MainActivity homeViewLogButton()");

		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		LogViewFragment logFrag = new LogViewFragment();	
		LogViewMenuFrag logMenuFrag = new LogViewMenuFrag();
		fragmentTransition(logFrag, "LogViewFrag", logMenuFrag, "LogMenuFrag");
		
		updateStatus("**VIEWING LOG**");
	}

	@Override
	public void homeSaveLogButton() {
		Log.i(EVENT, "MainActivity homeSaveLogButton()");

		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		LogSaveFragment saveLogFrag = new LogSaveFragment();
		fragmentTransition(saveLogFrag, "SaveLogFrag");
		
		updateStatus("**MANAGING LOG**");
	}
	
	//THIS AREA IS FOR THE LOG VIEW/SAVE SCREEN BUTTONS
	//_____________________________________
	//
	@Override
	public void saveLoadLogButton() {
		Log.i(EVENT, "MainActivity saveLoadLogButton()");
		
		loadLogInternalStorage();
		goToHomeScreen();
		updateStatus("**LOG LOADED**");
		
	}

	@Override
	public void saveSaveLogButton() {
		Log.i(EVENT, "MainActivity saveSaveLogButton()");
		
		saveLogInternalStorage();
		saveLogToCSV();
		goToHomeScreen();
		updateStatus("**LOG SAVED**");
		
	}

	public void saveClearLogButton() {
		Log.i(EVENT, "MainActivity() saveClearLogButton()");
			
		washLog = new Record[0];
		
		updateStatus("**LOG CLEARED**");
	}
	
	//GETS CALLED BY ProcessRecord OR ProcessRecordEdit TO SAVE THE PROCESS FRAGMENT'S RECORD INTO THE LOG
	@Override
	public void saveRecordToLog() {
		Log.i(EVENT, "MainActivity saveRecordButtonPush()");

		//GET REFERENCES TO THE ProcessRecordFragment AND BEGIN EXTRACTING INFORMATION FROM IT
		ProcessRecordFragment recordFrag = (ProcessRecordFragment) getSupportFragmentManager().findFragmentByTag("RecordFrag");

		//GET A RECORD FROM THE RUNNING ProcessRecordFragment AND ADD IT TO THE ARRAY IN THE POSITION IS WAS RECIEVED FROM (lastSelection SET IN editSelectedRecord)
		Record newRecord = recordFrag.getRecord();
		addRecordToLog(newRecord, lastSelection);

		updateStatus("**RECORD " + lastSelection + " SAVED**");
		
		lastSelection = -1;
		
		goToHomeScreen();

	}

	//GETS CALLED BY LogViewMenu, SETS UP THE RECORD FOR EDITING
	@Override
	public void editSelectedRecord() {
		Log.i(EVENT, "MainActivity editSelectedRecord() selection --> " + logHasSelection());

		if (logHasSelection() != -1) {
		
		//SET THE lastSelection GLOBAL SELECTION VARIABLE TO THE LogView SELECTION 
		lastSelection = logHasSelection();

		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		ProcessRecordFragment recordFrag = new ProcessRecordFragment();		
		ProcessRecordEditMenuFrag recordMenuFrag = new ProcessRecordEditMenuFrag();
		fragmentTransition(recordFrag, "RecordFrag", recordMenuFrag, "RecordMenuFrag");

		//SET THE FIELDS IN THE ProcessRecordFragment USING THE SELECTED ID FROM THE ListView
		recordFrag.setFields(lastSelection, washLog);
		updateStatus("**EDITING RECORD " + lastSelection + "**");
		}
		
	}
	
	private int logHasSelection() { //ACTIVITY METHOD
		Log.i(EVENT, "MainActivity selectionTrue()");

		//FIND THE LOG FRAGMENT WHILE IT IS STILL RUNNING AND EXTRACT THE SELECTION VALUE
		LogViewFragment logViewFrag = (LogViewFragment) getSupportFragmentManager().findFragmentByTag("LogViewFrag");

		return logViewFrag.hasSelection();
	}

	private void addRecordToLog(Record record, int selection){ //ACTIVITY METHOD
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
	
	private void addHeadersToLog(){ //ACTIVITY METHOD
		Log.i(EVENT, "MainActivity addHeadersToLog()");

		//GROW THE WASH LOG AND ADD A DEFAULT RECORD AT THE HEAD, THEREBY CREATING HEADERS FOR THE CSV FILE
		Record[] newArray = new Record[washLog.length+1];
		System.arraycopy(washLog, 0, newArray, 1, washLog.length);
		newArray[0] = new Record();
		washLog = newArray;
		
	}
	
	private void saveLogInternalStorage () { //ACTIVITY METHOD
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

	private void loadLogInternalStorage () { //ACTIVITY METHOD
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

	private void saveLogToCSV() { //ACTIVITY METHOD
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

		//SAVE THE CURRENT LOG TO INTERNAL STORAGE AND THEN ADD HEADERS
		saveLogInternalStorage();
		addHeadersToLog();

		//BUILD THE CSV USING FIELD PARSING AND LINE DEMARCATION
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

		//LOAD THE SAVED LOG FROM INTERNAL STORAGE, BASICALLY JUST REMOVING THE HEADERS
		loadLogInternalStorage();
		
	}

	private void fragmentTransition(Fragment topFrag, String topFragName, Fragment bottomFrag, String bottomFragName) { //ACTIVITY METHOD
		Log.i(EVENT, "MainActivity fragmentTransition(Fragment, String, Fragment, String)");

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.main_top_frame, topFrag, topFragName);
		transaction.replace(R.id.main_bottom_frame, bottomFrag, bottomFragName);
		transaction.addToBackStack(null);
		transaction.commit();
		getSupportFragmentManager().executePendingTransactions();
		
	}

	private void fragmentTransition(Fragment topFrag, String topFragName) { //ACTIVITY METHOD
		Log.i(EVENT, "MainActivity fragmentTransition(Fragment, String)");

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();	
		
		//TESTS FOR A LOWER FRAME AND THEN REMOVES IT IF IT EXISTS
		if (getSupportFragmentManager().findFragmentById(R.id.main_bottom_frame) != null) {
			Fragment menuFrag = (Fragment) getSupportFragmentManager().findFragmentById(R.id.main_bottom_frame);
			transaction.remove(menuFrag);
			}
		
		transaction.replace(R.id.main_top_frame, topFrag, topFragName);
		transaction.addToBackStack(null);
		transaction.commit();
		getSupportFragmentManager().executePendingTransactions();

	}

	public void goToHomeScreen() { //ACTIVITY METHOD
		Log.i(EVENT, "MainActivity goToHomeScreen()");
		
		//WORK THROUGH THE APPROPRIATE FRAGMENT TRANSITIONS
		HomeFragment homeFrag = new HomeFragment();		
		fragmentTransition(homeFrag, "HomeFrag");
		
		updateStatus("**AT HOME**");
	}

}
