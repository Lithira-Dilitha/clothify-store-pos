package edu.clothify.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.item.ItemBo;
import edu.clothify.pos.dto.Employee;
import edu.clothify.pos.dto.Item;
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

public class ItemFormController implements Initializable {
    public JFXButton btnDasshBoard;
    public JFXButton btnCustomer;
    public JFXButton btnOrder;
    public JFXButton btnItem;
    public JFXButton btnSupplier;
    public JFXButton btnLogOut;
    public TableView tblItems;
    public TableColumn colIteamcode;
    public TableColumn colName;
    public TableColumn colSize;
    public TableColumn colQty;
    public TableColumn colPrice;
    public JFXTextField txtIteamCode;
    public JFXTextField txtPrice;
    public JFXTextField txtSize;
    public JFXTextField txtQty;
    public JFXButton btnSearch;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnAdd;
    public JFXTextField txtName;
    public JFXTextField txtSupplierId;
    public TableColumn colsupplier;

    private ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);

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

    public void btnSearchOnAction(ActionEvent actionEvent) {
        Item item = itemBo.getItemById(txtIteamCode.getText());
        txtName.setText(item.getName());
        txtSize.setText(item.getSize());
        txtQty.setText(String.valueOf(item.getQty()));
        txtPrice.setText(String.valueOf(item.getPrice()));
        txtSupplierId.setText(item.getSupplier());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Item item = new Item(
                txtIteamCode.getText(),
                txtName.getText(),
                txtSize.getText(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtPrice.getText()),
                txtSupplierId.getText(),
                true
        );

        boolean b = itemBo.updateItem(txtIteamCode.getText(),item);
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Item Updated !").show();
            clearTextBoxes();
            loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Item Not Updated !").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        boolean b = itemBo.deleteItem(txtIteamCode.getText());
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Item Successfully Deleted").show();
            clearTextBoxes();
            loadTable();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Item Successfully Deleted").show();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Item item = new Item(
                txtIteamCode.getText(),
                txtName.getText(),
                txtSize.getText(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtPrice.getText()),
                txtSupplierId.getText(),
                true
        );
        boolean save = itemBo.saveItem(item);
        if(save){
            new Alert(Alert.AlertType.CONFIRMATION,"Item Add !").show();
            clearTextBoxes();
            loadTable();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Item Not Add !").show();
        }
    }

    private void loadTable() {
        ObservableList<Item> itemsTable = FXCollections.observableArrayList();

        List<Item> allItems = itemBo.getAllItemByIsActiveTrue();
        allItems.forEach(item ->{
            itemsTable.add(
                    new Item(
                            item.getItemCode(),
                            item.getName(),
                            item.getSize(),
                            item.getQty(),
                            item.getPrice(),
                            item.getSupplier(),
                            item.getIsActive()
                    )
            );
        });
        tblItems.setItems(itemsTable);
    }

    private void clearTextBoxes() {
        txtIteamCode.setText(null);
        txtSupplierId.setText(null);
        txtName.setText(null);
        txtQty.setText(null);
        txtSize.setText(null);
        txtPrice.setText(null);
        txtPrice.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colIteamcode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colsupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        loadTable();
    }
}
