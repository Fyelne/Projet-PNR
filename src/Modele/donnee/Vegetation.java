package Modele.donnee;

public class Vegetation {

    private int id;
    private NatureVege natureVege;
    private String vege;
    private int decritLieu;

    public Vegetation(int i, NatureVege nat, String veg, int desc){
        if(nat != null){
            this.id = i;
            this.natureVege = nat;
            this.vege = veg;
            this.decritLieu = desc;
        }
    }

    public int getId(){
        return this.id;
    }
    
    public void setId(int i){
        this.id = i;
    }

    public NatureVege getNatureVege(){
        return this.natureVege;
    } 

    public void setNatureVege(NatureVege nat){
        if(nat != null){
            this.natureVege = nat;
        }
    }

    public String getVege(){
        return this.vege;
    }

    public void setVege(String v){
        this.vege = v;
    }

    public int getDecritLieu(){
        return this.decritLieu;
    }

    public void setDecitLieu(int decrit){
        this.decritLieu = decrit;
    }
    
}
