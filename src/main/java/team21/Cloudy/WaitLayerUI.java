package team21.Cloudy;

/*
* Copyright (c) 2011, Oracle and/or its affiliates. All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions
* are met:
*
*   - Redistributions of source code must retain the above copyright
*     notice, this list of conditions and the following disclaimer.
*
*   - Redistributions in binary form must reproduce the above copyright
*     notice, this list of conditions and the following disclaimer in the
*     documentation and/or other materials provided with the distribution.
*
*   - Neither the name of Oracle or the names of its
*     contributors may be used to endorse or promote products derived
*     from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
* IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
* THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
* PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
* EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
* PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
* LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

/**
 * This class is used to make a loading animation if the API is taking too long
 */
public class WaitLayerUI extends LayerUI<JPanel> implements ActionListener
{
  /**
   * Stores if it is running or not
   */
  private boolean mIsRunning;

  /**
   * Stores if it is fading out or not
   */
  private boolean mIsFadingOut;

  /**
   * Keeps track of how long it will load for
   */
  private Timer mTimer;

  /**
   * Stores the angle of the loading spinner
   */
  private int mAngle;

  /**
   * Stores a count of the fade in
   */
  private int mFadeCount;

  /**
   * Sets limit of how long it can fade in for
   */
  private int mFadeLimit = 15;

  /**
   * Paints the loader on to the screen
   * @param g The graphics that should be painted
   * @param c The component that it should be painted on
   */
  @Override
  public void paint (Graphics g, JComponent c) {
    int w = c.getWidth();
    int h = c.getHeight();

    // Paint the view.
    super.paint (g, c);

    if (!mIsRunning) {
      return;
    }

    Graphics2D g2 = (Graphics2D)g.create();

    float fade = (float)mFadeCount / (float)mFadeLimit;
    // Gray it out.
    Composite urComposite = g2.getComposite();
    g2.setComposite(AlphaComposite.getInstance(
        AlphaComposite.SRC_OVER, .5f * fade));
    g2.fillRect(0, 0, w, h);
    g2.setComposite(urComposite);

    // Paint the wait indicator.
    int s = Math.min(w, h) / 5;
    int cx = w / 2;
    int cy = h / 2;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setStroke(
        new BasicStroke(s / 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    g2.setPaint(Color.white);
    g2.rotate(Math.PI * mAngle / 180, cx, cy);
    for (int i = 0; i < 12; i++) {
      float scale = (11.0f - (float)i) / 11.0f;
      g2.drawLine(cx + s, cy, cx + s * 2, cy);
      g2.rotate(-Math.PI / 6, cx, cy);
      g2.setComposite(AlphaComposite.getInstance(
          AlphaComposite.SRC_OVER, scale * fade));
    }

    g2.dispose();
  }

  /**
   * Everytime the user does anything this method is run
   * @param e The action that is being performed
   */
  public void actionPerformed(ActionEvent e) {
    if (mIsRunning) {
      firePropertyChange("tick", 0, 1);
      mAngle += 3;
      if (mAngle >= 360) {
        mAngle = 0;
      }
      if (mIsFadingOut) {
        if (--mFadeCount == 0) {
          mIsRunning = false;
          mTimer.stop();
        }
      }
      else if (mFadeCount < mFadeLimit) {
        mFadeCount++;
      }
    }
  }

  /**
   * Starts the animation
   */
  public void start() {
    if (mIsRunning) {
      return;
    }
    
    // Run a thread for animation.
    mIsRunning = true;
    mIsFadingOut = false;
    mFadeCount = 0;
    int fps = 24;
    int tick = 1000 / fps;
    mTimer = new Timer(tick, this);
    mTimer.start();
  }

  /**
   * Stops the animation
   */
  public void stop() {
    mIsFadingOut = true;
  }

  /**
   * Applys a change to the spinner (spins)
   * @param pce The event that triggered the change
   * @param l The layer that it is altering
   */
  @Override
  public void applyPropertyChange(PropertyChangeEvent pce, JLayer l) {
    if ("tick".equals(pce.getPropertyName())) {
      l.repaint();
    }
  }
}
