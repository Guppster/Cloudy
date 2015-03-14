package team21.Cloudy;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

public class keyTextField extends KeyAdapter
{
    private JTextField txtField;
    private List<String> regions;

    public keyTextField(JTextField txtFieldParam)
    {
        txtField = txtFieldParam;
        regions = new ArrayList<>();
        ReadInFile();
    }


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

    public void autoComplete(String str)
    {
        String complete = "";
        int start = str.length();
        int last = str.length();
        int a;

        for (a = 0; a < regions.size(); a++)
        {
            if (regions.get(a).startsWith(str))
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

    public void ReadInFile()
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(keyTextField.class.getResource("/city_list_clean.txt").getPath()));
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
