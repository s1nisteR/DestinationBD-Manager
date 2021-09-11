package dbdm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login implements ActionListener
{
    private Connection m_conn;

    final JFrame m_frame;
    final JButton login;
    final JButton demo;
    final JTextField username;
    final JPasswordField password;
    final JLabel username_label;
    final JLabel password_label;
    final JLabel status;
    Login()
    {
        m_frame = new JFrame();
        login = new JButton("Login");
        login.setBounds(280, 126, 80, 30);
        login.setFocusPainted(false);
        login.setBorderPainted(false);
        demo = new JButton("Demo");
        demo.setBounds(195, 126, 80, 30);
        demo.setFocusPainted(false);
        demo.setBorderPainted(false);
        demo.setVisible(false);
        demo.addActionListener(e -> {
            Warehouse warehouse = new Warehouse(true);
            m_frame.dispose();
        });
        username = new JTextField("");
        username.setBackground(new Color(36, 137, 171));
        username.setForeground(new Color(255, 255, 255));
        username.setBounds(160, 52, 200, 20);
        password = new JPasswordField("");
        password.setBackground(new Color(36, 137, 171));
        password.setForeground(new Color(255, 255, 255));
        password.setBounds(160, 92, 200, 20);
        username_label = new JLabel("Username: ");
        username_label.setForeground(new Color(255, 255, 255));
        username_label.setBounds(90, 20, 80, 80);
        password_label = new JLabel("Password: ");
        password_label.setBounds(91, 60, 80, 80);
        password_label.setForeground(new Color(255, 255, 255));
        status = new JLabel("Connecting...");
        status.setBounds(10, 190, 250, 20);
        status.setForeground(new Color(0, 0, 255));
        login.setEnabled(false);
        username.setEnabled(false);
        password.setEnabled(false);

        m_frame.setVisible(true);
        m_frame.setResizable(false);
        m_frame.setSize(new Dimension(500, 250));
        m_frame.getContentPane().setBackground(new Color(36, 137, 171));
        m_frame.setTitle("DestinationBD Manager - Login");
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_frame.setLayout(null);
        m_frame.setLocationRelativeTo(null);

        m_frame.add(username_label);
        m_frame.add(username);
        m_frame.add(password_label);
        m_frame.add(password);
        m_frame.add(login);
        m_frame.add(demo);
        m_frame.add(status);

        login.addActionListener(this);

        try
        {
            //Class.forName("com.mysql.jdbc.Driver");
            //database stuff
            m_conn = DriverManager.getConnection("jdbc:mysql://104.215.145.82/mdh", "mdh", "14411441");
            login.setEnabled(true);
            status.setText("Connection Status: Active");
            status.setForeground(new Color(0, 255, 0));
            username.setEnabled(true);
            password.setEnabled(true);
        }
        catch(Exception e)
        {
            status.setForeground(new Color(255, 0, 0));
            status.setText("Connection Status: Inactive");
            demo.setVisible(true);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == login)
        {
            String user = username.getText();
            char[] pass = password.getPassword();
            if(user.equals("") || pass.length == 0)
            {
                JOptionPane.showMessageDialog(m_frame, "Fill up the username and password!");
            }
            else
            {
                if(authorize(m_conn, user, pass))
                {
                    //JOptionPane.showMessageDialog(m_frame, "Congrats!");
                    Warehouse warehouse = new Warehouse(false);
                    m_frame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(m_frame, "Username or password was incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private boolean authorize(Connection conn, String username, char[] password)
    {
        try
        {
            String sql = "SELECT count(*) FROM User WHERE username=? and password=?";
            PreparedStatement loginStatement = conn.prepareStatement(sql);
            loginStatement.setString(1, username);
            loginStatement.setString(2, String.copyValueOf(password));
            ResultSet rs = loginStatement.executeQuery();
            rs.next();
            return rs.getInt("count(*)") != 0;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(m_frame, "There was an unknown error!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


}
