/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author Нурбек
 */
public class Room {
    private int depth;
    private int height;
    private int width;
    private String textureBottom;
    private String textureNorth;
    private String textureEast;
    private String textureWest;
    private String textureSouth;
    private String textureTop;
    public Room(){
        this.textureBottom="textures/floor/grass1.png";
        //this.textureTop="../../textures/skybox/sunny/top.png";
        this.textureNorth="textures/marble.png";
        this.textureEast="textures/mud.png";
        this.textureWest="textures/granite.png";
        this.depth=100;
        this.width=100;
        this.height=60;
    }
    public int getDepth(){
      return this.depth;  
    }
    public int getWidth(){
      return this.width;  
    }
    public int getHeight(){
      return this.height;  
    }
    public String getTextureBottom(){
        return this.textureBottom;
    }
    public String getTextureNorth(){
        return this.textureNorth;
    }
    public String getTextureEast(){
        return this.textureEast;
    }
    public String getTextureWest(){
        return this.textureWest;
    }
    public String getTextureTop(){
        return this.textureTop;
    }
    public String getTextureSouth(){
        return this.textureSouth;
    }
    public void setDepth(int depth){
        this.depth=depth;
    }
    public void setWidth(int width){
        this.width=width;
    }
    public void setHeight(int height){
        this.height=height;
    }
    public void setTextureTop(String top){
        this.textureTop=top;
    }
    public void setTextureSouth(String south){
        this.textureSouth=south;
    }
    public void setTextureWest(String west){
        this.textureWest=west;
    }
    public void setTextureNorth(String north){
        this.textureNorth=north;
    }
    public void setTextureEast(String east){
        this.textureEast=east;
    }
    public void setTextureBottom(String bottom){
        this.textureBottom=bottom;
    }
}
