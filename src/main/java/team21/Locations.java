package team21;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;


public class Locations {

    private JFrame frame;
    private JPanel locationsPanel;
    private Dimension buttonSize;
    private static HashMap<String, JButton> dynamicButtons;
    private final String emptySpace = "                                                                                                  ";
    boolean delete = false;

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

                    dynamicButtons = new HashMap<String, JButton>();

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

        final JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(275, 20, 85, 23);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(delete)
                {
                    delete = false;
                }
                else
                {
                    delete = true;
                }
            }
        });
        panel.add(btnDelete);

        JButton btnMultiview = new JButton("MultiView");
        btnMultiview.setBounds(455, 20, 85, 23);
        panel.add(btnMultiview);

        locationsPanel = new JPanel();
        locationsPanel.setBounds(0, 54, 636, 382);
        panel.add(locationsPanel);
        locationsPanel.setLayout(new BoxLayout(locationsPanel, BoxLayout.Y_AXIS));

        JButton buttonMars = new JButton(emptySpace + "Mars" + emptySpace + " ");
        locationsPanel.add(buttonMars);

        buttonSize = buttonMars.getMaximumSize();

        Box.createVerticalStrut(10);

        String name = JOptionPane.showInputDialog(this, "Please enter an initial Location");

        JButton buttonInitial = new JButton(name);
        buttonInitial.setMaximumSize(buttonSize);
        locationsPanel.add(buttonInitial);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                addButton();
                locationsPanel.revalidate();
                locationsPanel.validate();
            }
        });
    }

    public void addButton()
    {
        final String tempName = JOptionPane.showInputDialog(this, "Please enter a Location Name");

        JButton buttonTemp = new JButton(tempName);
        buttonTemp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(delete)
                {
                    removeButton(tempName);
                    System.out.println("Removing " + tempName);
                }
                else
                {
                    System.out.println("Clicked " + tempName);
                }
            }
        });
        buttonTemp.setMaximumSize(buttonSize);
        locationsPanel.add(buttonTemp);
        dynamicButtons.put(tempName, buttonTemp);
        Box.createVerticalStrut(10);
    }


    public void removeButton(String name)
    {
        JButton b = dynamicButtons.remove(name);
        locationsPanel.remove(b);
        locationsPanel.validate();
        delete = false;
    }
}
