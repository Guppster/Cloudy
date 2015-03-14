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

public class CustomInputDialog
{
    public static JLabel lbl;
    public static JTextField txt;
    public static JButton ok_button, cancel_button;
    public static JDialog optionWindow;

    public static String returnValue = null;

    public static String showInputDialog(String title, String message, String guess)
    {
        CustomInputDialog.returnValue = null;
        Object[] options = {"OK", "Cancel"};

        lbl = new JLabel(message); txt = new JTextField(guess, 40);

        txt.addKeyListener(new keyTextField(txt));

        ok_button = new javax.swing.JButton((String)options[0]);
        cancel_button = new javax.swing.JButton((String)options[1]);

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

        optionWindow.pack();
        optionWindow.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(), size = optionWindow.getSize();

        optionWindow.setLocation((screenSize.width-size.width)/2, (screenSize.height-size.height)/2);
        optionWindow.setVisible(true);
        return CustomInputDialog.returnValue;
    }
}