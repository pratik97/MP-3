/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discussionforum;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Pratik
 */
public class searchController implements Initializable{
    @FXML
    Label showid;
    @FXML
    Label showtag;
    @FXML
    Label showque;
    @FXML
    Label showans;
    @FXML
    Label showauth;
    @FXML
    Label showdate;
    Stage stage;Parent root;
    public void getData(String index,String tag,String question,String author,String date)
    {
       System.out.println("name");
        showid.setText(index);
        showtag.setText(tag);
        showque.setText(question);
        showauth.setText(author);
        showdate.setText(date);
    }
    public void getAnswer(String answer)
    {
       //System.out.println("name");
        showans.setText(answer);
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         //To change body of generated methods, choose Tools | Templates.
    }
    
}
