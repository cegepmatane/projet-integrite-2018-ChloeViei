package accesseur;

public interface PaysSQL {

	public static final String SQL_LISTER_PAYS = "SELECT * FROM pays";
	public static final String SQL_AJOUTER_PAYS = "INSERT INTO pays(nom, continent, population, langue, capital) VALUES(?,?,?,?,?)";
	public static final String SQL_MODIFIER_PAYS = "UPDATE pays SET nom = ?, continent = ?, population = ?, langue = ?, capital = ? WHERE id = ?";
	public static final String SQL_RAPPORTER_PAYS = "SELECT * FROM pays WHERE id = ?";
}
