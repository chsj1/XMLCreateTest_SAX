package com.example.xmlcreatetest_sax;

import java.io.File;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		File newxmlfile = new File(
				Environment.getExternalStorageDirectory()
						+ "/xmlparser_person_by_sax.xml");
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(newxmlfile);
		}catch(Exception e){
			e.printStackTrace();
		}
		XMLCreateBySAXUtils.saxToXml(fos);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
