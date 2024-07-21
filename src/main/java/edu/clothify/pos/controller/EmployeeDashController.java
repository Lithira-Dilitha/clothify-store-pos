package edu.clothify.pos.controller;

import com.jfoenix.controls.JFXButton;
import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.custom.CustomerBo;
import edu.clothify.pos.bo.orders.OrdersBo;
import edu.clothify.pos.bo.suplier.SupplierBo;
import edu.clothify.pos.bo.user.UserBo;
import edu.clothify.pos.dto.Orders;
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

public class EmployeeDashController implements Initializable {
    public JFXButton btnDasshBoard;
    public JFXButton btnCustomer;
    public JFXButton btnOrder;
    public JFXButton btnItem;
    public JFXButton btnSupplier;
    public JFXButton btnLogOut;
    public JFXButton btnAddEmployee;
    public Label lblEmployeecount;
    public Label lblOrdersCount;
    public Label lblCustomerCount;
    public Label lblSupplier;
    public TableView tblOrderDetails;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colCustomerId;
    public TableColumn colEmployeeId;
    public TableColumn colTotal;
    public Label lblTime;
    public Label lblDate;

    UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);
    CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);
    OrdersBo ordersBo = BoFactory.getInstance().getBo(BoType.ORDERS);
    SupplierBo supplierBo =BoFactory.getInstance().getBo(BoType.SUPPLIER);
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
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/employee-form.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadTable(){
        List<Orders> allOrders = ordersBo.getAllOrders();
        ObservableList<Orders> ordersList = FXCollections.observableArrayList();
        allOrders.forEach(orders ->{
                ordersList.add(orders);
        });
        tblOrderDetails.setItems(ordersList);
    }
    private void loaCountCards(){
        lblEmployeecount.setText(userBo.getAllEmployeeCount().toString());
        lblCustomerCount.setText(customerBo.getAllCustomerCount().toString());
        lblOrdersCount.setText(ordersBo.getAllOrdersCount().toString());
        lblSupplier.setText(supplierBo.getAllSupplierCount().toString());
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colTotal .setCellValueFactory(new PropertyValueFactory<>("total"));
        loaCountCards();
        loadTable();
        loadTimeAndDate();
    }
}
