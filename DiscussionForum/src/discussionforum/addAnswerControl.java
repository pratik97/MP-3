package discussionforum;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class addAnswerControl implements Initializable{

@FXML
private AnchorPane AnchorPane;
@FXML
private TextArea ansfield;
@FXML
private Label qlab;
@FXML
private Button answer;
    
    /**
     *
     * @param name
     */
int id;String nam;
Stage stage;Parent root;
    public void getQuestion(String name,int index)
    {
       System.out.println("name");
        qlab.setText(name);
        nam=name;
        id=index;
    }
    public void answer(ActionEvent e) throws ClassNotFoundException, SQLException, IOException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cn;
        cn=DriverManager.getConnection("jdbc:mysql://localhost/discfor","root","");
        Statement stmt=cn.createStatement();
        String sql="insert into question values ('"+id+"','"+nam+"','"+ansfield.getText()+"')";
        stmt.executeUpdate(sql);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText("Your Answer is Submitted.");
                alert1.setContentText("Dear User, Your Answer for this question is submitted successfully.");
                //alert1.showAndWait();
                Optional<ButtonType> result = alert1.showAndWait();
                if (result.isPresent()) {
                    // ok was pressed.
                    stage=(Stage)answer.getScene().getWindow();
            root=FXMLLoader.load(getClass().getResource("searchQuestion.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
                    //Stage pstage=new Stage();
            
            /*FXMLLoader loader=new FXMLLoader();
            try
            {
            AnchorPane pane=loader.load(getClass().getResource("searchQuestion.fxml").openStream());
            FXMLDocumentController fc=(FXMLDocumentController)loader.getController();
            ActionEvent xe;
            //fc.handleButtonAction(xe);
            System.out.println("1+"+fc);
                System.out.println("2+"+fc.toString());
            Scene scene=new Scene(pane);
            
            stage.setScene(scene);
            stage.show();
            
            }
            catch(Exception ae)
            {
                ae.printStackTrace();
            }*/
            
                } else {
                // cancel might have been pressed.
                    }
        
    }
    

@Override
    public void initialize(URL location, ResourceBundle resources) {
         //To change body of generated methods, choose Tools | Templates.
    }

    public void start(Stage primaryStage) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
        
        
    }
    
}
