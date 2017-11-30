package UI.Checkout;
/**
 * Create By Hao Li at Nov. 7th
 */
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Stack;

public class Orders {
    Place_Order place_order = new Place_Order();
    //Item_collection item_collection = new Item_collection();
    Payment_type payment_type = new Payment_type();
    Stage go_orders = new Stage();
    public Button checkout = new Button("CHECK OUT");
    CreditCardPayment ccp = new CreditCardPayment();
    //example stacks
    Stack<Label> ii;
    Stack<Label> tt;

    public Button checkout(){
        checkout.setPrefSize(180,60);
        checkout.getStylesheets().add("css/checkout.css");
        //for check out button
        checkout.setOnAction(e->{
            orders();
            place_order.UpdateChose(setIi());
            place_order.Updatenumber(settt());
        });
        //set up Modality
        go_orders.initModality(Modality.APPLICATION_MODAL);
        return checkout;
    }
    public void orders(){
        BorderPane orderPane = new BorderPane();
        orderPane.getStylesheets().add("css/order.css");
        orderPane.setStyle("-fx-background-color: cornsilk");
        orderPane.setPrefSize(650,400);
        go_orders.setTitle("PLACE YOUR ORDER");


        orderPane.setCenter(place_order.center_view());
        orderPane.setBottom(place_order.bottom_buttons());
        /*set listeners*/
        //for place orders listeners
        place_order.quit.setOnAction(e->{
            //go_orders.initModality(Modality.NONE);
            go_orders.close();
        });
        place_order.p_order.setOnAction(e->{
            orderPane.setCenter(payment_type.payment());
            orderPane.setBottom(null);

        });
        //for payment_type listeners
        payment_type.p_now.setOnAction(e->{
            if (payment_type.cash.isSelected()||payment_type.debit.isSelected()||payment_type.credit.isSelected()){
                payment_type.credit.setSelected(false);
                payment_type.cash.setSelected(false);
                payment_type.debit.setSelected(false);
                payment_type.p_now.setDisable(false);
                payment_type.cash.setDisable(false);
                payment_type.credit.setDisable(false);
                payment_type.debit.setDisable(false);
                // if pay by credit card through project
                orderPane.setCenter(ccp.pane());
                //go_orders.close();
            }
            else{
                payment_type.error.setText("Please choose the payment\n type first");
                payment_type.error.setTextFill(Color.RED);
            }

        });
        payment_type.p_later.setOnAction(e->{
            if (payment_type.cash.isSelected()||payment_type.debit.isSelected()||payment_type.credit.isSelected()){
                payment_type.cash.setSelected(false);
                payment_type.credit.setSelected(false);
                payment_type.debit.setSelected(false);
                payment_type.p_now.setDisable(false);
                payment_type.cash.setDisable(false);
                payment_type.credit.setDisable(false);
                payment_type.debit.setDisable(false);
                go_orders.close();
            }
            else{
                payment_type.error.setText("Please choose the payment\n type first");
                payment_type.error.setTextFill(Color.RED);
            }
        });
        //if pay by cash
        payment_type.cash.setOnAction(e-> {
            payment_type.error.setText(null);
            if (payment_type.cash.isSelected()){
                payment_type.p_now.setDisable(true);
                payment_type.debit.setSelected(false);
                payment_type.credit.setSelected(false);
                payment_type.debit.setDisable(true);
                payment_type.credit.setDisable(true);
            }
            else {
                payment_type.p_now.setDisable(false);
                payment_type.debit.setDisable(false);
                payment_type.credit.setDisable(false);
            }
        });
        //if pay by credit card
        payment_type.credit.setOnAction(e -> {
            payment_type.error.setText(null);
            if (payment_type.credit.isSelected()){
                //payment_type.p_now.setDisable(true);
                payment_type.debit.setSelected(false);
                payment_type.cash.setSelected(false);
                payment_type.debit.setDisable(true);
                payment_type.cash.setDisable(true);
            }
            else {
                //payment_type.p_now.setDisable(false);
                payment_type.debit.setDisable(false);
                payment_type.cash.setDisable(false);
            }
        });
        // if pay by debit card
        payment_type.debit.setOnAction(e -> {
            payment_type.error.setText(null);
            if (payment_type.debit.isSelected()){
                payment_type.p_now.setDisable(true);
                payment_type.cash.setSelected(false);
                payment_type.credit.setSelected(false);
                payment_type.cash.setDisable(true);
                payment_type.credit.setDisable(true);
            }
            else {
                payment_type.p_now.setDisable(false);
                payment_type.cash.setDisable(false);
                payment_type.credit.setDisable(false);
            }
        });
        Scene scene = new Scene(orderPane);
        go_orders.setScene(scene);



        go_orders.show();
    }
    
    
    /**
     Example code for how to change and use update listviews, need to replace by real data from data base
     **/
    Stack setIi(){
        ii = new Stack<>();
        Label temp1 = new Label("Middle Size Fries ");
        Label temp2= new Label("Teen Burger ");
        Label temp3 = new Label("Veggie Deluxe ");
        Label temp4 = new Label("Check Strips");
        Label temp5 = new Label("Dipping Sauce ");
        Label temp6 = new Label("Coke Zero ");
        ii.push(temp1);
        ii.push(temp2);
        ii.push(temp3);
        ii.push(temp4);
        ii.push(temp5);
        ii.push(temp6);

        return ii;
    }

    Stack settt(){
        tt = new Stack<>();
        Label temp1 = new Label("X 1");
        Label temp2 = new Label("X 2");
        Label temp3 = new Label("X 1");
        Label temp4 = new Label("X 4");
        Label temp5 = new Label("X 1");
        Label temp6 = new Label("X 4");
        tt.push(temp1);
        tt.push(temp2);
        tt.push(temp3);
        tt.push(temp4);
        tt.push(temp5);
        tt.push(temp6);
        return tt;
    }
}
