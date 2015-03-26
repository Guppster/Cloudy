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

public class UnitsButton extends JToggleButton implements ActionListener,
        Runnable, MouseMotionListener, MouseListener, HierarchyListener
{
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

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
        // threadStop = true;
        new Thread(this).start();
    }

    public UnitsButton(boolean isMetric)
    {
        this.isMetric = isMetric;
        this.addActionListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addHierarchyListener(this);
    }

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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!drag)
        {
            selected = !selected;
            // threadStop = true;
            new Thread(this).start();
        }
    }

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

    @Override
    public void mouseMoved(MouseEvent arg0)
    {
    }

    @Override
    public void mouseClicked(MouseEvent arg0)
    {
    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    {
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
    }

    @Override
    public void mousePressed(MouseEvent arg0)
    {
        // threadStop = false;
    }

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

    @Override
    public void hierarchyChanged(HierarchyEvent arg0)
    {
        new Thread(this).start();
    }
}
