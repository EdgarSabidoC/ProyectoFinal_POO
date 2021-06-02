/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.util.ArrayList;

/**
 *
 * @author edgar
 */
public class MagazineCollection {
    private String collectionName;
    private ArrayList<Magazine> magazineList;

    public MagazineCollection(String collectionName, ArrayList<Magazine> magazineList) {
        this.collectionName = collectionName;
        this.magazineList = magazineList;
    }
    
    // Getters:
    public String getCollectionName() {
        return collectionName;
    }

    public ArrayList<Magazine> getMagazineList() {
        return magazineList;
    }
    
    // Setters:
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setMagazineList(ArrayList<Magazine> magazineList) {
        this.magazineList = magazineList;
    }

    @Override
    public String toString() {
        return "MagazineCollection{" + "collectionName=" + collectionName + ", magazineList=" + magazineList + '}';
    }
    
}
