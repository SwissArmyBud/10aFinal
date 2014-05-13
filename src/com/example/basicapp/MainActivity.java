package com.example.basicapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.util.Log;


public class MainActivity extends Activity {

    private static final String TAG = "LifeCycle";
	
   /*
    * LIFECYCLE CREATION PATH
    
    ***CREATION***
	onCreate
	onStart
	onRestoreInstanceState (Dynamic Transition From VM)
	onResume
	***EXECUTION***
	onPause
	onSaveInstanceState (Dynamic Tranistion from VM)
	onStop
	onDestroy
	***DESTRUCTION***

    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Log.i(TAG, "onCreate");
        
        
    }

    protected void onStart() {
		super.onStart();
		Log.i(TAG, "onStart");
		
		
	}
	
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
		
		
	}
	
	// ***EXECUTION***
	
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause");
		
		
	}
	
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop");
		
		
	}
	
	protected void onRestart() { //CALLED BETWEEN onStop AND onStart WHEN RESTARTING FROM PAST onStop IN LIFECYCLE
		super.onRestart();
		Log.i(TAG, "onRestart");
		
		
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy");
		
		
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.i(TAG, "onSaveInstanceState");
		
		final EditText textBox = (EditText) findViewById(R.id.EDITTEXT);
		CharSequence userText = textBox.getText();
		outState.putCharSequence("savedText", userText);
		
		/*
		 * AN EXTRA BLOCK FOR PUTTING TEXT - REPLACE textBoxName WITH ID NAME
		
		textBox = (EditText) findViewById(R.id.textBoxName);
		userText = textBox.getText();
		outState.putCharSequence("savedText", userText);
		
		*/

	}
	
	protected void onRestoreInstanceState(Bundle savedState) {		
		Log.i(TAG, "onRestoreInstanceState");
		
		final EditText textBox = (EditText) findViewById(R.id.EDITTEXT);
		CharSequence userText = savedState.getCharSequence("savedText");
		textBox.setText(userText);
		
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
