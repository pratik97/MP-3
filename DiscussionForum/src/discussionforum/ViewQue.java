/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discussionforum;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Pratik
 */
public class ViewQue {
     public SimpleStringProperty tid = new SimpleStringProperty();
    public SimpleStringProperty ttag = new SimpleStringProperty(); 
    public SimpleStringProperty tque = new SimpleStringProperty();
    public SimpleStringProperty tauth=new SimpleStringProperty();
    public ViewQue(String id, String tag, String que) {
        System.out.println(id);
        this.tid=new SimpleStringProperty(id);
        this.ttag=new SimpleStringProperty(tag);
        this.tque=new SimpleStringProperty(que);
        
    }
    public String getAuth()
    {
        return tauth.get();
    }
    public String getId() {
        return tid.get();
    }
    public String getTag() {
        return ttag.get();
    }
    public String getQue() {
        return tque.get();
    }
    
    public void setAuth(String auth) {
        tauth.set(auth);
    }
    
    public void setId(String id) {
        tid.set(id);
    }
    public void setTag(String tag) {
         ttag.set(tag);
    }
    public void setQue(String que) {
        tque.set(que);
    }
    public StringProperty tidProperty()
    {
        return tid;
    }
    public StringProperty ttagProperty()
    {
        return ttag;
    }
    public StringProperty tqueProperty()
    {
        return tque;
    }
   
    
}
