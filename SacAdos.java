package types;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class SacAdos {

    private List<Objet> objetsPossibles;
    private List<Objet> meilleursObjets;
    private float poidsSolutionOpti;
    private float valeurOptimale;
    private float poidsMaximal;


    /**
     * Construit un sac a dos vide
     */
    public SacAdos() {
        this.meilleursObjets = new ArrayList<>();
        this.objetsPossibles = new ArrayList<>();
        this.valeurOptimale = 0;
        this.poidsSolutionOpti = 0;
    }


    public SacAdos(float poidsMaximal) {

        this.objetsPossibles = new ArrayList<>();
        this.meilleursObjets = new ArrayList<>();

        
        Scanner objt = new Scanner(System.in);
        System.out.print("le nombre des objets: ");
         int n= objt.nextInt();
        lireObjets(n);
        
        this.poidsMaximal=poidsMaximal;

    }

   public void lireObjets(int n) {
        float poids,Benifice;
        
        Objet Objet;
       Scanner objt = new Scanner(System.in);
       System.out.println("___Lecture des objet  ___");
        for(int i=1;i<=n;i++) {
                System.out.print("\"Poid \": ");
                poids = objt.nextInt();
                System.out.print("\"Benifice\": ");
                Benifice= objt.nextInt();
                
                Objet = new Objet(poids,Benifice,i);
               
                objetsPossibles.add(Objet);
            }
   
    }

  
    public void AfficheSolution() {
        calculPoidsSolution();

                
       
        System.out.println(" Meilleure solution realisable entier");
        for (Objet o : meilleursObjets) {
            System.out.println(o.toString());
           }
      System.out.println("les autres valeurs = null");
      
      System.out.println("le poids : " + poidsSolutionOpti+ " avec valeur optimale(Benifice) Z= : " + valeurOptimale);
       
    }
    
   private void SolutionInitiale(List<Objet> objetsPossibles,float poidsMaximal){
   
    float left_pi=0;
    float z=0;
    left_pi=this.poidsMaximal;
    System.out.println("____La solution initiale de SacAdos est : ____");
    for(Objet obj:objetsPossibles)
    { 
        if(obj.getPoids()<=left_pi)
        {   System.out.println("X"+ obj.getIndex()+"= 1");
             left_pi = left_pi - obj.getPoids();
             z += obj.getBenifice();
        }else if(left_pi!=0 && obj.getPoids()>left_pi){
            System.out.println("X"+ obj.getIndex()+"= "+ left_pi/obj.getPoids());
            z=z+obj.getBenifice()*(left_pi/obj.getPoids());
            left_pi=0;
        }  else
            System.out.println("X"+ obj.getIndex()+"=0 ");
            
    }
    System.out.println("la valeur optimale Z = "+z);
     
      }
/* Trie la liste d'objet*/
    public void triObjets() {

        Collections.sort(objetsPossibles);  
    }
  

    /* Calcule la valeur totale optimale du sac*/
    private void calculZOptimale() {
        float v = 0;
        for (Objet o : meilleursObjets) {
            v += o.getBenifice();
        } this.valeurOptimale = v;
    }
    /*Calcule le poids total de la solution optimale */
    private void calculPoidsSolution() {
        float p = 0;

        for (Objet o : meilleursObjets) {
            p += o.getPoids();
        }

        this.poidsSolutionOpti = p;
    }

   
    
    public void BranchAndBround() {
       triObjets();
       SolutionInitiale(objetsPossibles, poidsMaximal);
       new Arbre(objetsPossibles, poidsMaximal);

        valeurOptimale = Arbre.meilleureValeur;
        meilleursObjets = new ArrayList<>(Arbre.meilleursObjets);
    }
    
     public static void main(String[] args) {
        
        System.out.println("____________________Application Java__________________");
        System.out.println("________Branch & Bound pour le Problème de sac à dos________");
        System.out.println("____Implementer par Slimani Fatima ^_^ et Said Fatma ^_^____");
        System.out.println("");
        
         Scanner objt = new Scanner(System.in);
         System.out.print("le poids maximale de problème SacAdos: ");
         int PoidsMax = objt.nextInt();
         
        SacAdos execute = new SacAdos(PoidsMax);

        execute.BranchAndBround();
        System.out.println("\n");
        System.out.println("_____  Appliquer Branch & Bround @_@ ____");
        // Affiche la solution
       execute.AfficheSolution();

       System.out.println("\n _____   ^_^ ^_^  ______");
    }


}
