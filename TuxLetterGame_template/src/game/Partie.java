/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.Element;

/**
 *
 * @author Нурбек
 */
public class Partie {
    private String date;
    private String mot;
    private int niveau;
    private int trouve;
    private int temps;
    private int fin;
    public Partie(String mot,int niveau){
        this.date=new SimpleDateFormat("dd-MM-yyyy").format(new Date());;
        this.mot=mot;
        this.niveau=niveau;
    }

    public Partie(Element el_partie){
    
    
    }
    /*public Element getPartie(Document doc){
    
    
    }*/
  public void setTrouve(int nb_letters){
        this.trouve=(this.mot.length()-nb_letters)/this.mot.length();
    }
  public void setTemps(int temps){
      this.temps=temps;
  }
  public int getNiveau(){
      
      return this.niveau;
  }
  @Override
  public String toString(){
      return  String.format("Date is %s,word is %s,level is %s ,end is %d", this.date,this.mot,Integer.toString(this.niveau),this.fin);
    }
  public String getMot(){
    return this.mot;
  }
  public void setEnd(int temps){
      this.fin=temps;
  } 
}