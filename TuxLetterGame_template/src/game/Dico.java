/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import java.util.*;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 *
 * @author Нурбек
 */
public class Dico {
    private ArrayList<String> listeLevel1;
    private ArrayList<String> listeLevel2; 
    private ArrayList<String> listeLevel3;
    private ArrayList<String>listeLevel4;
    private ArrayList<String> listeLevel5;
    private String cheminFichierDico;
    public Dico(String cheminFichierDico){
        this.cheminFichierDico=cheminFichierDico;
        listeLevel1=new ArrayList<>();
        listeLevel2=new ArrayList<>();
        listeLevel3=new ArrayList<>();
        listeLevel4=new ArrayList<>();
        listeLevel5=new ArrayList<>();
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
        catch(Exception e){
            
        }
        
    }
}
