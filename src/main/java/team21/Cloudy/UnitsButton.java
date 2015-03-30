package team21.Cloudy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/**
 * The units slider
 */
public class UnitsButton extends JToggleButton implements ActionListener,
        Runnable, MouseMotionListener, MouseListener, HierarchyListener
{

    //Declare fields
    Image buttonImage = new ImageIcon(
            UnitsButton.class.getResource("/images/button.png")).getImage();
    Image bgImage = new ImageIcon(UnitsButton.class.getResource("/images/bg.png"))
            .getImage();

    boolean isMetric;
    boolean initial = true;

    int buttonX;
    int deltaX = -1;
    // boolean threadStop;
    boolean selected;
    boolean drag;

    /**
     * returns selected value
     * @return is it selected?
     */
    public boolean isSelected()
    {
        return selected;
    }

    /**
     * sets selected value
     * @param selected is it selected?
     */
    public void setSelected(boolean selected)
    {
        this.selected = selected;
        // threadStop = true;
        new Thread(this).start();
    }

    /**
     * Constructor
     * @param isMetric is the initial position metric?
     */
    public UnitsButton(boolean isMetric)
    {
        this.isMetric = isMetric;
        this.addActionListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addHierarchyListener(this);
    }

    /**
     * Painting the elements to the screen
     * @param g
     */
    public void paint(Graphics g)
    {
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null, null);

        if(isMetric && initial)
        {
           buttonX = this.getWidth() / 2 + 1;
            initial = false;
        }
        g.drawImage(buttonImage, buttonX, 0, getWidth() / 2, this.getHeight(),
                null, null);
    }

    /**
     * Every time button is clicked
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //If there is no dragging going on, call run
        if (!drag)
        {
            selected = !selected;
            // threadStop = true;
            new Thread(this).start();
        }
    }

    /**
     * Run this every time the button is clicked
     */
    @Override
    public void run()
    {
        // while (true) {
        // if (threadStop) {
        if (this.isSelected())
        {
            for (; buttonX <= this.getWidth() / 2; buttonX++)
            {
                this.repaint();
                try
                {
                    Thread.sleep(5);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

        }
        else
        {
            for (; buttonX > 0; buttonX--)
            {
                this.repaint();
                try
                {
                    Thread.sleep(5);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        repaint();
        // threadStop = false;
        // }
        // }
    }

    /**
     * This is for when the user is trying to drag the button instead of click it
     * @param evt The event taking place
     */
    @Override
    public void mouseDragged(MouseEvent evt)
    {
        drag = true;
        // threadStop = false;
        if (deltaX == -1)
        {
            deltaX = evt.getX() - buttonX;
        }

        buttonX = evt.getX() - deltaX;

        if (buttonX < 0)
        {
            buttonX = 0;
        }
        if (buttonX > this.getWidth() / 2)
        {
            buttonX = this.getWidth() / 2;
        }

        this.repaint();
    }

    /**
     *
     * @param arg0 The event taking place
     */
    @Override
    public void mouseMoved(MouseEvent arg0)
    {
    }

    /**
     *
     * @param arg0 The event taking place
     */
    @Override
    public void mouseClicked(MouseEvent arg0)
    {
    }

    /**
     *
     * @param arg0 The event taking place
     */
    @Override
    public void mouseEntered(MouseEvent arg0)
    {
    }

    /**
     *
     * @param arg0 The event taking place
     */
    @Override
    public void mouseExited(MouseEvent arg0)
    {
    }

    /**
     *
     * @param arg0 The event taking place
     */
    @Override
    public void mousePressed(MouseEvent arg0)
    {
        // threadStop = false;
    }

    /**
     *
     * @param arg0 The event taking place
     */
    @Override
    public void mouseReleased(MouseEvent arg0)
    {
        deltaX = -1;
        if (drag)
        {
            if (buttonX < this.getWidth() / 4)
            {
                this.setSelected(false);
            }
            else
            {
                this.setSelected(true);
            }
        }
        drag = false;
    }

    /**
     *
     * @param arg0 The event taking place
     */
    @Override
    public void hierarchyChanged(HierarchyEvent arg0)
    {
        new Thread(this).start();
    }
}
