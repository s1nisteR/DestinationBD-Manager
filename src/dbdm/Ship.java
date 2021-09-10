package dbdm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ship
{

    final JFrame m_frame;
    final JLabel l_boxes;
    final JLabel l_weight;
    final JLabel l_country;
    final JLabel l_city;
    final JLabel l_addressone;
    final JLabel l_addresstwo;
    final JLabel l_zip;
    final JLabel l_delivery;
    final JTextField boxes;
    final JTextField weight;
    final JTextField country;
    final JTextField city;
    final JTextField address;
    final JTextField address2;
    final JTextField zip;
    final JComboBox delivery;
    final JButton calculate;
    final JButton ship;
    final JLabel price;


    final DefaultTableModel model;

    Ship(DefaultTableModel model)
    {
        this.model = model;
        m_frame = new JFrame("Consolidate And Ship");

        int totalParcels = 0;
        float totalWeight = 0;


        for(int i = 0; i < this.model.getRowCount(); i++)
        {
            if(this.model.getValueAt(i, 0) == null)
            {
                continue;
            }
            totalParcels++;
            totalWeight = totalWeight + Float.parseFloat((String) this.model.getValueAt(i, 4));
        }

        final float tempTotalWeight = totalWeight;
        l_boxes= new JLabel("Total Boxes to Consolidate: ");
        l_boxes.setBounds(20, 20, 190, 20);
        l_weight = new JLabel("Total Weight: ");
        l_weight.setBounds(20, 45, 190, 20);
        l_country = new JLabel("Destination Country: ");
        l_country.setBounds(20, 70, 190, 20);
        l_city = new JLabel("Destination City: ");
        l_city.setBounds(20, 95, 190, 20);
        l_addressone = new JLabel("Address Line 1: ");
        l_addressone.setBounds(20, 120, 190, 20);
        l_addresstwo = new JLabel("Address Line 2: ");
        l_addresstwo.setBounds(20, 145, 190, 20);
        l_zip = new JLabel("Destination Zip Code: ");
        l_zip.setBounds(20, 170, 190, 20);
        l_delivery = new JLabel("Delivery Agent: ");
        l_delivery.setBounds(20, 195, 190, 20);
        boxes = new JTextField();
        boxes.setBounds(190, 20, 190, 20);
        boxes.setEnabled(false);
        boxes.setText(String.format("%d", totalParcels));
        weight = new JTextField();
        weight.setBounds(190, 45, 190, 20);
        weight.setEnabled(false);
        weight.setText(String.format("%.3f", totalWeight));
        country = new JTextField();
        country.setBounds(190, 70, 190, 20);
        city = new JTextField();
        city.setBounds(190, 95, 190, 20);
        address = new JTextField();
        address.setBounds(190, 120, 190, 20);
        address2 = new JTextField();
        address2.setBounds(190, 145, 190, 20);
        zip = new JTextField();
        zip.setBounds(190, 170, 190, 20);
        delivery = new JComboBox<String>(new String[] {"DHL", "FedEx", "UPS"});
        delivery.setBounds(190, 195, 190, 20);
        calculate = new JButton("Calculate");
        calculate.setBounds(190, 230, 90, 20);
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = "<html>Shipping Charge:<br/>" + calculate(String.valueOf(delivery.getSelectedItem()), tempTotalWeight) + "</html>";
                price.setText(result);
                ship.setEnabled(true);

            }
        });
        ship = new JButton("Ship");
        ship.setBounds(290, 230, 90, 20);
        ship.setEnabled(false);
        price = new JLabel("<html>Shipping Charge:<br/></html>", SwingConstants.CENTER);
        price.setFont(new Font("Serif", Font.BOLD, 19));
        price.setBounds(380, 60, 300, 120);

        m_frame.add(l_boxes);
        m_frame.add(l_weight);
        m_frame.add(l_country);
        m_frame.add(l_city);
        m_frame.add(l_addressone);
        m_frame.add(l_addresstwo);
        m_frame.add(l_delivery);
        m_frame.add(l_zip);
        m_frame.add(boxes);
        m_frame.add(weight);
        m_frame.add(country);
        m_frame.add(city);
        m_frame.add(address);
        m_frame.add(address2);
        m_frame.add(zip);
        m_frame.add(delivery);
        m_frame.add(calculate);
        m_frame.add(ship);
        m_frame.add(price);
        m_frame.setSize(700, 320);
        m_frame.setResizable(false);
        m_frame.setLayout(null);
        m_frame.setLocationRelativeTo(null);
        m_frame.setVisible(true);
    }

    private String calculate(String delivery, float totalWeight)
    {
        float result;
        String fs;
        if(totalWeight == 0.0f)
        {
            return "";
        }
        if(delivery.equals("DHL"))
        {
            result = 21.0f + (totalWeight * 2.5f);
        }
        else if(delivery.equals("FedEx"))
        {
            result = 17.0f + (totalWeight);
        }
        else
        {
            result = 22.0f + (totalWeight * 1.5f);
        }
        fs = String.format("$%.2f", result);
        return fs;
    }

}
