package com.example.basicapp;

import com.example.basicapp.HomeFragment.HomeFragmentInterface;
import com.example.basicapp.ProcessRecordMenuFrag.ProcessRecordMenuFragInterface;
import com.example.basicapp.RecordProcessFragment.RecordProcessFragInterface;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class MainActivity extends FragmentActivity implements HomeFragmentInterface, RecordProcessFragInterface, ProcessRecordMenuFragInterface {

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
        RecordProcessFragment recordFrag = new RecordProcessFragment();	
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
		// TODO Auto-generated method stub
		Log.i(EVENT, "HomeFragment viewLog push method in MainActivity");
	}

	@Override
	public void homeSaveLogButton() {
		// TODO Auto-generated method stub
		Log.i(EVENT, "HomeFragment saveLog push method in MainActivity");
	}

	@Override
	public void commitRecordButtonPush() {
		
		RecordProcessFragment recordFrag = (RecordProcessFragment) getSupportFragmentManager().findFragmentByTag("RecordFrag");
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
        transaction.remove(recordFrag);
        transaction.addToBackStack(null);
        transaction.commit();
		
	}
    
	
}
