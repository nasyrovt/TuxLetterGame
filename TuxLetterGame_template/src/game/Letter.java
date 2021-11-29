/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.advanced.EnvNode;

/**
 *
 * @author Нурбек
 */
public class Letter extends EnvNode {
    private char letter;
    public Letter(char letter,double x,double z){
        setScale(4.0);
        setY(getScale() * 1.1); 
        this.letter=letter;
        setX(x);
        setZ(z);
        setTexture(String.format("letter/%c.png",Character.toLowerCase(this.letter)));
        setModel("letter/cube.obj");
        
    }
    public void setLetter(char letter){
        this.letter=letter;
    }
    public char getLetter(){
        return this.letter;
    }
}
