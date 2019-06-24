package question2;

import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question1.Visiteur;
import question1.Cotisant;

public class CompositeValide implements Visiteur<Boolean>{
    // Le solde de chaque contributeur doit être supérieur ou égal à 0 
    // et il n’existe pas de groupe n’ayant pas de contributeurs.

    // Return vrai si solde >=0
    public Boolean visite(Contributeur c){ 
        return c.solde()>= 0; 
    } 

    //public Boolean visite(Contributeur c){
    //  return false; // a compléter;
    //}

    public Boolean visite(GroupeDeContributeurs g){
        boolean res = false;
        if(g.nombreDeCotisants()<=0){ 
            return res ;
        }
        for(Cotisant c : g.getChildren()){ 
            if( !c.accepter(this)){ 
                return res; 
            } 
        } return true ; 
    }
}