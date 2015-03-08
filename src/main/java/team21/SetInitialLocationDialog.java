package team21;

import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SetInitialLocationDialog extends JDialog {
	public SetInitialLocationDialog(JFrame parent) {
		super(parent, true);

		setUndecorated(true);
		AWTUtilities.setWindowOpaque(this, false);

		setTitle("Set Initial Location");

		JLabel label = new JLabel("Enter a Location!");
		label.setForeground(Color.WHITE);
		label.setFont(new Font(label.getFont().getFontName(), Font.PLAIN, 20));

		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(0xAA05579));
		panel.setLayout(new GridBagLayout());
		panel.add(label);
		panel.setPreferredSize(new Dimension(400, 300));

		panel.setFocusable(true);
		panel.requestFocusInWindow();

		getContentPane().add(panel);

	}
}
