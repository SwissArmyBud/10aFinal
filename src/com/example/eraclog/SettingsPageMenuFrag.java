package com.example.eraclog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class SettingsPageMenuFrag extends Fragment implements OnClickListener {

	private static final String EVENT = "Event";
	private static final String LIFE = "LifeCycle";

	Button saveButton;
	Button cancelButton;

	SettingsPageMenuFragInterface activityCallback;

	//CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
	public interface SettingsPageMenuFragInterface {
		public void settingsSaveButton();
		public void goToHomeScreen();
	}

	//THIS SECTION ENSURES INTERFACE COMPLIANCE
	@Override
	public void onAttach(Activity activity) { 
		super.onAttach(activity);
		Log.i(LIFE, "ProcessRecordMenuFrag onAttach");

		try {
			activityCallback = (SettingsPageMenuFragInterface) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement SettingsPageMenuFragInterface");
		}

	}

	//THIS SECTION SETS BUTTON LISTENERS
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(LIFE, "SettingsPageMenuFrag onCreateView");

		//INFLATE THE LAYOUT FOR THIS FRAGMENT
		View rootView = inflater.inflate(R.layout.settings_page_menu, container, false);

		//SET BUTTON CLICK LISTENERS
		cancelButton = (Button) rootView.findViewById(R.id.settings_page_cancel_button);
		cancelButton.setOnClickListener(this); 
		saveButton = (Button) rootView.findViewById(R.id.settings_page_save_button);
		saveButton.setOnClickListener(this);

		return rootView;
	}

	//THIS SECTION IS ACTIVATED WHEN A BUTTON IS CLICKED
	@Override  
	public void onClick(View v) { 
        Log.i(EVENT, "SettingsPageFragment onClick");

		switch (v.getId()) {

		//ADDING CASES FOR DIFFERENT BUTTON IDS
		case R.id.settings_page_save_button:
			Log.i(EVENT, "SettingsPageMenuFrag calling settingsSaveButton()");
			activityCallback.settingsSaveButton();
			break;
		case R.id.settings_page_cancel_button:
			Log.i(EVENT, "SettingsPageMenuFrag calling goToHomeScreen()");
			activityCallback.goToHomeScreen();
			break;
		}
	}

	/* ____________________________________________
	 *
	 *	THIS AREA IS FOR ADDING SUPPORTING METHODS
	 * ____________________________________________
	 */

}
