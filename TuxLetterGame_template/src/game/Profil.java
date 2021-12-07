/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
package XMLutil;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
        try{
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder p = dbFactory.newDocumentBuilder();
            // récupération de la structure objet du document
         Document doc = p.parse(filename);
         Element prof=(Element)doc.getChildNodes().item(0);//or 0
         this.nom=prof.getChildNodes().item(1).getTextContent();
         this.dateNaissance=prof.getChildNodes().item(5).getTextContent();
         this.avatar=prof.getChildNodes().item(3).getTextContent();
        
        } 
        catch(Exception e){
        
        }
    }
   /* private Document toXML(String filename){
    
    
    }*/
   
    public void ajouterPartie(Partie p){
        this.parties.add(p);
    }
    public int getDernierNiveau(){
        
        return this.parties.get(this.parties.size()-1).getNiveau();
    }
    public String toString(){
        return "";
    }
    public void sauvegarder(String filename){
    
    
    }
    /// Takes a date in XML format (i.e. ????-??-??) and returns a date
    /// in profile format: dd/mm/yyyy
    public static String xmlDateToProfileDate(String xmlDate) {
        String date;
        // récupérer le jour
        date = xmlDate.substring(xmlDate.lastIndexOf("-") + 1, xmlDate.length());
        date += "/";
        // récupérer le mois
        date += xmlDate.substring(xmlDate.indexOf("-") + 1, xmlDate.lastIndexOf("-"));
        date += "/";
        // récupérer l'année
        date += xmlDate.substring(0, xmlDate.indexOf("-"));

        return date;
    }

    /// Takes a date in profile format: dd/mm/yyyy and returns a date
    /// in XML format (i.e. ????-??-??)
    public static String profileDateToXmlDate(String profileDate) {
        String date;
        // Récupérer l'année
        date = profileDate.substring(profileDate.lastIndexOf("/") + 1, profileDate.length());
        date += "-";
        // Récupérer  le mois
        date += profileDate.substring(profileDate.indexOf("/") + 1, profileDate.lastIndexOf("/"));
        date += "-";
        // Récupérer le jour
        date += profileDate.substring(0, profileDate.indexOf("/"));

        return date;
    }
    /*
    private void toXml(){
        try {
            Document.DocumentTransform.writeDoc(_doc, "../src/xml/profil_temp.xml");
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        */
    private Document fromXml(){
        try {
            DocumentBuilderFactory oDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder oDocumentBuilder = oDocumentBuilderFactory.newDocumentBuilder();
            Document oDocument = null;
            oDocument = oDocumentBuilder.parse(new File("../src/xml/profil_temp.xml"));
            return oDocument;
            
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Boolean charge(String nom){
        return false;//method in jeu
    
    }
}
