/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author Нурбек
 */
public class TestDico {
    public static void main(String args[]) {
        // Declare un Jeu
       Dico dico=new Dico("asd");
       dico.AddWordDico(1,"hey");
       dico.AddWordDico(1, "you");
       dico.AddWordDico(1, "dude");
       System.out.println(dico.getMotFromListe(1));
       System.out.println(dico.getMotFromListe(1));
       System.out.println(dico.getMotFromListe(1));
       
        //?!!?;
    }
}
