package question3;
import java.util.HashMap;
import question1.*;
/**  
 * Visiteur qui implémente le clone visiteur du cours pour pouvoir ne pas sauvegarder le pointeur dans  
 * le Memento  
 */ 
public class VisiteurSauvegarde implements question1.Visiteur<HashMap<Cotisant, Integer>>{
    public HashMap<Cotisant, Integer> visite(Contributeur c){ 
        //Contributeur clone = new Contributeur(c.nom(),c.solde());         
        HashMap<Cotisant, Integer> toSave = new HashMap<Cotisant, Integer>();                             
        Integer solde = new Integer(c.solde());                             
        toSave.put(c, solde); return toSave; 
    } 

    public HashMap<Cotisant, Integer> visite(GroupeDeContributeurs g) {                            
        HashMap<Cotisant, Integer> toSave = new HashMap<Cotisant, Integer>(); 
        for(Cotisant c: g.getChildren()){                             
            HashMap<Cotisant, Integer> nouveaux = c.accepter(this);                             
            toSave.putAll(nouveaux); 
        } 
        return toSave; 
    } 
}