import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
import processing.opengl.PShader;

class Buildings {
    private final PApplet p;
    private final boolean isRight;
    private final PShader shader;
    private final float centerZ = 300;
    private final float centerY;
    private final float amplitude = 500;
    private final float step; // radians per frame
    private float angleRad;
    private final PVector pos = new PVector();
    private final PVector size = new PVector();

    public Buildings(PApplet p, boolean isRight, PShader shader) {
        this.p = p;
        this.isRight = isRight;
        this.shader = shader;
        this.angleRad = p.random(0, PApplet.TWO_PI);
        this.step = PApplet.TWO_PI / 360f;
        this.centerY = p.height - p.height / 7f + (isRight ?  0 : 350);
        pos.x = isRight ? p.width - p.width / 6f : p.width / 6f;
        pos.y = p.height / 2f;
        pos.z = -300;
        randomizeSize();
    }

    public void display(PGraphics pg) {
        pg.pushMatrix();
        pg.rotate(PApplet.radians(-15));
        // Translate to the position, but adjust Y to place the base (not center) at pos.y
        pg.translate(pos.x, pos.y - size.y/2, pos.z);
        pg.shader(shader);
        pg.fill(150);
        pg.box(size.x, size.y, size.z);
        pg.resetShader();
        pg.popMatrix();
        parametricMove();
    }

    private void parametricMove() {
        pos.z = centerZ + amplitude * PApplet.cos(angleRad);
        pos.y = centerY + amplitude * PApplet.sin(angleRad);
        angleRad = (angleRad + step) % PApplet.TWO_PI;
        if (angleRad < step) randomizeSize();
    }

    private void randomizeSize() {
        size.x = p.random(p.width / 12f, p.width / 6f);
        size.y = p.random(60f, 800f);
        size.z = p.random(p.width / 12f, p.width / 6f);
    }
}

public class Sketch extends PApplet {
    private final ArrayList<Buildings> lbuildings = new ArrayList<>();
    private final ArrayList<Buildings> rbuildings = new ArrayList<>();
    private static final int BUILDING_COUNT = 20;
    private PShader fragShader;
    float road;
    public void settings() {
        fullScreen(P3D);
    }

    public void setup() {
        fragShader = loadShader("buildingFrag.frag", "buildingVert.vert");
        background(90);
        for (int i = 0; i < BUILDING_COUNT; i++) {
            lbuildings.add(new Buildings(this, false, fragShader));
            rbuildings.add(new Buildings(this, true, fragShader));
        }
        road = 0;
    }

    public void draw() {
        background(0);
        lights();
        for (Buildings b : lbuildings) {
            b.display(this.g);
        }
        push();
        rotate(radians(30));
        for (Buildings b : rbuildings) {
            b.display(this.g);
        }
        pop();
        translate(width/2, height +120, 350);
        rotateX(radians(-road));
        sphere(600);
        road += 1f;
        // println(frameRate);
    }

    public static void main(String[] args) {
        PApplet.main("Sketch");
    }
}
