/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import java.io.File;
import java.util.*;
import java.util.Random;
import org.xml.sax.Attributes;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import org.w3c.dom.DOMException;
/**
 *
 * @author Нурбек
 */
public class Dico extends DefaultHandler {
    StringBuffer buffer = new StringBuffer();
    int niveauSax;
    private boolean inDictionnaire, inMot;
    private ArrayList<String> listeLevel1=new ArrayList<>();
    private ArrayList<String> listeLevel2=new ArrayList<>();
    private ArrayList<String> listeLevel3=new ArrayList<>();
    private ArrayList<String> listeLevel4=new ArrayList<>();
    private ArrayList<String> listeLevel5=new ArrayList<>();
    private String cheminFichierDico;
    public Dico(String cheminFichierDico){
        super();
        this.cheminFichierDico=cheminFichierDico;
    }
    public String getMotFromListe(int level){
        switch (level_verif(level)){
            case 1:
                return this.getWordFromList(this.listeLevel1);
            case 2:
                return this.getWordFromList(this.listeLevel2);
            case 3:
                return this.getWordFromList(this.listeLevel3);
            case 4:
                return this.getWordFromList(this.listeLevel4);
            case 5:
                return this.getWordFromList(this.listeLevel5);
            default:
                return "";

        }

    }
    public void AddWordDico(int level,String mot){
        switch(this.level_verif(level)){
            case 1:
                this.listeLevel1.add(mot);
                break;
            case 2:
                this.listeLevel2.add(mot);
                break;
            case 3:
                this.listeLevel3.add(mot);
                break;
            case 4:
                this.listeLevel4.add(mot);
                break;
            case 5:
                this.listeLevel5.add(mot);
                break;
            default:
                System.out.println("Error in AddWordDico");

        }
    }
    public String getCheminFichierDico(){
        return this.cheminFichierDico;
    }
    private int level_verif(int level){
        if(level>=1 && level<=5)
            return level;
        return -1;
    }
    private String getWordFromList(ArrayList<String> list){
        if (list.isEmpty()){
            return "Word is null\n";
        }
        Random r=new Random();
        int rand=r.nextInt(list.size());
        return list.get(rand);
    }

    void lireDictionnaire() throws org.xml.sax.SAXException, ParserConfigurationException, IOException{
        SAXParserFactory fabrique = SAXParserFactory.newInstance();

        // création d'un parseur SAX
        SAXParser parseur = fabrique.newSAXParser();

        // lecture d'un fichier XML avec un DefaultHandler
        File fichier = new File(this.getCheminFichierDico());
        parseur.parse(fichier, this); 
        /*On utilise this, parce que la class Dico herite de Default Handler, 
        alors c'est elle qui s'occupe des fonctions de parse SAX*/
    }



    public void lireDictDom(String path,String filename){
        try {
            // analyse du document
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder p = dbFactory.newDocumentBuilder();
            // récupération de la structure objet du document
            Document doc = p.parse(path+"/"+filename);
            NodeList nodeList=doc.getElementsByTagName("mot");

            for(int i=0;i<nodeList.getLength();i++){
                switch(nodeList.item(i).getAttributes().item(0).getTextContent()){
                    case "1":
                        this.AddWordDico(1, nodeList.item(i).getTextContent());
                        break;
                    case "2":
                        this.AddWordDico(2, nodeList.item(i).getTextContent());
                        break;
                    case "3":
                        this.AddWordDico(3, nodeList.item(i).getTextContent());
                        break;
                    case "4":
                        this.AddWordDico(4, nodeList.item(i).getTextContent());
                        break;
                    case "5":
                        this.AddWordDico(5, nodeList.item(i).getTextContent());
                        break;
                    default:

                }
            }
        }
        catch(IOException | ParserConfigurationException | DOMException | SAXException e){
            System.out.println("Exception" + e);
        }

    }

    /**
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "dictionnaire":
                inDictionnaire = true;
                break;
            case "mot":
                try{
                    niveauSax = Integer.parseInt(attributes.getValue("niveau"));
                }catch(NumberFormatException e){
                    //erreur, le contenu de niveau n'est pas un entier
                    throw new SAXException(e);
                }   inMot = true;
                break;
            default:
                buffer = new StringBuffer();
                //erreur, on peut lever une exception
                throw new SAXException("Balise "+qName+" inconnue.");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "dictionnaire":
                inDictionnaire = false;
                break;
            case "mot":
                AddWordDico(niveauSax, buffer.toString());
                buffer = new StringBuffer();
                inMot = false;
                break;
            default:
                //erreur, on peut lever une exception
                throw new SAXException("Balise "+qName+" inconnue.");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String lecture = new String(ch,start,length);
        if(inMot && buffer != null)buffer.append(lecture);
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Début du parsing SAX de fichier dico.xml");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Fin du parsing SAX");
        System.out.println("Resultats du parsing SAX");
    }
}
