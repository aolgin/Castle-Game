import javalib.funworld.*;
import javalib.worldimages.*;
import javalib.colors.*;
import java.util.Random;

//assignment 4
//pair 026
//Olgin, Adam
//aolgin
//Levine, Ben
//levinebe

// represents a castle, or the game world
public class Castle extends World {
    
    // the knight moving
    Knight knight;
    
    // the dragon moving
    Dragon dragon;
    
    // the list of falling fireballs
    ILoF fbs;
    
    // the score
    int score;
    
    // constants
    int width = 500;
    int height = 450;
    CartPt start = new CartPt(30, 350); // left door loc
    CartPt end = new CartPt(430, 350); // right door loc

    
    WorldImage background =
            new FromFileImage 
            (new Posn (width / 2, height / 2), "p026-wall.jpg");
    
    Castle(Knight knight, Dragon dragon, ILoF fbs, int score) {
        this.knight = knight;
        this.dragon = dragon;
        this.fbs = fbs;
        this.score = score;
    }
    
    /* TEMPLATE:
    FIELDS:
    ...this.knight...  --Knight
    ...this.dragon...  --Dragon
    ...this.fbs...     --ILoF
    ...this.score...   --int
    
    METHODS:
    ... this.onTick() ...              -- World
    ... this.onKeyEvent(String) ...    -- World
    ... this.makeImage() ...           -- WorldImage
    ... this.worldEnds() ...           -- WorldEnd
     
    METHODS FOR FIELDS:
    ...this.knight.onKeyEvent(String)...   --Knight
    ...this.knight.moveLeft()...           --Knight
    ...this.knight.moveRight()...          --Knight
    ...this.knight.knightImage()...        --WorldImage
    ...this.knight.reachDoor(CartPt)...    --boolean
    ...this.knight.loseLife(Fireball)...   --int
    ...this.knight.isHit(Fireball)...      --boolean
      
    ...this.dragon.dragonImage()...  --WorldImage
    ...this.dragon.hitLeft()...      --boolean
    ...this.dragon.hitRight()...     --boolean
    ...this.dragon.move()...         --Dragon
    ...this.dragon.moveTo()...       --Dragon
    
    ...this.fbs.falling()...         --ILoF
    ...this.fbs.iLoFImage()...       --WorldImage
    ...this.fbs.isDead(Knight)...    --Knight
    
     */
    
    
    // produces the Castle after one tick passed
    // the dragon moves and the fireballs fall, the rest stays
    public World onTick() {
        return
             new Castle (this.fbs.isDead(this.knight), this.dragon.moveTo(),
                     this.fbs.falling(), this.score);
    }
    
    // produce the Castle in response to a key event
    // the knight moves left or right, the dragon and fireballs do not move
    // if knight is at right door, reset him to left door and increase score
    public World onKeyEvent(String ke) {
        if (knight.reachDoor(this.end))
            return new Castle(
                    new Knight(this.start, knight.name, knight.lives),
                    this.dragon, this.fbs, this.score + 5);
        else if (knight.reachDoor(this.start))
            return new Castle(
                    new Knight(new CartPt(80, 350), knight.name, knight.lives),
                    this.dragon, this.fbs, this.score);
        else
            return new Castle (this.knight.onKeyEvent(ke), this.dragon, 
                    this.fbs, this.score);
    }
    
    
    // After each tick, check if the knight has 0 lives or 100 points
    // if yes, end the game
    public WorldEnd worldEnds() {
        if (this.knight.lives == 0)
            return new WorldEnd(true, this.makeImage().overlayImages(
                    new TextImage(new Posn (width / 2, height / 2),
                            "The Knight has died", 36, 1, new White())));
        else if (this.score == 100)
                return new WorldEnd(true, 
                        new FromFileImage(new Posn (width / 2, height / 2),
                        "p026-anothercastle.png"));
        else
            return (new WorldEnd(false, this.makeImage()));
    }
    
    // produce the image that represents this castle game world
    public WorldImage makeImage() {
      return 
      this.background.overlayImages(this.knight.knightImage(), 
                                    this.dragon.dragonImage(),
                                    this.fbs.iLoFImage(),
                                    new TextImage (new Posn(10, 30), 
                                            Integer.toString(this.score),
                                            20, new Black()),
                                    new TextImage (new Posn(450, 30), 
                                            Integer.toString(this.knight.lives)
                                                    + " lives",
                                                    20, new Black()));
      }
}

// represents a knight
class Knight {
    CartPt loc;
    String name;
    int lives; // how many lives he has
    
    Knight(CartPt loc, String name, int lives) {
        this.loc = loc;
        this.name = name;
        this.lives = lives;
    }
    
    /* TEMPLATE
      
      Fields:
      ...this.loc...   --CartPt
      ...this.name...  --String
      ...this.lives... --int
      
      Methods:
      ...this.onKeyEvent(String)...   --Knight
      ...this.moveLeft()...           --Knight
      ...this.moveRight()...          --Knight
      ...this.knightImage()...        --WorldImage
      ...this.reachDoor(Door)...      --boolean
      ...this.loseLife()...           --int
      ...this.isHit()...              --boolean
      
      Methods for Fields:
      ...this.loc.moveBy(int, int)... --CartPt
      ...this.loc.distTo(CartPt)...   --double
     */
    
    // did this knight reach the given door?
    boolean reachDoor(CartPt d) {
        return this.loc.x == d.x;
    }
    
    // produce the image of this knight at its position
    WorldImage knightImage() {
        return new FromFileImage(this.loc, this.name);
    }
    
    // moves the knight left and right on corresponding key press
    Knight onKeyEvent(String ke) {
        if (ke.equals("left"))
            return this.moveLeft();
        else if (ke.equals("right"))
            return this.moveRight();
        else 
            return this;
    }
    
    // from this knight produce a new knight, moved left a bit (3 pixels)
    Knight moveLeft() {
      return new Knight(this.loc.moveBy(-50, 0), this.name, this.lives);
    }
    
    // from this knight produce a new knight, moved right a bit (3 pixels)
    Knight moveRight() {
      return new Knight(this.loc.moveBy(50, 0), this.name, this.lives);
    }
    
    // is this knight hit by the given fireball?
    boolean isHit (Fireball f) {
        return (50 >= this.loc.distTo(f.loc)) &&
                (this.loc.distTo(f.loc) >= 0);
    }
    
    // remove one life from this knight if the given
    // fireball hits him
    Knight loseLife(Fireball f) {
        if (this.isHit(f))
           return new Knight(new CartPt(30, 350), this.name, this.lives - 1);
        else
           return this;
    }
}

// represents a dragon
class Dragon {
    CartPt loc;
    int vel;
    String name;
    
    Dragon(CartPt loc, int vel, String name) {
        this.loc = loc;
        this.vel = vel;
        this.name = name;
    }
    
    // random
    Random rand = new Random();
    
    /* TEMPLATE
    
    Fields:
    ...this.loc...    --CartPt
    ...this.vel...    --int
    ...this.name...   --String
    
    Methods:
    ...this.dragonImage()...  --WorldImage
    ...this.hitLeft()...      --boolean
    ...this.hitRight()...     --boolean
    ...this.move()...         --Dragon
    ...this.moveTo()...       --Dragon
    
    Methods for Fields:
    ...this.loc.moveBy(int, int)... --CartPt
    ...this.loc.distTo(CartPt)...   --double
    */
    
    
    // produce the image of this dragon at its position
    WorldImage dragonImage() {
        return new FromFileImage(this.loc, this.name);
    }
    
    // does this dragon hit the left tower?
    boolean hitLeft() {
        return this.loc.x <= 200;
    }
    
    // does this dragon hit the right tower?
    boolean hitRight() {
        return this.loc.x >= 300;
    }
    
    // moves this dragon by its velocity
    Dragon move() {
        return new Dragon(new CartPt(this.loc.x + this.vel, this.loc.y),
                this.vel, this.name);
    }
    
    // moves the dragon between two points.
    // reverses velocity once this dragon
    // hits either predefined edge
    Dragon moveTo() {
        if (this.hitLeft())
            return new Dragon(this.loc, this.vel * -1, this.name).move();
        else if (this.hitRight())
            return new Dragon(this.loc, this.vel * -1, this.name).move();
        else
            return this.move();
    }
}

//extension of the Posn class with move methods
class CartPt extends Posn {
    CartPt(int x, int y) {
        super(x, y);
        }

    // produce a point moved by the given distance from this point
    CartPt moveBy(int dx, int dy) {
        return new CartPt(this.x + dx, this.y + dy);
        }

    // Compute the distance from this point to the given one
    double distTo(CartPt that) {
        return Math.sqrt((this.x - that.x) * (this.x - that.x) + 
                (this.y - that.y) * (this.y - that.y));
        }


}