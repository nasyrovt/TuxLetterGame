/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import env3d.advanced.EnvNode;
import env3d.Env;
import env3d.EnvBasic;
import org.lwjgl.input.Keyboard;
/**
 *
 * @author Нурбек
 */
public class Tux extends EnvNode{
    private Room room;
    private Env env;
    public Tux(Room room,Env env){
        this.room=room;
        this.env=env;
        setScale(4.0);
        setX( this.room.getWidth()/2 );// positionnement au milieu de la largeur de la room
        setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
        setZ( this.room.getDepth()/2 ); // positionnement au milieu de la profondeur de la room
        setTexture("models/tux/tux.png");
        setModel("models/tux/tux.obj");   
    }
    public void deplace(){
           // Haut
      if(this.testeRoomCollision(this.getX(), this.getZ())==true){
        if (env.getKeyDown(Keyboard.KEY_W) || env.getKeyDown(Keyboard.KEY_UP)) { // Fleche 'haut' ou Z
         // Haut
         this.setRotateY(180);
         this.setZ(this.getZ() - 1.0);

         }
      if (env.getKeyDown(Keyboard.KEY_A) || env.getKeyDown(Keyboard.KEY_LEFT)) { // Fleche 'gauche' ou Q
          this.setRotateY(270);
          this.setX(this.getX()-1.0);

          }
      if (env.getKeyDown(Keyboard.KEY_D) || env.getKeyDown(Keyboard.KEY_RIGHT)) { // Fleche 'gauche' ou Q
          this.setRotateY(-270);
          this.setX(this.getX()+1.0);

          }

      if (env.getKeyDown(Keyboard.KEY_S) || env.getKeyDown(Keyboard.KEY_DOWN)) { // Fleche 'gauche' ou Q
          this.setRotateY(360);
          this.setZ(this.getZ() + 1.0);
          }
      }
    }
   public Boolean testeRoomCollision(double x,double z){
       if((x>=this.room.getWidth()-1)||( z>=this.room.getDepth()-1) ||( x<=1.0) ||( z<=1.0)){
           setX( this.room.getWidth()/2 );// positionnement au milieu de la largeur de la room
           setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
           setZ( this.room.getDepth()/2 );
       }
       return true;
   }
}
