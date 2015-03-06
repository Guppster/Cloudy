package team21;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import java.awt.FlowLayout;


public class tempGUI {

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
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 520, 418);
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
        sliderShortTerm.setBounds(0, 211, 504, 26);
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
        separator_1.setBounds(0, 187, 504, 50);
        frame.getContentPane().add(separator_1);

        JPanel panel = new JPanel();
        panel.setBounds(0, 269, 504, 110);
        frame.getContentPane().add(panel);

        JButton btnNewButton = new JButton("New button");

        JButton btnNewButton_1 = new JButton("New button");

        JButton btnNewButton_2 = new JButton("New button");

        JButton btnNewButton_3 = new JButton("New button");

        JButton btnNewButton_4 = new JButton("New button");
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.add(btnNewButton);
        panel.add(btnNewButton_1);
        panel.add(btnNewButton_2);
        panel.add(btnNewButton_3);
        panel.add(btnNewButton_4);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 258, 504, 23);
        frame.getContentPane().add(separator);
    }
}
