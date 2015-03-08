package team21;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class tempGUI
{

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    tempGUI window = new tempGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public tempGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);//Allows the Look and Feel to change the window frame GUI

        try
        {
          //Set the Look and Feel to Magellan
          UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel");
          }catch(Exception e){System.out.println("Substance-Magellan failed to initialize");}

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 585, 477);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnGoToLocations = new JButton("=");
        btnGoToLocations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnGoToLocations.setBounds(10, 11, 41, 23);
        frame.getContentPane().add(btnGoToLocations);

        JLabel lblLocation = new JLabel(" London, Ontario");
        lblLocation.setFont(new Font("Century Gothic", Font.PLAIN, 21));
        lblLocation.setBounds(85, 35, 231, 50);
        frame.getContentPane().add(lblLocation);

        JSlider sliderShortTerm = new JSlider();
        sliderShortTerm.setPaintLabels(true);
        sliderShortTerm.setBounds(0, 211, 579, 26);
        frame.getContentPane().add(sliderShortTerm);

        JLabel label = new JLabel("-17");
        label.setFont(new Font("Century Gothic", Font.PLAIN, 43));
        label.setBounds(298, 35, 114, 81);
        frame.getContentPane().add(label);

        JLabel lblHigh = new JLabel("10");
        lblHigh.setBounds(308, 127, 46, 14);
        frame.getContentPane().add(lblHigh);

        JLabel lblLow = new JLabel("-19");
        lblLow.setBounds(366, 127, 46, 14);
        frame.getContentPane().add(lblLow);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 187, 579, 50);
        frame.getContentPane().add(separator_1);

        JPanel panel = new JPanel();

        panel.setBounds(0, 269, 569, 169);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JPanel panel_10 = new JPanel();
        panel_10.setBounds(451, 11, 96, 147);
        panel.add(panel_10);
        panel_10.setLayout(null);

        JLabel lblFriday = new JLabel("Friday");
        lblFriday.setBounds(33, 11, 30, 14);
        panel_10.add(lblFriday);

        JLabel label_7 = new JLabel("High : 5");
        label_7.setBounds(22, 74, 46, 14);
        panel_10.add(label_7);

        JLabel label_8 = new JLabel("Low : -4");
        label_8.setBounds(22, 99, 46, 14);
        panel_10.add(label_8);

        JLabel label_13 = new JLabel("10");
        label_13.setBounds(43, 36, 12, 14);
        panel_10.add(label_13);

        JPanel panel_9 = new JPanel();
        panel_9.setBounds(345, 11, 96, 147);
        panel.add(panel_9);
        panel_9.setLayout(null);

        JLabel lblThursday = new JLabel("Thursday");
        lblThursday.setBounds(26, 11, 45, 14);
        panel_9.add(lblThursday);

        JLabel label_5 = new JLabel("High : 5");
        label_5.setBounds(25, 74, 46, 14);
        panel_9.add(label_5);

        JLabel label_6 = new JLabel("Low : -4");
        label_6.setBounds(25, 99, 46, 14);
        panel_9.add(label_6);

        JLabel label_12 = new JLabel("10");
        label_12.setBounds(36, 36, 12, 14);
        panel_9.add(label_12);

        JPanel panel_8 = new JPanel();
        panel_8.setBounds(239, 11, 96, 147);
        panel.add(panel_8);
        panel_8.setLayout(null);

        JLabel lblWednessday = new JLabel("Wednesday");
        lblWednessday.setBounds(19, 11, 57, 14);
        panel_8.add(lblWednessday);

        JLabel label_3 = new JLabel("High : 5");
        label_3.setBounds(30, 73, 46, 14);
        panel_8.add(label_3);

        JLabel label_4 = new JLabel("Low : -4");
        label_4.setBounds(30, 98, 46, 14);
        panel_8.add(label_4);

        JLabel label_11 = new JLabel("10");
        label_11.setBounds(41, 36, 12, 14);
        panel_8.add(label_11);

        JPanel panel_7 = new JPanel();
        panel_7.setBounds(133, 11, 96, 147);
        panel.add(panel_7);
        panel_7.setLayout(null);

        JLabel lblTuesday = new JLabel("Tuesday");
        lblTuesday.setBounds(27, 11, 41, 14);
        panel_7.add(lblTuesday);

        JLabel label_1 = new JLabel("High : 5");
        label_1.setBounds(22, 71, 46, 14);
        panel_7.add(label_1);

        JLabel label_2 = new JLabel("Low : -4");
        label_2.setBounds(22, 96, 46, 14);
        panel_7.add(label_2);

        JLabel label_10 = new JLabel("10");
        label_10.setBounds(37, 36, 12, 14);
        panel_7.add(label_10);

        JPanel panel_6 = new JPanel();
        panel_6.setBounds(27, 11, 96, 147);
        panel.add(panel_6);
        panel_6.setLayout(null);

        JLabel lblHigh_1 = new JLabel("High : 5");
        lblHigh_1.setBounds(26, 70, 46, 14);
        panel_6.add(lblHigh_1);

        JLabel lblMonday = new JLabel("Monday");
        lblMonday.setBounds(26, 11, 38, 14);
        lblMonday.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        panel_6.add(lblMonday);

        JLabel lblNewLabel = new JLabel("Low : -4");
        lblNewLabel.setBounds(26, 95, 46, 14);
        panel_6.add(lblNewLabel);

        JLabel label_9 = new JLabel("10");
        label_9.setBounds(36, 36, 46, 14);
        panel_6.add(label_9);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 258, 579, 23);
        frame.getContentPane().add(separator);
    }
}
