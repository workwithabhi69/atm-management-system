package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener {

    String pin;
    JButton button;

    mini(String pin){
        this.pin = pin;

        getContentPane().setBackground(new Color(255,204,204));
        setSize(400,600);
        setLocation(20,20);
        setLayout(null);

        JLabel label1 = new JLabel();
        label1.setBounds(20,140,400,200);
        add(label1);

        JLabel label2 = new JLabel("Mini Statement");
        label2.setFont(new Font("System", Font.BOLD,15));
        label2.setBounds(150,20,200,20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20,80,300,20);
        add(label3);

        JLabel label4 = new JLabel();
        label4.setBounds(20,400,300,20);
        add(label4);

        // 🔹 Fetch Card Number
        try{
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery(
                    "SELECT * FROM login WHERE pin = '"+pin+"'"
            );

            while (rs.next()){
                String card = rs.getString("card_number");
                label3.setText("Card Number: " +
                        card.substring(0,4) + "XXXXXXXX" + card.substring(12));
            }

        }catch (Exception e ){
            e.printStackTrace();
        }

        // 🔹 Fetch Transactions + Balance
        try{
            int balance = 0;
            Conn c = new Conn();

            ResultSet rs = c.statement.executeQuery(
                    "SELECT * FROM bank WHERE pin = '"+pin+"'"
            );

            while (rs.next()){

                label1.setText(label1.getText() + "<html>" +
                        rs.getString("date") + "&nbsp;&nbsp;&nbsp;" +
                        rs.getString("type") + "&nbsp;&nbsp;&nbsp;" +
                        rs.getString("amount") +
                        "<br><br></html>");

                if (rs.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            label4.setText("Total Balance: Rs " + balance);

        }catch (Exception e){
            e.printStackTrace();
        }

        // 🔹 Exit Button
        button = new JButton("Exit");
        button.setBounds(20,500,100,25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini("1234"); // 👉 put test PIN from DB
    }
}
