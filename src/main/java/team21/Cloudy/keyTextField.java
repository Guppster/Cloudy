package team21.Cloudy;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

/**
 * This is a custom keylistener for textfields that will autocomplete location names all around the world based on a text file.
 */
public class keyTextField extends KeyAdapter
{
    //Declare fields
    private JTextField txtField;
    private List<String> regions;

    /**
     * Constructor
     * @param txtFieldParam the textfield which is attached to this listener
     */
    public keyTextField(JTextField txtFieldParam)
    {
        //Initialize Fields
        txtField = txtFieldParam;
        regions = new ArrayList<>();
        ReadInFile();
    }

    /**
     * Specifies what to do when a key is pressed
     * @param key which key is pressed
     */
    public void keyPressed(KeyEvent key)
    {
        switch (key.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
                break;
            case KeyEvent.VK_ENTER:
                txtField.setText(txtField.getText());
                break;
            default:
                EventQueue.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        String kt = txtField.getText();
                        autoComplete(kt);

                    }
                });
        }

    }

    /**
     * Auto completes the rest of the string based on textfile
     * @param str the previously typed string
     */
    public void autoComplete(String str)
    {
        String complete = "";
        int start = str.length();
        int last = str.length();
        int a;

        for (a = 0; a < regions.size(); a++)
        {
            if (regions.get(a).toLowerCase().startsWith(str.toLowerCase()))
            {
                complete = regions.get(a);
                last = complete.length();
                break;
            }
        }
        if (last > start)
        {
            txtField.setText(complete);
            txtField.setCaretPosition(last);
            txtField.moveCaretPosition(start);
        }
    }

    /**
     *
     */
    public void ReadInFile()
    {
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(keyTextField.class.getResourceAsStream("/other/city_list_clean.txt")));
            String str;

            while ((str = in.readLine()) != null)
            {
                regions.add(str);
            }
        } catch (IOException ignored)
        {
            System.out.println("Error: Could not read in region file to do auto-completion");
        }
    }
}
