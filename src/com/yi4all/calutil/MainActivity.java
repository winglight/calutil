package com.yi4all.calutil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText idTxt = (EditText) findViewById(R.id.idTxt);
		final TextView resultTxt = (TextView) findViewById(R.id.resultTxt);
		Button calBtn = (Button) findViewById(R.id.calBtn);
		calBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String input = idTxt.getText().toString();
				resultTxt.setText(getString(R.string.result, generateHash(input)));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private String generateHash(String input){
		
		String hash = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(input.getBytes());
			BigInteger bigInt = new BigInteger(1, thedigest);
			hash = bigInt.toString(16);
			while (hash.length() < 32) {
				hash = "0" + hash;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash.substring(24);
	}

}
