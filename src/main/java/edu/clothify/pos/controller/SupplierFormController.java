package edu.clothify.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.suplier.SupplierBo;
import edu.clothify.pos.dto.Employee;
import edu.clothify.pos.dto.Supplier;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    public JFXButton btnDasshBoard;
    public JFXButton btnCustomer;
    public JFXButton btnOrder;
    public JFXButton btnItem;
    public JFXButton btnSupplier;
    public JFXButton btnLogOut;
    public TableColumn colSupplerId;
    public TableColumn colName;
    public TableColumn ColCompany;
    public TableColumn ColEmail;
    public JFXTextField txtSupplierId;
    public JFXTextField txtEmail;
    public JFXTextField txtCompany;
    public JFXButton btnSearch;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton BtnAdd;
    public JFXTextField txtName;
    public TableView tblSupplier;

    private SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);
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
        Supplier supplier = supplierBo.getSupplierById(txtSupplierId.getText());
        txtName.setText(supplier.getName());
        txtCompany.setText(supplier.getCompany());
        txtEmail.setText(supplier.getEmail());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                txtSupplierId.getText(),
                txtName.getText(),
                txtCompany.getText(),
                txtEmail.getText(),
                true
        );
        boolean b = supplierBo.updateSupplier(txtSupplierId.getText(), supplier);
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Updated !").show();
            clearTextBoxes();
            loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier Not Updated !").show();
        }
    }

    private void loadTable() {
        ObservableList<Supplier> Suppliers = FXCollections.observableArrayList();
        List<Supplier> supplierList = supplierBo.getAllSupplierByIsActiveTrue();
        supplierList.forEach(supplier ->{
            Suppliers.add(
                    new Supplier(
                            supplier.getSupplierId(),
                            supplier.getName(),
                            supplier.getCompany(),
                            supplier.getEmail(),
                            supplier.getIsActive()
                    )
            );
        });
        tblSupplier.setItems(Suppliers);
    }

    private void clearTextBoxes() {
        txtSupplierId.setText(null);
        txtName.setText(null);
        txtCompany.setText(null);
        txtEmail.setText(null);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        boolean b = supplierBo.deleteSupplier(txtSupplierId.getText());
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Updated !").show();
            clearTextBoxes();
            loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier Not Updated !").show();
        }
    }

    public void BtnAddOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                txtSupplierId.getText(),
                txtName.getText(),
                txtCompany.getText(),
                txtEmail.getText(),
                true
        );
        boolean save = supplierBo.save(supplier);
        if(save){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Updated !").show();
            clearTextBoxes();
            loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier Not Updated !").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplerId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadTable();
    }
}
