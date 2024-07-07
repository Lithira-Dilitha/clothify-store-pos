package edu.clothify.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.email.EmailSending;
import edu.clothify.pos.utill.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgotPasswordFormController {

    public JFXTextField txtEmail;
    public JFXPasswordField txtOtp;
    public JFXButton btnSnedOtp;
    public JFXButton btnDone;

    EmailSending emailSending = BoFactory.getInstance().getBo(BoType.MAILSENDING);
    String otp ;
    public void btnSnedOtpOnAction(ActionEvent actionEvent) {
        String to = txtEmail.getText();
        otp = emailSending.generateOtp();
        emailSending.sendMail(to,"email address verification",otp);
    }

    public void btnDoneOnAction(ActionEvent actionEvent) {
        if(otp.equals(txtOtp.getText())){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/change-password-form.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"your verification fail, Try Again!").show();
        }
    }
}
