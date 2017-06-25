package com.norseamerican.autonomous.agents;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Mover {

    PApplet pApplet;

    PVector location;
    PVector velocity;
    PVector acceleration;
//    Additional variable for size
    float r = 3.0f;
    float maxforce = 0.1f;
    float maxspeed = 4.0f;

    Mover(PApplet pApplet, float x, float y) {
        this.pApplet = pApplet;
        acceleration = new PVector(0, 0);
        velocity = new PVector(0, 1);
        location = new PVector(x, y);
    }

    //    Our standard “Euler integration” motion model
    void update() {
        velocity.add(acceleration);
        velocity.limit(maxspeed);
        location.add(velocity);
        acceleration.mult(0);
        checkEdges();
    }

    void applyForce(PVector force) {
        acceleration.add(force);
    }

    void display() {
//        Vehicle is a triangle pointing in the direction of velocity; since it is drawn pointing up, we rotate it an additional 90 degrees.
        float theta = velocity.heading() + PConstants.PI/2;
        pApplet.fill(175);
        pApplet.stroke(0);
        pApplet.pushMatrix();
        pApplet.translate(location.x,location.y);
        pApplet.rotate(theta);
        pApplet.beginShape();
        pApplet.vertex(0, -r*2);
        pApplet.vertex(-r, r*2);
        pApplet.vertex(r, r*2);
        pApplet.endShape(PConstants.CLOSE);
        pApplet.popMatrix();
    }

    // TODO: Move this to container instead of agent
    void checkEdges() {

        if (location.x > pApplet.width) {
            location.x = 0;
        } else if (location.x < 0) {
            location.x = pApplet.width;
        }

        if (location.y > pApplet.height) {
            location.y = 0;
        } else if (location.y < 0) {
            location.y = pApplet.height;
        }

    }
}