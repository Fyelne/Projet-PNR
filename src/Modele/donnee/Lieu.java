package Modele.donnee;

public class Lieu {
    private double xCoord;
    private double yCoord;

    /**
     * Constructeur de la classe Lieu
     * @param xCoord coordonnée x
     * @param yCoord coordonnée y
     */
    public Lieu(double xCoord, double yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     * Retourne la coordonnée x
     * @return coordonnée x
     */
    public double getxCoord() {
        return xCoord;
    }

    /**
     * Retourne la coordonnée y
     * @return coordonnée y
     */
    public double getyCoord() {
        return yCoord;
    }

    /**
     * Modifie la coordonnée x
     * @param xCoord coordonnée x
     */
    public void setXCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Modifie la coordonnée y
     * @param yCoord coordonnée y
     */
    public void setYCoord(double yCoord) {
        this.yCoord = yCoord;
    }
}
