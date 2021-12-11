/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.w3c.dom.Document;
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
        this.date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.mot=mot;
        this.niveau=niveau;
        switch(niveau){
            case 1:
                setTemps(8);
                break;
            case 2:
                setTemps(10);
                break;
            case 3:
                setTemps(12);
                break;
            case 4:
                setTemps(15);
                break;
            case 5:
                setTemps(20);
                break;
            default:
                setTemps(20);
        }
    }

    public Partie(Element el_partie){
    
    date=el_partie.getAttributes().item(0).getTextContent();
    mot=el_partie.getElementsByTagName("mot").item(0).getTextContent();
    niveau=Integer.valueOf(el_partie.getElementsByTagName("mot").item(0).getAttributes().item(0).getTextContent());
    }
    
  public Element getPartie(Document doc){
     try{
        
        final Element the_el=(Element) doc.createElement("partie");
        final Element word=(Element) doc.createElement("mot");
        final Element time=(Element) doc.createElement("temps");
        the_el.setAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        word.setAttribute("niveau", Integer.toString(this.niveau));
        word.setTextContent(this.mot);
        the_el.setAttribute("trouve",Integer.toString(this.trouve));
        time.setTextContent(Integer.toString(this.temps));
        the_el.appendChild(time);
        the_el.appendChild(word);
        return the_el;
    }
    catch (Exception e){
        
        System.out.println("Cant create an emlement as partie\n");
        
    }
    return null;
  }
  public void setTrouve(int nb_letters){
        Integer x=this.mot.length()-(this.mot.length()-nb_letters);
        Double y=x.doubleValue();
        y=(y/this.mot.length())*100.0;
        this.trouve=y.intValue();

    }
  public void setTemps(int temps){
      this.temps=temps;
  }
  public int getNiveau(){
      
      return this.niveau;
  }
  @Override
  public String toString(){
      return  String.format("Date is %s,word is %s,level is %d \n", this.date,this.mot,this.niveau,this.fin);
    }
  public String getMot(){
    return this.mot;
  }
 
  public int getTemps(){
      return this.temps;
  
  }
  
}