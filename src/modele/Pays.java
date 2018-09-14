package modele;

public class Pays {

	protected int id;
    protected String nom;
    protected String continent;
    protected String population;
    protected String langue;
    protected String capital;

    public Pays(String nom) {
        super();
        this.nom = nom;
    }
    public Pays(String nom, String continent) {
        super();
        this.nom = nom;
        this.continent = continent;
    }
    public Pays(String nom, String continent, String population) {
        super();
        this.nom = nom;
        this.continent = continent;
        this.population = population;
    }
    public Pays(String nom, String continent, String population, String langue, String capital) {
        super();
        this.nom = nom;
        this.continent = continent;
        this.population = population;
        this.langue = langue;
        this.capital = capital;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return id;
    }

}
