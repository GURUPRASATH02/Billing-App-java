package restra;

import javax.swing.*;
import java.awt.print.PrinterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class restra extends JFrame {

    private JLabel customer, name, contact;
    private JTextField tfNumber, tfname, tfcontact;
    private JButton reset, print, receipt;
    private JTable foodTable, drinkTable;
    private JScrollPane scrollPane, foodScroll, drinkScroll;
    private JTextArea receiptArea;

    public restra() {
        setTitle("AB Coffee & Restaurant");
        setSize(1200, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        customer = new JLabel("Customer No : ");
        customer.setBounds(20, 50, 120, 30);
        tfNumber = new JTextField();
        tfNumber.setBounds(150, 50, 200, 30);

        name = new JLabel("Customer Name : ");
        name.setBounds(20, 100, 120, 30);
        tfname = new JTextField();
        tfname.setBounds(150, 100, 200, 30);

        contact = new JLabel("Customer Contact : ");
        contact.setBounds(20, 150, 120, 30);
        tfcontact = new JTextField();
        tfcontact.setBounds(150, 150, 200, 30);

        // Food table
        String[] foodColumns = {"Food", "Quantity", "Type"};
        Object[][] foodData = {
            {"Dosa", 0, "Normal"},
            {"Porotta", 0, "Normal"},
            {"Poori", 0, "Normal"}
        };
        foodTable = new JTable(foodData, foodColumns);
        foodScroll = new JScrollPane(foodTable);
        foodScroll.setBounds(400, 50, 350, 100);

        // Drink table
        String[] drinkColumns = {"Drink", "Quantity", "Type"};
        Object[][] drinkData = {
            {"Lemon", 0, "Normal"},
            {"Orange", 0, "Normal"},
            {"Apple", 0, "Normal"}
        };
        drinkTable = new JTable(drinkData, drinkColumns);
        drinkScroll = new JScrollPane(drinkTable);
        drinkScroll.setBounds(400, 170, 350, 100);

        receiptArea = new JTextArea();
        scrollPane = new JScrollPane(receiptArea);
        scrollPane.setBounds(800, 50, 350, 300);

        reset = new JButton("Reset");
        reset.setBounds(400, 350, 80, 30);
        reset.addActionListener(e -> {
            tfNumber.setText("");
            tfname.setText("");
            tfcontact.setText("");
            for (int i = 0; i < foodTable.getRowCount(); i++) {
                foodTable.setValueAt(0, i, 1);
                foodTable.setValueAt("Normal", i, 2);
            }
            for (int i = 0; i < drinkTable.getRowCount(); i++) {
                drinkTable.setValueAt(0, i, 1);
                drinkTable.setValueAt("Normal", i, 2);
            }
            receiptArea.setText("");
        });

        print = new JButton("Print");
        print.setBounds(600, 350, 80, 30);
        print.addActionListener(e -> {
            try {
                receiptArea.print();
            } catch (PrinterException ex) {
                System.out.print(ex.getMessage());
            }
        });

        receipt = new JButton("Receipt");
        receipt.setBounds(800, 350, 80, 30);
        receipt.addActionListener(e -> {
            String customerNo = tfNumber.getText();
            String customerName = tfname.getText();
            String customerContact = tfcontact.getText();

            Map<String, Double> normalFoodPrice = Map.of("Dosa", 50.0, "Porotta", 30.0, "Poori", 35.0);
            Map<String, Double> dietFoodPrice = Map.of("Dosa", 40.0, "Porotta", 25.0, "Poori", 30.0);
            Map<String, Double> normalDrinkPrice = Map.of("Lemon", 20.0, "Orange", 25.0, "Apple", 30.0);
            Map<String, Double> dietDrinkPrice = Map.of("Lemon", 15.0, "Orange", 20.0, "Apple", 25.0);

            double total = 0;
            receiptArea.setText("********** AB Coffee & Restaurant **********\n");
            receiptArea.append("Date/Time       : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n");
            receiptArea.append("Customer No     : " + customerNo + "\n");
            receiptArea.append("Customer Name   : " + customerName + "\n");
            receiptArea.append("Contact         : " + customerContact + "\n");
            receiptArea.append("-------------------------------------------\n");

            for (int i = 0; i < foodTable.getRowCount(); i++) {
                int qty = Integer.parseInt(foodTable.getValueAt(i, 1).toString());
                String type = foodTable.getValueAt(i, 2).toString();
                if (qty > 0) {
                    String item = foodTable.getValueAt(i, 0).toString();
                    double price = type.equals("Normal") ? normalFoodPrice.get(item) : dietFoodPrice.get(item);
                    total += price * qty;
                    receiptArea.append(String.format("%-15s x%d (%s) = ₹%.2f\n", item, qty, type, price * qty));
                }
            }

            for (int i = 0; i < drinkTable.getRowCount(); i++) {
                int qty = Integer.parseInt(drinkTable.getValueAt(i, 1).toString());
                String type = drinkTable.getValueAt(i, 2).toString();
                if (qty > 0) {
                    String item = drinkTable.getValueAt(i, 0).toString();
                    double price = type.equals("Normal") ? normalDrinkPrice.get(item) : dietDrinkPrice.get(item);
                    total += price * qty;
                    receiptArea.append(String.format("%-15s x%d (%s) = ₹%.2f\n", item, qty, type, price * qty));
                }
            }

            double discount = total > 100 ? total * 0.10 : 0;
            double tax = total * 0.05;
            double finalAmount = total - discount + tax;

            receiptArea.append("-------------------------------------------\n");
            receiptArea.append(String.format("Subtotal        : ₹%.2f\n", total));
            receiptArea.append(String.format("Discount        : ₹%.2f\n", discount));
            receiptArea.append(String.format("Tax (5%%)        : ₹%.2f\n", tax));
            receiptArea.append(String.format("Total Amount    : ₹%.2f\n", finalAmount));
            receiptArea.append("*******************************************\n");
            receiptArea.append("Thank you! Visit Again!\n");
        });

        add(customer); add(tfNumber);
        add(name); add(tfname);
        add(contact); add(tfcontact);
        add(foodScroll); add(drinkScroll);
        add(reset); add(print); add(receipt);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new restra();
    }
}