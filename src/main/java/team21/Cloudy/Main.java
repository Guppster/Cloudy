package team21.Cloudy;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
    private static final String emptySpace = "                                                                                                  ";
    private static boolean  delete = false;

    private static JFrame frameLocations;

    private static JLabel lblFridayHigh;
    private static JLabel lblFridayTemp;
    private static JLabel lblFridayLow;
    private static JLabel lblFridaySummery;
    private static JLabel imgFriday;

    private static JLabel lblMondayHigh;
    private static JLabel lblMondayLow;
    private static JLabel lblMondayTemp;
    private static JLabel lblMondaySummery;
    private static JLabel imgMonday;

    private static JLabel lblTuesdayHigh;
    private static JLabel lblTuesdayTemp;
    private static JLabel lblTuesdayLow;
    private static JLabel lblTuesdaySummery;
    private static JLabel imgTuesday;

    private static JLabel lblWednesdayHigh;
    private static JLabel lblWednesdayTemp;
    private static JLabel lblWednesdayLow;
    private static JLabel lblWednessdaySummery;
    private static JLabel imgWednesday;

    private static JLabel lblThursdayHigh;
    private static JLabel lblThursdayTemp;
    private static JLabel lblThursdayLow;
    private static JLabel lblThursdaySummery;
    private static JLabel imgThursday;


    private static JLabel lblWeathercondition;
    private static JLabel lblLocation;
    private static JLabel lblTime;
    private static JLabel lblTemp;
    private static JLabel lblHigh;
    private static JLabel lblLow;

    private static JFrame frameForecast;

    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");

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

                    initializeLocations();

                    frameLocations.setVisible(true);

                    dynamicButtons = new HashMap<String, JButton>();

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        //checkInitialRun();

    }//End of main method


    /**
     * This methods updates all TermObjects in all location objects within the location list
     */
    private static void update()
    {
        for (Location region : locations.getLocationList())
        {
            netController.setLocation(region);
            netController.fetch();
        }
    }

    /**
     * Initialize the contents of the frame.
     */


    public static void addButton()
    {
        final String tempName = JOptionPane.showInputDialog(frameLocations, "Please enter a Location Name");

        final Location tempRegion = new Location(tempName);
        locations.addRegion(tempRegion);

        update();

        JButton buttonTemp = new JButton(tempRegion.getName());
        buttonTemp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(delete)
                {
                    removeButton(tempRegion.getName());
                    System.out.println("Removing " + tempRegion.getName());
                }
                else
                {
                    System.out.println("Clicked " + tempRegion.getName());
                    currentLocation = locations.searchList(tempRegion.getName());
                    frameLocations.setVisible(false);
                    initializeForecast();
                    frameForecast.setVisible(true);
                }
            }
        });
        buttonTemp.setMaximumSize(buttonSize);
        locationsPanel.add(buttonTemp);

        /*
        Test implementation to remove errors with slow connections

        System.out.println("Waiting for Data Sync:");
        while(tempRegion.getCurrentTerm() == null || tempRegion.getShortTerm() == null || tempRegion.getLongTerm() == null)
        {
            System.out.print(" ... ");
        }
        buttonTemp.setEnabled(true);

        dynamicButtons.put(tempRegion.getName(), buttonTemp);
        Box.createVerticalStrut(10);

        **/
    }


    public static void removeButton(String name)
    {
        JButton b = dynamicButtons.remove(name);
        locationsPanel.remove(b);
        locationsPanel.validate();
        delete = false;
    }

    private static void initializeLocations()
    {
        frameLocations = new JFrame();
        frameLocations.setResizable(false);
        frameLocations.setBounds(100, 100, 642, 473);
        frameLocations.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        panel.setBounds(10, 32, 603, 390);
        frameLocations.getContentPane().add(panel);
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
        buttonMars.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(delete)
                {
                    System.out.println("Access Denied: Removing Mars is Not Allowed ");
                }
                else
                {
                    System.out.println("Clicked: Mars");
                }
            }
        });
        locationsPanel.add(buttonMars);

        buttonSize = buttonMars.getMaximumSize();

        Box.createVerticalStrut(10);

        final String initialname = JOptionPane.showInputDialog(frameLocations, "Please enter an initial Location");

        final Location tempRegion = new Location(initialname);
        locations.addRegion(tempRegion);

        update();

        final JButton buttonInitial = new JButton(tempRegion.getName());
        buttonInitial.setMaximumSize(buttonSize);
        buttonInitial.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(delete)
                {
                    removeButton(tempRegion.getName());
                    System.out.println("Removing " + tempRegion.getName());
                }
                else
                {
                    System.out.println("Clicked " + tempRegion.getName());
                    currentLocation = locations.searchList(tempRegion.getName());
                    frameLocations.setVisible(false);
                    initializeForecast();
                    frameForecast.setVisible(true);
                }
            }
        });

        locationsPanel.add(buttonInitial);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                addButton();
                locationsPanel.revalidate();
                locationsPanel.validate();
            }
        });
    }

    private static void initializeForecast()
    {
        try
        {
            frameForecast = new JFrame();
            frameForecast.setBounds(100, 100, 642, 475);
            frameForecast.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameForecast.getContentPane().setLayout(null);

            JButton btnGoToLocations = new JButton("=");
            btnGoToLocations.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0)
                {
                    frameForecast.setVisible(false);
                    frameLocations.setVisible(true);
                }
            });
            btnGoToLocations.setBounds(10, 11, 41, 23);
            frameForecast.getContentPane().add(btnGoToLocations);

            lblLocation = new JLabel(currentLocation.getName());
            lblLocation.setFont(new Font("Century Gothic", Font.PLAIN, 36));
            lblLocation.setBounds(62, 5, 231, 50);
            frameForecast.getContentPane().add(lblLocation);

            JSlider sliderShortTerm = new JSlider();
            sliderShortTerm.setPaintLabels(true);
            sliderShortTerm.setBounds(0, 211, 626, 26);
            frameForecast.getContentPane().add(sliderShortTerm);

            lblTemp = new JLabel(Double.toString(currentLocation.getCurrentTerm().getData()[0].getTemp()));
            lblTemp.setFont(new Font("Century Gothic", Font.PLAIN, 50));
            lblTemp.setBounds(447, 33, 136, 81);
            frameForecast.getContentPane().add(lblTemp);

            lblHigh = new JLabel(Double.toString(currentLocation.getCurrentTerm().getData()[0].getTempMax()));
            lblHigh.setBounds(443, 104, 46, 14);
            frameForecast.getContentPane().add(lblHigh);

            lblLow = new JLabel(Double.toString(currentLocation.getCurrentTerm().getData()[0].getTempMin()));
            lblLow.setBounds(501, 104, 46, 14);
            frameForecast.getContentPane().add(lblLow);

            JLabel lblHumidityValue = new JLabel(Double.toString(currentLocation.getCurrentTerm().getData()[0].getTemp()));
            lblHumidityValue.setBounds(80, 129, 46, 14);
            frameForecast.getContentPane().add(lblHumidityValue);

            JLabel lblWindSpeedValue = new JLabel(Double.toString(currentLocation.getCurrentTerm().getData()[0].getWindSpeed()));
            lblWindSpeedValue.setBounds(80, 146, 46, 14);
            frameForecast.getContentPane().add(lblWindSpeedValue);

            JLabel lblPressureValue = new JLabel(Double.toString(currentLocation.getCurrentTerm().getData()[0].getPressure()));
            lblPressureValue.setBounds(80, 166, 46, 14);
            frameForecast.getContentPane().add(lblPressureValue);

            JLabel lblSunriseValue = new JLabel(Double.toString(currentLocation.getCurrentTerm().getData()[0].getWindSpeed()));
            lblSunriseValue.setBounds(198, 146, 46, 14);
            frameForecast.getContentPane().add(lblSunriseValue);

            JLabel lblSunsetValue = new JLabel(Double.toString(currentLocation.getCurrentTerm().getData()[0].getPressure()));
            lblSunsetValue.setBounds(198, 165, 46, 14);
            frameForecast.getContentPane().add(lblSunsetValue);

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

            JLabel lblFriday = new JLabel("Friday");
            lblFriday.setHorizontalAlignment(SwingConstants.CENTER);
            lblFriday.setBounds(0, 11, 111, 14);
            panel_10.add(lblFriday);


            lblFridayHigh = new JLabel(Double.toString((currentLocation.getLongTerm().getData()[4].getTempMax())));
            lblFridayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblFridayHigh.setBounds(0, 108, 52, 28);
            panel_10.add(lblFridayHigh);

            lblFridayTemp = new JLabel(Double.toString(currentLocation.getLongTerm().getData()[4].getTemp()));
            lblFridayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblFridayTemp.setBounds(0, 95, 111, 14);
            panel_10.add(lblFridayTemp);

            lblFridayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[4].getTempMin()));
            lblFridayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblFridayLow.setBounds(62, 108, 49, 28);
            panel_10.add(lblFridayLow);

            lblFridaySummery = new JLabel(currentLocation.getLongTerm().getData()[4].getDescription());
            lblFridaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblFridaySummery.setBounds(-1, 78, 112, 14);
            panel_10.add(lblFridaySummery);

            imgFriday = new JLabel("");
            imgFriday.setHorizontalAlignment(SwingConstants.CENTER);
            imgFriday.setBounds(0, 46, 111, 14);
            panel_10.add(imgFriday);

            JPanel panel_6 = new JPanel();
            panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel_6.setBounds(10, 0, 113, 147);
            panel2.add(panel_6);
            panel_6.setLayout(null);

            lblMondayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[0].getTempMax()));
            lblMondayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblMondayHigh.setBounds(0, 106, 59, 30);
            panel_6.add(lblMondayHigh);

            JLabel lblMonday = new JLabel("Monday");
            lblMonday.setHorizontalAlignment(SwingConstants.CENTER);
            lblMonday.setBounds(0, 11, 113, 14);
            lblMonday.setAlignmentY(Component.BOTTOM_ALIGNMENT);
            panel_6.add(lblMonday);

            lblMondayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[0].getTempMin()));
            lblMondayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblMondayLow.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblMondayLow.setBounds(56, 106, 57, 30);
            panel_6.add(lblMondayLow);

            lblMondayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[0].getTemp()));
            lblMondayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblMondayTemp.setBounds(0, 95, 113, 14);
            panel_6.add(lblMondayTemp);

            lblMondaySummery = new JLabel(currentLocation.getLongTerm().getData()[0].getDescription());
            lblMondaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblMondaySummery.setBounds(0, 78, 113, 14);
            panel_6.add(lblMondaySummery);

            imgMonday = new JLabel("");
            imgMonday.setHorizontalAlignment(SwingConstants.CENTER);
            //imgMonday.setIcon(new ImageIcon(MainWindow.class.getResource("/sun/print/resources/oneside.png")));
            imgMonday.setBounds(1, 46, 111, 14);
            panel_6.add(imgMonday);

            JPanel panel_7 = new JPanel();
            panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel_7.setBounds(133, 0, 113, 147);
            panel2.add(panel_7);
            panel_7.setLayout(null);

            JLabel lblTuesday = new JLabel("Tuesday");
            lblTuesday.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesday.setBounds(0, 11, 113, 14);
            panel_7.add(lblTuesday);

            lblTuesdayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[1].getTempMax()));
            lblTuesdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesdayHigh.setBounds(0, 107, 52, 29);
            panel_7.add(lblTuesdayHigh);

            lblTuesdayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[1].getTemp()));
            lblTuesdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesdayTemp.setBounds(0, 95, 113, 14);
            panel_7.add(lblTuesdayTemp);

            lblTuesdayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[1].getTempMin()));
            lblTuesdayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesdayLow.setBounds(62, 107, 51, 29);
            panel_7.add(lblTuesdayLow);

            lblTuesdaySummery = new JLabel(currentLocation.getLongTerm().getData()[1].getDescription());
            lblTuesdaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesdaySummery.setBounds(0, 78, 113, 14);
            panel_7.add(lblTuesdaySummery);

            imgTuesday = new JLabel("");
            imgTuesday.setHorizontalAlignment(SwingConstants.CENTER);
            imgTuesday.setBounds(1, 46, 111, 14);
            panel_7.add(imgTuesday);

            JPanel panel_8 = new JPanel();
            panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel_8.setBounds(256, 0, 113, 147);
            panel2.add(panel_8);
            panel_8.setLayout(null);

            JLabel lblWednessday = new JLabel("Wednesday");
            lblWednessday.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednessday.setBounds(0, 11, 113, 14);
            panel_8.add(lblWednessday);

            lblWednesdayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[2].getTempMax()));
            lblWednesdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednesdayHigh.setBounds(0, 105, 46, 31);
            panel_8.add(lblWednesdayHigh);

            lblWednesdayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[2].getTemp()));
            lblWednesdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednesdayTemp.setBounds(0, 95, 113, 14);
            panel_8.add(lblWednesdayTemp);

            lblWednesdayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[2].getTempMin()));
            lblWednesdayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednesdayLow.setBounds(62, 105, 51, 31);
            panel_8.add(lblWednesdayLow);

            lblWednessdaySummery = new JLabel(currentLocation.getLongTerm().getData()[2].getDescription());
            lblWednessdaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednessdaySummery.setBounds(0, 78, 113, 14);
            panel_8.add(lblWednessdaySummery);

            imgWednesday = new JLabel("");
            imgWednesday.setHorizontalAlignment(SwingConstants.CENTER);
            imgWednesday.setBounds(1, 46, 109, 14);
            panel_8.add(imgWednesday);

            JPanel panel_9 = new JPanel();
            panel_9.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel_9.setBounds(379, 0, 113, 147);
            panel2.add(panel_9);
            panel_9.setLayout(null);

            JLabel lblThursday = new JLabel("Thursday");
            lblThursday.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursday.setBounds(0, 11, 113, 14);
            panel_9.add(lblThursday);

            lblThursdayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[3].getTempMax()));
            lblThursdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursdayHigh.setBounds(0, 107, 52, 29);
            panel_9.add(lblThursdayHigh);

            lblThursdayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[3].getTemp()));
            lblThursdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursdayTemp.setBounds(0, 95, 113, 14);
            panel_9.add(lblThursdayTemp);

            lblThursdayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().getData()[3].getTempMin()));
            lblThursdayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursdayLow.setBounds(62, 107, 51, 29);
            panel_9.add(lblThursdayLow);

            lblThursdaySummery = new JLabel(currentLocation.getLongTerm().getData()[3].getDescription());
            lblThursdaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursdaySummery.setBounds(-2, 78, 115, 14);
            panel_9.add(lblThursdaySummery);

            imgThursday = new JLabel("");
            imgThursday.setHorizontalAlignment(SwingConstants.CENTER);
            imgThursday.setBounds(3, 46, 109, 14);
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
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("Tried updating GUI before data was recieved");
                    }
                    updateGUI();
                }
            });
            btnRefresh.setBounds(531, 11, 89, 23);
            frameForecast.getContentPane().add(btnRefresh);
        }catch(NullPointerException e)
        {
            System.out.println("Data not loaded yet... Please wait");
        }

        JLabel lblLastUpdated = new JLabel("Last Updated:");
        lblLastUpdated.setBounds(400, 125, 76, 14);
        frameForecast.getContentPane().add(lblLastUpdated);

        lblTime = new JLabel(ZonedDateTime.now().format(format));
        lblTime.setBounds(480, 125, 130, 14);
        frameForecast.getContentPane().add(lblTime);
    }

    /*

     */
    private static void updateGUI()
    {
        lblLocation = new JLabel(currentLocation.getName());
        lblHigh.setText(String.valueOf(currentLocation.getCurrentTerm().getData()[0].getTempMax()));
        lblTemp.setText(String.valueOf(currentLocation.getCurrentTerm().getData()[0].getTemp()));
        lblLow.setText(String.valueOf(currentLocation.getCurrentTerm().getData()[0].getTempMin()));

        lblFridayHigh.setText(String.valueOf(currentLocation.getLongTerm().getData()[4].getTempMax()));
        lblFridayTemp.setText(String.valueOf(currentLocation.getLongTerm().getData()[4].getTemp()));
        lblFridayLow.setText(String.valueOf(currentLocation.getLongTerm().getData()[4].getTempMin()));
        lblFridaySummery.setText(currentLocation.getLongTerm().getData()[4].getDescription());
        imgFriday.setText("");

        lblMondayHigh.setText(String.valueOf(currentLocation.getLongTerm().getData()[0].getTempMax()));
        lblMondayLow.setText(String.valueOf(currentLocation.getLongTerm().getData()[0].getTempMin()));
        lblMondayTemp.setText(String.valueOf(currentLocation.getLongTerm().getData()[0].getTemp()));
        lblMondaySummery.setText(currentLocation.getLongTerm().getData()[0].getDescription());
        imgMonday.setText("");

        lblTuesdayHigh.setText(String.valueOf(currentLocation.getLongTerm().getData()[1].getTempMax()));
        lblTuesdayTemp.setText(String.valueOf(currentLocation.getLongTerm().getData()[1].getTemp()));
        lblTuesdayLow.setText(String.valueOf(currentLocation.getLongTerm().getData()[1].getTempMin()));
        lblTuesdaySummery.setText(currentLocation.getLongTerm().getData()[1].getDescription());
        imgTuesday.setText("");

        lblWednesdayHigh.setText(String.valueOf(currentLocation.getLongTerm().getData()[2].getTempMax()));
        lblWednesdayTemp.setText(String.valueOf(currentLocation.getLongTerm().getData()[2].getTemp()));
        lblWednesdayLow.setText(String.valueOf(currentLocation.getLongTerm().getData()[2].getTempMin()));
        lblWednessdaySummery.setText(currentLocation.getLongTerm().getData()[2].getDescription());
        imgWednesday.setText("");

        lblThursdayHigh.setText(String.valueOf(currentLocation.getLongTerm().getData()[3].getTempMax()));
        lblThursdayTemp.setText(String.valueOf(currentLocation.getLongTerm().getData()[3].getTemp()));
        lblThursdayLow.setText(String.valueOf(currentLocation.getLongTerm().getData()[3].getTempMin()));
        lblThursdaySummery.setText(currentLocation.getLongTerm().getData()[3].getDescription());
        imgThursday.setText("");

        lblWeathercondition.setText(currentLocation.getCurrentTerm().getData()[0].getDescription());
        lblTime.setText(ZonedDateTime.now().format(format));
    }
}//End of main class
