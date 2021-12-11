/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author Нурбек
 */
public class LanceurDeJeu {
    public static void main(String args[]) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        // Declare un Jeu
        //Jeu jeu=new Jeu(); 
        //Instancie un nouveau jeu
        //?!!?;
        //Execute le jeu
        
        Jeu jeu=new JeuDevineLeMotOrdre();
        jeu.execute();
        
        //?!!?;
    }
}
