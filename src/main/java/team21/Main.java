package team21;

import aurelienribon.tweenengine.Tween;
import team21.slidinglayout.*;

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

        JLabel lblLocation = new JLabel(" London, Ontario");
        lblLocation.setFont(new Font("Century Gothic", Font.PLAIN, 21));
        lblLocation.setBounds(62, 5, 231, 50);
        frame.getContentPane().add(lblLocation);

        JSlider sliderShortTerm = new JSlider();
        sliderShortTerm.setPaintLabels(true);
        sliderShortTerm.setBounds(0, 211, 626, 26);
        frame.getContentPane().add(sliderShortTerm);

        JLabel lblTemp = new JLabel("-17\u00B0");
        lblTemp.setFont(new Font("Century Gothic", Font.PLAIN, 43));
        lblTemp.setBounds(447, 33, 114, 81);
        frame.getContentPane().add(lblTemp);

        JLabel lblHigh = new JLabel("10");
        lblHigh.setBounds(443, 104, 46, 14);
        frame.getContentPane().add(lblHigh);

        JLabel lblLow = new JLabel("-19");
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

        JLabel lblFridayHigh = new JLabel("5");
        lblFridayHigh.setHorizontalAlignment(SwingConstants.CENTER);
        lblFridayHigh.setBounds(0, 108, 52, 28);
        panel_10.add(lblFridayHigh);

        JLabel lblFridayTemp = new JLabel("10\u00B0");
        lblFridayTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblFridayTemp.setBounds(0, 95, 111, 14);
        panel_10.add(lblFridayTemp);

        JLabel lblFridayLow = new JLabel("-4");
        lblFridayLow.setHorizontalAlignment(SwingConstants.CENTER);
        lblFridayLow.setBounds(62, 108, 49, 28);
        panel_10.add(lblFridayLow);

        JLabel lblFridaySummery = new JLabel("Cloudy");
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

        JLabel lblMondayHigh = new JLabel("5");
        lblMondayHigh.setHorizontalAlignment(SwingConstants.CENTER);
        lblMondayHigh.setBounds(0, 106, 59, 30);
        panel_6.add(lblMondayHigh);

        JLabel lblMonday = new JLabel("Monday");
        lblMonday.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonday.setBounds(0, 11, 113, 14);
        lblMonday.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        panel_6.add(lblMonday);

        JLabel lblMondayLow = new JLabel("-4");
        lblMondayLow.setHorizontalAlignment(SwingConstants.CENTER);
        lblMondayLow.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblMondayLow.setBounds(56, 106, 57, 30);
        panel_6.add(lblMondayLow);

        JLabel lblMondayTemp = new JLabel("10\u00B0");
        lblMondayTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblMondayTemp.setBounds(0, 95, 113, 14);
        panel_6.add(lblMondayTemp);

        JLabel lblMondaySummery = new JLabel("Cloudy");
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

        JLabel lblTuesdayHigh = new JLabel("5");
        lblTuesdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
        lblTuesdayHigh.setBounds(0, 107, 52, 29);
        panel_7.add(lblTuesdayHigh);

        JLabel lblTuesdayTemp = new JLabel("10\u00B0");
        lblTuesdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblTuesdayTemp.setBounds(0, 95, 113, 14);
        panel_7.add(lblTuesdayTemp);

        JLabel lblTuesdayLow = new JLabel("-4");
        lblTuesdayLow.setHorizontalAlignment(SwingConstants.CENTER);
        lblTuesdayLow.setBounds(62, 107, 51, 29);
        panel_7.add(lblTuesdayLow);

        JLabel lblTuesdaySummery = new JLabel("Cloudy");
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

        JLabel lblWednesdayHigh = new JLabel(" 5");
        lblWednesdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
        lblWednesdayHigh.setBounds(0, 105, 46, 31);
        panel_8.add(lblWednesdayHigh);

        JLabel lblWednesdayTemp = new JLabel("10\u00B0");
        lblWednesdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblWednesdayTemp.setBounds(0, 95, 113, 14);
        panel_8.add(lblWednesdayTemp);

        JLabel lblWednesdayLow = new JLabel("-4");
        lblWednesdayLow.setHorizontalAlignment(SwingConstants.CENTER);
        lblWednesdayLow.setBounds(62, 105, 51, 31);
        panel_8.add(lblWednesdayLow);

        JLabel lblWednessdaySummery = new JLabel("Cloudy");
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

        JLabel lblThursdayHigh = new JLabel("5");
        lblThursdayHigh.setHorizontalAlignment(SwingConstants.CENTER);
        lblThursdayHigh.setBounds(0, 107, 52, 29);
        panel_9.add(lblThursdayHigh);

        JLabel lblThursdayTemp = new JLabel("10\u00B0");
        lblThursdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblThursdayTemp.setBounds(0, 95, 113, 14);
        panel_9.add(lblThursdayTemp);

        JLabel lblThursdayLow = new JLabel("-4");
        lblThursdayLow.setHorizontalAlignment(SwingConstants.CENTER);
        lblThursdayLow.setBounds(62, 107, 51, 29);
        panel_9.add(lblThursdayLow);

        JLabel lvlThursdaySummery = new JLabel("Cloudy");
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

        JLabel lblWeathercondition = new JLabel("WeatherCondition");
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

            }
        });
        btnRefresh.setBounds(531, 11, 89, 23);
        frame.getContentPane().add(btnRefresh);
    }
}//End of main class
