/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
/**
 *
 * @author Нурбек
 */
public class Profil {
    private String nom;
    private String dateNaissance;
    private String avatar;
    private ArrayList<Partie> parties;
    public Document _doc;
    public Profil(String nom,String dateNaissance){
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.avatar="image/tux.jpg";
        this.parties = new ArrayList<Partie>();
    }
    public Profil(String filename){
    
    }
   /* private Document toXML(String filename){
    
    
    }*/
    public void ajouterPartie(Partie p){
    
    }
    public int getDernier(){
        return 0;
    }
    public String toString(){
        return "";
    }
    public void sauvegarder(String filename){
    
    
    }
    private String xmlDateToProfileDate(String date){
        return "";
    }
    private String profileDateToXmlDate(String date){
        return "";
    }
    
    
    public Boolean charge(String nom){
        return false;
        
    }
}
