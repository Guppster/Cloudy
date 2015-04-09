package team21.Cloudy;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.Toolkit;

/**
 * This class is a custom input dialog used when user is entering data. Custom was needed to implement auto correction features
 */
public class CustomInputDialog
{
    /**
     * Label for title of input Dialong
     */
    public static JLabel lbl;

    /**
     * Text field for entering location
     */
    public static JTextField txt;

    /**
     * Button to navigate the interface
     */
    public static JButton ok_button, cancel_button;

    /**
     * The dialog box itself
     */
    public static JDialog optionWindow;

    /**
     * Stores the value to return at end
     */
    public static String returnValue = null;

    /**
     * Main viewing method
     * @param title title of the window
     * @param message message to be displayed
     * @param guess   guess the user input, put in textfield
     * @return returns the user input
     */
    public static String showInputDialog(String title, String message, String guess)
    {
        //Add two buttons one for OK and one for Cancel
        CustomInputDialog.returnValue = null;
        Object[] options = {"OK", "Cancel"};

        //Show the message passed into this method
        lbl = new JLabel(message); txt = new JTextField(guess, 40);

        //Add the custom key listener that does AutoCorrection
        txt.addKeyListener(new keyTextField(txt));

        //Set Buttons
        ok_button = new javax.swing.JButton((String)options[0]);
        cancel_button = new javax.swing.JButton((String)options[1]);

        //Set each buttons actions when pressed
        ok_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomInputDialog.returnValue = txt.getText();
                optionWindow.dispose();
            }
        });
        cancel_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionWindow.dispose();
            }
        });

        //GUI PLACEMENT CODE
        Container text_container = new java.awt.Container();

        text_container.setLayout(new FlowLayout());

        text_container.add(txt);

        Container button_container = new java.awt.Container();

        button_container.setLayout(new FlowLayout());

        button_container.add(ok_button);
        button_container.add(cancel_button);

        Container ct = new java.awt.Container();
        GroupLayout layout = new GroupLayout(ct);

        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(lbl).addContainerGap())
                        .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(text_container).addContainerGap())
                        .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(button_container).addContainerGap())
        );

        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lbl)
                                        .addContainerGap()
                                        .addComponent(text_container)
                                        .addContainerGap()
                                        .addComponent(button_container)
                                        .addContainerGap()
                        )
        );

        ct.setLayout(layout);

        optionWindow = new JDialog((JFrame)null, title, true);

        optionWindow.setContentPane(ct);
        txt.selectAll();

        //Pack all elements into window and dont let it be resizable
        optionWindow.pack();
        optionWindow.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(), size = optionWindow.getSize();

        optionWindow.setLocation((screenSize.width-size.width)/2, (screenSize.height-size.height)/2);
        optionWindow.setVisible(true);
        return CustomInputDialog.returnValue;
    }
}//End of CustomInputDialog class
