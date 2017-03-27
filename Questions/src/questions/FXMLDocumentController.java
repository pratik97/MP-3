/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questions;

import java.awt.TextArea;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Pratik
 */

public class FXMLDocumentController implements Initializable {
    public static int incrementer=0;   
    public static String id1="";
    public database_handler db=new database_handler();
    @FXML
    Label qid;
    @FXML
    Button next;
    @FXML
    Button prev;
    @FXML
    ComboBox<String> qtype;
    @FXML
    ComboBox<String> difficulty;
    @FXML
    TextField question;
    @FXML
    TextField OptionA;
    @FXML
    TextField OptionB;
    @FXML
    TextField OptionC;
    @FXML
    TextField OptionD;
    @FXML
    TextField ans;
    
   
    @FXML
    @SuppressWarnings("empty-statement")
    public void handleButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        String question1=question.getText();
        String optiona=OptionA.getText();
        String optionb=OptionB.getText();
        String optionc=OptionC.getText();
        String optiond=OptionD.getText();
        
        String ans1="0";
        ans1=ans.getText();
        
        
        String combo1=qtype.getValue();
        String combo2=difficulty.getValue();
       // System.out.println("Combo");
       // System.out.println(combo2);
        //System.out.println(incrementer);
        
        if(event.getSource()==qtype)
        {
            System.out.println("Hello");
            String qtype2=(String)qtype.getValue();
            switch(qtype2)
            {
                case "DBMS":
                {incrementer=db.returnlastid("1");
                    incrementer++;
                    id1="0001"+String.format("%03d",incrementer);
                    System.out.println(id1);
                   
                    break;
                }
                case "OS":
                {incrementer=db.returnlastid("2");
                    incrementer++;
                    id1="0002"+String.format("%03d",incrementer);
                   
                    System.out.println(id1);

                    break;
                }
                case "DCN":
                {incrementer=db.returnlastid("3");
                    incrementer++;
                    id1="0003"+String.format("%03d",incrementer);
                    System.out.println(id1);
                    break;
                }
                case "ThoC":
                   {incrementer=db.returnlastid("4");
                    incrementer++;
                    id1="0004"+String.format("%03d",incrementer);
                    System.out.println(id1);
                    break;
                }
                case "OOP":
                    {incrementer=db.returnlastid("5");
                    incrementer++;
                    id1="0005"+String.format("%03d",incrementer);
                    break;
                }
                case "DS":
                    {incrementer=db.returnlastid("6");
                    incrementer++;
                    id1="0006"+String.format("%03d",incrementer);
                    break;
                }
                 
                case "Algorithms":
                    {incrementer=db.returnlastid("7");
                    incrementer++;
                    id1="0007"+String.format("%03d",incrementer);
                    break;
                }
                case "SE":
                    {incrementer=db.returnlastid("8");
                    incrementer++;
                    id1="0008"+String.format("%03d",incrementer);
                    break;
                }
                /*default:
                {
                    incrementer=db.returnlastid("1");
                    incrementer++;
                    id1="0001"+String.format("%03d",incrementer);
                    break;
                }*/
                    
            }
         
        }
        
        if(event.getSource()==next)   
        {
            
            System.out.println(id1);
            if(id1=="" || question1=="" || combo1=="" || combo1==null||combo2==""|| combo2==null ||optiona=="" ||optionb=="" ||optionc=="" ||optiond=="" || ans1=="")
            {
                
                Alert alert1 = new Alert(AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Question can not be created!");
                alert1.setContentText("Dear User, Your Question has possibly one or more fields empty or unselected. To create a question you have to give information about all the fields.");
                alert1.showAndWait();
            }
            else
            {
            db.insert_data(id1, question1,combo1,combo2,optiona,optionb,optionc,optiond,ans1);
            question.setText("");                                               
            OptionA.setText("");
            OptionB.setText("");
            OptionC.setText("");
            OptionD.setText("");
            qtype.setPromptText("Select Subject");
            qtype.setValue(null);
             difficulty.setPromptText("Select Difficulty");
             difficulty.setValue(null);
            ans.setText("");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Your Question is Created!");
            alert.setContentText("Dear User, Your Question is Created!");
            Thread thread = new Thread(() -> {
            try {
                // Wait for 5 secs
                Thread.sleep(2000);
                if (alert.isShowing()) {
                    Platform.runLater(() -> alert.close());
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();

        alert.showAndWait();
        }
           
        }
        
        //difficulty.setValue();
        
        
    }
    ObservableList<String> qtype1=FXCollections.observableArrayList("DBMS","OS","DCN","ThoC","OOP","DS","Algorithm","SE");
    ObservableList<String> diff1=FXCollections.observableArrayList("Easy","Medium","Hard");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        qtype.setItems(qtype1);
       // qtype.setValue("DBMS");
        difficulty.setItems(diff1);
       // difficulty.setValue("Easy");
    }    
    
}
