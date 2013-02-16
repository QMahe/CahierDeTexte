package com.example.cahierdetexte;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListeTravauxActivity extends ListActivity {
	private ListView liste = null;
	private static final String TAG_INFOS = "infos";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste_travaux);
		
		this.liste = getListView();
		
		TravailTable travailTable = new TravailTable(this);
		travailTable.Open();
		ArrayList<Travail> travaux = (ArrayList<Travail>) travailTable.findAll();
		travailTable.Close();
		
		Log.i("Nombre de donnees", String.valueOf(travaux.size()));
		
		if (travaux.size() > 0) {
			String[] tabTravail = new String[travaux.size()];
			int id = 0;
			for (Travail t : travaux) {
				String date = t.getJour()+"/"+(t.getMois()+1)+"/"+t.getAnnee();
				tabTravail[id++] = date+" - "+t.getClasse()+" - "+t.getMatiere()+" - "+t.getTypeTravail();				
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, tabTravail);
			liste.setAdapter(adapter);
		}
		else {
			Toast.makeText(getBaseContext(), "Pas de travail prévu.", Toast.LENGTH_SHORT).show();
		}
		
		this.liste.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String infos = (String) parent.getItemAtPosition(position);				
				Intent in = new Intent(getApplicationContext(), EditTravailActivity.class);
				in.putExtra(TAG_INFOS, infos);
				startActivityForResult(in, 100);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 100) {
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}
	}
}
