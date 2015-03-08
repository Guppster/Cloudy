package team21;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: Gurpreet
 */
public class Main
{
    private static LocationList locations;
    private static Configuration config;
    private static NetworkController netController;
    private static Location currentLocation;

    static JLabel lblFriday;
    static JLabel lblFridayHigh;
    static JLabel lblFridayTemp;
    static JLabel lblFridayLow;
    static JLabel lblFridaySummery;
    static JLabel imgFriday;

    static JLabel lblMondayHigh;
    static JLabel lblMonday;
    static JLabel lblMondayLow;
    static JLabel lblMondayTemp;
    static JLabel lblMondaySummery;
    static JLabel imgMonday;

    static JLabel lblTuesday;
    static JLabel lblTuesdayHigh;
    static JLabel lblTuesdayTemp;
    static JLabel lblTuesdayLow;
    static JLabel lblTuesdaySummery;
    static JLabel imgTuesday;

    static JLabel lblWednessday;
    static JLabel lblWednesdayHigh;
    static JLabel lblWednesdayTemp;
    static JLabel lblWednesdayLow;
    static JLabel lblWednessdaySummery;
    static JLabel imgWednesday;

    static JLabel lblThursday;
    static JLabel lblThursdayHigh;
    static JLabel lblThursdayTemp;
    static JLabel lblThursdayLow;
    static JLabel lvlThursdaySummery;
    static JLabel imgThursday;

    static JLabel lblWeathercondition;
    static JLabel lblHumidity;
    static JLabel lblWindSpeed;
    static JLabel lblPressure;
    static JLabel lblSunrise;
    static JLabel lblSunset;
    static JLabel lblLocation;
    static JLabel lblTemp;
    static JLabel lblHigh;
    static JLabel lblLow;

    private static JFrame frame;

    /**
     * *
     *
     * @param args
     */
    public static void main(String[] args)
    {
        locations = new LocationList();
        config = new Configuration(locations);
        netController = new NetworkController();


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame.setDefaultLookAndFeelDecorated(true);//Allows the Look and Feel to change the window frame GUI

                    try
                    {
                        //Set the Look and Feel to Magellan
                        UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel");
                    }catch(Exception e){System.out.println("Substance-Magellan failed to initialize");}

                    initialize();

                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        checkInitialRun();

        currentLocation = locations.searchList(currentLocation.getName());


    }//End of main method

    private static void checkInitialRun()
    {
        if(locations.isListEmpty())
        {
            SetInitialLocationDialog dialog = new SetInitialLocationDialog(frame);
            SwingUtils.fadeIn(dialog);
            //Add region to location list
        }
        else
        {
            update();
            updateGUI();
        }
    }


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
    private static void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 642, 475);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnGoToLocations = new JButton("=");
        btnGoToLocations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnGoToLocations.setBounds(10, 11, 41, 23);
        frame.getContentPane().add(btnGoToLocations);

        JLabel lblLocation = new JLabel(currentLocation.getName());
        lblLocation.setFont(new Font("Century Gothic", Font.PLAIN, 21));
        lblLocation.setBounds(62, 5, 231, 50);
        frame.getContentPane().add(lblLocation);

        JSlider sliderShortTerm = new JSlider();
        sliderShortTerm.setPaintLabels(true);
        sliderShortTerm.setBounds(0, 211, 626, 26);
        frame.getContentPane().add(sliderShortTerm);

        JLabel lblTemp = new JLabel(String.valueOf(currentLocation.getCurrentTerm().data[0].getTemp()));
        lblTemp.setFont(new Font("Century Gothic", Font.PLAIN, 43));
        lblTemp.setBounds(447, 33, 114, 81);
        frame.getContentPane().add(lblTemp);

        JLabel lblHigh = new JLabel(String.valueOf(currentLocation.getCurrentTerm().data[0].getTempMax()));
        lblHigh.setBounds(443, 104, 46, 14);
        frame.getContentPane().add(lblHigh);

        JLabel lblLow = new JLabel(String.valueOf(currentLocation.getCurrentTerm().data[0].getTempMin()));
        lblLow.setBounds(501, 104, 46, 14);
        frame.getContentPane().add(lblLow);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 187, 626, 50);
        frame.getContentPane().add(separator_1);

        JPanel panel = new JPanel();

        panel.setBounds(0, 269, 626, 169);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JPanel panel_10 = new JPanel();
        panel_10.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_10.setBounds(502, 0, 111, 147);
        panel.add(panel_10);
        panel_10.setLayout(null);

        JLabel lblFriday = new JLabel("Friday");
        lblFriday.setHorizontalAlignment(SwingConstants.CENTER);
        lblFriday.setBounds(0, 11, 111, 14);
        panel_10.add(lblFriday);

        JLabel lblFridayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().data[4].getTempMax()));
        lblFridayHigh.setHorizontalAlignment(SwingConstants.CENTER);
        lblFridayHigh.setBounds(0, 108, 52, 28);
        panel_10.add(lblFridayHigh);

        JLabel lblFridayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().data[4].getTemp()));
        lblFridayTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblFridayTemp.setBounds(0, 95, 111, 14);
        panel_10.add(lblFridayTemp);

        JLabel lblFridayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().data[4].getTempMin()));
        lblFridayLow.setHorizontalAlignment(SwingConstants.CENTER);
        lblFridayLow.setBounds(62, 108, 49, 28);
        panel_10.add(lblFridayLow);

        JLabel lblFridaySummery = new JLabel(currentLocation.getLongTerm().data[4].getDescription());
        lblFridaySummery.setHorizontalAlignment(SwingConstants.CENTER);
        lblFridaySummery.setBounds(-1, 78, 112, 14);
        panel_10.add(lblFridaySummery);

        JLabel imgFriday = new JLabel("");
        imgFriday.setHorizontalAlignment(SwingConstants.CENTER);
        imgFriday.setBounds(0, 46, 111, 14);
        panel_10.add(imgFriday);

        JPanel panel_6 = new JPanel();
        panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_6.setBounds(10, 0, 113, 147);
        panel.add(panel_6);
        panel_6.setLayout(null);

        JLabel lblMondayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().data[0].getTempMax()));
        lblMondayHigh.setHorizontalAlignment(SwingConstants.CENTER);
        lblMondayHigh.setBounds(0, 106, 59, 30);
        panel_6.add(lblMondayHigh);

        JLabel lblMonday = new JLabel("Monday");
        lblMonday.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonday.setBounds(0, 11, 113, 14);
        lblMonday.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        panel_6.add(lblMonday);

        JLabel lblMondayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().data[0].getTempMin()));
        lblMondayLow.setHorizontalAlignment(SwingConstants.CENTER);
        lblMondayLow.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblMondayLow.setBounds(56, 106, 57, 30);
        panel_6.add(lblMondayLow);

        JLabel lblMondayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().data[0].getTemp()));
        lblMondayTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblMondayTemp.setBounds(0, 95, 113, 14);
        panel_6.add(lblMondayTemp);

        JLabel lblMondaySummery = new JLabel(currentLocation.getLongTerm().data[0].getDescription());
        lblMondaySummery.setHorizontalAlignment(SwingConstants.CENTER);
        lblMondaySummery.setBounds(0, 78, 113, 14);
        panel_6.add(lblMondaySummery);

        JLabel imgMonday = new JLabel("");
        imgMonday.setHorizontalAlignment(SwingConstants.CENTER);
        //imgMonday.setIcon(new ImageIcon(MainWindow.class.getResource("/sun/print/resources/oneside.png")));
        imgMonday.setBounds(1, 46, 111, 14);
        panel_6.add(imgMonday);

        JPanel panel_7 = new JPanel();
        panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_7.setBounds(133, 0, 113, 147);
        panel.add(panel_7);
        panel_7.setLayout(null);

        JLabel lblTuesday = new JLabel("Tuesday");
        lblTuesday.setHorizontalAlignment(SwingConstants.CENTER);
        lblTuesday.setBounds(0, 11, 113, 14);
        panel_7.add(lblTuesday);

        JLabel lblTuesdayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().data[1].getTempMax()));
        lblTuesdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
        lblTuesdayHigh.setBounds(0, 107, 52, 29);
        panel_7.add(lblTuesdayHigh);

        JLabel lblTuesdayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().data[1].getTemp()));
        lblTuesdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblTuesdayTemp.setBounds(0, 95, 113, 14);
        panel_7.add(lblTuesdayTemp);

        JLabel lblTuesdayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().data[1].getTempMin()));
        lblTuesdayLow.setHorizontalAlignment(SwingConstants.CENTER);
        lblTuesdayLow.setBounds(62, 107, 51, 29);
        panel_7.add(lblTuesdayLow);

        JLabel lblTuesdaySummery = new JLabel(currentLocation.getLongTerm().data[1].getDescription());
        lblTuesdaySummery.setHorizontalAlignment(SwingConstants.CENTER);
        lblTuesdaySummery.setBounds(0, 78, 113, 14);
        panel_7.add(lblTuesdaySummery);

        JLabel imgTuesday = new JLabel("");
        imgTuesday.setHorizontalAlignment(SwingConstants.CENTER);
        imgTuesday.setBounds(1, 46, 111, 14);
        panel_7.add(imgTuesday);

        JPanel panel_8 = new JPanel();
        panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_8.setBounds(256, 0, 113, 147);
        panel.add(panel_8);
        panel_8.setLayout(null);

        JLabel lblWednessday = new JLabel("Wednesday");
        lblWednessday.setHorizontalAlignment(SwingConstants.CENTER);
        lblWednessday.setBounds(0, 11, 113, 14);
        panel_8.add(lblWednessday);

        JLabel lblWednesdayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().data[2].getTempMax()));
        lblWednesdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
        lblWednesdayHigh.setBounds(0, 105, 46, 31);
        panel_8.add(lblWednesdayHigh);

        JLabel lblWednesdayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().data[2].getTemp()));
        lblWednesdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblWednesdayTemp.setBounds(0, 95, 113, 14);
        panel_8.add(lblWednesdayTemp);

        JLabel lblWednesdayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().data[2].getTempMin()));
        lblWednesdayLow.setHorizontalAlignment(SwingConstants.CENTER);
        lblWednesdayLow.setBounds(62, 105, 51, 31);
        panel_8.add(lblWednesdayLow);

        JLabel lblWednessdaySummery = new JLabel(currentLocation.getLongTerm().data[2].getDescription());
        lblWednessdaySummery.setHorizontalAlignment(SwingConstants.CENTER);
        lblWednessdaySummery.setBounds(0, 78, 113, 14);
        panel_8.add(lblWednessdaySummery);

        JLabel imgWednesday = new JLabel("");
        imgWednesday.setHorizontalAlignment(SwingConstants.CENTER);
        imgWednesday.setBounds(1, 46, 109, 14);
        panel_8.add(imgWednesday);

        JPanel panel_9 = new JPanel();
        panel_9.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_9.setBounds(379, 0, 113, 147);
        panel.add(panel_9);
        panel_9.setLayout(null);

        JLabel lblThursday = new JLabel("Thursday");
        lblThursday.setHorizontalAlignment(SwingConstants.CENTER);
        lblThursday.setBounds(0, 11, 113, 14);
        panel_9.add(lblThursday);

        JLabel lblThursdayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().data[3].getTempMax()));
        lblThursdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
        lblThursdayHigh.setBounds(0, 107, 52, 29);
        panel_9.add(lblThursdayHigh);

        JLabel lblThursdayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().data[3].getTemp()));
        lblThursdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblThursdayTemp.setBounds(0, 95, 113, 14);
        panel_9.add(lblThursdayTemp);

        JLabel lblThursdayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().data[3].getTempMin()));
        lblThursdayLow.setHorizontalAlignment(SwingConstants.CENTER);
        lblThursdayLow.setBounds(62, 107, 51, 29);
        panel_9.add(lblThursdayLow);

        JLabel lvlThursdaySummery = new JLabel(currentLocation.getLongTerm().data[3].getDescription());
        lvlThursdaySummery.setHorizontalAlignment(SwingConstants.CENTER);
        lvlThursdaySummery.setBounds(-2, 78, 115, 14);
        panel_9.add(lvlThursdaySummery);

        JLabel imgThursday = new JLabel("");
        imgThursday.setHorizontalAlignment(SwingConstants.CENTER);
        imgThursday.setBounds(3, 46, 109, 14);
        panel_9.add(imgThursday);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 258, 626, 23);
        frame.getContentPane().add(separator);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(247, 90, 46, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblWeathercondition = new JLabel(currentLocation.getCurrentTerm().data[0].getDescription());
        lblWeathercondition.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblWeathercondition.setBounds(8, 104, 136, 14);
        frame.getContentPane().add(lblWeathercondition);

        JLabel lblHumidity = new JLabel("Humidity:");
        lblHumidity.setBounds(8, 129, 61, 14);
        frame.getContentPane().add(lblHumidity);

        JLabel lblWindSpeed = new JLabel("Wind Speed:");
        lblWindSpeed.setBounds(8, 146, 81, 14);
        frame.getContentPane().add(lblWindSpeed);

        JLabel lblPressure = new JLabel("Pressure:");
        lblPressure.setBounds(8, 165, 66, 14);
        frame.getContentPane().add(lblPressure);

        JLabel lblSunrise = new JLabel("Sunrise:");
        lblSunrise.setBounds(150, 146, 46, 14);
        frame.getContentPane().add(lblSunrise);

        JLabel lblSunset = new JLabel("Sunset:");
        lblSunset.setBounds(150, 165, 46, 14);
        frame.getContentPane().add(lblSunset);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                update();
                updateGUI();
            }
        });
        btnRefresh.setBounds(531, 11, 89, 23);
        frame.getContentPane().add(btnRefresh);
    }

    /*

     */
    private static void updateGUI()
    {
        lblLocation = new JLabel(currentLocation.getName());
        lblHigh = new JLabel(String.valueOf(currentLocation.getCurrentTerm().data[0].getTempMax()));
        lblTemp = new JLabel(String.valueOf(currentLocation.getCurrentTerm().data[0].getTemp()));
        lblLow = new JLabel(String.valueOf(currentLocation.getCurrentTerm().data[0].getTempMin()));

        lblFridayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().data[4].getTempMax()));
        lblFridayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().data[4].getTemp()));
        lblFridayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().data[4].getTempMin()));
        lblFridaySummery = new JLabel(currentLocation.getLongTerm().data[4].getDescription());
        imgFriday = new JLabel("");

        lblMondayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().data[0].getTempMax()));
        lblMondayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().data[0].getTempMin()));
        lblMondayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().data[0].getTemp()));
        lblMondaySummery = new JLabel(currentLocation.getLongTerm().data[0].getDescription());
        imgMonday = new JLabel("");

        lblTuesdayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().data[1].getTempMax()));
        lblTuesdayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().data[1].getTemp()));
        lblTuesdayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().data[1].getTempMin()));
        lblTuesdaySummery = new JLabel(currentLocation.getLongTerm().data[1].getDescription());
        imgTuesday = new JLabel("");

        lblWednesdayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().data[2].getTempMax()));
        lblWednesdayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().data[2].getTemp()));
        lblWednesdayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().data[2].getTempMin()));
        lblWednessdaySummery = new JLabel(currentLocation.getLongTerm().data[2].getDescription());
        imgWednesday = new JLabel("");

        lblThursdayHigh = new JLabel(String.valueOf(currentLocation.getLongTerm().data[3].getTempMax()));
        lblThursdayTemp = new JLabel(String.valueOf(currentLocation.getLongTerm().data[3].getTemp()));
        lblThursdayLow = new JLabel(String.valueOf(currentLocation.getLongTerm().data[3].getTempMin()));
        lvlThursdaySummery = new JLabel(currentLocation.getLongTerm().data[3].getDescription());
        imgThursday = new JLabel("");

        lblWeathercondition = new JLabel(currentLocation.getCurrentTerm().data[0].getDescription());
    }
}//End of main class
