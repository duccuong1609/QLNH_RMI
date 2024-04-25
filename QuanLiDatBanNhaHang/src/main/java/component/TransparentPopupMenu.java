/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPopupMenu;

/**
 *
 * @author dmx
 */
public class TransparentPopupMenu extends JPopupMenu {
    public TransparentPopupMenu() {
        setOpaque(false); // Make the popup menu non-opaque
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f)); // Set the transparency level as needed
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight()); // Fill the background with the translucent color
        super.paintComponent(g2d);
        g2d.dispose();
    }
}
