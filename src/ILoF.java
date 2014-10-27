import javalib.worldimages.FromFileImage;
import javalib.worldimages.WorldImage;
import javalib.worldimages.*;
import java.awt.Color;
import java.util.Random;

//assignment 4
//pair 026
//Olgin, Adam
//aolgin
//Levine, Ben
//levinebe

// represents a list of fireballs
public interface ILoF {
    
    // moves this ILoF
    ILoF falling();
    
    // renders this ILoF
    WorldImage iLoFImage();
    
    // is the given knight hit by any of the fireballs in this list?
    Knight isDead(Knight k);
}

// represents an empty list of fireballs
class MtLoF implements ILoF {
    
     /* TEMPLATE
     
      Methods:
      ...this.falling()...      --ILoF
      ...this.iLoFImage()...    --WorldImage
      ...this.isDead(Knight)... --Knight
      
     */
    
    // moves this empty list
    public ILoF falling() {
        return this;
    }
    
    // renders this empty list
    // currently renders it as a miniscule
    // outlined circle in a remote loc in background
    public WorldImage iLoFImage() {
        //return null;
        return new CircleImage(new CartPt(480, 100), 2, 
                new Color(154, 217, 234)); // skyBlue
    }
    
    // is the given knight hit by any fireball in this list?
    public Knight isDead(Knight k) {
        return k;
    }
}

// represents a filled list of fireballs
class ConsLoF implements ILoF {
    Fireball first;
    ILoF rest;
    
    ConsLoF(Fireball first, ILoF rest) {
        this.first = first;
        this.rest = rest;
    }
    
    /* TEMPLATE
     
     Fields:
     ...this.first... --Fireball
     ...this.rest...  --ILoF
     
     Methods:
     ...this.falling()...      --ILoF
     ...this.ILoFImage()...    --WorldImage
     ...this.isDead(Knight)... --Knight
     
     Methods for Fields:
     ...this.first.fireballImage().. --WorldImage
     ...this.first.hitGround(int)... --boolean
     ...this.first.fall()...         --Fireball
     ...this.rest.ILoFImage()...     --WorldImage
     ...this.rest.falling()...       --ILoF
     ...this.rest.isDead(Knight)...  --Knight
     */
    
    //move a list of fireballs
    public ILoF falling() {
        return
            new ConsLoF(this.first.fall(), this.rest.falling());
    }
    
    // renders a list of fireballs
    public WorldImage iLoFImage() {
        return
            this.first.fireballImage().overlayImages(
                    this.rest.iLoFImage());
    }
    
    // is the given hit by any fireball in this list?
    // if so, take a life
    public Knight isDead(Knight k) {
        if (k.isHit(this.first))
            return k.loseLife(this.first);
        else 
            return (this.rest.isDead(k)); 
    }
}

// represents a fireball
class Fireball {
    CartPt loc;
    String name;
    
    Fireball(CartPt loc, String name) {
        this.loc = loc;
        this.name = name;
    }
    
    /* TEMPLATE:
    FIELDS:
    ...this.loc...     -- CartPt
    ...this.name...    -- String
    
    METHODS:
    ...this.fireballImage()...   -- WorldImage
    ...this.hitGround(int)...    -- boolean
    ...this.fall()...            -- Fireball
     
    METHODS FOR FIELDS:
    ... this.loc.moveBy(int, int) ...  -- CartPt
    ... this.loc.distTo(CartPt) ...    -- double
     
     */
    
    
    // produce the image of this fireball at its position
    WorldImage fireballImage() {
        return new FromFileImage(this.loc, this.name);
    }
    
    // did this fireball hit the ground?
    boolean hitGround(int h) {
      return this.loc.y >= 400;
    }
    
    Random rand = new Random();
    
    // produce a new fireball that has fallen 10 pixels down
    // or produce a new fireball if this fireball hit the ground
    Fireball fall() {
        if (hitGround(450))
            return new Fireball(new CartPt((rand.nextInt(340) + 90), 150),
                    this.name);
        else
            return new Fireball(this.loc.moveBy(0, 10), this.name);
    }
}