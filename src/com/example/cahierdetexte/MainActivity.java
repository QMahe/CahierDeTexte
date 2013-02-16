package com.example.cahierdetexte;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button lister, ajouter, quitter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.lister = (Button)findViewById(R.id.buttonLister);
		this.ajouter = (Button)findViewById(R.id.buttonAjouter);
		this.quitter = (Button)findViewById(R.id.buttonQuitter);
		
        this.lister.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), ListeTravauxActivity.class);
				startActivity(i);
			}        	
        });
        
        this.ajouter.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), AjouterActivity.class);
				startActivity(i);
			}        	
        });
		
        this.quitter.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				finish();
			}        	
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
