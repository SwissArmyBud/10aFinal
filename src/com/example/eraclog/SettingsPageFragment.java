
package com.example.eraclog;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

public class SettingsPageFragment extends Fragment implements OnClickListener {

	private static final String EVENT = "Event";
	private static final String LIFE = "LifeCycle";
	
	RadioButton nameRadio;
	RadioButton dateRadio;

	//THIS SECTION SETS BUTTON LISTENERS
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LIFE, "SettingsPageFragment onCreateView");

		//INFLATE THE LAYOUT FOR THIS FRAGMENT
		View rootView = inflater.inflate(R.layout.settings_page_fragment, container, false);

		//SET BUTTON CLICK LISTENERS
        nameRadio = (RadioButton) rootView.findViewById(R.id.settings_name_radio);
        nameRadio.setOnClickListener(this); 
        dateRadio = (RadioButton) rootView.findViewById(R.id.settings_date_radio);
        dateRadio.setOnClickListener(this); 
		
		//RETURN THE VIEW
		return rootView;
	}

	//THIS SECTION IS ACTIVATED WHEN A BUTTON IS CLICKED
		@Override  
	    public void onClick(View v) { 
	        Log.i(EVENT, "SettingsPageFragment onClick");
			
			switch (v.getId()) {
			
			//ADDING CASES FOR DIFFERENT BUTTON IDS
			case R.id.settings_name_radio:
		    	Log.i(EVENT, "SettingsPageFragment calling nameClick()");
		    	nameClick();
				break;
			case R.id.settings_date_radio:
		    	Log.i(EVENT, "SettingsPageFragment calling dateClick()");
		    	dateClick();
				break;
			}
	    }
	
	/* ____________________________________________
	 *
	 *	THIS AREA IS FOR ADDING SUPPORTING METHODS
	 * ____________________________________________
	 */
		
		private void nameClick() {
	        Log.i(EVENT, "SettingsPageFragment nameClick()");
			
			EditText text = (EditText) getView().findViewById(R.id.settings_default_path);
			text.setEnabled(true);
			if (MainActivity.defaultCSVFilename == null || MainActivity.defaultCSVFilename.equalsIgnoreCase("") || MainActivity.defaultCSVFilename.equalsIgnoreCase("Saved Log")) {
				text.setText(null);
				text.setHint("Default: Saved Log");
			} else {text.setText(MainActivity.defaultCSVFilename);}
			
		}

		private void dateClick() {
	        Log.i(EVENT, "SettingsPageFragment dateClick()");
			
	        EditText text = (EditText) getView().findViewById(R.id.settings_default_path);
			text.setEnabled(false);
			text.setText(null);
			text.setHint("DATE FORMAT SELECTED");
			
		}
		
		public JSONObject getSettingsInJSON() {
	        Log.i(EVENT, "SettingsPageFragment getSettingsInJSON()");
			
	        EditText branch = (EditText) getView().findViewById(R.id.settings_default_branch);
	        EditText ERAC = (EditText) getView().findViewById(R.id.settings_default_erac);
	        EditText path = (EditText) getView().findViewById(R.id.settings_default_path);
			
	        String branchText = branch.getText().toString();
			String ERACText = ERAC.getText().toString();
			String pathText = path.getText().toString();
	        
			JSONObject settingsJSON = new JSONObject();
			
			try {
				
				settingsJSON.put("DEFAULT_BRANCH", branchText);
				settingsJSON.put("ERAC_NUMBER", ERACText);
				if (pathText == null || pathText.equalsIgnoreCase("") || pathText.equalsIgnoreCase("Saved Log")) {
					pathText = "Saved Log";
				}
				settingsJSON.put("FILE_NAME", filenameScrubber(pathText));
				
			} catch (JSONException e) {e.printStackTrace();}
			
	        
			return settingsJSON;
		}
		
		public void setFields(JSONObject settingsJSON) {
	        Log.i(EVENT, "SettingsPageFragment getSettingsInJSON()");
			
	        EditText branch = (EditText) getView().findViewById(R.id.settings_default_branch);
	        EditText ERAC = (EditText) getView().findViewById(R.id.settings_default_erac);
	        EditText path = (EditText) getView().findViewById(R.id.settings_default_path);
			
	        try {
	        	try{
	        	
	        branch.setText(settingsJSON.getString("DEFAULT_BRANCH"));
			ERAC.setText(settingsJSON.getString("ERAC_NUMBER"));
			
			if (settingsJSON.getString("FILE_NAME") == null || settingsJSON.getString("FILE_NAME").equalsIgnoreCase("") || settingsJSON.getString("FILE_NAME").equalsIgnoreCase("Saved Log")) {
				path.setText(null);
				path.setHint("Default: Saved Log");
			} else {path.setText(settingsJSON.getString("FILE_NAME"));}
			
			if (settingsJSON.getString("ERAC_NUMBER") == null || settingsJSON.getString("ERAC_NUMBER").equalsIgnoreCase("") || settingsJSON.getString("ERAC_NUMBER").equalsIgnoreCase("E863PB")) {
				ERAC.setText("E863PB");
			} else {path.setText(settingsJSON.getString("ERAC_NUMBER"));}
			
			if (settingsJSON.getString("DEFAULT_BRANCH") == null || settingsJSON.getString("DEFAULT_BRANCH").equalsIgnoreCase("") || settingsJSON.getString("DEFAULT_BRANCH").equalsIgnoreCase("23DP")) {
				branch.setText("23DP");
			} else {path.setText(settingsJSON.getString("ERAC_NUMBER"));}
			
	        }catch (NullPointerException e) {e.printStackTrace();}
	        } catch (JSONException e) {e.printStackTrace();}

		}

		private String filenameScrubber(String filename) {

			//SET FILE SEPARATOR AND ESCAPE CHARACTERS
			char fileSep = '/';
			char escape = '%';
			int len = filename.length();
			StringBuilder sb = new StringBuilder(len);
			
			//PULLS FILE EXTENSIONS OUT OF filename
			for (int i = 0; i < len; i++) {
				char ch = filename.charAt(i);
				if (ch == '.') {
					filename = filename.substring(0, i);
					len = filename.length();
					break;
				}
			}
			
			//PULLS FOLDER TREES OUT OF filename
			for (int i = len - 1; i >= 0; i--) {
				char ch = filename.charAt(i);
				if (ch == fileSep) {
					filename = filename.substring(i+1);
					len = filename.length();
					i = len - 1;
				}
			}
			
			//REMOVES ILLEGAL CHARACTERS FROM filename
			for (int i = 0; i < len; i++) {
				char ch = filename.charAt(i);
				if (ch < ' ' || ch >= 0x7F || ch == escape) { //ILLEGAL CHARACTERS
					sb.append(escape); //ESCAPES IF ILLEGAL
					if (ch < 0x10) {
						sb.append('0'); //ADDS A ZERO IF BELOW A CERTAIN HEX LEVEL
					}
					sb.append(Integer.toHexString(ch)); //IF ABOVE CERTAIN HEX LEVEL, ITEM IS ENCODED AS HEX
				} else {sb.append(ch);} //OTHERWISE THE CHARACTER IS ENCODED STRAIGHT INTO THE STRING
			}
			
			String cleanName = sb.toString();

			return cleanName;
		}
}
