package types;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Arbre {

    static float meilleureValeur;
    static List<Objet> meilleursObjets;
    private static float PoidsMaximun;

    private List<Objet> objsRestants;
    private List<Objet> objs;


    Arbre(List<Objet> objsPossibles, float PoidsMax) {

        this.objsRestants = new ArrayList<>(objsPossibles);
        this.objs = new ArrayList<>();
        PoidsMaximun = PoidsMax;

        Separation();
    }


    /**
     * Effectue des appels recursif au constructeur pour creer l'arbre et ses branches
     */
    private void Separation() {

        Arbre filsGauche, filsDroit;

        if (!this.objsRestants.isEmpty() && peuxAjouterProchainObjet() && calculerBorneSuperieure() > meilleureValeur) {
            filsGauche = new Arbre(this.objsRestants, this.objs, true);
            filsDroit = new Arbre(this.objsRestants, this.objs, false);

        } else if (!this.objsRestants.isEmpty() && !peuxAjouterProchainObjet() && calculerBorneSuperieure() > meilleureValeur) {
            filsGauche = new Arbre(this.objsRestants, this.objs, true);
            filsDroit = null;

        } else {
            filsGauche = null;
            filsDroit = null;
        }
    }


    private Arbre(List<Objet> objsRestants, List<Objet> objs, boolean arbreGauche) {

        // On lui donne les memes objets que l'arbre pere
        this.objsRestants = new ArrayList<>(objsRestants);
        this.objs = new ArrayList<>(objs);


        if (arbreGauche) {
            // On garde la meme liste que l'arbre pere pour le fils gauche
            this.objsRestants.remove(this.objsRestants.get(0));
        } else {


            // On ajoute l'objet d'après pour le fils droit
            this.objs.add(this.objsRestants.get(0));
            this.objsRestants.remove(this.objsRestants.get(0));
        }

        // Si la nouvelle branche est une meilleure solution, on mets a jour la solution optimale
        MisAjourMeilleureSolution();

        // On fait des appels recursif au consructeur pour crer les branches de l'arbre initial
       Separation();
    }
    
    private float calculerBorneSuperieure() {
        float b = 0;

        // Somme des objets dans la branche
        for (Objet o : objs) {
            b += o.getBenifice();
        }

        // Somme des objets que l'on peut encore mettre dans les branches fils
        for (Objet o : objsRestants) {
            b += o.getBenifice();
        }

        return b;
    }

    private void MisAjourMeilleureSolution() {
        float bestValeur = 0;

        if (this.objs != null) {
            for (Objet o : this.objs) {
                bestValeur += o.getBenifice();
            }
        }


        // Si la valeur de la branche actuelle est > la meilleure valeur, cette branche est
        /*la meilleure solution et on mets a jour les variables statiques*/
        if (bestValeur > meilleureValeur) {
            meilleureValeur = bestValeur;
            meilleursObjets = new ArrayList<>(objs);
        }
    }


    /* Verifie si l'on peut ajouter le prochain objet  */
    private boolean peuxAjouterProchainObjet() {
        float p = this.objsRestants.get(0).getPoids(); 
        if (!this.objs.isEmpty()) {
            for (Objet o : objs) {
                p += o.getPoids();
            }
        }
        return p <= PoidsMaximun;
    }
    

}
