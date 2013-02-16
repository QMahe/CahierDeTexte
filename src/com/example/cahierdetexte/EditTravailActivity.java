package com.example.cahierdetexte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class EditTravailActivity extends Activity {
	private Button update, delete;
	private EditText et_classe, et_matiere, et_travail, et_notes;
	private DatePicker dp_date;
	private String infos, classe, matiere, travail;
	private int jour, mois, annee, id;
	private static final String TAG_INFOS = "infos";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editer);
		
		this.dp_date = (DatePicker)findViewById(R.id.datePicker1);
		
		this.et_classe = (EditText)findViewById(R.id.editTextEditClasse);
		this.et_matiere = (EditText)findViewById(R.id.editTextEditMatiere);
		this.et_travail = (EditText)findViewById(R.id.editTextEditTravail);
		this.et_notes = (EditText)findViewById(R.id.editTextEditNotes);
		
		this.update = (Button)findViewById(R.id.buttonUpdate);
		this.delete = (Button)findViewById(R.id.buttonDelete);
		
		Intent i = getIntent();
		infos = i.getStringExtra(TAG_INFOS);
		
		Pattern p = Pattern.compile("^([0-9]{2})/([0-9]{1,2})/([0-9]{4}) - (.+) - (.+) - (.+)$");
		Matcher m = p.matcher(infos);
		boolean b = m.matches();
		
		if (b) {
			jour = Integer.valueOf(m.group(1));
			mois = Integer.valueOf(m.group(2))-1;
			annee = Integer.valueOf(m.group(3));
			classe = m.group(4);
			matiere = m.group(5);
			travail = m.group(6);
			TravailTable travailTable = new TravailTable(this);
			travailTable.Open();
			Travail t = travailTable.findIdNotes(jour, mois, annee, classe, matiere, travail);
			travailTable.Close();
			dp_date.updateDate(annee, mois, jour);		
			et_classe.setText(classe);
			et_matiere.setText(matiere);
			et_travail.setText(travail);
			et_notes.setText(t.getNotes());
			id = Integer.valueOf(t.getId()).intValue();
		}
		
        this.update.setOnClickListener(new Button.OnClickListener(){
        	
			public void onClick(View arg0) {
				EditerTravail(id);
			}        	
        });
        
        this.delete.setOnClickListener(new Button.OnClickListener(){
        	
			public void onClick(View arg0) {
				SupprimerTravail(id);
			}        	
        });
	}
	
	public void EditerTravail(int id) {
		try {
			TravailTable travailTable = new TravailTable(this);
			Travail travail = new Travail();
			travail.setId(id);
			travail.setJour(dp_date.getDayOfMonth());
			travail.setMois(dp_date.getMonth());
			travail.setAnnee(dp_date.getYear());
			travail.setClasse(et_classe.getText().toString());
			travail.setMatiere(et_matiere.getText().toString());
			travail.setTypeTravail(et_travail.getText().toString());
			travail.setNotes(et_notes.getText().toString());
			travailTable.Open();
			travailTable.Update(travail);
			travailTable.Close();
			Toast.makeText(getBaseContext(), "Le travail a bien été modifié !", Toast.LENGTH_SHORT).show();
		}
		catch (Exception e) {
			Log.i("Erreur modification : ",e.toString());
			Toast.makeText(getBaseContext(), "Une erreur est survenue lors de la mise à jour." ,Toast.LENGTH_SHORT).show();
		}
		Intent i = getIntent();
		setResult(100, i);
		finish();
	}
	
	public void SupprimerTravail(int id) {
		try {
			TravailTable travailTable = new TravailTable(this);
			travailTable.Open();
			travailTable.Delete(id);
			travailTable.Close();
			Toast.makeText(getBaseContext(), "Le travail a bien été supprimé !", Toast.LENGTH_SHORT).show();
		}
		catch (Exception e) {
			Log.i("Erreur suppression : ",e.toString());
			Toast.makeText(getBaseContext(), "Une erreur est survenue lors de la suppression.", Toast.LENGTH_SHORT).show();
		}
		Intent i = getIntent();
		setResult(100, i);
		finish();
	}
}
