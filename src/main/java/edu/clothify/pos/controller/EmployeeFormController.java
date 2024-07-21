package edu.clothify.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.encryption.PasswordEncryption;
import edu.clothify.pos.bo.user.UserBo;
import edu.clothify.pos.dto.User;
import edu.clothify.pos.utill.BoType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    public JFXButton btnDasshBoard;
    public JFXButton btnCustomer;
    public JFXButton btnOrder;
    public JFXButton btnItem;
    public JFXButton btnSupplier;
    public JFXButton btnLogOut;
    public TableView tblEmployeeTable;
    public TableColumn colEmployeeId;
    public TableColumn colName;
    public JFXTextField txtEmployeeId;
    public JFXTextField txtName;
    public JFXButton btnSearch;
    public JFXButton btnUpdateEmployee;
    public JFXButton btnDeleteEmployee;
    public JFXButton btnAddEmployee;
    public JFXTextField txtEmail;
    public JFXTextField txtPassWord;
    public TableColumn colPassWord;
    public TableColumn colEmail;
    public TableColumn colRole;
    public Label lblTime;
    public Label lblDate;

    UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);
    PasswordEncryption encryption = BoFactory.getInstance().getBo(BoType.ENCRYPTION);
    public void btnDasshBoardOnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/employee-dash.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/add-customer-form.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnOrderOnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/order-form.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnIteamOnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/item-form.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/supplier-form.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/login-form.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) {

        User user =new User(
                txtEmployeeId.getText(),
                txtName.getText(),
                "employee",
                txtEmail.getText(),
                encryption.hashPassword(txtPassWord.getText()),
                true
        );
        boolean save = userBo.save(user);
        if(save){
            new Alert(Alert.AlertType.CONFIRMATION,"User Updated !").show();
            clearTextBoxes();
            loadTable();
            setEmployeeId();
        }else{
            new Alert(Alert.AlertType.ERROR,"User Not Updated !").show();
        }
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) {
        boolean b = userBo.deleteUser(txtEmployeeId.getText());
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"User Updated !").show();
            clearTextBoxes();
            loadTable();
            setEmployeeId();
        }else{
            new Alert(Alert.AlertType.ERROR,"User Not Updated !").show();
        }
    }

    private void loadTable() {
        ObservableList<User> EmployeeList = FXCollections.observableArrayList();
        List<User> users = userBo.getAllUserByIsActiveTrue();
        users.forEach(user ->{
            EmployeeList.add(new User(
                    user.getUserId(),
                    user.getName(),
                    user.getRole(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getIsActive()
            ));
        });
        tblEmployeeTable.setItems(EmployeeList);
    }

    private void clearTextBoxes() {
        txtName.setText(null);
        txtPassWord.setText(null);
        txtEmail.setText(null);
    }
    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) {
        User user =new User(
                txtEmployeeId.getText(),
                txtName.getText(),
                "employee",
                txtEmail.getText(),
                encryption.hashPassword(txtPassWord.getText()),
                true
        );
        boolean b = userBo.updateUser(txtEmployeeId.getText(), user);
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"User Updated !").show();
            clearTextBoxes();
            loadTable();
            setEmployeeId();
        }else{
            new Alert(Alert.AlertType.ERROR,"User Not Updated !").show();
        }
    }
    private void setEmployeeId(){
        txtEmployeeId.setText(userBo.generateUserId());
    }

    private void loadTimeAndDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime time = LocalTime.now();
            lblTime.setText(
                    time.getHour() + " : " + time.getMinute() + " : " + time.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {
        User userById = userBo.getUserById(txtEmployeeId.getText());
        txtName.setText(userById.getName());
        txtEmail.setText(userById.getEmail());
        txtPassWord.setText(userById.getPassword());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPassWord.setCellValueFactory(new PropertyValueFactory<>("password"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        loadTable();
        setEmployeeId();
        loadTimeAndDate();
    }
}
