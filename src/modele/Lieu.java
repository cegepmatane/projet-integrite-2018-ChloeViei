package modele;

import java.util.Calendar;

public class Lieu {
	
	protected int id;
	protected String nom;
	protected String type;
	protected String detail;
	
	public Lieu() {
		
	}
	
	public Lieu(String nom, String type) {
		super();
		this.nom = nom;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDetail() {
		return detail;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}

}
