package team21.Cloudy;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author: Gurpreet
 */
public class Main
{
    private static LocationList locations;
    private static Configuration config;
    private static NetworkController netController;
    private static Location currentLocation;

    private static JPanel locationsPanel;
    private static Dimension buttonSize;
    private static HashMap<String, JButton> dynamicButtons;
    private static boolean problem = false;
    private static boolean displayable = false;

    private static JFrame frameLocations;

    private static JLabel lblFridayHigh;
    private static JLabel lblFridayTemp;
    private static JLabel lblFridayLow;
    private static JLabel lblFridaySummery;
    private static JLabel imgFriday;
    private static JLabel lblFriday;

    private static JLabel lblMondayHigh;
    private static JLabel lblMondayLow;
    private static JLabel lblMondayTemp;
    private static JLabel lblMondaySummery;
    private static JLabel imgMonday;
    private static JLabel lblMonday;

    private static JLabel lblTuesdayHigh;
    private static JLabel lblTuesdayTemp;
    private static JLabel lblTuesdayLow;
    private static JLabel lblTuesdaySummery;
    private static JLabel imgTuesday;
    private static JLabel lblTuesday;

    private static JLabel lblWednesdayHigh;
    private static JLabel lblWednesdayTemp;
    private static JLabel lblWednesdayLow;
    private static JLabel lblWednessdaySummery;
    private static JLabel imgWednesday;
    private static JLabel lblWednesday;

    private static JLabel lblThursdayHigh;
    private static JLabel lblThursdayTemp;
    private static JLabel lblThursdayLow;
    private static JLabel lblThursdaySummery;
    private static JLabel imgThursday;
    private static JLabel lblThursday;

    private static JLabel lblWeathercondition;
    private static JLabel lblLocation;
    private static JLabel lblTime;
    private static JLabel lblTemp;
    private static JLabel lblHigh;
    private static JLabel lblLow;
    private static JToggleButton btnDelete;

    private static JLabel lblWindSpeedValue;
    private static JLabel lblPressureValue;
    private static JSlider sliderShortTerm;
    private static JFrame frameForecast;
    private static WaitLayerUI layerUI;
    private static JLayer<JPanel> jlayer;
    private static Location tempRegion;

    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");
    private static DateTimeFormatter STformat = DateTimeFormatter.ofPattern("hh:mm a");
    private static DateTimeFormatter LTformat = DateTimeFormatter.ofPattern("EEE MMM d");



    /**
     * *
     *
     * @param args
     */
    public static void main(String[] args)
    {
        locations = new LocationList();

        config = new Configuration(locations);
        config.save();

        locations = config.getLocations();

        netController = new NetworkController();

        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    JFrame.setDefaultLookAndFeelDecorated(true);//Allows the Look and Feel to change the window frame GUI

                    try
                    {
                        //Set the Look and Feel to Magellan
                        UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel");
                    } catch (Exception e)
                    {
                        System.out.println("Substance-Magellan failed to initialize");
                    }

                    dynamicButtons = new HashMap<String, JButton>();

                    initializeLocations();

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                config.setLocations(locations);
                config.save();
            }
        });

    }//End of main method


    /**
     * This methods updates all TermObjects in all location objects within the location list
     */
    private static void update()
    {
        for (Location region : locations.getLocationList())
        {
            netController.setLocation(region);
            if(!netController.fetch())
            {
                problem = true;
                break;
            }
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    public static void addButton()
    {
        final String tempName = CustomInputDialog.showInputDialog("Input a Location", "Which region's weather would you like to see today?", "Enter a location here");
        System.out.println("Requesting Location: " + tempName);

        //If input is empty, do nothing and exit method but update the mars location
        if(tempName == null || tempName.equals(""))
        {
            update();
            displayable = false;
            return;
        }

        tempRegion = new Location(tempName);
        locations.addRegion(tempRegion);
        currentLocation = tempRegion;

        config.setLocations(locations);
        config.save();

        layerUI.start();

        update();

        layerUI.stop();

        if(!problem && !dynamicButtons.containsKey(tempName))
        {
            JButton buttonTemp = new JButton(locations.searchList(tempName).getOfficialName());
            buttonTemp.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(btnDelete.isSelected())
                    {
                        removeButton(tempName);
                        locations.deleteRegion(locations.searchList(tempName));
                        System.out.println("Removing " + tempName);
                        reinitializeDelete();
                    }
                    else
                    {
                        System.out.println("Opening " + tempName);
                        currentLocation = locations.searchList(tempName);
                        frameLocations.setVisible(false);
                        initializeForecast();
                        frameForecast.setVisible(true);
                    }
                }
            });
            buttonTemp.setMaximumSize(buttonSize);
            buttonTemp.setFont(new Font("Century Gothic", Font.PLAIN, 25));
            locationsPanel.add(buttonTemp);
            dynamicButtons.put(tempName, buttonTemp);
            reinitializeDelete();
            displayable = true;
        }
        else
        {
            JOptionPane.showMessageDialog(frameLocations, "Oops! That city does not exist!\n Try using the Auto-Completion feature!");
            locations.deleteRegion(tempRegion);
            problem = false;
            displayable = false;
        }

        config.setLocations(locations);
        config.save();
    }//End of addButton method

    /**
     * Load in the saved locations and create buttons for each one
     */
    public static void loadButtons()
    {
        ArrayList<Location> tempList = locations.getLocationList();

        update();

        for (int i = 0; i < tempList.size(); i++)
        {
            final String tempName = tempList.get(i).getName();

            if(tempName.equals("mars"))
            {
                continue;
            }

            tempRegion = new Location(tempName);
            currentLocation = tempRegion;

            JButton buttonTemp = new JButton(locations.searchList(tempName).getOfficialName());
            buttonTemp.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (btnDelete.isSelected())
                    {
                        removeButton(tempName);
                        locations.deleteRegion(locations.searchList(tempName));
                        System.out.println("Removing " + tempName);
                        reinitializeDelete();
                    }
                    else
                    {
                        System.out.println("Opening " + tempName);
                        currentLocation = locations.searchList(tempName);
                        frameLocations.setVisible(false);
                        initializeForecast();
                        frameForecast.setVisible(true);
                    }
                }
            });
            buttonTemp.setMaximumSize(buttonSize);
            buttonTemp.setFont(new Font("Century Gothic", Font.PLAIN, 25));
            locationsPanel.add(buttonTemp);
            dynamicButtons.put(tempName, buttonTemp);
            reinitializeDelete();
        }

        layerUI.start();
        update();
        layerUI.stop();
    }

    private static void reinitializeDelete()
    {
        if(dynamicButtons.isEmpty())
        {
            btnDelete.setVisible(false);
        }
        else
        {
            btnDelete.setVisible(true);
        }
    }

    public static void removeButton(String name)
    {
        JButton b = dynamicButtons.remove(name);
        locationsPanel.remove(b);
        locationsPanel.validate();
        locationsPanel.update(locationsPanel.getGraphics());
        btnDelete.setSelected(false);
    }

    private static void unitsStateChanged(ItemEvent evt)
    {
        int state = evt.getStateChange();
        if (state == ItemEvent.SELECTED)
        {
            config.setDegrees(tempUnits.METRIC);
            config.save();
            System.out.println("Metric");
        } else
        {
            config.setDegrees(tempUnits.IMPERIAL);
            config.save();
            System.out.println("Imperial");
        }
        update();
    }

    private static void initializeLocations()
    {
        frameLocations = new JFrame();
        frameLocations.setResizable(false);
        frameLocations.setBounds(100, 100, 642, 473);
        frameLocations.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLocations.setTitle("Cloudy : Location List");

        layerUI = new WaitLayerUI();

        final JPanel panel = new JPanel();
        panel.setBounds(10, 32, 603, 390);

        panel.setLayout(null);

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        btnAdd.setBounds(56, 20, 89, 23);
        panel.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                addButton();
                locationsPanel.revalidate();
                locationsPanel.validate();

                if(displayable)
                {
                    frameLocations.setVisible(false);
                    initializeForecast();
                    frameForecast.setVisible(true);
                }
            }
        });

        btnDelete = new JToggleButton("Delete");
        btnDelete.setBounds(201, 20, 89, 23);
        btnDelete.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        panel.add(btnDelete);
        btnDelete.setVisible(false);

        //Multiview removed until later
        //JButton btnMultiview = new JButton("MultiView");
        //btnMultiview.setBounds(201, 20, 89, 23);
        //btnMultiview.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        //panel.add(btnMultiview);

        UnitsButton unitsToggle = new UnitsButton(config.getDegrees().equals(tempUnits.METRIC));
        unitsToggle.setBounds(491, 20, 89, 23);

        unitsToggle.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                unitsStateChanged(e);
            }
        });

        panel.add(unitsToggle);

        locationsPanel = new JPanel();
        locationsPanel.setBounds(0, 54, 636, 382);
        panel.add(locationsPanel);
        locationsPanel.setLayout(new BoxLayout(locationsPanel, BoxLayout.Y_AXIS));

        JButton buttonMars = new JButton("Mars");
        tempRegion = new Location("mars");
        buttonMars.setMaximumSize(new Dimension(frameLocations.getWidth(), 100));
        buttonMars.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(btnDelete.isSelected())
                {
                    JOptionPane.showMessageDialog(frameLocations, "Sorry, Mars is not allowed to be deleted.");
                    btnDelete.setSelected(false);
                }
                else
                {
                    System.out.println("Clicked: Mars");
                    currentLocation = locations.searchList("mars");
                    frameLocations.setVisible(false);
                    initializeForecast();
                    frameForecast.setVisible(true);
                }
            }
        });
        buttonMars.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        locations.addRegion(tempRegion);
        locationsPanel.add(buttonMars);

        buttonSize = buttonMars.getMaximumSize();

        jlayer = new JLayer<JPanel>(panel, layerUI);

        frameLocations.getContentPane().add(panel);

        if(config.exists())
        {
            loadButtons();
        }

        if(locations.getLocationList().size() == 1)
        {
            addButton();
        }

        if(displayable)
        {
            frameLocations.setVisible(false);
            initializeForecast();
            frameForecast.setVisible(true);
        }
        else
        {
            frameLocations.setVisible(true);
        }
    }

    private static void initializeForecast()
    {
        try
        {
            frameForecast = new JFrame();
            frameForecast.setResizable(false);
            frameForecast.setBounds(100, 100, 642, 475);
            frameForecast.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameForecast.getContentPane().setLayout(null);
            frameForecast.setTitle("Cloudy : Forecast for " + currentLocation.getName());

            JButton btnGoToLocations = new JButton();
            btnGoToLocations.setBorderPainted(false);
            btnGoToLocations.setBorder(null);
            btnGoToLocations.setMargin(new Insets(0, 0, 0, 0));
            btnGoToLocations.setFocusable(false);
            btnGoToLocations.setIcon(new ImageIcon(UnitsButton.class.getResource("/images/lines.png")));
            btnGoToLocations.setHorizontalAlignment(SwingConstants.CENTER);
            btnGoToLocations.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0)
                {
                    frameForecast.setVisible(false);
                    frameLocations.setVisible(true);
                }
            });
            btnGoToLocations.setBounds(10, 11, 40, 25);
            frameForecast.getContentPane().add(btnGoToLocations);

            lblLocation = new JLabel(currentLocation.getOfficialName());
            lblLocation.setFont(new Font("Century Gothic", Font.PLAIN, 36));
            lblLocation.setBounds(62, 5, 300, 50);
            frameForecast.getContentPane().add(lblLocation);

            lblTemp = new JLabel(String.valueOf((int)currentLocation.getCurrentTerm().getData()[0].getTemp())+ config.getTempUnit());
            lblTemp.setFont(new Font("Century Gothic", Font.PLAIN, 50));
            lblTemp.setBounds(447, 33, 136, 81);
            frameForecast.getContentPane().add(lblTemp);

            lblHigh = new JLabel(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTempMax())+ config.getTempUnit());
            lblHigh.setBounds(443, 104, 46, 14);
            frameForecast.getContentPane().add(lblHigh);

            lblLow = new JLabel(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTempMin())+ config.getTempUnit());
            lblLow.setBounds(501, 104, 46, 14);
            frameForecast.getContentPane().add(lblLow);

          /*  JLabel lblHumidityValue = new JLabel(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTemp())+ config.getTempUnit());
            lblHumidityValue.setBounds(80, 129, 46, 14);
            frameForecast.getContentPane().add(lblHumidityValue);
*/
            lblWindSpeedValue = new JLabel(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getWindSpeed())+config.getWindUnit());
            lblWindSpeedValue.setBounds(80, 146, 46, 14);
            frameForecast.getContentPane().add(lblWindSpeedValue);

            lblPressureValue = new JLabel(String.valueOf( currentLocation.getCurrentTerm().getData()[0].getPressure())+config.getPressureUnit());
            lblPressureValue.setBounds(80, 166, 70, 14);
            frameForecast.getContentPane().add(lblPressureValue);
/*
            JLabel lblSunriseValue = new JLabel(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getWindSpeed()));
            lblSunriseValue.setBounds(198, 146, 46, 14);
            frameForecast.getContentPane().add(lblSunriseValue);

            JLabel lblSunsetValue = new JLabel(String.valueOf( currentLocation.getCurrentTerm().getData()[0].getPressure()));
            lblSunsetValue.setBounds(198, 165, 46, 14);
            frameForecast.getContentPane().add(lblSunsetValue);
*/
            JSeparator separator_1 = new JSeparator();
            separator_1.setBounds(0, 187, 626, 50);
            frameForecast.getContentPane().add(separator_1);

            JPanel panel2 = new JPanel();

            panel2.setBounds(0, 269, 626, 169);
            frameForecast.getContentPane().add(panel2);
            panel2.setLayout(null);

            JPanel panel_10 = new JPanel();
            panel_10.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel_10.setBounds(502, 0, 111, 147);
            panel2.add(panel_10);
            panel_10.setLayout(null);

            lblFriday = new JLabel(getLTName(currentLocation.getLongTerm().getData()[4].getName()));
            lblFriday.setHorizontalAlignment(SwingConstants.CENTER);
            lblFriday.setBounds(0, 11, 111, 14);
            panel_10.add(lblFriday);

            lblFridayHigh = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[4].getTempMax())+config.getTempUnit());
            lblFridayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblFridayHigh.setBounds(0, 108, 52, 28);
            panel_10.add(lblFridayHigh);

            lblFridayTemp = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[4].getTemp())+config.getTempUnit());
            lblFridayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblFridayTemp.setBounds(0, 95, 111, 14);
            panel_10.add(lblFridayTemp);

            lblFridayLow = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[4].getTempMin())+config.getTempUnit());
            lblFridayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblFridayLow.setBounds(62, 108, 49, 28);
            panel_10.add(lblFridayLow);

            lblFridaySummery = new JLabel(currentLocation.getLongTerm().getData()[4].getDescription());
            lblFridaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblFridaySummery.setBounds(-1, 78, 112, 14);
            panel_10.add(lblFridaySummery);

            imgFriday = new JLabel();
            imgFriday.setHorizontalAlignment(SwingConstants.CENTER);
            imgFriday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[4]));
            imgFriday.setBounds(0, 30, 111, 46);
            panel_10.add(imgFriday);

            JPanel panel_6 = new JPanel();
            panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel_6.setBounds(10, 0, 113, 147);
            panel2.add(panel_6);
            panel_6.setLayout(null);

            lblMondayHigh = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[0].getTempMax())+config.getTempUnit());
            lblMondayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblMondayHigh.setBounds(0, 106, 59, 30);
            panel_6.add(lblMondayHigh);

            lblMonday = new JLabel(getLTName(currentLocation.getLongTerm().getData()[0].getName()));
            lblMonday.setHorizontalAlignment(SwingConstants.CENTER);
            lblMonday.setBounds(0, 11, 113, 14);
            lblMonday.setAlignmentY(Component.BOTTOM_ALIGNMENT);
            panel_6.add(lblMonday);

            lblMondayLow = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[0].getTempMin())+config.getTempUnit());
            lblMondayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblMondayLow.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblMondayLow.setBounds(56, 106, 57, 30);
            panel_6.add(lblMondayLow);

            lblMondayTemp = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[0].getTemp())+config.getTempUnit());
            lblMondayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblMondayTemp.setBounds(0, 95, 113, 14);
            panel_6.add(lblMondayTemp);

            lblMondaySummery = new JLabel(currentLocation.getLongTerm().getData()[0].getDescription());
            lblMondaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblMondaySummery.setBounds(0, 78, 113, 14);
            panel_6.add(lblMondaySummery);

            imgMonday = new JLabel("");
            imgMonday.setHorizontalAlignment(SwingConstants.CENTER);
            imgMonday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[0]));
            imgMonday.setBounds(0, 30, 111, 46);
            panel_6.add(imgMonday);

            JPanel panel_7 = new JPanel();
            panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel_7.setBounds(133, 0, 113, 147);
            panel2.add(panel_7);
            panel_7.setLayout(null);

            lblTuesday = new JLabel(getLTName(currentLocation.getLongTerm().getData()[1].getName()));
            lblTuesday.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesday.setBounds(0, 11, 113, 14);
            panel_7.add(lblTuesday);

            lblTuesdayHigh = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[1].getTempMax())+config.getTempUnit());
            lblTuesdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesdayHigh.setBounds(0, 107, 52, 29);
            panel_7.add(lblTuesdayHigh);

            lblTuesdayTemp = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[1].getTemp())+config.getTempUnit());
            lblTuesdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesdayTemp.setBounds(0, 95, 113, 14);
            panel_7.add(lblTuesdayTemp);

            lblTuesdayLow = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[1].getTempMin())+config.getTempUnit());
            lblTuesdayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesdayLow.setBounds(62, 107, 51, 29);
            panel_7.add(lblTuesdayLow);

            lblTuesdaySummery = new JLabel(currentLocation.getLongTerm().getData()[1].getDescription());
            lblTuesdaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesdaySummery.setBounds(0, 78, 113, 14);
            panel_7.add(lblTuesdaySummery);

            imgTuesday = new JLabel("");
            imgTuesday.setHorizontalAlignment(SwingConstants.CENTER);
            imgTuesday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[1]));
            imgTuesday.setBounds(0, 30, 111, 46);
            panel_7.add(imgTuesday);

            JPanel panel_8 = new JPanel();
            panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel_8.setBounds(256, 0, 113, 147);
            panel2.add(panel_8);
            panel_8.setLayout(null);

            lblWednesday = new JLabel(getLTName(currentLocation.getLongTerm().getData()[2].getName()));
            lblWednesday.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednesday.setBounds(0, 11, 113, 14);
            panel_8.add(lblWednesday);

            lblWednesdayHigh = new JLabel(String.valueOf((int)currentLocation.getLongTerm().getData()[2].getTempMax())+config.getTempUnit());
            lblWednesdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednesdayHigh.setBounds(0, 105, 46, 31);
            panel_8.add(lblWednesdayHigh);

            lblWednesdayTemp = new JLabel(String.valueOf((int)currentLocation.getLongTerm().getData()[2].getTemp())+config.getTempUnit());
            lblWednesdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednesdayTemp.setBounds(0, 95, 113, 14);
            panel_8.add(lblWednesdayTemp);

            lblWednesdayLow = new JLabel(String.valueOf((int)currentLocation.getLongTerm().getData()[2].getTempMin())+config.getTempUnit());
            lblWednesdayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednesdayLow.setBounds(62, 105, 51, 31);
            panel_8.add(lblWednesdayLow);

            lblWednessdaySummery = new JLabel(currentLocation.getLongTerm().getData()[2].getDescription());
            lblWednessdaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednessdaySummery.setBounds(0, 78, 113, 14);
            panel_8.add(lblWednessdaySummery);

            imgWednesday = new JLabel("");
            imgWednesday.setHorizontalAlignment(SwingConstants.CENTER);
            imgWednesday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[2]));
            imgWednesday.setBounds(0, 30, 111, 46);
            panel_8.add(imgWednesday);

            JPanel panel_9 = new JPanel();
            panel_9.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel_9.setBounds(379, 0, 113, 147);
            panel2.add(panel_9);
            panel_9.setLayout(null);

            lblThursday = new JLabel(getLTName(currentLocation.getLongTerm().getData()[3].getName()));
            lblThursday.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursday.setBounds(0, 11, 113, 14);
            panel_9.add(lblThursday);

            lblThursdayHigh = new JLabel(String.valueOf((int)currentLocation.getLongTerm().getData()[3].getTempMax())+config.getTempUnit());
            lblThursdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursdayHigh.setBounds(0, 107, 52, 29);
            panel_9.add(lblThursdayHigh);

            lblThursdayTemp = new JLabel(String.valueOf((int)currentLocation.getLongTerm().getData()[3].getTemp())+config.getTempUnit());
            lblThursdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursdayTemp.setBounds(0, 95, 113, 14);
            panel_9.add(lblThursdayTemp);

            lblThursdayLow = new JLabel(String.valueOf((int)currentLocation.getLongTerm().getData()[3].getTempMin())+config.getTempUnit());
            lblThursdayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursdayLow.setBounds(62, 107, 51, 29);
            panel_9.add(lblThursdayLow);

            lblThursdaySummery = new JLabel(currentLocation.getLongTerm().getData()[3].getDescription());
            lblThursdaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursdaySummery.setBounds(-2, 78, 115, 14);
            panel_9.add(lblThursdaySummery);

            imgThursday = new JLabel("");
            imgThursday.setHorizontalAlignment(SwingConstants.CENTER);
            imgThursday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[3]));
            imgThursday.setBounds(0, 30, 111, 46);
            panel_9.add(imgThursday);

            JSeparator separator = new JSeparator();
            separator.setBounds(0, 258, 626, 23);
            frameForecast.getContentPane().add(separator);

            JLabel lblNewLabel_1 = new JLabel("");
            lblNewLabel_1.setBounds(247, 90, 46, 14);
            frameForecast.getContentPane().add(lblNewLabel_1);

            lblWeathercondition = new JLabel(currentLocation.getCurrentTerm().getData()[0].getDescription());
            lblWeathercondition.setFont(new Font("Century Gothic", Font.PLAIN, 23));
            lblWeathercondition.setBounds(8, 90, 194, 28);
            frameForecast.getContentPane().add(lblWeathercondition);

            JLabel lblHumidity = new JLabel("Humidity:");
            lblHumidity.setBounds(8, 129, 61, 14);
            frameForecast.getContentPane().add(lblHumidity);

            JLabel lblWindSpeed = new JLabel("Wind Speed:");
            lblWindSpeed.setBounds(8, 146, 81, 14);
            frameForecast.getContentPane().add(lblWindSpeed);

            JLabel lblPressure = new JLabel("Pressure:");
            lblPressure.setBounds(8, 165, 66, 14);
            frameForecast.getContentPane().add(lblPressure);

            JLabel lblSunrise = new JLabel("Sunrise:");
            lblSunrise.setBounds(150, 146, 46, 14);
            frameForecast.getContentPane().add(lblSunrise);

            JLabel lblSunset = new JLabel("Sunset:");
            lblSunset.setBounds(150, 165, 46, 14);
            frameForecast.getContentPane().add(lblSunset);

            JButton btnRefresh = new JButton("Refresh");
            btnRefresh.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0)
                {
                    update();
                    updateGUI();
                }
            });
            btnRefresh.setBounds(531, 11, 89, 23);
            frameForecast.getContentPane().add(btnRefresh);
        }catch(NullPointerException e)
        {
            System.out.println("Not all elements displayed properly!");
        }

        sliderShortTerm = new JSlider();
        sliderShortTerm.setBounds(0, 211, 626, 50);

        sliderShortTerm.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                {
                    updateSTGUI(source.getValue());
                }
            }
        });
        sliderShortTerm.setMajorTickSpacing(1);
        sliderShortTerm.setPaintTicks(true);
        sliderShortTerm.setMaximum(8);
        sliderShortTerm.setValue(0);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();

        ZonedDateTime tempZDT;
        Date tempDate;

        labelTable.put(0, new JLabel("Current"));
        for (int i = 0; i < currentLocation.getShortTerm().data.length; i++)
        {
            tempDate = new Date(Integer.parseInt(currentLocation.getShortTerm().data[i].getName())*1000L);
            tempZDT = tempDate.toInstant().atZone(ZoneId.systemDefault());
            labelTable.put(i+1, new JLabel(tempZDT.format(STformat)));
        }
        sliderShortTerm.setLabelTable(labelTable);

        sliderShortTerm.setPaintLabels(true);

        frameForecast.getContentPane().add(sliderShortTerm);

        JLabel lblLastUpdated = new JLabel("Last Updated:");
        lblLastUpdated.setBounds(400, 125, 76, 14);
        frameForecast.getContentPane().add(lblLastUpdated);

        lblTime = new JLabel(ZonedDateTime.now().format(format));
        lblTime.setBounds(480, 125, 130, 14);
        frameForecast.getContentPane().add(lblTime);
    }

    private static Icon getCorrectImage(BaseData data)
    {
        return new ImageIcon(UnitsButton.class.getResource("/images/" + data.getIconID() + ".png"));
    }

    private static void updateSTGUI(int hours)
    {
        if(hours == 0)
        {
            lblTemp.setText(String.valueOf((int)currentLocation.getCurrentTerm().getData()[0].getTemp()) + config.getTempUnit());
            lblHigh.setText(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTempMax()) + config.getTempUnit());
            lblLow.setText(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTempMin()) + config.getTempUnit());
            lblWindSpeedValue.setText(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getWindSpeed()) + config.getWindUnit());
            lblPressureValue.setText(String.valueOf(currentLocation.getCurrentTerm().getData()[0].getPressure()) + config.getPressureUnit());
            lblWeathercondition.setText(currentLocation.getCurrentTerm().getData()[0].getDescription());
            return;
        }

        lblTemp.setText(String.valueOf((int)currentLocation.getShortTerm().getData()[hours-1].getTemp()) + config.getTempUnit());
        lblHigh.setText(String.valueOf((int) currentLocation.getShortTerm().getData()[hours-1].getTempMax())+ config.getTempUnit());
        lblLow.setText(String.valueOf((int) currentLocation.getShortTerm().getData()[hours-1].getTempMin()) + config.getTempUnit());
        lblWindSpeedValue.setText(String.valueOf((int) currentLocation.getShortTerm().getData()[hours-1].getWindSpeed()) + config.getWindUnit());
        lblPressureValue.setText(String.valueOf( currentLocation.getShortTerm().getData()[hours-1].getPressure()) + config.getPressureUnit());
        lblWeathercondition.setText(currentLocation.getShortTerm().getData()[hours-1].getDescription());

    }//End of updateSTGUI

    /*

     */
    private static void updateGUI()
    {
        lblLocation = new JLabel(currentLocation.getName());
        lblHigh.setText(String.valueOf((int)currentLocation.getCurrentTerm().getData()[0].getTempMax())+config.getTempUnit());
        lblTemp.setText(String.valueOf((int)currentLocation.getCurrentTerm().getData()[0].getTemp())+config.getTempUnit());
        lblLow.setText(String.valueOf((int)currentLocation.getCurrentTerm().getData()[0].getTempMin())+config.getTempUnit());

        lblFridayHigh.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[4].getTempMax())+config.getTempUnit());
        lblFridayTemp.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[4].getTemp())+config.getTempUnit());
        lblFridayLow.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[4].getTempMin())+config.getTempUnit());
        lblFridaySummery.setText(currentLocation.getLongTerm().getData()[4].getDescription());
        lblFriday.setText(getLTName(currentLocation.getLongTerm().getData()[4].getName()));
        imgFriday.setText("");

        lblMondayHigh.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[0].getTempMax())+config.getTempUnit());
        lblMondayLow.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[0].getTempMin())+config.getTempUnit());
        lblMondayTemp.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[0].getTemp())+config.getTempUnit());
        lblMondaySummery.setText(currentLocation.getLongTerm().getData()[0].getDescription());
        lblMonday.setText(getLTName(currentLocation.getLongTerm().getData()[0].getName()));
        imgMonday.setText("");

        lblTuesdayHigh.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[1].getTempMax())+config.getTempUnit());
        lblTuesdayTemp.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[1].getTemp())+config.getTempUnit());
        lblTuesdayLow.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[1].getTempMin())+config.getTempUnit());
        lblTuesdaySummery.setText(currentLocation.getLongTerm().getData()[1].getDescription());
        lblTuesday.setText(getLTName(currentLocation.getLongTerm().getData()[1].getName()));
        imgTuesday.setText("");

        lblWednesdayHigh.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[2].getTempMax())+config.getTempUnit());
        lblWednesdayTemp.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[2].getTemp())+config.getTempUnit());
        lblWednesdayLow.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[2].getTempMin())+config.getTempUnit());
        lblWednessdaySummery.setText(currentLocation.getLongTerm().getData()[2].getDescription());
        lblWednesday.setText(getLTName(currentLocation.getLongTerm().getData()[2].getName()));
        imgWednesday.setText("");

        lblThursdayHigh.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[3].getTempMax())+config.getTempUnit());
        lblThursdayTemp.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[3].getTemp())+config.getTempUnit());
        lblThursdayLow.setText(String.valueOf((int)currentLocation.getLongTerm().getData()[3].getTempMin())+config.getTempUnit());
        lblThursdaySummery.setText(currentLocation.getLongTerm().getData()[3].getDescription());
        lblThursday.setText(getLTName(currentLocation.getLongTerm().getData()[3].getName()));
        imgThursday.setText("");

        lblWeathercondition.setText(currentLocation.getCurrentTerm().getData()[0].getDescription());
        lblTime.setText(ZonedDateTime.now().format(format));

        sliderShortTerm.setValue(0);
    }

    private static String getLTName(String num)
    {
        int dt = Integer.parseInt(num);

        Date date = new Date(dt*1000L);
        ZonedDateTime ZDT = ZonedDateTime.ofInstant(date.toInstant(),
                ZoneId.systemDefault());

        return ZDT.format(LTformat);
    }
}//End of main class
