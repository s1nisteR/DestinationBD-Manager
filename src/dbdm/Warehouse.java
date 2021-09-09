package dbdm;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Warehouse
{
    final JFrame m_frame;
    private boolean isDemo;
    final JButton us;
    final JButton uk;
    final JButton china;
    final JButton india;
    final Icon us_icon;
    final Icon uk_icon;
    final Icon china_icon;
    final Icon india_icon;
    Warehouse(boolean isDemo)
    {
        this.isDemo = isDemo;
        us_icon = new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/USA.png"))).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        uk_icon = new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/UK.png"))).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        china_icon = new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/China.png"))).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        india_icon = new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/India.png"))).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));


        m_frame = new JFrame();
        us = new JButton(us_icon);
        uk = new JButton(uk_icon);
        china = new JButton(china_icon);
        india = new JButton(india_icon);
        us.setCursor(new Cursor(Cursor.HAND_CURSOR));
        uk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        china.setCursor(new Cursor(Cursor.HAND_CURSOR));
        india.setCursor(new Cursor(Cursor.HAND_CURSOR));
        us.setBorderPainted(false);
        us.setFocusPainted(false);
        uk.setBorderPainted(false);
        uk.setFocusPainted(false);
        china.setBorderPainted(false);
        china.setFocusPainted(false);
        india.setBorderPainted(false);
        india.setFocusPainted(false);
        m_frame.setResizable(false);
        m_frame.setSize(400, 400);
        m_frame.setTitle("DestinationBD Manager - Warehouse");
        m_frame.setLayout(new GridLayout(2, 2));
        m_frame.setLocationRelativeTo(null);
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        us.addActionListener(e -> {
            Management mag = new Management("USA", isDemo);
            m_frame.dispose();
        });

        uk.addActionListener(e -> {
            Management mag = new Management("UK", isDemo);
            m_frame.dispose();
        });

        china.addActionListener(e -> {
            Management mag = new Management("China", isDemo);
            m_frame.dispose();
        });

        india.addActionListener(e -> {
            Management mag = new Management("India", isDemo);
            m_frame.dispose();
        });

        m_frame.add(us);
        m_frame.add(uk);
        m_frame.add(china);
        m_frame.add(india);


        m_frame.setVisible(true);



    }



}
