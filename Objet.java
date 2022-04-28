package types;


public class Objet implements Comparable<Objet> {

    private float poids;
    private float Benifice;
    private int index;
 
    Objet(float poids, float Benifice,int index) {
        this.poids = poids;
        this.Benifice = Benifice;
        this.index = index;        
       
    }

    void setPoids(float poids) {
        this.poids = poids;
    }
  int getIndex(){
    return index;
  }
    float getPoids() {
        return poids;
    }


    float getBenifice() {
        return Benifice;
    }

     float getRapport() {
        return Benifice / poids;
    }

    public int compareTo(Objet o) {
        float resultatComparaison = getRapport() - o.getRapport();

        if (resultatComparaison > 0) {
            return -1;
        } else if (resultatComparaison < 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        
        return " X" + this.index+" = 1" ;
    }
}
