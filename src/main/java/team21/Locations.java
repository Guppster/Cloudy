package team21;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Locations {

    private JFrame frame;
    private final String emptySpace = "                                                                                               ";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame.setDefaultLookAndFeelDecorated(true);//Allows the Look and Feel to change the window frame GUI

                    try
                    {
                        //Set the Look and Feel to Magellan
                        UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel");
                    }catch(Exception e){System.out.println("Substance-Magellan failed to initialize");}

                    Locations window = new Locations();

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
    public Locations() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 642, 473);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        panel.setBounds(10, 32, 603, 390);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(95, 20, 85, 23);
        panel.add(btnAdd);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(275, 20, 85, 23);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        panel.add(btnDelete);

        JButton btnMultiview = new JButton("MultiView");
        btnMultiview.setBounds(455, 20, 85, 23);
        panel.add(btnMultiview);

        final JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 54, 636, 382);
        panel.add(panel_1);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

        JButton buttonMars = new JButton(emptySpace + "Mars" + emptySpace + " ");
        panel_1.add(buttonMars);

        final Dimension d = buttonMars.getMaximumSize();

        Box.createVerticalStrut(10);

        String name = JOptionPane.showInputDialog(this, "Please enter an initial Location");

        JButton buttonInitial = new JButton(name);
        buttonInitial.setMaximumSize(d);
        panel_1.add(buttonInitial);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String tempName = JOptionPane.showInputDialog(this, "Please enter a Location Name");

                JButton buttonTemp = new JButton(tempName);
                buttonTemp.setMaximumSize(d);
                panel_1.add(buttonTemp);
                Box.createVerticalStrut(10);

                panel_1.revalidate();
                panel_1.validate();
            }
        });
    }
}
