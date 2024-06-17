package edu.clothify.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.custom.CustomerBo;
import edu.clothify.pos.dto.Customer;
import edu.clothify.pos.utill.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
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

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadTable();
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
                txtEmail.getText()
        );
        boolean save = customerBo.save(customer);
        System.out.println(save);
        if(save){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Add !").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer Not Add !").show();
        }
    }

    private void loadTable() {
        ObservableList<Customer> customersTable = FXCollections.observableArrayList();

        List<Customer> allCustomers = customerBo.getAllCustomers();
        allCustomers.forEach(customer -> {
            customersTable.add(
                    new Customer(
                            customer.getCustomerId(),
                            customer.getName(),
                            customer.getAddress(),
                            customer.getEmail()
                    )
            );
        });
        tblCustomerTable.setItems(customersTable);
    }

}
