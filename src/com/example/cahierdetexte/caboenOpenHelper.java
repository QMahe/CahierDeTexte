package com.example.cahierdetexte;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class caboenOpenHelper extends SQLiteOpenHelper {
	
	// Version de la base de données
	private static final int DATABASE_VERSION = 1;
	
	// Nom de la base de données
	private static final String DATABASE_NAME = "caboen.db";

	// Nom de la table de maintenance
	public static final String DATABASE_TABLE = "travail";
	
	// Description des colonnes
	public static final String COLUMN_ID_TRAVAIL = "id_travail";
	public static final int NUM_COLUMN_ID_TRAVAIL = 0;
	public static final String COLUMN_JOUR = "jour";
	public static final int NUM_COLUMN_JOUR = 1;
	public static final String COLUMN_MOIS = "mois";
	public static final int NUM_COLUMN_MOIS = 2;
	public static final String COLUMN_ANNEE = "année";
	public static final int NUM_COLUMN_ANNEE = 3;
	public static final String COLUMN_CLASSE = "classe";
	public static final int NUM_COLUMN_CLASSE = 4;
	public static final String COLUMN_MATIERE = "matière";
	public static final int NUM_COLUMN_MATIERE = 5;
	public static final String COLUMN_TYPE_TRAVAIL = "type_travail";
	public static final int NUM_COLUMN_TYPE_TRAVAIL = 6;
	public static final String COLUMN_NOTES = "notes";
	public static final int NUM_COLUMN_NOTES = 7;
	
	// Requête SQL de création de la base
	public static final String REQUETE_CREATION_BDD = "create table if not exists " + DATABASE_TABLE + " (" + COLUMN_ID_TRAVAIL 
			+ " integer primary key autoincrement, " + COLUMN_JOUR + " integer not null, " + COLUMN_MOIS + " integer not null, " 
			+ COLUMN_ANNEE + " integer not null, " + COLUMN_CLASSE + " text not null, " + COLUMN_MATIERE + " text not null, " 
			+ COLUMN_TYPE_TRAVAIL + " text not null, " + COLUMN_NOTES + " text);";
	
	public caboenOpenHelper(Context context, CursorFactory factory) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(REQUETE_CREATION_BDD);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > DATABASE_VERSION) {
			// Inscrit la mise à jour de version dans le journal
			Log.w("TaskDBAdapter", "Mise à jour de la version " + oldVersion + " vers la version " + newVersion + ", ce qui détruira toutes les anciennes données.");
			db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		// TODO Auto-generated method stub
		
	}
}
