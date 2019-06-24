package question1;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant>{
  private List<Cotisant> liste;
  
  public GroupeDeContributeurs(String nomDuGroupe){
    super(nomDuGroupe);
            this.liste = new ArrayList<Cotisant>();
  }
  
  public void ajouter(Cotisant cotisant){
        if (cotisant == null) throw new IllegalArgumentException();
        liste.add(cotisant);
        cotisant.setParent(this);
  }
  
  public int nombreDeCotisants(){
    int nombre = 0;
    Iterator<Cotisant> it = liste.iterator();
        while(it.hasNext()){                             
            Cotisant c = it.next(); 
            if(c instanceof Contributeur){                             
                nombre +=1; }
            else{                             
                nombre += c.nombreDeCotisants(); 
            }
        }
                return nombre;
  }
   
  public String toString(){
    StringBuffer str = new StringBuffer();
    str.append("<GroupeDeContributeurs>\n");
        for (Cotisant cotisant : liste) {
            str.append(cotisant.toString() + "\n");
        }
        str.append("</GroupeDeContributeurs>");
        return str.toString();
    }

  
  public List<Cotisant> getChildren(){
        return new ArrayList<>(liste);
    }
  
  public void debit(int somme) throws SoldeDebiteurException{
    if(somme <  0){ 
            throw new RuntimeException("nombre négatif !!!"); 
        } 
        else{
            for(Cotisant c: this.liste){
                try{                             
                    c.debit(somme); 
                }catch( SoldeDebiteurException e){ 
                    throw new SoldeDebiteurException(); 
                } 
            } 
        } 
  }
  
  public void credit(int somme){
    if(somme <  0){ 
            throw new RuntimeException("nombre négatif !!!"); 
        } 
        else{ 
            for(Cotisant c: this.liste){                             
                c.credit(somme); 
            } 
        } 
  }
  
  public int solde(){
    int solde = 0; 
        for(Cotisant c: this.liste){                            
            solde += c.solde(); 
        } 
    return solde;
  }
  
  // mÃ©thodes fournies
  
 public Iterator<Cotisant> iterator(){
    return new GroupeIterator(liste.iterator());
  }
  
  private class GroupeIterator implements Iterator<Cotisant>{
    private Stack<Iterator<Cotisant>> stk;
    
    public GroupeIterator(Iterator<Cotisant> iterator){
      this.stk = new Stack<Iterator<Cotisant>>();
      this.stk.push(iterator);
    }
    
    public boolean hasNext(){
      if(stk.empty()){
        return false;
      }else{
         Iterator<Cotisant> iterator = stk.peek();
         if( !iterator.hasNext()){
           stk.pop();
           return hasNext();
         }else{
           return true;
         }
      }
    }
    
    public Cotisant next(){
      if(hasNext()){
        Iterator<Cotisant> iterator = stk.peek();
        Cotisant cpt = iterator.next();
        if(cpt instanceof GroupeDeContributeurs){
          GroupeDeContributeurs gr = (GroupeDeContributeurs)cpt;
          stk.push(gr.liste.iterator());
        }
        return cpt;
      }else{
        throw new NoSuchElementException();
      }
    }
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }
  

  public <T> T accepter(Visiteur<T> visiteur){
    return visiteur.visite(this);
  }
  

}
