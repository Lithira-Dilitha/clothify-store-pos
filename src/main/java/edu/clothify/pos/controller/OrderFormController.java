package edu.clothify.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.custom.CustomerBo;
import edu.clothify.pos.bo.item.ItemBo;
import edu.clothify.pos.bo.orderdetails.OrderDetailsBo;
import edu.clothify.pos.bo.orders.OrdersBo;
import edu.clothify.pos.dto.*;
import edu.clothify.pos.utill.BoType;
import jakarta.persistence.criteria.Order;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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
    public JFXTextField txtQtyformCustomer;
    public TableColumn colTotal;
    public Label lblTime;

    CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);
    ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);
    OrdersBo ordersBo = BoFactory.getInstance().getBo(BoType.ORDERS);
    OrderDetailsBo orderDetailsBo =BoFactory.getInstance().getBo(BoType.ORDER_DETAILS);
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

    ObservableList<CartTable> cartList =FXCollections.observableArrayList();
    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        String itemCode =(String) cmbItemId.getValue();
        int qtyFormCustomer = Integer.parseInt(txtQtyformCustomer.getText());
        String name = lblIteamName.getText();
        Integer qty =Integer.parseInt(lblIteamQty.getText());
        String size = lblIteamSize.getText();
        double unitPrice = Double.parseDouble(lblUnitePrice.getText());
        Double total = unitPrice*qtyFormCustomer;
        CartTable cartTable = new CartTable(itemCode, name, qtyFormCustomer, size, unitPrice, total);
        if(qtyFormCustomer>qty){
           new  Alert(Alert.AlertType.WARNING,"Invalid Qty").show();
           return;
        }
        cartList.add(cartTable);
        tblCart.setItems(cartList);
        netTotalCalculate();
    }
    private void ClearTextBoxes(){
        lblUnitePrice.setText(null);
        lblIteamSize.setText(null);
        lblIteamQty.setText(null);
        lblIteamName.setText(null);
        lblCustomerEmail.setText(null);
        lblCustomerName.setText(null);
        lblNetTotal.setText(null);
        txtQtyformCustomer.setText(null);
        tblCart.setItems(null);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        ClearTextBoxes();
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(lblDate.getText());
            List<OrderDetails> orderDetailsList = new ArrayList<>();
            cartList.forEach(cartTable ->{
                cartTable.getItemCode();
                cartTable.getQty();
                orderDetailsList.add(new OrderDetails(
                        lblOrderId.getText(),
                        cartTable.getItemCode(),
                        cartTable.getQty()));
            });
            Orders orders = new Orders(lblOrderId.getText(), cmbCustId.getValue().toString(), date, orderDetailsList);
            boolean isAdd = ordersBo.placeOrder(orders);
            if(isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Order Placed !").show();
                ClearTextBoxes();
                setOrderId();
            }else{
                new Alert(Alert.AlertType.ERROR,"Order Not Placed !").show();
            }
            System.out.println(orders);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

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
    private void loadTimeAndDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,e->{
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
    private void setOrderId(){
        lblOrderId.setText(ordersBo.getOrderId());
    }
    private void netTotalCalculate(){
        double total =0;
        for(CartTable cartObject : cartList){
            total += cartObject.getTotal();
        }
        lblNetTotal.setText(String.valueOf(total)+"/=");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        cloUnitPrice.setCellValueFactory(new PropertyValueFactory<>("uniPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        loadCustomerId();
        loadItemId();
        loadTimeAndDate();
        setOrderId();
        cmbCustId.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setCustomerDataForLbls((String) newValue);
        });
        cmbItemId.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setItemDetailsLbls((String) newValue);
        });
    }
}
