package com.example.chandan.coffyorder;

import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
//   here quantity is declared as globle variable

    //   globle variable is accessed     any where in the programm


    int quantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /*
          this method is called when the order button is clicked
    */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        EditText mobileNo = (EditText) findViewById(R.id.mobile_no);
        String mobile = mobileNo.getText().toString();

        EditText emailIdField = (EditText) findViewById(R.id.email_field);
        String emailId = emailIdField.getText().toString();

        EditText addressField = (EditText) findViewById(R.id.address_field);
        String address = addressField.getText().toString();


        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox extraChocolateCreamCheckBox = (CheckBox) findViewById(R.id.extra_chocolate_checkbox);
        boolean hasExtraChocolate = extraChocolateCreamCheckBox.isChecked();


        // display(price);
        int price = calculatePrice(hasWhippedCream, hasExtraChocolate);
        String priceMessage = createOrderSummary(address, emailId, mobile, name, price, hasWhippedCream, hasExtraChocolate);
        displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

    }


    /*
           this method is called when pluse(+) button is clicked
    * */
    public void increment(View view) {
        if (quantity == 25) {
            Toast.makeText(this, "you can not have more than 25 cups ", Toast.LENGTH_LONG).show();
            return;
        }
        quantity++;
        display(quantity);




     /*
           this method is called when minus (-) button is clicked
     */

    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "you can not have  less than 1 cups ", Toast.LENGTH_LONG).show();

            return;
        }
        quantity--;
        display(quantity);

    }

    private int calculatePrice(boolean addWhippedCream, boolean addExtrachocolate) {
        int basePrice = 5;
        if (addWhippedCream) {
            basePrice = basePrice + 1;

        }
        if (addExtrachocolate) {
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;
    }


    private String createOrderSummary(String address, String emailid, String mobile, String name, int price, boolean addWhippedCream, boolean addExtraChocolate) {

        String priceMessage = "Name: " + name + ".";
        priceMessage += "\nMobile no: " + mobile + ".";
        priceMessage += "\nEmail: " + emailid + ".";
        priceMessage += "\nAddress: " + address + ".";
        priceMessage += "\nAdd whipprd cream? " + addWhippedCream;
        priceMessage += "\nAdd extra chocolate? " + addExtraChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you";
        return priceMessage;

    }

    /*
    this mathod displays  the given quantity on the screen
    */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);


    }
    /*
      this method displays the price of quntity on the screen
       */

//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(
//                R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//
//    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);


    }
}




