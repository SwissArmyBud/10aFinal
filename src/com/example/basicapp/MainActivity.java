package com.example.basicapp;

import com.example.basicapp.HomeFragment.HomeFragmentInterface;
import com.example.basicapp.LogViewFragment.LogViewInterface;
import com.example.basicapp.LogViewMenuFrag.LogViewMenuFragInterface;
import com.example.basicapp.ProcessRecordMenuFrag.ProcessRecordMenuFragInterface;
import com.example.basicapp.ProcessRecordFragment.RecordProcessFragInterface;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class MainActivity extends FragmentActivity implements HomeFragmentInterface, RecordProcessFragInterface, ProcessRecordMenuFragInterface, LogViewInterface, LogViewMenuFragInterface {

	private static Record[] washLog = {new Record(), new Record(), new Record()};
	private static final String LIFECYCLE = "LifeCycle";
    private static final String EVENT = "Event";
	
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
        Log.i(LIFECYCLE, "onCreate");
        
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
        
        Log.i(EVENT, "onCreate Fragment Commit Finished");
        
    }

    protected void onStart() {
		super.onStart();
		Log.i(LIFECYCLE, "onStart");
		
	}
	
	protected void onResume() {
		super.onResume();
		Log.i(LIFECYCLE, "onResume");
		
	}
	
	// ***EXECUTION***
	
	protected void onPause() {
		super.onPause();
		Log.i(LIFECYCLE, "onPause");
		
	}
	
	protected void onStop() {
		super.onStop();
		Log.i(LIFECYCLE, "onStop");
		
	}
	
	protected void onRestart() { //CALLED BETWEEN onStop AND onStart WHEN RESTARTING FROM PAST onStop IN LIFECYCLE
		super.onRestart();
		Log.i(LIFECYCLE, "onRestart");
		
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.i(LIFECYCLE, "onDestroy");
		
	}

	protected void onSaveInstanceState(Bundle outState) {  //CALLED AFTER onStop USUALLY DURING ORIENTATION TRANSITION
		super.onSaveInstanceState(outState);
		Log.i(LIFECYCLE, "onSaveInstanceState");
		
		/*
		EditText textBox = (EditText) findViewById(R.id.main_text);
		CharSequence userText = textBox.getHint();
		outState.putCharSequence("savedText", userText);
		*/
		
	}
	
	protected void onRestoreInstanceState(Bundle savedState) {	//CALLED AFTER onStart USUALLY DURING ORIENTATION TRANSITION
		Log.i(LIFECYCLE, "onRestoreInstanceState");
		
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
	    	EditText textBox = (EditText) findViewById(R.id.main_text);
			textBox.setHint("Settings Pressed");
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
        TextView statusText = (TextView) findViewById(R.id.current_status);
        statusText.setText(string);
	}
	
	public void setTextHint(String string) {
		
		FragmentOne fragOne = (FragmentOne) getSupportFragmentManager().findFragmentByTag("FragOneTrans");
		fragOne.setHint(string);
		Log.i(EVENT, "MainActivity setHint() complete");
		
	}

	@Override
	public void homeAddRecordButtonPush() {
		Log.i(EVENT, "HomeFragment addRecord push method in MainActivity");
		
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
	}
	
	@Override
	public void homeViewLogButton() {
		Log.i(EVENT, "HomeFragment viewLog push method in MainActivity");
		
		//SETUP THE TRANSACTION MANAGER
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
		//SETUP THE RECORD AND MENU FRAGMENTS
        LogViewFragment logFrag = new LogViewFragment();	
        LogViewMenuFrag logMenuFrag = new LogViewMenuFrag();	
        logFrag.setArguments(getIntent().getExtras());	
        logMenuFrag.setArguments(getIntent().getExtras());	
        
        //ADD THE LOG FRAGMENT TO THE TOP FRAME AND THE LOG MENU TO THE BOTTOM FRAME
        transaction.replace(R.id.main_top_frame, logFrag, "LogFrag");
        transaction.add(R.id.main_bottom_frame, logMenuFrag, "LogMenuFrag");
        transaction.addToBackStack(null);
        transaction.commit();
	}

	@Override
	public void homeSaveLogButton() {
		// TODO Auto-generated method stub
		Log.i(EVENT, "HomeFragment saveLog push method in MainActivity");
	}

	@Override
	public void commitRecordButtonPush() {
		
		ProcessRecordMenuFrag recordMenuFrag = (ProcessRecordMenuFrag) getSupportFragmentManager().findFragmentByTag("RecordMenuFrag");
		ProcessRecordFragment recordFrag = (ProcessRecordFragment) getSupportFragmentManager().findFragmentByTag("RecordFrag");
		String string = recordFrag.getRecord("");
		Log.i(EVENT, string);
		
		//
		//
		//TODO
		//THIS IS WHERE THE LOGIC FOR CREATING AND STORING THE RECORD INTO THE WASHLOG GOES
		//
		//
		//
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        HomeFragment homeFrag = new HomeFragment();		
        homeFrag.setArguments(getIntent().getExtras());		
        
        transaction.replace(R.id.main_top_frame, homeFrag, "HomeFrag");
        transaction.remove(recordMenuFrag);
        transaction.addToBackStack(null);
        transaction.commit();
		
	}

	@Override
	public void viewLogEntry(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logCancel() {
		Log.i(EVENT, "logCancel()");
		LogViewMenuFrag logViewMenuFrag = (LogViewMenuFrag) getSupportFragmentManager().findFragmentByTag("LogMenuFrag");
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        HomeFragment homeFrag = new HomeFragment();		
        homeFrag.setArguments(getIntent().getExtras());		
        
        transaction.replace(R.id.main_top_frame, homeFrag, "HomeFrag");
        transaction.remove(logViewMenuFrag);
        transaction.addToBackStack(null);
        transaction.commit();
	}

	@Override
	public void recordCancel() {
		Log.i(EVENT, "recordCancel()");
		ProcessRecordMenuFrag recordMenuFrag = (ProcessRecordMenuFrag) getSupportFragmentManager().findFragmentByTag("RecordMenuFrag");
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        HomeFragment homeFrag = new HomeFragment();		
        homeFrag.setArguments(getIntent().getExtras());		
        
        transaction.replace(R.id.main_top_frame, homeFrag, "HomeFrag");
        transaction.remove(recordMenuFrag);
        transaction.addToBackStack(null);
        transaction.commit();
	}
	
	public static Record[] getCurrentLog () {
		return washLog;
	}
	
	public Record[] addRecordToLog(Record record, Record[] washLog){

        Record[] newArray = new Record[washLog.length+1];
        System.arraycopy(washLog, 0, newArray, 0, washLog.length);
    	newArray[newArray.length - 1] = record;
    	return newArray;
        
    }
	
}
