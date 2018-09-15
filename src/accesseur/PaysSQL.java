package accesseur;

public interface PaysSQL {

	String SQL_LISTER_PAYS = "SELECT * FROM pays";
	String SQL_AJOUTER_PAYS = "INSERT INTO pays(nom, continent, population, langue, capital) VALUES(?,?,?,?,?)";
	String SQL_MODIFIER_PAYS = "UPDATE pays SET nom = ?, continent = ?, population = ?, langue = ?, capital = ? WHERE id = ?";
	String SQL_RAPPORTER_PAYS = "SELECT * FROM pays WHERE id = ?";
}
