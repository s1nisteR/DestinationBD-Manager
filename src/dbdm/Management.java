package dbdm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.io.File;
import java.util.Scanner;


public class Management
{
    final JToolBar toolbar;
    final DefaultTableModel model;
    final JMenu file;
    final JTable table;
    final boolean isDemo;
    final String warehouse;
    final File usa;
    final File uk;
    final File india;
    final File china;
    final JMenuItem add;
    final JMenuItem save;
    final JButton t_add;
    final JButton t_remove;
    final JButton t_ship;
    final JButton t_info;
    final JMenuItem remove;
    final JMenuItem exit;
    //final JMenuItem moveUp;
    //final JMenuItem moveDown;
    final JMenuItem ship;
    final JMenuItem warehouseSelection;
    final JMenuItem about;
    final JMenu help;
    //final JMenu edit;
    final JFrame m_frame;
    final JMenuBar menubar;
    Management(String warehouse, boolean isDemo)
    {
        this.isDemo = isDemo;
        this.warehouse = warehouse;

        usa = new File("usa.dbdm");
        uk = new File("uk.dbdm");
        india = new File("india.dbdm");
        china = new File("china.dbdm");

        t_add = new JButton(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/add.png"))).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        t_add.setBorderPainted(true);
        t_add.setFocusPainted(false);
        t_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.insertRow(model.getRowCount(), new Object[] {});
            }
        });
        t_remove = new JButton(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/remove.png"))).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        t_remove.setFocusPainted(false);
        t_remove.setBorderPainted(true);
        t_remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeRow(model.getRowCount() - 1);
            }
        });
        //t_up = new JButton(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/up.png"))).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        //t_up.setFocusPainted(false);
        //t_up.setBorderPainted(true);
        //t_down = new JButton(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/down.png"))).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        //t_down.setFocusPainted(false);
        //t_down.setBorderPainted(true);
        t_ship = new JButton(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/ship.png"))).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        t_ship.setFocusPainted(false);
        t_ship.setBorderPainted(true);
        t_ship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ship sh = new Ship(model);
            }
        });
        t_info = new JButton(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/info.png"))).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        t_info.setFocusPainted(false);
        t_info.setBorderPainted(true);
        m_frame = new JFrame(warehouse + " - Management");
        menubar = new JMenuBar();
        file = new JMenu("File");
        add = new JMenuItem("Add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.insertRow(model.getRowCount(), new Object[] {});
            }
        });
        remove = new JMenuItem("Remove");
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeRow(model.getRowCount() - 1);
            }
        });
        save = new JMenuItem("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData(warehouse);
            }
        });
        ship = new JMenuItem("Ship");
        ship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ship sh = new Ship(model);
            }
        });
        warehouseSelection = new JMenuItem("Warehouse Selection");
        warehouseSelection.addActionListener(e -> {
            Warehouse warh = new Warehouse(isDemo);
            m_frame.dispose();
        });
        exit = new JMenuItem("Exit");
        exit.addActionListener(e -> {
            m_frame.dispose();
            System.exit(0);
        });
        file.add(add);
        file.add(remove);
        file.add(save);
        file.addSeparator();
        file.add(ship);
        file.add(warehouseSelection);
        file.addSeparator();
        file.add(exit);
        //edit = new JMenu("Edit");
        //moveUp = new JMenuItem("Move Up");
        //moveDown = new JMenuItem("Move Down");
        //edit.add(moveUp);
        //add(moveDown);
        help = new JMenu("Help");
        about = new JMenuItem("About");

        help.add(about);
        menubar.add(file);
        //menubar.add(edit);
        menubar.add(help);
        toolbar = new JToolBar();
        toolbar.setRollover(true);
        toolbar.setFloatable(false);
        toolbar.add(t_add);
        toolbar.addSeparator(new Dimension(5, 0));
        toolbar.add(t_remove);
        toolbar.addSeparator(new Dimension(5, 0));
        //toolbar.add(t_up);
        toolbar.addSeparator(new Dimension(5, 0));
        //toolbar.add(t_down);
        toolbar.addSeparator(new Dimension(5, 0));
        toolbar.add(t_ship);
        toolbar.addSeparator(new Dimension(5, 0));
        toolbar.add(t_info);
        m_frame.getContentPane().add(toolbar, BorderLayout.NORTH);
        m_frame.setJMenuBar(menubar);



        //Table goes here
        model = getData(this.isDemo, this.warehouse);
        table = new JTable(model);

        JScrollPane sp =new JScrollPane(table);
        m_frame.add(sp);




        m_frame.setSize(800, 600);
        m_frame.setLocationRelativeTo(null);
        m_frame.setVisible(true);
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void saveData(String warehouse)
    {
        if(!isDemo) {
            try {
                if (warehouse.equals("USA")) {

                    FileWriter writer = new FileWriter("usa.dbdm");
                    for (int row = 0; row < model.getRowCount(); row++) {
                        for (int col = 0; col < model.getColumnCount() - 1; col++) {
                            writer.write(model.getValueAt(row, col) + ",");
                        }
                        writer.write(model.getValueAt(row, 5) + "\n");
                    }
                    writer.close();
                } else if (warehouse.equals("UK")) {

                    FileWriter writer = new FileWriter("uk.dbdm");
                    for (int row = 0; row < model.getRowCount(); row++) {
                        for (int col = 0; col < model.getColumnCount() - 1; col++) {
                            writer.write(model.getValueAt(row, col) + ",");
                        }
                        writer.write(model.getValueAt(row, 5) + "\n");
                    }
                    writer.close();
                } else if (warehouse.equals("India")) {

                    FileWriter writer = new FileWriter("india.dbdm");
                    for (int row = 0; row < model.getRowCount(); row++) {
                        for (int col = 0; col < model.getColumnCount() - 1; col++) {
                            writer.write(model.getValueAt(row, col) + ",");
                        }
                        writer.write(model.getValueAt(row, 5) + "\n");
                    }
                    writer.close();
                } else if (warehouse.equals("China")) {

                    FileWriter writer = new FileWriter("china.dbdm");
                    for (int row = 0; row < model.getRowCount(); row++) {
                        for (int col = 0; col < model.getColumnCount() - 1; col++) {
                            writer.write(model.getValueAt(row, col) + ",");
                        }
                        writer.write(model.getValueAt(row, 5) + "\n");
                    }
                    writer.close();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(m_frame, "There was a fatal error when trying to process the stored files!", "Error", JOptionPane.ERROR_MESSAGE);
                m_frame.dispose();
            }
        }
    }

    private DefaultTableModel getData(boolean isDemo, String warehouse)
    {
        DefaultTableModel model = new DefaultTableModel();
        int i = 0;
        model.addColumn("Parcel Number");
        model.addColumn("Product Name");
        model.addColumn("Type/Shade");
        model.addColumn("Quantity");
        model.addColumn("Weight/kg");
        model.addColumn("Customer Name");

        if(isDemo)
        {
            for(i = 0; i < 300; ++i)
            {
                model.insertRow(i, new Object[] {"Demo", "Demo","Demo","Demo","0.1","Demo"});
            }
            return model;
        }
        else
        {
            try
            {
                boolean result;
                String line = "";
                Scanner inp;
                String[] splitString;
                //Auto creates the files IF they DON'T exist
                result = usa.createNewFile();
                result = uk.createNewFile();
                result = china.createNewFile();
                result = india.createNewFile();

                if(warehouse.equals("USA"))
                {
                   inp = new Scanner(usa);
                    while(inp.hasNextLine())
                    {
                        line = inp.nextLine();
                        splitString = line.split(",");
                        model.insertRow(i, new Object[] {splitString[0], splitString[1], splitString[2], splitString[3], splitString[4], splitString[5]});
                        i++;
                    }
                }
                else if(warehouse.equals("UK"))
                {
                    inp = new Scanner(uk);
                    while(inp.hasNextLine())
                    {
                        line = inp.nextLine();
                        splitString = line.split(",");
                        model.insertRow(i, new Object[] {splitString[0], splitString[1], splitString[2], splitString[3], splitString[4], splitString[5]});
                        i++;
                    }
                }
                else if(warehouse.equals("India"))
                {
                    inp = new Scanner(india);
                    while(inp.hasNextLine())
                    {
                        line = inp.nextLine();
                        splitString = line.split(",");
                        model.insertRow(i, new Object[] {splitString[0], splitString[1], splitString[2], splitString[3], splitString[4], splitString[5]});
                        i++;
                    }
                }
                else if(warehouse.equals("China"))
                {
                    inp = new Scanner(china);
                    while(inp.hasNextLine())
                    {
                        line = inp.nextLine();
                        splitString = line.split(",");
                        model.insertRow(i, new Object[] {splitString[0], splitString[1], splitString[2], splitString[3], splitString[4], splitString[5]});
                        i++;
                    }
                }


            }catch(IOException e)
            {
                JOptionPane.showMessageDialog(m_frame, "There was a fatal error when trying to process the stored files!", "Error" , JOptionPane.ERROR_MESSAGE);
                m_frame.dispose();
            }
            return model;
        }
    }
}
