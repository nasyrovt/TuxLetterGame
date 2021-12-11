/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import env3d.Env;
import java.util.*;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Нурбек
 */
public abstract class Jeu  {
    enum MENU_VAL {
        MENU_SORTIE, MENU_CONTINUE, MENU_JOUE
    }
    protected final Env env;
    private Room mainRoom;
    private Profil profil;
    protected List<Letter> letters;
    protected List<Letter> mot;
    protected Dico dict;
    protected EnvTextMap menuText;   
    private final Room menuRoom;
    private Tux tux;
    public  String the_mot;
    private boolean check_new_user;
    protected int level;
    public Jeu(){
        
        this.level=1;
        this.letters=new ArrayList<Letter>();
        // Crée un nouvel environnement
        env = new Env();
        this.mot=new ArrayList<Letter>();       // Instancie une Room
        mainRoom =new Room();
        menuRoom = new Room();
        menuRoom.setTextureEast("textures/red.png");
        menuRoom.setTextureWest("textures/red.png");
        menuRoom.setTextureNorth("textures/red.png");
        menuRoom.setTextureBottom("textures/red.png");
       
        // Règle la camera
        env.setCameraXYZ(50, 60, 175);
        env.setCameraPitch(-20);
        
        /*
        Pour initialliser le room via DOM.
        menuRoom.setRoomViaDoc("../src/xml/plateau.xml");
        
        */
        // Désactive les contrôles par défaut
        env.setDefaultControl(false);

        // Instancie un prprofil par défaut
       
        
       
        dict=new Dico("../src/xml/dico.xml");//src/xml/dico.xml
        dict.lireDictDom("../src/xml", "dico.xml");
        menuText = new EnvTextMap(env);
        
        // Textes affichés à l'écran
        menuText.addText("Voulez vous ?", "Question", 200, 300);
        menuText.addText("1. Commencer une nouvelle partie ?", "Jeu1", 250, 280);
        menuText.addText("2. Charger une partie existante ?", "Jeu2", 250, 260);
        menuText.addText("3. Sortir de ce jeu ?", "Jeu3", 250, 240);
        menuText.addText("4. Quitter le jeu ?", "Jeu4", 250, 220);
        menuText.addText("Choisissez un nom de joueur : ", "NomJoueur", 200, 300);
        menuText.addText("Donnez votre date de naissance dd/mm/aaaa : ", "Date", 200, 200);
        menuText.addText("1. Charger un profil de joueur existant ?", "Principal1", 250, 280);
        menuText.addText("2. Créer un nouveau joueur ?", "Principal2", 250, 260);
        menuText.addText("3. Sortir du jeu ?", "Principal3", 250, 240);
        menuText.addText("5. Rechoisir niveau ?", "Jeu5", 250, 200);
        menuText.addText("Give me your level :", "LVLUP", 250, 220);
        
        
        tux=new Tux(this.mainRoom,this.env);
       
       }
    
    
    public void execute() {
        MENU_VAL mainLoop;
        
        mainLoop = MENU_VAL.MENU_SORTIE;
        do {
            mainLoop = menuPrincipal();
        } while (mainLoop != MENU_VAL.MENU_SORTIE);
        
     // pour l'instant, nous nous contentons d'appeler la méthode joue comme cela
     // et nous créons une partie vide, juste pour que cela fonctionne
        
      // Détruit l'environnement et provoque la sortie du programme 
        mainLoop=end();
        if(mainLoop!=MENU_VAL.MENU_SORTIE){
            execute();
        }
        else{
            env.exit();
        }
    }
     
     private String getDateJoueur(){
        String date = "";
        menuText.getText("Date").display();
        date = menuText.getText("Date").lire(true);
        menuText.getText("Date").clean();
        return date;
     
     }
     private String getNomJoueur() {
        String nomJoueur = "";
        menuText.getText("NomJoueur").display();
        nomJoueur = menuText.getText("NomJoueur").lire(true);
        menuText.getText("NomJoueur").clean();
        return nomJoueur;
    }    
    public void joue(Partie partie) {
 
        // TEMPORAIRE : on règle la room de l'environnement. Ceci sera à enlever lorsque vous ajouterez les menus.
        env.setRoom(mainRoom);
        //dict.lireDictDom("../src/xml", "dico.xml");
        // Instancie un Tux
        
        env.addObject(this.tux);
        //Letter letter=new Letter('a',10.0,5.0);
       
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
            if(appliqueRegles(partie)){
                env.advanceOneFrame();
                break;
            }
 
            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }
 
        // Ici on peut calculer des valeurs lorsque la partie est terminée
        
        terminePartie(partie);
        
    }   
    
    protected double distance (Letter letter){
        double x=Math.pow(letter.getX()-this.tux.getX(),2);
        //double y=Math.pow(letter.getY()- this.tux.getY(),2);
        double z=Math.pow(letter.getZ()- this.tux.getZ(),2);
        double radiusSum = (tux.getScale()/2.0) + (letter.getScale()/2.0);
        return (Math.sqrt(x+z)-radiusSum);
    }
    protected boolean distance_for_all (Double x,Double z,ArrayList<Double> Xlist,ArrayList<Double> Zlist){
        double sum=0;
        for(int i=0;i<Xlist.size();i++){
            double new_x=Math.pow(x-Xlist.get(i),2);
            double new_z=Math.pow(x-Zlist.get(i),2);
            sum=Math.sqrt(x+z);
            if(sum<4.0){
                return false;
            }
        }
        return true;
        
        
    }
    protected Boolean colission(Letter letter){
       
        if(distance(letter)<2.0){
           
            if (env.getKeyDown(Keyboard.KEY_E)){
                env.removeObject(letter);
                return true;
            }
            
        
        }
        return false;
    }
    
  
    protected void AddWordInEnv(int level,String word){
        boolean check=true;
        ArrayList<Double> x=new ArrayList<Double>();
        ArrayList<Double> z=new ArrayList<Double>();
        for(int i=0;i<word.length();i++){
            Double posX,posZ=0.0;
            
               
            do {
                
            posX=Math.random()*100+i*5;
            posZ=Math.random()*100+i*5;
               if(!x.isEmpty() && !z.isEmpty()){
                  check=false;
                  if(this.distance_for_all(posX,posZ,x,z))
                      check=true;
                }
            }
            while(((posX>=this.mainRoom.getWidth()-2)||( posZ>=this.mainRoom.getDepth()-2)||( posX<=2)||( posZ<=2)) && check);
            Letter letter=new Letter(word.charAt(i),posX,posZ);
            x.add(posX);
            z.add(posZ);
            this.letters.add(letter);
            
            env.addObject(this.letters.get(i));
            
        }
    
    }
    private MENU_VAL menuJeu() {

        MENU_VAL playTheGame;
        playTheGame = MENU_VAL.MENU_JOUE;
        Partie partie;
        do {
            // restaure la room du menu
            env.setRoom(menuRoom);
            // affiche menu
            menuText.getText("Question").display();
            menuText.getText("Jeu1").display();
            menuText.getText("Jeu2").display();
            menuText.getText("Jeu3").display();
            menuText.getText("Jeu4").display();
            menuText.getText("Jeu5").display();
            
            // vérifie qu'une touche 1, 2, 3 ou 4 est pressée
            int touche = 0;
            while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3 || touche == Keyboard.KEY_4 || touche == Keyboard.KEY_5)) {
                touche = env.getKey();
                env.advanceOneFrame();
            }

            // nettoie l'environnement du texte
            menuText.getText("Question").clean();
            menuText.getText("Jeu1").clean();
            menuText.getText("Jeu2").clean();
            menuText.getText("Jeu3").clean();
            menuText.getText("Jeu4").clean();
            menuText.getText("Jeu5").clean();

            // restaure la room du jeu
            

            // et décide quoi faire en fonction de la touche pressée
            switch (touche) {
                // -----------------------------------------
                // Touche 1 : Commencer une nouvelle partie
                // -----------------------------------------                
                case Keyboard.KEY_1: // choisi un niveau et charge un mot depuis le dico
                    // .......... dico.******
                    // crée un nouvelle partie
                    //System.out.println("STARGINTG");
                    //dict.lireDictDom("../src/xml", "dico.xml");
                    if(the_mot==null){
                     
                        this.check_new_user=true;
                        the_mot=dict.getMotFromListe(level);//dans le cas si l'utilisateur deja existe
                    }
                    playTheGame=clue();
                    Partie p=new Partie(the_mot,level);
                    


                    System.out.println(p.getMot());
                    
                    this.joue(p);
                    this.profil.ajouterPartie(p);
                    if(this.check_new_user){
                        this.profil.sauvegarder("../src/xml/profil-temp.xml");
                    }
                    else{
                        System.out.println("HERE WE GO");
                        this.profil.sauvegarder_new("../src/xml/profil_temp.xml");
                    }
                    // enregistre la partie dans le profil --> enregistre le profil
                    // .......... profil.******
                    playTheGame = MENU_VAL.MENU_JOUE;
                    
                    break;

                // -----------------------------------------
                // Touche 2 : Charger une partie existante
                // -----------------------------------------                
                case Keyboard.KEY_2: // charge une partie existante
                    playTheGame=clue();
                    if(!this.check_new_user){
                        System.out.println("Vous avez pas les nouvelles parties\n");
                        playTheGame = MENU_VAL.MENU_CONTINUE;
                        break;
                    }
                    // Recupère le mot de la partie existante
                    // ..........
                    // joue
                    //******************************************************DERNIERE PARTIE***********************
                    Partie part=this.profil.getParties().get(this.profil.getParties().size()-1);
                    joue(part);
                    // enregistre la partie dans le profil --> enregistre le profil
                    // .......... profil.******
                    playTheGame = MENU_VAL.MENU_JOUE;
                    break;

                // -----------------------------------------
                // Touche 3 : Sortie de ce jeu
                // -----------------------------------------                
                case Keyboard.KEY_3:
                    playTheGame = MENU_VAL.MENU_SORTIE;
                    break;

                // -----------------------------------------
                // Touche 4 : Quitter le jeu
                // -----------------------------------------                
                case Keyboard.KEY_4:
                    playTheGame = MENU_VAL.MENU_CONTINUE;
                    break;
                case Keyboard.KEY_5:
                    
                    String lvl = "";
                    menuText.getText("LVLUP").display();
                    lvl = menuText.getText("LVLUP").lire(true);

                    this.level= Integer.valueOf(lvl);
                    if(this.level<1 || this.level>5){
                        this.level=1;
                    }
                    menuText.getText("LVLUP").clean();
                    //rechoisir niveau;
                    
                   playTheGame = MENU_VAL.MENU_JOUE;
                break;
            }
        } while (playTheGame == MENU_VAL.MENU_JOUE);
        return playTheGame;
    }

    private MENU_VAL menuPrincipal() {

        MENU_VAL choix = MENU_VAL.MENU_CONTINUE;
        String nomJoueur;

        // restaure la room du menu
        env.setRoom(menuRoom);

        menuText.getText("Question").display();
        menuText.getText("Principal1").display();
        menuText.getText("Principal2").display();
        menuText.getText("Principal3").display();
            
        // vérifie qu'une touche 1, 2 ou 3 est pressée
        int touche = 0;
        while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3 || touche== Keyboard.KEY_4)) {
            touche = env.getKey();
            env.advanceOneFrame();
        }

        menuText.getText("Question").clean();
        menuText.getText("Principal1").clean();
        menuText.getText("Principal2").clean();
        menuText.getText("Principal3").clean();
        
        // et décide quoi faire en fonction de la touche pressée
        switch (touche) {
            // -------------------------------------
            // Touche 1 : Charger un profil existant
            // -------------------------------------
            case Keyboard.KEY_1:
                // demande le nom du joueur existant
                this.profil=new Profil("../src/xml/profil-temp.xml");
                nomJoueur = getNomJoueur();
                // charge le profil de ce joueur si possible
                if (profil.charge(nomJoueur)){
                    choix = menuJeu();
                } else {
                    choix = MENU_VAL.MENU_CONTINUE;//CONTINUE;
                   
                }
                break;

            // -------------------------------------
            // Touche 2 : Créer un nouveau joueur
            // -------------------------------------
            case Keyboard.KEY_2:
                // demande le nom du nouveau joueur
                nomJoueur = getNomJoueur();
                String date=getDateJoueur();
                // crée un profil avec le nom d'un nouveau joueur
                profil = new Profil(nomJoueur,date);
                System.out.println(profil.toString());
                
                choix = menuJeu();
                choix=clue();
                break;

            // -------------------------------------
            // Touche 3 : Sortir du jeu
            // -------------------------------------
            case Keyboard.KEY_3:
                env.exit();
                
            
        }
        return choix;
    }
    private MENU_VAL clue(){
        MENU_VAL choix = MENU_VAL.MENU_CONTINUE;
        this.the_mot=dict.getMotFromListe(this.level);
        String mot=this.the_mot;
        menuText.addText(String.format("Votre mot est %s\n\n\n LEVEL : %d\n", mot.toUpperCase(),1),"Mot",200, 300);
        menuText.getText("Mot").display();
        
        
        
        env.advanceOneFrame();
        
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        menuText.getText("Mot").clean();
        //menuText.getText("Compt").clean();
        choix = MENU_VAL.MENU_JOUE;
        
        env.setRoom(mainRoom);
        return choix;
    }
    private MENU_VAL end(){
        MENU_VAL choix=MENU_VAL.MENU_SORTIE;
        String word="";
        String fake="";
        for(int i=0;i<this.letters.size();i++){
           word+=this.letters.get(i).getLetter();
        }
        for(int i=0;i<this.mot.size();i++){
            
            fake+=this.mot.get(i).getLetter();
        }
        menuText.addText(String.format("Your word is %s\nExpected word is %s\nPress Space to restart game or press ESC key to exit", fake,word), "End", 170, 300);
        menuText.getText("End").display();
        //On enleve tous les lettres
        this.letters.clear();
        this.mot.clear();
        int touch=0; 
        do{
            touch = env.getKey();
            env.advanceOneFrame();
            if(touch==Keyboard.KEY_ESCAPE){
            menuText.addText("Au revoir", "Bye", 250, 220);
            menuText.getText("Bye").display();
            env.advanceOneFrame();
            try{
                Thread.sleep(2000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
                 return MENU_VAL.MENU_SORTIE;
            }
        //ystem.out.println("HI");
        }
        
        
        while (touch != Keyboard.KEY_SPACE);
        choix=menuJeu();
        menuText.getText("End").clean();
        menuText.getText("Bye").clean();
        return choix;
       
    }
    protected abstract void demarrerPartie(Partie partie);
    protected abstract boolean appliqueRegles(Partie partie);
    protected abstract void terminePartie(Partie partie);
}
