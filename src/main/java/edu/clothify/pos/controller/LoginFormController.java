package edu.clothify.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.encryption.PasswordEncryption;
import edu.clothify.pos.bo.user.UserBo;
import edu.clothify.pos.dto.User;
import edu.clothify.pos.utill.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassWord;
    public JFXButton btnLogin;
    public Hyperlink btnfogotPassWord;
    public Hyperlink btnCreateAccount;
    UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);
    PasswordEncryption encryption = BoFactory.getInstance().getBo(BoType.ENCRYPTION);
    public void btnfogotPassWordOnAction(ActionEvent actionEvent) {
    }

    public void btnCreateAccountOnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/singin-form.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        User userByEmail = userBo.getUserByEmail(txtEmail.getText());
        System.out.println(userByEmail);
        System.out.println(txtEmail.getText());
        System.out.println(txtPassWord.getText());
        boolean isTrue = userByEmail.getEmail().equals(txtEmail.getText()) &&
                encryption.checkPassword(txtPassWord.getText(),userByEmail.getPassword());
        System.out.println(isTrue);
        if(isTrue){
            if("employee".equalsIgnoreCase(userByEmail.getRole())){
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/employee-dash.fxml"));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/owner-dash.fxml"));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else{

        }
    }
}
