/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discussionforum;

/*import com.sun.java.util.jar.pack.Attribute.Layout;*/
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *
 * @author Pratik
 */

public  class FXMLDocumentController extends Application implements Initializable {
    static int id=0;
    public static String ques;
    @FXML
    Button addque;
    @FXML
    Label showid;
    @FXML
    Label showtag;
    @FXML
    Label showques;
    @FXML
    Label showans;
     @FXML
    Label showauthor;
    @FXML
    Button viewque;
    @FXML
    Label discforum;
    @FXML
    TextField texttag;
    @FXML
    Label lid;
    @FXML
    Label lqdate;
    @FXML
    Button ask;
    @FXML
    TextArea textque;
    @FXML
    TextField textuser;
    @FXML
    Button refresh;
    @FXML
    ObservableList<ViewQue> data=null;
    @FXML
    TableView<ViewQue> table;
    @FXML
    TableColumn<ViewQue,String>  tid;
    @FXML
    TableColumn<ViewQue,String>  ttag;
    @FXML
    TableColumn<ViewQue,String>  tque;
    @FXML
    Button back;
    @FXML
    Button addans;
    @FXML
    Label qlab;
    @FXML
    TextArea ansfield;
  Stage stage;Parent root;
  ViewQue temp;
Date lastClickTime;
@FXML
private void handleRowSelect() throws ClassNotFoundException, SQLException, IOException{
    //ViewQue row = table.getSelectionModel().getSelectedItem();
    //if (row==null) return;
    Object object =  table.getSelectionModel().selectedItemProperty().get();
    int index = table.getSelectionModel().selectedIndexProperty().get()+1;
    System.out.println(index);
    Class.forName("com.mysql.jdbc.Driver");
    Connection cn;
    cn=DriverManager.getConnection("jdbc:mysql://localhost/discfor","root","");
    Statement stmt=cn.createStatement();
    String sql="select tag,Question,Author,Date1 from addque where id='"+index+"';";
    ResultSet rs=stmt.executeQuery(sql);
    String tag = null,answer = "\n",question="",author="",date="";
    
    while(rs.next())
    {
        tag=rs.getString(1);
        question=rs.getString(2);
        author=rs.getString(3);
        date=rs.getString(4);
                
    }
    sql="select answer from question where question='"+question+"';";
    rs=stmt.executeQuery(sql);
    int i=1;
    while(rs.next())
    {
        answer+="Answer "+i+"\n";
        answer+=rs.getString(1);
        answer+="\n\n";
        i++;
    }
    System.out.println(tag);
    System.out.println(question);
    Stage pstage=new Stage();
            
            FXMLLoader loader=new FXMLLoader();
            try
            {
            AnchorPane pane=loader.load(getClass().getResource("showQuestion.fxml").openStream());
            
            searchController ac=(searchController)loader.getController();
            //System.out.println("out of the blue");
            //System.out.println(question);
            String ind=Integer.toString(index);
            ac.getData(ind,tag,question,author,date);
            ac.getAnswer(answer);
            Scene scene=new Scene(pane);
            pstage.setScene(scene);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            
             pstage.show();
    
}
  /*@FXML
    public void onClick(MouseEvent event) throws SQLException, ClassNotFoundException, IOException
    {
        if (event.getClickCount() == 2) //Checking double click
        {
            int id=table.getSelectionModel().getSelectedIndex()+1;
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn;
            cn=DriverManager.getConnection("jdbc:mysql://localhost/discfor","root","");
            Statement stmt=cn.createStatement();
            String sql="select id,tag,question,author from addque where id='"+id+"';";
            ResultSet rs=stmt.executeQuery(sql);
            String tag = null,author,question;
            while(rs.next())
            {
                tag=rs.getString(2);
                question=rs.getString(3);
                author=rs.getString(4);
            }
            stage=(Stage)back.getScene().getWindow();
            root=FXMLLoader.load(getClass().getResource("showQuestion.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
            
            showtag.setText(tag);
            
        }
    }
    */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
        //Stage stage;Parent root;
        Class.forName("com.mysql.jdbc.Driver");
        Connection cn;
        cn=DriverManager.getConnection("jdbc:mysql://localhost/discfor","root","");
        if(event.getSource()==addque)
        {
            stage=(Stage)addque.getScene().getWindow();
            root=FXMLLoader.load(getClass().getResource("addQuestions.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(event.getSource()==ask)
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String date1=dateFormat.format(date);
            String tag=texttag.getText();
            String que=textque.getText();
            String user=textuser.getText();
            
            if(date1.length()==0 || tag.length()==0 || que.length()==0 || user.length()==0)
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Question can not be created!");
                alert1.setContentText("Dear User, Your Question has possibly one or more fields empty. To create a question you have to give information about all the fields.");
                alert1.showAndWait();
            }
            else
            {
                String last_id="";
       
                Statement statement = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs= statement.executeQuery("select count(*) from addque;");
                while(rs.next())
                {
                    last_id=rs.getString(1);
                }
                id=Integer.parseInt(last_id);
                id++;
                System.out.println(last_id);
                String x=Integer.toString(id);
                System.out.println(date1);
                lid.setText(last_id);
                lqdate.setText(date1);
                Statement stmt=cn.createStatement();
                stmt.executeUpdate("INSERT INTO addque VALUES('"+x+"','"+tag+"','"+que+"','"+user+"','"+date1+"')");
                
                cn.close();
            }
            
            
        }
        if(event.getSource()==viewque)
        {
            stage=(Stage)viewque.getScene().getWindow();
            try
            {
            root=FXMLLoader.load(getClass().getResource("searchQuestion.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();   
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        if(event.getSource()==back)
        {
            stage=(Stage)back.getScene().getWindow();
            root=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();   
        }
        if(event.getSource()==addans)
        {
           
            int index=table.getSelectionModel().getSelectedIndex()+1;
            Statement stmt=cn.createStatement();
            String question ="a";
            String sql="select question from addque where id='"+index+"';";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                question=rs.getString(1);
            }
            //stage=(Stage)addans.getScene().getWindow();
            
            Stage pstage=new Stage();
            
            FXMLLoader loader=new FXMLLoader();
            try
            {
            AnchorPane pane=loader.load(getClass().getResource("addAnswer.fxml").openStream());
            addAnswerControl ac=(addAnswerControl)loader.getController();
            System.out.println("out of the blue");
            System.out.println(question);
                System.out.println(ac);
                System.out.println(ac.toString());
            ac.getQuestion(question,index);
            Scene scene=new Scene(pane);
            pstage.setScene(scene);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            
          // root=FXMLLoader.load(getClass().getResource("addAnswer.fxml"));
            
           
            //method(question);
            pstage.show();
            
            
            //qlab.setText(question);
            /*stage=new Stage();
            System.out.println("1++"+question);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addAnswer.fxml"));
            root=loader.load();
            ac = loader.getController();
            ac.setName(question);
            loader.setRoot(root);
            loader.setController(ac);
            stage.setScene(new Scene(root));
            //stage.initOwner(addans.getScene().getWindow());
            stage.show();
            */
          
        }
        
    }
   /* TableView<ViewQue> table1=new TableView<>();
    @FXML
    table1.setRowFactory( tv -> {
            TableRow<ViewQue> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    ViewQue rowData = row.getItem();
                    System.out.println(rowData);
            }
        });
            return row ;
        });*/
    

    public void loadDataFromDatabase(ActionEvent e) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cn=null;
        cn=DriverManager.getConnection("jdbc:mysql://localhost/discfor","root","");
        
        Statement stmt=(Statement) cn.createStatement();
        data = FXCollections.observableArrayList();
        String query="select id,tag,question from addque";
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next())
        {
            data.add(new ViewQue(rs.getString(1),rs.getString(2),rs.getString(3)));
        }
        tid.setCellValueFactory(new PropertyValueFactory<>("tid"));
            ttag.setCellValueFactory(new PropertyValueFactory<>("ttag"));
            tque.setCellValueFactory(new PropertyValueFactory<>("tque"));
            table.setItems(null);
            table.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*table.setMousePressed(new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent me)
                {
                    FXMLLoader loader=new FXMLLoader();
                    Loader.setSelection(getClass.)
                }
                });*/
        
    }    
   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
         //To change body of generated methods, choose Tools | Templates.
        
    }

   
  
}
  