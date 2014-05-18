package com.example.basicapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


public class MainActivity extends FragmentActivity {

    private static final String LIFECYCLE = "LifeCycle";
    private static final String EVENT = "Event";
	
   /*
    * LIFECYCLE CREATION PATH
    
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
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Log.i(LIFECYCLE, "onCreate");
        
        //SETTING FRAGMENT VIEW
        if (savedInstanceState != null) {
            return;
        }
        FragmentOne firstFragment = new FragmentOne();		
        firstFragment.setArguments(getIntent().getExtras());
        FragmentManager fragManager = getSupportFragmentManager();		
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.add(R.id.main_container, firstFragment);	
        transaction.commit();
        Log.i(EVENT, "Fragment Commit Finished");
        
        
        /*__________________________________
         * 
         * AN EXTRA BLOCK FOR SETTING BUTTON LISTENERS - REPLACE button_name WITH ID NAME
         * _________________________________
         *
         
         Button button = (Button)findViewById(R.id.button_name);
         button.setOnClickListener(
            	new Button.OnClickListener() {
            		public void onClick(View v) {
            			//TODO
            		}
            	}
         );
            
		*
		*
		*/
         
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

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.i(LIFECYCLE, "onSaveInstanceState");
		
		final EditText textBox = (EditText) findViewById(R.id.main_text);
		CharSequence userText = textBox.getHint();
		outState.putCharSequence("savedText", userText);
		
		/*
		 * AN EXTRA BLOCK FOR PUTTING TEXT - REPLACE textBoxName WITH ID NAME
		
		textBox = (EditText) findViewById(R.id.textBoxName);
		userText = textBox.getText();
		outState.putCharSequence("savedText", userText);
		
		*/

	}
	
	protected void onRestoreInstanceState(Bundle savedState) {		
		Log.i(LIFECYCLE, "onRestoreInstanceState");
		
		final EditText textBox = (EditText) findViewById(R.id.main_text);
		CharSequence userText = savedState.getCharSequence("savedText");
		textBox.setHint(userText);
		
		/*
		 * AN EXTRA BLOCK FOR GETTING TEXT - REPLACE textBoxName WITH ID NAME
		
		textBox = (EditText) findViewById(R.id.textBoxName);
		userText = savedState.getCharSequence("savedText");
		textBox.setText(userText);
		
		 */
	}
    
   /* ____________________________________________
	*
	*	THIS AREA IS FOR HANDLING THE MENU SYSTEM
	* ____________________________________________
   */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		Log.i(EVENT, "MENU CREATED");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		int id = item.getItemId();
		//ADD MENU OPTION EFFECTS
		
		if (id == R.id.settings_button) {
			View rootView = findViewById(R.id.main_text);
			EditText textBox = (EditText) rootView.findViewById(R.id.main_text);
			textBox.setHint("Settings Pressed");
			Log.i(EVENT, "SETTINGS PRESSED");
			return true;
		}
		
		/*
		 * AN EXTRA BLOCK FOR MENU OPTION EFFECTS - REPLACE buttonID WITH ID NAME
		
		if (id == R.id.buttonID) {
			return true;
		}
		
		*/
		
		return super.onOptionsItemSelected(item);

	}
	
	
	
}
