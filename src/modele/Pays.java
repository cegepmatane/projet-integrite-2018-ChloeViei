package modele;

public class Pays {

    protected String nom;
    protected String continent;
    protected String population;
    protected String langue;

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
    public Pays(String nom, String continent, String population, String langue) {
        super();
        this.nom = nom;
        this.continent = continent;
        this.population = population;
        this.langue = langue;
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

}
