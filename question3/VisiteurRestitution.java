package question3;
import java.util.HashMap;
import question1.*;

public class VisiteurRestitution implements question1.Visiteur<Cotisant> { 
    private HashMap<Cotisant, Integer> state; 
    
    public VisiteurRestitution(HashMap<Cotisant, Integer> state){                             
        this.state=state; 
    } 
    
    public Cotisant visite(Contributeur c){ 
        int somme = this.state.get(c);                            
        c.affecterSolde(somme); 
        return c ; 
    } 

    public Cotisant visite(GroupeDeContributeurs g){ 
        for(Cotisant c : g.getChildren()){
            c.accepter(this); 
        } 
        return g; 
    } 
} 