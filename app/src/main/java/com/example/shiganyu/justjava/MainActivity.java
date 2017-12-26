package com.example.shiganyu.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocoloateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocoloateCheckbox.isChecked();

        int price = CalculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = CreateOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);

    }

    /**
     * This method creates an order summary
     *
     * @param name
     * @param price
     * @param addWhippedCream
     * @param addChocolate
     * @return order summary
     */
    private String CreateOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = getResources().getString(R.string.order_summary_name, name);
        priceMessage += "\n" + getString(R.string.order_summary_add_whipped_cream, addWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_add_chocolate, addChocolate);
        priceMessage += "\n" + getResources().getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_summary_total, price);
        priceMessage += "\n" + getResources().getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * Calculate price of the order
     *
     * @param addWhippedCream
     * @param addChocolate
     * @return total price
     */
    private int CalculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int baseprice = 5;
        if (addWhippedCream)
            baseprice += 1;
        if (addChocolate)
            baseprice += 2;
        return quantity * baseprice;
    }

    /**
     * This method displays the given text on the screen
     */
    private void displayMessage(String priceMessage) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText(number);
    }

    /**
     * This method displays the given price value on the screen
     *
     * @param number
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(
                R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void increment(View view) {
        if (quantity >= 100) {
            Toast.makeText(this, "Quantity must be less than 100", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity <= 1) {
            Toast.makeText(this, "Quantity must be more than 1", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);

    }
}
