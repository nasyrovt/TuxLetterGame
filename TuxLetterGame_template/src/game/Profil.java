/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.File;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
    public Element prof;
    public Profil(String nom,String dateNaissance){
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.avatar="image/tux.jpg";
        this.parties = new ArrayList<>();
        System.out.println(this.parties.size());
    }
    public Profil(String filename){//via parser DOM
        
           
         try{
            // récupération de la structure objet du document
         Document doc = this.fromXml(filename);
         Element prof=(Element)doc.getChildNodes().item(1);//or 0
         this.nom=prof.getChildNodes().item(1).getTextContent();
         System.out.println(this.nom);
         this.dateNaissance=prof.getChildNodes().item(5).getTextContent();
         System.out.println(this.dateNaissance);
         this.avatar=prof.getChildNodes().item(3).getTextContent();
         System.out.println(this.avatar);
         this.parties=new ArrayList<>();
         NodeList nodeList=doc.getElementsByTagName("partie");
         System.out.println("The number of parties \n");
         System.out.println(nodeList.getLength());
         System.out.print(nodeList.item(2).getTextContent());
         
         for(int i=0;i<nodeList.getLength();i++){
             
             Partie p=Profil.FromElementToObj((Element)nodeList.item(i));
             System.out.println(p.toString());
             parties.add(p);
         
            } 
         }
         
        catch(Exception profil_err){
            System.out.println("The profil doesnt exist\n");
        }
    }
  
    public ArrayList<Partie> getParties(){
        return this.parties;
    }
    public void ajouterPartie(Partie p){
        this.parties.add(p);
    }
    public int getDernierNiveau(){
        
        return this.parties.get(this.parties.size()-1).getNiveau();
    }
    public String toStringPart(){//pour savoir afficher les parties
        String s="";
        for(int i=0;i<this.parties.size();i++){
            s+=this.parties.get(i).toString();
        }
        return s;
    }
    public String toString(){
        return String.format("Profil name is %s,date of birth is %s",this.nom,this.dateNaissance);
    }
    
    public void sauvegarder(String file){//sauvegarde de nouvelle partie de l'utilisateur existante qui se trouve dans profil-temp.xml (alban)
        try{
        /*DocumentBuilderFactory oDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder oDocumentBuilder = oDocumentBuilderFactory.newDocumentBuilder();
        Document oDocument = null;
        oDocument = oDocumentBuilder.parse(new File(file));//"../src/xml/profil-temp.xml"*/
        Document oDocument=this.fromXml(file);
        Partie p=this.getParties().get(this.getParties().size()-1);
        Element el_p=p.getPartie(oDocument);
        Node node=oDocument.getElementsByTagName("parties").item(0);
        node.appendChild(el_p);
        to_XML(file,oDocument);
        }
        catch(Exception save){
            System.out.println("Error in save *partie*\n");
        }
    
    }
    
    public void sauvegarder_new(String file){//sauvegarde d'une nouvelle partie de nouveau utilisateur ce qui va se trouver dans profil_temp.xml
        try{
        DocumentBuilderFactory oDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder oDocumentBuilder = oDocumentBuilderFactory.newDocumentBuilder();
        Document oDocument=null;
        oDocument = oDocumentBuilder.newDocument();
        Element profil=oDocument.createElement("profil");
        profil.setAttribute("xmlns", "http://myGame/tux");
        profil.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        profil.setAttribute("xsi:schemaLocation", "http://myGame/tux ../xsd/profil.xsd");
        Element nom=oDocument.createElement("nom");
        nom.setTextContent(this.nom);
        Element avatar=oDocument.createElement("avatar");
        avatar.setTextContent(this.avatar);
        Element birth=oDocument.createElement("anniversaire");
        birth.setTextContent(this.dateNaissance);
        Element parties=oDocument.createElement("parties");
        for(int i=0;i<this.parties.size();i++){
            Partie p=this.getParties().get(i);
            Element el_p=p.getPartie(oDocument);
            parties.appendChild(el_p);
        }
        profil.appendChild(nom);
        profil.appendChild(avatar);
        profil.appendChild(birth);
        profil.appendChild(parties);
        to_XML(file,oDocument);
        System.out.println("NEW PROFIL SAVED INTO profil_xml\n");
        }
        catch(Exception save_new){
            
            System.out.println("Impossible to create new profil\n");
            
        }
    }
    public void to_XML(String filename,Document d){//transformation d'un doc a xmlfile
        try{
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(new File(filename));
        Source input = new DOMSource(d);

        transformer.transform(input, output);
        }
        catch(Exception toXML_err){
            System.out.println("Impossible to transform file to xml\n");
        }
    
    }
    public static Partie FromElementToObj(Element e) {//de l'object dom a la partie Object
      
      String mot=e.getElementsByTagName("mot").item(0).getTextContent();
      System.out.println(mot);
     
      String niv_str=e.getElementsByTagName("mot").item(0).getAttributes().getNamedItem("niveau").getNodeValue();;
      System.out.println(niv_str);
      Partie p=new Partie(mot,Integer.valueOf(niv_str));
      try{
        String temp=e.getElementsByTagName("temps").item(0).getTextContent();
        int t=Math.round(Float.valueOf(temp));
        p.setTemps(t);}
      catch(Exception fromEltoObj_err){
          System.out.println("impossible to get the temps from noeud\n");
      }

      boolean check_trouve=e.hasAttribute("trouve");
    
      String trouv="";
      if(check_trouve){
        trouv=e.getAttributes().getNamedItem("trouve").getNodeValue();
      }

      if(check_trouve){
          p.setTrouve(Integer.valueOf(trouv));
      }

      return p;
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
    
    private Document fromXml(String file){
        try {
            DocumentBuilderFactory oDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder oDocumentBuilder = oDocumentBuilderFactory.newDocumentBuilder();
            Document oDocument = null;
            oDocument = oDocumentBuilder.parse(new File(file));
            return oDocument;
            
        } catch (Exception from_xml_error) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, from_xml_error);
        }
        return null;
    }
    
    public Boolean charge(String nom){
        try{
            Document doc=this.fromXml("../src/xml/profil-temp.xml");
            Element prof=(Element)doc.getChildNodes().item(1).getChildNodes().item(1);
            String nom_in_doc=prof.getTextContent();
            
            if(nom_in_doc.equals(nom)){
                
                return true;
            }
        }
        catch (Exception e){
            System.out.println("This profil doesnt exit\n");
        }
        return false;//method in jeu
    
    }
}
