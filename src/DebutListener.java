import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DebutListener {

    //passer a la page de connexion
    @FXML
    void conClicked(ActionEvent event) {
        Button bt = (Button) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("frame//Connexion.fxml"));
            sc.setRoot(root);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("test");
        
    }



    // try

    @FXML
    private PasswordField mdp;

    @FXML
    private TextField user;

    @FXML
    private Label wrong;

    @FXML
    void connect(ActionEvent event) {
        String username = user.getText();
        String password = mdp.getText();
        System.out.println(username);
        System.out.println(password);
        System.out.println(username.equals("hugo"));
        System.out.println(password.equals("123456")); 

        if(username.equals("hugo") && password.equals("123456")){
            Stage newStage = new Stage();
            Parent r;
            try {
                r = FXMLLoader.load(getClass().getResource("frame\\Accueil.fxml"));
                Scene s = new Scene(r);
                newStage.setTitle("Accueil");
                newStage.setWidth(600);
                newStage.setHeight(435);
                newStage.setScene(s);
                newStage.show();
                newStage.centerOnScreen();
            }catch (IOException e) {
                e.printStackTrace();
            }
            //fermeture de la page de connexion
            Button bt = (Button) event.getSource();
            Scene sc = bt.getScene();
            Stage st = (Stage) sc.getWindow();
            st.close();
        }else{
            wrong.setVisible(true);
        }
    }
   



}
