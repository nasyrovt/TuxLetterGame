/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Нурбек
 */
public class JeuDevineLeMotOrdre extends Jeu {
    private int nbLettreRestantes;
    private Chronometre chrono;
    private int index_letter;
    JeuDevineLeMotOrdre() throws SAXException, ParserConfigurationException, IOException{
        super();  
    }
    private Boolean tuxTrouveLettre(){
        
        for (int i=0;i<super.letters.size();i++){
            if(super.colission(super.letters.get(i))){
                
                this.index_letter=i;
                 
                 return true;
               }
            
               
        }
        return false;
    }
    @Override
    protected void demarrerPartie(Partie partie) {
         //To change body of generated methods, choose Tools | Templates.
         
        
         this.chrono=new Chronometre(partie.getTemps());
         chrono.start();
         Long time=chrono.getTime();
         
         super.AddWordInEnv(super.level,partie.getMot());
         this.nbLettreRestantes=super.letters.size();
         System.out.println( Integer.toString(this.nbLettreRestantes));
         
         //partie.setEnd();
         System.out.println("Demmarer partie "+partie.toString());
         //System.out.println("End partie "+partie.toString());
    }

    @Override
    protected boolean appliqueRegles(Partie partie) {
       //To change body of generated methods, choose Tools | Templates.
        if(this.tuxTrouveLettre() && !chrono.remainsTime()){
            Letter tmp=super.letters.get(this.index_letter);
            if(!mot.isEmpty()){
               
                if(mot.get(mot.size()-1)!=tmp){
                    super.mot.add(tmp);
                  }
                
                
            }
            else{
                super.mot.add(tmp);
            }//env.removeObject(tmp);
        }
        if(chrono.remainsTime()){
            return true;
        }
        return false;
       
    }

    @Override
    protected void terminePartie(Partie partie) {
        if(!chrono.remainsTime()){
            chrono.stop();
        }
        System.out.println("Termine partie "+partie.toString());
        System.out.println("Times is up!!");
        String word="";
        String fake="";
        for(int i=0;i<super.letters.size();i++){
           word+=super.letters.get(i).getLetter();
        }
        for(int i=0;i<super.mot.size();i++){
            
            fake+=super.mot.get(i).getLetter();
        }
        partie.setTrouve(super.mot.size());
        System.out.println("Your word here is "+fake);
        System.out.println("Expected word here is "+word);
       
        //To change body of generated methods, choose Tools | Templates.
    }
}
