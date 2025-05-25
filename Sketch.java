import processing.core.PApplet;

public class Sketch extends PApplet {

    public void settings() {
        size(800, 600, P3D);
    }

    public void setup() {
        background(30);
    }

    public void draw() {
        lights();
        background(50);
        translate(width / 2, height / 2);
        rotateY(frameCount * 0.01f);
        box(100);
    }

    public static void main(String[] args) {
        PApplet.main("Sketch");
    }
}