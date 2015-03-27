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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Gurpreet
 */
public class Main
{
    //Fields
    private static LocationList locations;                      //Maintains a list of locations
    private static Configuration config;                        //Maintains a constant configuration
    private static NetworkController netController;             //Controls all network interfacing
    private static Location currentLocation;                    //Stores the current location on forecast view

    private static JPanel locationsPanel;                       //The panel that shows location list
    private static Dimension buttonSize;                        //A constant button size for location elements
    private static HashMap<String, JButton> dynamicButtons;     //Map stores all buttons on screen
    private static boolean problem = false;                     //Boolean value indicating if there is a network error with recent fetch
    private static boolean displayable = false;                 //Boolean value controlling if currentLocation is viewable or not (!problem)

    private static JFrame frameLocations;                       //The main frame that displays everything
    private static JFrame frameForecast;                        //Stores the forecast frame, which shows individual locations
    private static JFrame frameMars;                            //Stores the Mars frame, which shows the Mars weather

    private static JLabel lblMondayHigh;                        //Stores the high value of the first long term box
    private static JLabel lblMondayLow;                         //Stores the temp value of the first long term box
    private static JLabel lblMondayTemp;                        //Stores the low value of the first long term box
    private static JLabel lblMondaySummery;                     //Stores the summery value of the first long term box
    private static JLabel imgMonday;                            //Stores the image value of the first long term box
    private static JLabel lblMonday;                            //Stores the name value of the first long term box

    private static JLabel lblTuesdayHigh;                       //Stores the high value of the second long term box
    private static JLabel lblTuesdayTemp;                       //Stores the temp value of the second long term box
    private static JLabel lblTuesdayLow;                        //Stores the low value of the second long term box
    private static JLabel lblTuesdaySummery;                    //Stores the summery value of the second long term box
    private static JLabel imgTuesday;                           //Stores the image value of the second long term box
    private static JLabel lblTuesday;                           //Stores the name value of the second long term box

    private static JLabel lblWednesdayHigh;                     //Stores the high value of the third long term box
    private static JLabel lblWednesdayTemp;                     //Stores the temp value of the third long term box
    private static JLabel lblWednesdayLow;                      //Stores the low value of the third long term box
    private static JLabel lblWednessdaySummery;                 //Stores the summery value of the third long term box
    private static JLabel imgWednesday;                         //Stores the image value of the third long term box
    private static JLabel lblWednesday;                         //Stores the name value of the third long term box

    private static JLabel lblThursdayHigh;                      //Stores the high value of the forth long term box
    private static JLabel lblThursdayTemp;                      //Stores the temp value of the forth long term box
    private static JLabel lblThursdayLow;                       //Stores the low value of the forth long term box
    private static JLabel lblThursdaySummery;                   //Stores the summery value of the forth long term box
    private static JLabel imgThursday;                          //Stores the image value of the forth long term box
    private static JLabel lblThursday;                          //Stores the name value of the forth long term box

    private static JLabel lblFridayHigh;                        //Stores the high value of the last long term box
    private static JLabel lblFridayTemp;                        //Stores the temp value of the last long term box
    private static JLabel lblFridayLow;                         //Stores the low value of the last long term box
    private static JLabel lblFridaySummery;                     //Stores the summery value of the last long term box
    private static JLabel imgFriday;                            //Stores the image value of the last long term box
    private static JLabel lblFriday;                            //Stores the name value of the last long term box

    private static JLabel lblWeathercondition;                  //Stores the current/shortterm weather's condition
    private static JLabel lblLocation;                          //Stores the current weather's location name
    private static JLabel lblTime;                              //Stores the last updated time
    private static JLabel lblTemp;                              //Stores the current weather's temperature
    private static JLabel lblHigh;                              //Stores the current weather's high temperature
    private static JLabel lblLow;                               //Stores the current weather's low temperature
    private static JLabel lblWindSpeedValue;                    //Stores the current weather's wind speed value
    private static JLabel lblPressureValue;                     //Stores the current weather's pressure value
    private static JLabel lblHumidityValue;                     //Stores the current weather's humidity value
    private static JLabel imgMainImage;                         //Stores the current weather's image file
    private static JLabel lblWindDirValue;

    private static JToggleButton btnDelete;                     //Contains the delete button on locations view

    private static JSlider sliderShortTerm;                     //Stores the short term slider
    private static WaitLayerUI layerUI;                         //Stores a layer on top of gui to do loading fadeout/fadein animiation
    private static JLayer<JPanel> jlayer;                       //Stores the layer used by layerUI
    private static Location tempRegion;                         //Stores the location temporarily when the user first enters it

    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d hh:mm a");         //A format used for displaying last update time
    private static DateTimeFormatter STformat = DateTimeFormatter.ofPattern("hh:mm a");             //A format used for short term name times
    private static DateTimeFormatter LTformat = DateTimeFormatter.ofPattern("EEE MMM d");           //A format used for long term name times


    /**
     * *
     *The main method executes the entire program
     * @param args
     */
    public static void main(String[] args)
    {
        //Initialze a locationlist object
        locations = new LocationList();

        //Initialize the configuration
        config = new Configuration(locations);

        //Save the configuration to registry
        config.save();

        //Populate the locations list from the stored configuration
        locations = config.getLocations();

        //Initialize the network controller
        netController = new NetworkController();

        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    //Try to set the look and feel of the program to default
                    JFrame.setDefaultLookAndFeelDecorated(true);//Allows the Look and Feel to change the window frame GUI

                    try
                    {
                        //Try to Set the Look and Feel to Magellan
                        UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel");
                    } catch (Exception e)
                    {
                        //If it doesn't work correctly indicate that it failed to initialize
                        System.out.println("Substance-Magellan failed to initialize");
                    }

                    //Initialize the buttons hashmap to store buttons
                    dynamicButtons = new HashMap<String, JButton>();

                    //Call method to initialize the locations panel
                    initializeLocations();

                } catch (Exception e)
                {
                    //If there is a problem print the stack trace
                    e.printStackTrace();
                }
            }
        });

        //When the program closes run this
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                //Set the locations stored in configuration to the current locations on screen
                config.setLocations(locations);

                //Save the configuration with the new locations
                config.save();
            }
        });

    }//End of main method


    /**
     * This methods updates all TermObjects in all location objects within the location list
     */
    private static void update()
    {
        //Iterate through each location in locations list
        for (Location region : locations.getLocationList())
        {
            //Set the current iteration as the location in netController
            netController.setLocation(region);

            //Call the fetch method in netcontroller, if it returns false that means there was a problem
            if (!netController.fetch())
            {
                //Set the problem flag if netController did not succeed
                problem = true;

                //Break out of the loop
                break;
            }
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    public static void addButton()
    {
        //Prompts the user for input using a CustomInputDialog to use AutoCorrection feature
        final String tempName = CustomInputDialog.showInputDialog("Input a Location", "Which region's weather would you like to see today?", "Enter a location here");

        //If input is empty, do nothing and exit method but update the mars location
        if (tempName == null || tempName.equals(""))
        {
            //Update the mars location
            update();

            //Set flag indicating that current location is not instantly viewable
            displayable = false;

            //Break out of method using return
            return;
        }

        //Initialize tempRegion using name entered by user
        tempRegion = new Location(tempName);

        //Add the temp region to the locations list for tracking
        locations.addRegion(tempRegion);

        //Initialize the currentLocation using user entered information
        currentLocation = tempRegion;

        //Save the new locations list
        config.setLocations(locations);
        config.save();

        //Start loading animation
        layerUI.start();

        //Fetch new updates
        update();

        //Stop loading animation
        layerUI.stop();

        //If there was no problem with the fetch and the button doesnt already exist
        if (!problem && !dynamicButtons.containsKey(tempName))
        {
            //Declare and initialize a new button with the offical name of the temp location
            JButton buttonTemp = new JButton(locations.searchList(tempName).getOfficialName());

            //Add an action listener to the tempButton
            buttonTemp.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    //If the delete button is toggled
                    if (btnDelete.isSelected())
                    {
                        //Remove the button
                        removeButton(tempName);

                        //Delete location from the location list
                        locations.deleteRegion(locations.searchList(tempName));

                        //Call function to Re-initialize the state of the delete button
                        reinitializeDelete();
                    }
                    else
                    {
                        //Set the current location object using button clicked
                        currentLocation = locations.searchList(tempName);

                        //Make location list frame invisible
                        frameLocations.setVisible(false);

                        //Initialize the forecast view
                        initializeForecast();

                        //Set the forecast frame to be visible
                        frameForecast.setVisible(true);
                    }
                }
            });

            //Sets the correct button size for new location button
            buttonTemp.setMaximumSize(buttonSize);

            //Sets the correct botton font for new location button
            buttonTemp.setFont(new Font("Century Gothic", Font.PLAIN, 25));

            //Add the new button to the locationsPanel panel (the list of buttons)
            locationsPanel.add(buttonTemp);

            //Add new button to the dynamicButtons hashmap for tracking
            dynamicButtons.put(tempName, buttonTemp);

            //Call a method to Re-initialize the delete button's state
            reinitializeDelete();

            //Set flag to indicate the current location is displayable
            displayable = true;
        }
        else
        {
            if(dynamicButtons.containsKey(tempName))
            {
                //Inform the user that there has been a problem
                JOptionPane.showMessageDialog(frameLocations, "Oops! That city is already in your location list!\n Find someplace unique using the Auto-Completion feature!");
            }
            else
            {
                //Inform the user that there has been a problem
                JOptionPane.showMessageDialog(frameLocations, "Oops! That city does not exist!\n Try using the Auto-Completion feature!");
            }

            //Delete the location from the locations list
            locations.deleteRegion(tempRegion);

            //Reset the problem flag
            problem = false;

            //Reset the displayable flag
            displayable = false;
        }

        //Set the config's location list to the current location list
        config.setLocations(locations);

        //Save the configuration with the new location list
        config.save();
    }//End of addButton method

    /**
     * Load in the saved locations and create buttons for each one
     */
    public static void loadButtons()
    {
        int timeout = 0;
        //Declare and initialize new arraylist to store all locations
        ArrayList<Location> tempList = locations.getLocationList();

        //Call method to Update all locations until there are no problems
        do{
            //Reset problem variable
            problem = false;

            //Increment timeout so it doesnt run forever with back output
            timeout++;
            update();
        }while(problem && timeout < 10);

        if(timeout == 10)
        {
            //Create a new Locationlist
            locations = new LocationList();

            //ReInitialize the configuration
            config = new Configuration(locations);

            //Inform the user that there has been a problem
            JOptionPane.showMessageDialog(frameLocations, "Oops! We are unable to retrieve your recent locations due to server problems\n Sorry for the inconvenience!");

            //Exit the loading method
            return;
        }

        for (Location aTempList : tempList)
        {
            final String tempName = aTempList.getName();

            if (tempName.equals("mars"))
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
        if (dynamicButtons.isEmpty())
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
        }
        else
        {
            config.setDegrees(tempUnits.IMPERIAL);
            config.save();
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

        btnAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

                addButton();
                locationsPanel.revalidate();
                locationsPanel.validate();

                if (displayable)
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
        btnDelete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
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
                if (btnDelete.isSelected())
                {
                    JOptionPane.showMessageDialog(frameLocations, "Sorry, Mars is not allowed to be deleted.");
                    btnDelete.setSelected(false);
                }
                else
                {
                    currentLocation = locations.searchList("mars");
                    frameLocations.setVisible(false);
                    initializeMars();
                    frameMars.setVisible(true);
                }
            }
        });

        buttonMars.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        locations.addRegion(tempRegion);
        locationsPanel.add(buttonMars);

        buttonSize = buttonMars.getMaximumSize();

        jlayer = new JLayer<JPanel>(panel, layerUI);

        frameLocations.getContentPane().add(panel);

        if (config.exists())
        {
            loadButtons();
        }

        if (locations.getLocationList().size() == 1)
        {
            addButton();
        }

        if (displayable)
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

    private static void initializeMars()
    {
        frameMars = new JFrame();
        frameMars.setResizable(false);
        frameMars.setBounds(100, 100, 642, 475);
        frameMars.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMars.getContentPane().setLayout(null);

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
                frameMars.setVisible(false);
                frameLocations.setVisible(true);
            }
        });

        btnGoToLocations.setBounds(10, 11, 40, 25);
        frameMars.getContentPane().add(btnGoToLocations);

        JLabel imgPlanet = new JLabel("");
        imgPlanet.setBounds(416, 102, 210, 220);
        imgPlanet.setIcon(new ImageIcon(UnitsButton.class.getResource("/images/MARSBAR.png")));
        frameMars.getContentPane().add(imgPlanet);

        JLabel lblMarsTitle = new JLabel("Mars, Universe");
        lblMarsTitle.setFont(new Font("Century Gothic", Font.PLAIN, 36));
        lblMarsTitle.setBounds(62, 5, 350, 50);
        frameMars.getContentPane().add(lblMarsTitle);

        JLabel lblMarsTemp = new JLabel(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTemp()) + config.getTempUnit());
        lblMarsTemp.setFont(new Font("Century Gothic", Font.PLAIN, 33));
        lblMarsTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblMarsTemp.setBounds(76, 162, 200, 39);
        frameMars.getContentPane().add(lblMarsTemp);

        JLabel lblMarsTempMin = new JLabel(String.valueOf(currentLocation.getCurrentTerm().getData()[0].getTempMin()));     // + config.getTempUnit() Taken out because it looks better this way
        lblMarsTempMin.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        lblMarsTempMin.setHorizontalAlignment(SwingConstants.CENTER);
        lblMarsTempMin.setBounds(76, 207, 200, 50);
        frameMars.getContentPane().add(lblMarsTempMin);

        JLabel lblMarsTempHigh = new JLabel(String.valueOf(currentLocation.getCurrentTerm().getData()[0].getTempMax()));    // + config.getTempUnit() Taken out because it looks better this way
        lblMarsTempHigh.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        lblMarsTempHigh.setHorizontalAlignment(SwingConstants.CENTER);
        lblMarsTempHigh.setBounds(76, 115, 200, 50);
        frameMars.getContentPane().add(lblMarsTempHigh);

        JLabel lblEarthTime = new JLabel("Earth Time:");
        lblEarthTime.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblEarthTime.setBounds(34, 290, 82, 23);
        frameMars.getContentPane().add(lblEarthTime);

        String earthDate = currentLocation.getCurrentTerm().getData()[0].getName();

        JLabel lblEarthTimeValue = new JLabel( earthDate.substring(6, 8) + "-" + earthDate.substring(4, 6)+ "-" + earthDate.substring(0, 4));
        lblEarthTimeValue.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblEarthTimeValue.setBounds(113, 290, 67, 23);
        frameMars.getContentPane().add(lblEarthTimeValue);

        JLabel lblMarsPressure = new JLabel("Pressure:");
        lblMarsPressure.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblMarsPressure.setBounds(34, 319, 67, 23);
        frameMars.getContentPane().add(lblMarsPressure);

        JLabel lblMarsPressureValue = new JLabel(String.valueOf(currentLocation.getCurrentTerm().getData()[0].getPressure()) + config.getPressureUnit());
        lblMarsPressureValue.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblMarsPressureValue.setBounds(113, 319, 67, 23);
        frameMars.getContentPane().add(lblMarsPressureValue);

        JLabel lblAtmosphere = new JLabel("Atmosphere:");
        lblAtmosphere.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblAtmosphere.setBounds(195, 319, 93, 23);
        frameMars.getContentPane().add(lblAtmosphere);

        JLabel lblAtmosphereValue = new JLabel(String.valueOf(currentLocation.getCurrentTerm().getData()[0].getDescription()));
        lblAtmosphereValue.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblAtmosphereValue.setBounds(295, 319, 67, 23);
        frameMars.getContentPane().add(lblAtmosphereValue);

        JLabel lblSeason = new JLabel("Season:");
        lblSeason.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblSeason.setBounds(195, 290, 82, 23);
        frameMars.getContentPane().add(lblSeason);

        JLabel lblSeasonValue = new JLabel(currentLocation.getCurrentTerm().getData()[0].getIconID());
        lblSeasonValue.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblSeasonValue.setBounds(295, 290, 67, 23);
        frameMars.getContentPane().add(lblSeasonValue);
    }//End of initializeMars method

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
            lblLocation.setBounds(62, 5, 350, 50);
            frameForecast.getContentPane().add(lblLocation);

            imgMainImage = new JLabel("");
            imgMainImage.setHorizontalAlignment(SwingConstants.CENTER);
            imgMainImage.setIcon(getCorrectImage(currentLocation.getCurrentTerm().getData()[0], "Big"));
            imgMainImage.setBounds(255, 15, 150, 150);
            frameForecast.getContentPane().add(imgMainImage);

            lblTemp = new JLabel(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTemp()) + config.getTempUnit());
            lblTemp.setFont(new Font("Century Gothic", Font.PLAIN, 50));
            lblTemp.setBounds(447, 33, 140, 81);
            frameForecast.getContentPane().add(lblTemp);

            lblHigh = new JLabel(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTempMax()) + config.getTempUnit());
            lblHigh.setBounds(443, 104, 46, 14);
            frameForecast.getContentPane().add(lblHigh);

            lblLow = new JLabel(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTempMin()) + config.getTempUnit());
            lblLow.setBounds(501, 104, 46, 14);
            frameForecast.getContentPane().add(lblLow);

            lblHumidityValue = new JLabel(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getHumidity()) + config.getTempUnit());
            lblHumidityValue.setBounds(93, 147, 46, 14);
            frameForecast.getContentPane().add(lblHumidityValue);

            lblWindSpeedValue = new JLabel(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getWindSpeed()) + config.getWindUnit());
            lblWindSpeedValue.setBounds(93, 126, 55, 14);
            frameForecast.getContentPane().add(lblWindSpeedValue);

            lblPressureValue = new JLabel(String.valueOf(currentLocation.getCurrentTerm().getData()[0].getPressure()) + config.getPressureUnit());
            lblPressureValue.setBounds(93, 168, 70, 14);
            frameForecast.getContentPane().add(lblPressureValue);

            ZonedDateTime tempZDT;
            Date tempDate;

            tempDate = new Date(currentLocation.getCurrentTerm().getData()[0].getSunrise() * 1000L);
            tempZDT = tempDate.toInstant().atZone(ZoneId.systemDefault());

            JLabel lblSunriseValue = new JLabel(tempZDT.format(STformat));
            lblSunriseValue.setBounds(235, 147, 80, 14);
            frameForecast.getContentPane().add(lblSunriseValue);

            tempDate = new Date(currentLocation.getCurrentTerm().getData()[0].getSunset() * 1000L);
            tempZDT = tempDate.toInstant().atZone(ZoneId.systemDefault());

            JLabel lblSunsetValue = new JLabel(tempZDT.format(STformat));
            lblSunsetValue.setBounds(235, 168, 80, 14);
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

            lblFriday = new JLabel(getLTName(currentLocation.getLongTerm().getData()[4].getName()));
            lblFriday.setHorizontalAlignment(SwingConstants.CENTER);
            lblFriday.setBounds(0, 11, 111, 14);
            panel_10.add(lblFriday);

            lblFridayHigh = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[4].getTempMax()) + config.getTempUnit());
            lblFridayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblFridayHigh.setBounds(0, 108, 52, 28);
            panel_10.add(lblFridayHigh);

            lblFridayTemp = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[4].getTemp()) + config.getTempUnit());
            lblFridayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblFridayTemp.setBounds(0, 95, 111, 14);
            panel_10.add(lblFridayTemp);

            lblFridayLow = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[4].getTempMin()) + config.getTempUnit());
            lblFridayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblFridayLow.setBounds(62, 108, 49, 28);
            panel_10.add(lblFridayLow);

            lblFridaySummery = new JLabel(currentLocation.getLongTerm().getData()[4].getDescription());
            lblFridaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblFridaySummery.setBounds(-1, 78, 112, 14);
            panel_10.add(lblFridaySummery);

            imgFriday = new JLabel();
            imgFriday.setHorizontalAlignment(SwingConstants.CENTER);
            imgFriday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[4], "Small"));
            imgFriday.setBounds(0, 30, 111, 46);
            panel_10.add(imgFriday);

            JPanel panel_6 = new JPanel();
            panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel_6.setBounds(10, 0, 113, 147);
            panel2.add(panel_6);
            panel_6.setLayout(null);

            lblMondayHigh = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[0].getTempMax()) + config.getTempUnit());
            lblMondayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblMondayHigh.setBounds(0, 106, 59, 30);
            panel_6.add(lblMondayHigh);

            lblMonday = new JLabel(getLTName(currentLocation.getLongTerm().getData()[0].getName()));
            lblMonday.setHorizontalAlignment(SwingConstants.CENTER);
            lblMonday.setBounds(0, 11, 113, 14);
            lblMonday.setAlignmentY(Component.BOTTOM_ALIGNMENT);
            panel_6.add(lblMonday);

            lblMondayLow = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[0].getTempMin()) + config.getTempUnit());
            lblMondayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblMondayLow.setFont(new Font("Tahoma", Font.PLAIN, 11));
            lblMondayLow.setBounds(56, 106, 57, 30);
            panel_6.add(lblMondayLow);

            lblMondayTemp = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[0].getTemp()) + config.getTempUnit());
            lblMondayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblMondayTemp.setBounds(0, 95, 113, 14);
            panel_6.add(lblMondayTemp);

            lblMondaySummery = new JLabel(currentLocation.getLongTerm().getData()[0].getDescription());
            lblMondaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblMondaySummery.setBounds(0, 78, 113, 14);
            panel_6.add(lblMondaySummery);

            imgMonday = new JLabel("");
            imgMonday.setHorizontalAlignment(SwingConstants.CENTER);
            imgMonday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[0], "Small"));
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

            lblTuesdayHigh = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[1].getTempMax()) + config.getTempUnit());
            lblTuesdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesdayHigh.setBounds(0, 107, 52, 29);
            panel_7.add(lblTuesdayHigh);

            lblTuesdayTemp = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[1].getTemp()) + config.getTempUnit());
            lblTuesdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesdayTemp.setBounds(0, 95, 113, 14);
            panel_7.add(lblTuesdayTemp);

            lblTuesdayLow = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[1].getTempMin()) + config.getTempUnit());
            lblTuesdayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesdayLow.setBounds(62, 107, 51, 29);
            panel_7.add(lblTuesdayLow);

            lblTuesdaySummery = new JLabel(currentLocation.getLongTerm().getData()[1].getDescription());
            lblTuesdaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblTuesdaySummery.setBounds(0, 78, 113, 14);
            panel_7.add(lblTuesdaySummery);

            imgTuesday = new JLabel("");
            imgTuesday.setHorizontalAlignment(SwingConstants.CENTER);
            imgTuesday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[1], "Small"));
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

            lblWednesdayHigh = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[2].getTempMax()) + config.getTempUnit());
            lblWednesdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednesdayHigh.setBounds(0, 105, 46, 31);
            panel_8.add(lblWednesdayHigh);

            lblWednesdayTemp = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[2].getTemp()) + config.getTempUnit());
            lblWednesdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednesdayTemp.setBounds(0, 95, 113, 14);
            panel_8.add(lblWednesdayTemp);

            lblWednesdayLow = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[2].getTempMin()) + config.getTempUnit());
            lblWednesdayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednesdayLow.setBounds(62, 105, 51, 31);
            panel_8.add(lblWednesdayLow);

            lblWednessdaySummery = new JLabel(currentLocation.getLongTerm().getData()[2].getDescription());
            lblWednessdaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblWednessdaySummery.setBounds(0, 78, 113, 14);
            panel_8.add(lblWednessdaySummery);

            imgWednesday = new JLabel("");
            imgWednesday.setHorizontalAlignment(SwingConstants.CENTER);
            imgWednesday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[2], "Small"));
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

            lblThursdayHigh = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[3].getTempMax()) + config.getTempUnit());
            lblThursdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursdayHigh.setBounds(0, 107, 52, 29);
            panel_9.add(lblThursdayHigh);

            lblThursdayTemp = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[3].getTemp()) + config.getTempUnit());
            lblThursdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursdayTemp.setBounds(0, 95, 113, 14);
            panel_9.add(lblThursdayTemp);

            lblThursdayLow = new JLabel(String.valueOf((int) currentLocation.getLongTerm().getData()[3].getTempMin()) + config.getTempUnit());
            lblThursdayLow.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursdayLow.setBounds(62, 107, 51, 29);
            panel_9.add(lblThursdayLow);

            lblThursdaySummery = new JLabel(currentLocation.getLongTerm().getData()[3].getDescription());
            lblThursdaySummery.setHorizontalAlignment(SwingConstants.CENTER);
            lblThursdaySummery.setBounds(-2, 78, 115, 14);
            panel_9.add(lblThursdaySummery);

            imgThursday = new JLabel("");
            imgThursday.setHorizontalAlignment(SwingConstants.CENTER);
            imgThursday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[3], "Small"));
            imgThursday.setBounds(0, 30, 111, 46);
            panel_9.add(imgThursday);

            JSeparator separator = new JSeparator();
            separator.setBounds(0, 258, 626, 23);
            frameForecast.getContentPane().add(separator);

            lblWeathercondition = new JLabel(GetUpperCaseDescription(0, currentLocation.getCurrentTerm()));
            lblWeathercondition.setFont(new Font("Century Gothic", Font.PLAIN, 23));
            lblWeathercondition.setBounds(8, 90, 220, 28);
            frameForecast.getContentPane().add(lblWeathercondition);

            JLabel lblWindSpeed = new JLabel("Wind Speed:");
            lblWindSpeed.setBounds(8, 126, 81, 14);
            frameForecast.getContentPane().add(lblWindSpeed);

            JLabel lblHumidity = new JLabel("Humidity:");
            lblHumidity.setBounds(8, 147, 61, 14);
            frameForecast.getContentPane().add(lblHumidity);

            JLabel lblPressure = new JLabel("Pressure:");
            lblPressure.setBounds(8, 168, 66, 14);
            frameForecast.getContentPane().add(lblPressure);

            JLabel lblWindDir = new JLabel("Direction:");
            lblWindDir.setBounds(175, 126, 80, 14);
            frameForecast.getContentPane().add(lblWindDir);

            lblWindDirValue = new JLabel(getDirection(currentLocation.getCurrentTerm().getData()[0].getWindDirection()));
            lblWindDirValue.setBounds(235, 126, 80, 14);
            frameForecast.getContentPane().add(lblWindDirValue);

            JLabel lblSunrise = new JLabel("Sunrise:");
            lblSunrise.setBounds(175, 147, 80, 14);
            frameForecast.getContentPane().add(lblSunrise);

            JLabel lblSunset = new JLabel("Sunset:");
            lblSunset.setBounds(175, 168, 80, 14);
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


            sliderShortTerm = new JSlider();
            sliderShortTerm.setBounds(0, 211, 626, 50);

            sliderShortTerm.addChangeListener(new ChangeListener()
            {
                @Override
                public void stateChanged(ChangeEvent e)
                {
                    JSlider source = (JSlider) e.getSource();
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

            labelTable.put(0, new JLabel("Current"));
            for (int i = 0; i < currentLocation.getShortTerm().data.length; i++)
            {
                tempDate = new Date(Integer.parseInt(currentLocation.getShortTerm().data[i].getName()) * 1000L);
                tempZDT = tempDate.toInstant().atZone(ZoneId.systemDefault());
                labelTable.put(i + 1, new JLabel(tempZDT.format(STformat)));
            }
            sliderShortTerm.setLabelTable(labelTable);

            sliderShortTerm.setPaintLabels(true);

            frameForecast.getContentPane().add(sliderShortTerm);

            JLabel lblLastUpdated = new JLabel("Last Updated:");
            lblLastUpdated.setBounds(400, 125, 100, 14);
            frameForecast.getContentPane().add(lblLastUpdated);

            lblTime = new JLabel(ZonedDateTime.now().format(format));
            lblTime.setBounds(499, 125, 130, 14);
            frameForecast.getContentPane().add(lblTime);
        } catch (NullPointerException e)
        {
            System.out.println("Not all elements displayed properly!");
        }
    }//End of initializeForecast method

    /**
     *
     * @param windDirection
     * @return
     */
    private static String getDirection(int windDirection)
    {
        if(windDirection == 0 || windDirection == 360)
        {
            return "N";
        }
        else if(windDirection > 0 && windDirection < 90)
        {
            return "NE";
        }
        else if(windDirection == 90)
        {
            return "E";
        }
        else if(windDirection > 90 && windDirection < 180)
        {
            return "SE";
        }
        else if(windDirection == 180)
        {
            return "S";
        }
        else if(windDirection > 180 && windDirection < 270)
        {
            return "SW";
        }
        else if(windDirection == 270)
        {
            return "W";
        }
        else if(windDirection > 270 && windDirection < 360)
        {
            return "NW";
        }

        return "N";
    }//End of getDirection method

    /**
     *
     * @param data
     * @param size
     * @return
     */
    private static Icon getCorrectImage(BaseData data, String size)
    {
        return new ImageIcon(UnitsButton.class.getResource("/images/" + size + "/" +  data.getIconID() + ".png"));
    }

    /**
     *
     * @param hours
     */
    private static void updateSTGUI(int hours)
    {
        if (hours == 0)
        {
            lblTemp.setText(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTemp()) + config.getTempUnit());
            lblHigh.setText(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTempMax()) + config.getTempUnit());
            lblLow.setText(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTempMin()) + config.getTempUnit());
            lblHumidityValue.setText(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getHumidity()) + config.getHumidUnit());
            lblWindSpeedValue.setText(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getWindSpeed()) + config.getWindUnit());
            lblPressureValue.setText(String.valueOf(currentLocation.getCurrentTerm().getData()[0].getPressure()) + config.getPressureUnit());
            lblWeathercondition.setText(GetUpperCaseDescription(0, currentLocation.getCurrentTerm()));
            lblWindDirValue.setText(getDirection(currentLocation.getCurrentTerm().getData()[0].getWindDirection()));
            imgMainImage.setIcon(getCorrectImage(currentLocation.getCurrentTerm().getData()[0], "Big"));
            return;
        }

        lblTemp.setText(String.valueOf((int) currentLocation.getShortTerm().getData()[hours - 1].getTemp()) + config.getTempUnit());
        lblHigh.setText(String.valueOf((int) currentLocation.getShortTerm().getData()[hours - 1].getTempMax()) + config.getTempUnit());
        lblLow.setText(String.valueOf((int) currentLocation.getShortTerm().getData()[hours - 1].getTempMin()) + config.getTempUnit());
        lblHumidityValue.setText(String.valueOf((int) currentLocation.getShortTerm().getData()[hours - 1].getHumidity()) + config.getHumidUnit());
        lblWindSpeedValue.setText(String.valueOf((int) currentLocation.getShortTerm().getData()[hours - 1].getWindSpeed()) + config.getWindUnit());
        lblPressureValue.setText(String.valueOf(currentLocation.getShortTerm().getData()[hours - 1].getPressure()) + config.getPressureUnit());
        lblWeathercondition.setText(GetUpperCaseDescription(hours-1, currentLocation.getShortTerm()));
        lblWindDirValue.setText(getDirection(currentLocation.getShortTerm().getData()[hours - 1].getWindDirection()));
        imgMainImage.setIcon(getCorrectImage(currentLocation.getShortTerm().getData()[hours - 1], "Big"));


    }//End of updateSTGUI

    private static String GetUpperCaseDescription(int hours, TermObject term)
    {
        String condition = term.getData()[hours].getDescription();

        //Stores a constant StringBuffer object used for capitilizing description's first letter
        StringBuffer stringbf = new StringBuffer();
        //Stores a constant matcher object used for capitilizing description's first letter
        Matcher m = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(condition);

        while (m.find())
        {
            m.appendReplacement(stringbf, m.group(1).toUpperCase() + m.group(2).toLowerCase());
        }

        return m.appendTail(stringbf).toString();
    }//End of GetUpperCaseDescription method

    /**
     * Updates all GUI elements on the forecast screen
     */
    private static void updateGUI()
    {
        //Update the location name
        lblLocation = new JLabel(currentLocation.getName());

        //Update the Temp/High/Low values
        lblHigh.setText(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTempMax()) + config.getTempUnit());
        lblTemp.setText(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTemp()) + config.getTempUnit());
        lblLow.setText(String.valueOf((int) currentLocation.getCurrentTerm().getData()[0].getTempMin()) + config.getTempUnit());

        //Updates the long term element for Monday
        lblMondayHigh.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[0].getTempMax()) + config.getTempUnit());
        lblMondayLow.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[0].getTempMin()) + config.getTempUnit());
        lblMondayTemp.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[0].getTemp()) + config.getTempUnit());
        lblMondaySummery.setText(currentLocation.getLongTerm().getData()[0].getDescription());
        lblMonday.setText(getLTName(currentLocation.getLongTerm().getData()[0].getName()));
        imgMonday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[0], "Small"));

        //Updates the long term element for Tuesday
        lblTuesdayHigh.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[1].getTempMax()) + config.getTempUnit());
        lblTuesdayTemp.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[1].getTemp()) + config.getTempUnit());
        lblTuesdayLow.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[1].getTempMin()) + config.getTempUnit());
        lblTuesdaySummery.setText(currentLocation.getLongTerm().getData()[1].getDescription());
        lblTuesday.setText(getLTName(currentLocation.getLongTerm().getData()[1].getName()));
        imgTuesday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[1], "Small"));

        //Updates the long term element for Wednesday
        lblWednesdayHigh.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[2].getTempMax()) + config.getTempUnit());
        lblWednesdayTemp.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[2].getTemp()) + config.getTempUnit());
        lblWednesdayLow.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[2].getTempMin()) + config.getTempUnit());
        lblWednessdaySummery.setText(currentLocation.getLongTerm().getData()[2].getDescription());
        lblWednesday.setText(getLTName(currentLocation.getLongTerm().getData()[2].getName()));
        imgWednesday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[2], "Small"));

        //Updates the long term element for Thursday
        lblThursdayHigh.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[3].getTempMax()) + config.getTempUnit());
        lblThursdayTemp.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[3].getTemp()) + config.getTempUnit());
        lblThursdayLow.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[3].getTempMin()) + config.getTempUnit());
        lblThursdaySummery.setText(currentLocation.getLongTerm().getData()[3].getDescription());
        lblThursday.setText(getLTName(currentLocation.getLongTerm().getData()[3].getName()));
        imgThursday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[3], "Small"));

        //Updates the long term element for Friday
        lblFridayHigh.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[4].getTempMax()) + config.getTempUnit());
        lblFridayTemp.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[4].getTemp()) + config.getTempUnit());
        lblFridayLow.setText(String.valueOf((int) currentLocation.getLongTerm().getData()[4].getTempMin()) + config.getTempUnit());
        lblFridaySummery.setText(currentLocation.getLongTerm().getData()[4].getDescription());
        lblFriday.setText(getLTName(currentLocation.getLongTerm().getData()[4].getName()));
        imgFriday.setIcon(getCorrectImage(currentLocation.getLongTerm().getData()[4], "Small"));

        //Updates the weather condition label and time label
        lblWeathercondition.setText(GetUpperCaseDescription(0, currentLocation.getCurrentTerm()));
        lblTime.setText(ZonedDateTime.now().format(format));

        sliderShortTerm.setValue(0);
    }//End of UpdateGUI method

    /**
     * This method gets in a string unix time object and returns a long term formatted date string
     *
     * @param num unix time
     * @return Long term formatted date string
     */
    private static String getLTName(String num)
    {
        //Convert the string into an integer value and store it
        int dt = Integer.parseInt(num);

        //Convert the dt object into java date object
        Date date = new Date(dt*1000L);

        //Use the date object to retrieve a ZonedDateTime
        ZonedDateTime ZDT = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        //return a string output of ZDT in the format of long term time stated above
        return ZDT.format(LTformat);
    }//End of getLTName method
}//End of main class
