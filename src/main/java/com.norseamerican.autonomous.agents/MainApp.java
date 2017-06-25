package com.norseamerican.autonomous.agents;

import processing.core.PApplet;

public class MainApp extends PApplet {

    public static void main(String[] args) {
        PApplet.main("com.norseamerican.autonomous.agents.MainApp", args);
    }

    Mover mover;

    public void settings() {
        size(900, 700);
    }

    public void setup() {
        background(255);
        mover = new Mover(this, random(width), random(height));
    }

    public void draw() {
        background(255);
        mover.update();
        mover.display();
    }
}