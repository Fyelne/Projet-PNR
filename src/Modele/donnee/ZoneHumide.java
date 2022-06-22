package Modele.donnee;

public class ZoneHumide {
    private int id;
    private boolean tempo;
    private double profondeur;
    private double surface;
    private TypeMare typeMare;
    private Pente pente;
    private Ouverture ouv;
    
    public ZoneHumide(int i, boolean temp, double pro, double sur, TypeMare mare, Pente p, Ouverture o){
        this.id = i;
        this.tempo = temp;
        this.profondeur = pro;
        this.surface = sur;
        this.typeMare = mare;
        this.pente = p;
        this.ouv = o;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int i){
        this.id = i;
    }

    public boolean getTempo(){
        return this.tempo;
    }

    public void setTemp(boolean t){
        this.tempo = t;
    }

    public double getProdondeur(){
        return this.profondeur;
    }

    public void setProfondeur(double prof){
        this.profondeur = prof;
    }

    public double getSurface(){
        return this.surface;
    }

    public void setSurface(double s){
        this.surface = s;
    }

    public TypeMare getTypeMaree(){
        return this.typeMare;
    }

    public void setTypeMarre(TypeMare maree){
        this.typeMare = maree;
    }

    public Pente getPente(){
        return this.pente;
    }

    public void setPente(Pente p){
        this.pente = p;
    }

    public Ouverture getOuverture(){
        return this.ouv;
    }

    public void setOuverture(Ouverture o){
        this.ouv = o;
    }


}
