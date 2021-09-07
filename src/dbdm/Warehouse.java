package dbdm;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Warehouse
{
    final JFrame m_frame;
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
        us_icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/USA.png")));
        uk_icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/UK.png")));
        china_icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/China.png")));
        india_icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/India.png")));

        m_frame = new JFrame();
        us = new JButton(us_icon);
        uk = new JButton(uk_icon);
        china = new JButton(china_icon);
        india = new JButton(india_icon);
        us.setBorderPainted(false);
        us.setFocusPainted(false);
        uk.setBorderPainted(false);
        uk.setFocusPainted(false);
        china.setBorderPainted(false);
        china.setFocusPainted(false);
        india.setBorderPainted(false);
        india.setFocusPainted(false);
        m_frame.setVisible(true);
        m_frame.setResizable(false);
        m_frame.setSize(400, 400);
        m_frame.setTitle("DestinationBD Manager - Warehouse");
        m_frame.setLayout(new GridLayout(2, 2));
        m_frame.setLocationRelativeTo(null);


        m_frame.add(us);
        m_frame.add(uk);
        m_frame.add(china);
        m_frame.add(india);






    }



}
