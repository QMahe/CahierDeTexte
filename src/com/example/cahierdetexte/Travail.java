package com.example.cahierdetexte;

import java.util.HashMap;

public class Travail {
	
	// Définition des infos concernant un travail
	private Integer id_travail, jour, mois, annee;
	private String classe, matiere, type_travail, notes;
	
	public Travail() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id_travail;
	}
	
	public void setId(Integer id) {
		this.id_travail = id;
	}
	
	public Integer getJour() {
		return jour;
	}
	
	public void setJour(Integer jour) {
		this.jour = jour;
	}
	
	public Integer getMois() {
		return mois;
	}
	
	public void setMois(Integer mois) {
		this.mois = mois;
	}
	
	public Integer getAnnee() {
		return annee;
	}
	
	public void setAnnee(Integer annee) {
		this.annee = annee;
	}
	
	public String getClasse() {
		return classe;
	}
	
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	public String getMatiere() {
		return matiere;
	}
	
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	
	public String getTypeTravail() {
		return type_travail;
	}
	
	public void setTypeTravail(String type_travail) {
		this.type_travail = type_travail;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public HashMap<String, String> toArray() {
		HashMap<String, String> array = new HashMap<String, String>();
		array.put("id", String.valueOf(this.getId()));
		array.put("jour", String.valueOf(this.getJour()));
		array.put("mois", String.valueOf(this.getMois()));
		array.put("annee", String.valueOf(this.getAnnee()));
		array.put("classe", this.getClasse());
		array.put("matiere", this.getMatiere());
		array.put("type_travail", this.getTypeTravail());
		array.put("notes", this.getNotes());
		
		return array;		
	}
}
