package question2;
import question1.*;

public class DebitMaximal implements Visiteur<Integer>{

    //retourne le debit max. d'un seul contributeur
    public Integer visite(Contributeur c){
        return c.solde();
    }
    //retourne le debit max. d'un groupe de contributeurs
    public Integer visite(GroupeDeContributeurs g){
        int res = 0;
        for (Cotisant c : g) {
            int d = c.accepter(this);
            if (res ==0 || d < res ){
                res=d; 
            }
        }
            return res;
        }    
    }
