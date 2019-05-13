/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaire;

/**
 *
 * @author dell
 */
public class Position {
    private int i;
    private int j;
    public int getI(){
        return i;
    }
    public int getJ(){
        return j;
    }

    public Position(int i, int j,int n) {
        this.i = i;
        this.j = j;
    }
}
