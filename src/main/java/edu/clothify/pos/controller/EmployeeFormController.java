package edu.clothify.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.employee.EmployeeBo;
import edu.clothify.pos.dto.Customer;
import edu.clothify.pos.dto.Employee;
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
    public TableColumn colAddress;
    public TableColumn colEmail;
    public JFXTextField txtEmployeeId;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXButton btnSearch;
    public JFXButton btnUpdateEmployee;
    public JFXButton btnDeleteEmployee;
    public JFXButton btnAddEmployee;
    public JFXTextField txtEmail;

     EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);
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
        Employee employee = new Employee(
                txtEmployeeId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                true
        );
        boolean save = employeeBo.save(employee);
        if(save){
            new Alert(Alert.AlertType.CONFIRMATION,"Employee Add !").show();
            clearTextBoxes();
            loadTable();
        }else{
           new Alert(Alert.AlertType.CONFIRMATION,"Employee Not Add !").show();
        }
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) {
        boolean b = employeeBo.deleteEmployee(txtEmployeeId.getText());
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Employee Successfully Deleted").show();
            clearTextBoxes();
            loadTable();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Employee Successfully Deleted").show();
        }

    }

    private void loadTable() {
        ObservableList<Employee> employeeTable = FXCollections.observableArrayList();

        List<Employee> allEmployees = employeeBo.getAllEmployeeByIsActiveTrue();
        allEmployees.forEach(employee ->{
            employeeTable.add(
                    new Employee(
                          employee.getEmployeeId(),
                          employee.getName(),
                          employee.getAddress(),
                          employee.getEmail(),
                            employee.getIsActive()
                    )
            );
        });
        tblEmployeeTable.setItems(employeeTable);
    }

    private void clearTextBoxes() {
        txtEmployeeId.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtEmail.setText(null);
    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) {
        Employee employee = new Employee(
                txtEmployeeId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                true
        );
        boolean b = employeeBo.updateEmployee(txtEmployeeId.getText(),employee);
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Employee Updated !").show();
            clearTextBoxes();
            loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Employee Not Updated !").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        Employee employee = employeeBo.getEmployeeById(txtEmployeeId.getText());
        txtName.setText(employee.getName());
        txtAddress.setText(employee.getAddress());
        txtEmail.setText(employee.getEmail());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadTable();
    }
}
