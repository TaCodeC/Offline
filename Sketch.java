import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
class Buildings {
    PVector pos, size;
    PApplet parent;

    public Buildings(PApplet p) {
        pos = new PVector(p.width/2, p.width/2, -300);
        size = new PVector(p.random(p.width/80, p.width/40), p.random(10, 40), p.random(p.width/80, p.width/40));
    }

    public void display(PGraphics pg) {
        pg.pushMatrix();
        pg.translate(pos.x, pos.y, pos.z);
        pg.fill(150, 150, 150);
        pg.box(size.x, size.y, size.z);
        pg.popMatrix();
        update();
    }
    public void update(){
        pos.z+=1;
    }
}


public class Sketch extends PApplet {

    Buildings b ;

        public void settings() {
            fullScreen(P3D);
        }

        public void setup() {
            background(90);
            b = new Buildings(this);
        }

        public void draw() {
            background(0);
            lights();
            b.display(g);
        }

        public static void main(String[] args) {
            PApplet.main("Sketch");
        }
}