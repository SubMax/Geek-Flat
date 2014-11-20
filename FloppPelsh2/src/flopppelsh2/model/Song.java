/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flopppelsh2.model;

/**
 *
 * @author touch_p
 */
public class Song {
    String  name;
    String  path;
    int     cost;
    
    public Song ( String name, String path, int cost ) {
        this.name = name;
        //System.out.println(name);
        this.path = path;
        //System.out.println(path);
        this.cost = cost;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPath() {
        return path;
    }
    
    public int getCost() {
        return cost;
    }
}
