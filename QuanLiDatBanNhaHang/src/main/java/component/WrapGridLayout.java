/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

/**
 *
 * @author Laptop
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WrapGridLayout implements LayoutManager {
    private int hgap;
    private int vgap;
    private List<Dimension> componentSizes;

    public WrapGridLayout(int hgap, int vgap) {
        this.hgap = hgap;
        this.vgap = vgap;
        this.componentSizes = new ArrayList<>();
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        Dimension size = comp.getPreferredSize();
        componentSizes.add(size);
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int x = insets.left + hgap;
        int y = insets.top + vgap;
        int maxWidth = parent.getWidth() - insets.left - insets.right;
        int rowHeight = 0;

        for (int i = 0; i < parent.getComponentCount(); i++) {
            Component component = parent.getComponent(i);
            Dimension size = componentSizes.get(i);
            if (x + size.width > maxWidth) {
                x = insets.left + hgap;
                y += rowHeight + vgap;
                rowHeight = 0;
            }
            component.setBounds(x, y, size.width, size.height);
            x += size.width + hgap;
            rowHeight = Math.max(rowHeight, size.height);
        }
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        synchronized (parent.getTreeLock()) {
            int maxWidth = parent.getWidth();
            int x = 0;
            int y = 0;
            int rowHeight = 0;
            for (int i = 0; i < parent.getComponentCount(); i++) {
                Component component = parent.getComponent(i);
                Dimension size = componentSizes.get(i);
                if (x + size.width > maxWidth) {
                    x = 0;
                    y += rowHeight + vgap;
                    rowHeight = 0;
                }
                x += size.width + hgap;
                rowHeight = Math.max(rowHeight, size.height);
            }
            return new Dimension(maxWidth, y + rowHeight + vgap);
        }
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        // Not needed
    }
}

