/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import env3d.Env;
import java.util.*;
/**
 *
 * @author Нурбек
 */
public class Jeu  {
    private Env env;
    private Room room;
    private Profil profil;
    private List<Letter> letters;
    private Dico dict;
    public Jeu(){
    
        this.letters=new ArrayList<Letter>();
        // Crée un nouvel environnement
        env = new Env();
        
        // Instancie une Room
        room =new Room();

        // Règle la camera
        env.setCameraXYZ(50, 60, 175);
        env.setCameraPitch(-20);

        // Désactive les contrôles par défaut
        env.setDefaultControl(false);

        // Instancie un profil par défaut
        profil =new Profil();
        dict=new Dico("../");

        }
    
    public void execute() {

     // pour l'instant, nous nous contentons d'appeler la méthode joue comme cela
     // et nous créons une partie vide, juste pour que cela fonctionne
        joue(new Partie());

        // Détruit l'environnement et provoque la sortie du programme 
        env.exit();
    }
         
         
    public void joue(Partie partie) {
 
        // TEMPORAIRE : on règle la room de l'environnement. Ceci sera à enlever lorsque vous ajouterez les menus.
        env.setRoom(room);
        dict.lireDictDom("../src/xml", "dico.xml");
        // Instancie un Tux
        Tux tux=new Tux(this.room,this.env);
        env.addObject(tux);
        //Letter letter=new Letter('a',10.0,5.0);
        this.AddWordInEnv(5);
        /*
        String  s=dict.getMotFromListe(1);
        for(int i=0;i<3;i++){
            Letter letter=new Letter(s.charAt(i),20.0+i*5,5.0+i*5);
            this.letters.add(letter);
            //System.out.println(s.charAt(i));
            //System.out.println(letter.getLetter());
        }
        for(int i=0;i<this.letters.size();i++){
            env.addObject(this.letters.get(i));
        }
        */
        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        demarrerPartie(partie);
         
        // Boucle de jeu
        Boolean finished;
        finished = false;
        while (!finished) {
 
            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            if (env.getKey() == 1) {
                finished = true;
            }
            tux.deplace();
            // Contrôles des déplacements de Tux (gauche, droite, ...)
            // ... (sera complété plus tard) ...
 
            // Ici, on applique les regles
            appliqueRegles(partie);
 
            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }
 
        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);
 
    }   
    
    protected void demarrerPartie(Partie partie){
    
    }
    protected void appliqueRegles(Partie partie){
    
    }
    protected void terminePartie(Partie partie){
    
    }
    private void AddWordInEnv(int level){
        String  word=dict.getMotFromListe(level);
        for(int i=0;i<word.length();i++){
            Double posX,posZ=0.0;
            do {
            posX=Math.random()*100+i*5;
            posZ=Math.random()*100+i*5;
            }
            while((posX>=this.room.getWidth()-2)||( posZ>=this.room.getDepth()-2)||( posX<=2)||( posZ<=2));
            Letter letter=new Letter(word.charAt(i),posX,posZ);
            this.letters.add(letter);
            env.addObject(this.letters.get(i));
            
        }
        
    }
}
