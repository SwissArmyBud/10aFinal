package com.example.basicapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentOne extends Fragment implements OnClickListener{

	Button button;
    private static final String EVENT = "Event";
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        
        button = (Button) rootView.findViewById(R.id.main_button);
        button.setOnClickListener(this); 
        
        return rootView;
    }
	
	public void setHint(String string) {
		View rootView = getView().findViewById(R.id.main_text);
		EditText textBox = (EditText) rootView.findViewById(R.id.main_text);
		textBox.setHint(string);
	}
	
	@Override
    public void onClick(View v) {
		setHint("Banana Button");
		Log.i(EVENT, "BUTTON CLICKED");
    }
	
}
