package com.example.cahierdetexte;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AjouterActivity extends Activity {
	private Button add, retour;
	private EditText et_classe, et_matiere, et_travail, et_notes;
	private DatePicker dp_date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajouter);
		
		this.dp_date = (DatePicker)findViewById(R.id.datePicker);
		
		this.et_classe = (EditText)findViewById(R.id.editTextClasse);
		this.et_matiere = (EditText)findViewById(R.id.editTextMatiere);
		this.et_travail = (EditText)findViewById(R.id.editTextTravail);
		this.et_notes = (EditText)findViewById(R.id.editTextNotes);
		
		this.add = (Button)findViewById(R.id.buttonAdd);
		this.retour = (Button)findViewById(R.id.buttonRetour);
		
        this.add.setOnClickListener(new Button.OnClickListener(){
        	
			public void onClick(View arg0) {
				ajouterTravail();
			}        	
        });
		
        this.retour.setOnClickListener(new Button.OnClickListener(){
        	
			public void onClick(View arg0) {
				finish();
			}        	
        });
	}
	
	public void ajouterTravail() {
		try {
			TravailTable travailTable = new TravailTable(this);
			Travail travail = new Travail();
			travail.setJour(dp_date.getDayOfMonth());
			travail.setMois(dp_date.getMonth());
			travail.setAnnee(dp_date.getYear());
			travail.setClasse(et_classe.getText().toString());
			travail.setMatiere(et_matiere.getText().toString());
			travail.setTypeTravail(et_travail.getText().toString());
			travail.setNotes(et_notes.getText().toString());
			travailTable.Open();
			travailTable.Save(travail);
			travailTable.Close();
			Toast.makeText(getBaseContext(), "Le travail a bien été ajouté !", Toast.LENGTH_SHORT).show();
		}
		catch (Exception e) {
			Log.i("Erreur ajout : ",e.toString());
			Toast.makeText(getBaseContext(), "Une erreur est survenue." ,Toast.LENGTH_SHORT).show();
		}
		Calendar now = Calendar.getInstance();
		dp_date.updateDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
		et_classe.setText(null);
		et_matiere.setText(null);
		et_travail.setText(null);
		et_notes.setText(null);
	}
}
