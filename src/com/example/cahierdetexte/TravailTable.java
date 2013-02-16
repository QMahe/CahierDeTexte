package com.example.cahierdetexte;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class TravailTable extends Table {
	
	public TravailTable(Context context) {
		sqLiteOpenHelper = new caboenOpenHelper(context, null);
	}

	public void Delete(int id) {
		maBDD.delete(caboenOpenHelper.DATABASE_TABLE, caboenOpenHelper.COLUMN_ID_TRAVAIL + "=?", new String[] { String.valueOf(id) });
	}
	
	public List<Travail> findAll() {
        // Récupération de la liste des travaux
        Cursor cursor = maBDD.query(caboenOpenHelper.DATABASE_TABLE,
		new String[] { caboenOpenHelper.COLUMN_ID_TRAVAIL,
                       caboenOpenHelper.COLUMN_JOUR,
                       caboenOpenHelper.COLUMN_MOIS,
                       caboenOpenHelper.COLUMN_ANNEE,
                       caboenOpenHelper.COLUMN_CLASSE,
                       caboenOpenHelper.COLUMN_MATIERE,
                       caboenOpenHelper.COLUMN_TYPE_TRAVAIL,
                       caboenOpenHelper.COLUMN_NOTES }, null, null, null, null, null, null);

        return ConvertCursorToListObject(cursor);
    }
	
	public Travail findIdNotes(int jour, int mois, int annee, String classe, String matiere, String travail) {
		//"+caboenOpenHelper.COLUMN_ID_TRAVAIL+","+caboenOpenHelper.COLUMN_NOTES+"
		String query = "select * from "+caboenOpenHelper.DATABASE_TABLE+" where "+caboenOpenHelper.COLUMN_JOUR+" = "+jour+" AND "
																				 +caboenOpenHelper.COLUMN_MOIS+" = "+mois+" AND "
																				 +caboenOpenHelper.COLUMN_ANNEE+" = "+annee+" AND "
																				 +caboenOpenHelper.COLUMN_CLASSE+" = '"+classe+"' AND "
																				 +caboenOpenHelper.COLUMN_MATIERE+" = '"+matiere+"' AND "
																				 +caboenOpenHelper.COLUMN_TYPE_TRAVAIL+" = '"+travail+"';"; 
		Cursor cursor = maBDD.rawQuery(query, null);
		cursor.moveToFirst();
        return ConvertCursorToObject(cursor);
	}
	
	public List<Travail> ConvertCursorToListObject(Cursor c) {
		List<Travail> liste = new ArrayList<Travail>();		
        if (c.getCount() == 0) {
        	return liste;
        }
        
        // position sur le premier item
        c.moveToFirst();

        // Pour chaque item
        do {
        	Travail travail = ConvertCursorToObject(c);
        	liste.add(travail);
        }
        while (c.moveToNext());

        // Fermeture du curseur
        c.close();
        return liste;
    }
	
    public Travail ConvertCursorToObject(Cursor c) {
        Travail travail = new Travail();
        travail.setId(c.getInt(caboenOpenHelper.NUM_COLUMN_ID_TRAVAIL));
        travail.setJour(c.getInt(caboenOpenHelper.NUM_COLUMN_JOUR));
        travail.setMois(c.getInt(caboenOpenHelper.NUM_COLUMN_MOIS));
        travail.setAnnee(c.getInt(caboenOpenHelper.NUM_COLUMN_ANNEE));
        travail.setClasse(c.getString(caboenOpenHelper.NUM_COLUMN_CLASSE));
        travail.setMatiere(c.getString(caboenOpenHelper.NUM_COLUMN_MATIERE));
        travail.setTypeTravail(c.getString(caboenOpenHelper.NUM_COLUMN_TYPE_TRAVAIL));
        travail.setNotes(c.getString(caboenOpenHelper.NUM_COLUMN_NOTES));
        return travail;
    }
    
    public void Save(Travail entite) {
    	// Enregistrement d'un nouveau travail
    	ContentValues contentValues = new ContentValues();
        contentValues.put(caboenOpenHelper.COLUMN_JOUR, entite.getJour());
        contentValues.put(caboenOpenHelper.COLUMN_MOIS, entite.getMois());
        contentValues.put(caboenOpenHelper.COLUMN_ANNEE, entite.getAnnee());
        contentValues.put(caboenOpenHelper.COLUMN_CLASSE, entite.getClasse());
        contentValues.put(caboenOpenHelper.COLUMN_MATIERE, entite.getMatiere());
        contentValues.put(caboenOpenHelper.COLUMN_TYPE_TRAVAIL, entite.getTypeTravail());
        contentValues.put(caboenOpenHelper.COLUMN_NOTES, entite.getNotes());
        maBDD.insert(caboenOpenHelper.DATABASE_TABLE, null, contentValues);
    }
    
	public void Update(Travail entite) {
		// Mise à jour d'un travail existant
        ContentValues contentValues = new ContentValues();
        contentValues.put(caboenOpenHelper.COLUMN_JOUR, entite.getJour());
        contentValues.put(caboenOpenHelper.COLUMN_MOIS, entite.getMois());
        contentValues.put(caboenOpenHelper.COLUMN_ANNEE, entite.getAnnee());
        contentValues.put(caboenOpenHelper.COLUMN_CLASSE, entite.getClasse());
        contentValues.put(caboenOpenHelper.COLUMN_MATIERE, entite.getMatiere());
        contentValues.put(caboenOpenHelper.COLUMN_TYPE_TRAVAIL, entite.getTypeTravail());
        contentValues.put(caboenOpenHelper.COLUMN_NOTES, entite.getNotes());
        maBDD.update(caboenOpenHelper.DATABASE_TABLE, contentValues, caboenOpenHelper.COLUMN_ID_TRAVAIL + "=?", new String[] { String.valueOf(entite.getId()) });
    }
}
