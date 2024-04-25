/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;

/**
 *
 * @author dmx
 */
public class ModelColor {

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getPosition() {
        return position;
    }

    public void setPosition(float position) {
        this.position = position;
    }

    public ModelColor(Color color, float position) {
        this.color = color;
        this.position = position;
    }

    public ModelColor() {
    }

    private Color color;
    private float position;
}
