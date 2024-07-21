package edu.clothify.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.custom.CustomerBo;
import edu.clothify.pos.dto.Customer;
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

public class AddcustomerFormController implements Initializable {

    public JFXButton btnDasshBoard;
    public JFXButton btnCustomer;
    public JFXButton btnOrder;
    public JFXButton btnItem;
    public JFXButton btnSupplier;
    public JFXButton btnLogOut;
    public JFXTextField txtCustomerId;
    public JFXTextField txtEmail;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXButton btnAddCustomer;
    public TableView tblCustomerTable;
    public TableColumn colCustomerId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public JFXButton btnUpdateCustomer;
    public JFXButton btnSearch;
    public JFXButton btnDelete;
    public Label lblTime;
    public Label lblDate;

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadTable();
        setCustomerId();
        loadTimeAndDate();
    }

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

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(
                txtCustomerId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                true
        );
        boolean save = customerBo.save(customer);
        System.out.println(save);
        if(save){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Add !").show();
            clearTextBoxes();
            loadTable();
            setCustomerId();
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer Not Add !").show();
        }
    }

    private void loadTable() {
        ObservableList<Customer> customersTable = FXCollections.observableArrayList();

        List<Customer> allCustomers = customerBo.getAllCustomerByIsActiveTrue();
        allCustomers.forEach(customer -> {
            customersTable.add(
                    new Customer(
                            customer.getCustomerId(),
                            customer.getName(),
                            customer.getAddress(),
                            customer.getEmail(),
                            customer.getIsActive()
                    )
            );
        });
        tblCustomerTable.setItems(customersTable);
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
    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(
                txtCustomerId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                true
        );
        boolean b = customerBo.updateCustomer(txtCustomerId.getText(),customer);
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated !").show();
            clearTextBoxes();
            loadTable();
            setCustomerId();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Updated !").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        Customer customer = customerBo.getCustomerById(txtCustomerId.getText());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtEmail.setText(customer.getEmail());
    }

    private void clearTextBoxes(){
        txtName.setText(null);
        txtAddress.setText(null);
        txtEmail.setText(null);
    }

    private void setCustomerId(){
        txtCustomerId.setText(customerBo.generateCustomerId());
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        boolean b = customerBo.deleteCustomer(txtCustomerId.getText());
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Successfully Deleted").show();
            clearTextBoxes();
            loadTable();
            setCustomerId();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Successfully Deleted").show();
        }
    }
}
