/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import javax.swing.JMenuItem;

/**
 *
 * @author dmx
 */
public class MyJMenuItem extends JMenuItem {
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius;

    public MyJMenuItem(String text) {
        super(text);
        // Initialize default values
        color = new Color(234, 124, 105);
        colorOver = new Color(31, 29, 43);
        colorClick = new Color(31, 29, 43);
        borderColor = new Color(31, 29, 43);
        radius = 10;
        setBackground(new Color(234, 124, 105));
        setForeground(Color.WHITE);
        setHorizontalAlignment(CENTER);
        setHorizontalTextPosition(CENTER);
        setVerticalAlignment(CENTER);
        setVerticalTextPosition(CENTER);
        setFont(new Font("Segoe UI",Font.BOLD,14));
        
        // Add mouse listener to handle color changes
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setColor(color);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(color);
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setBackground(colorClick);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBackground(color);
            }
        });
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Paint border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        // Border set 2 Pix
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }

    public void addMouseListener() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
