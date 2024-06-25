package edu.clothify.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.custom.CustomerBo;
import edu.clothify.pos.bo.item.ItemBo;
import edu.clothify.pos.dto.Customer;
import edu.clothify.pos.dto.Item;
import edu.clothify.pos.dto.Orders;
import edu.clothify.pos.utill.BoType;
import jakarta.persistence.criteria.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    public JFXButton btnDasshBoard;
    public JFXButton btnCustomer;
    public JFXButton btnOrder;
    public JFXButton btnItem;
    public JFXButton btnSupplier;
    public JFXButton btnLogOut;
    public TableView tblCart;
    public TableColumn colItemCode;
    public TableColumn colName;
    public TableColumn colQty;
    public TableColumn colSize;
    public TableColumn cloUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnAddToCart;
    public Label lblCustomerName;
    public Label lblCustomerEmail;
    public Label lblIteamName;
    public Label lblIteamSize;
    public Label lblIteamQty;
    public Label lblUnitePrice;
    public JFXButton btnClear;
    public JFXButton btnPlaceOrder;
    public Label lblNetTotal;
    public Label lblOrderId;
    public JFXComboBox cmbCustId;
    public JFXComboBox cmbItemId;
    public Label lblDate;

    CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);
    ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);
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
    private void loadCustomerId(){
        List<Customer> allCustomerByIsActiveTrue = customerBo.getAllCustomerByIsActiveTrue();
        ObservableList<String> ids = FXCollections.observableArrayList();
        allCustomerByIsActiveTrue.forEach(customer -> {
            ids.add(customer.getCustomerId());
        });
        cmbCustId.setItems(ids);
    }
    private void loadItemId(){
        List<Item> allItemByIsActiveTrue = itemBo.getAllItemByIsActiveTrue();
        ObservableList<String> ids = FXCollections.observableArrayList();
        allItemByIsActiveTrue.forEach(item -> {
            ids.add(item.getItemCode());
        });
        cmbItemId.setItems(ids);
    }
    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        new Orders();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    private void setCustomerDataForLbls(String customerId){
        Customer customer  = customerBo.getCustomerById(customerId);
        lblCustomerName.setText(customer.getName());
        lblCustomerEmail.setText(customer.getEmail());
    }
    private void setItemDetailsLbls(String itemCode){
        Item item = itemBo.getItemById(itemCode);
        lblIteamName.setText(item.getName());
        lblIteamSize.setText(item.getSize());
        lblIteamQty.setText(String.valueOf(item.getQty()));
        lblUnitePrice.setText(String.valueOf(item.getPrice()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCustomerId();
        loadItemId();
        cmbCustId.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setCustomerDataForLbls((String) newValue);
        });
        cmbItemId.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setItemDetailsLbls((String) newValue);
        });
    }
}
