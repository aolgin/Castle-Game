import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import tester.Tester;
import javalib.colors.*;

// assignment 4
// pair 026
// Olgin, Adam
// aolgin
// Levine, Ben
// levinebe

public class ExamplesFundies2Game {

    // CONSTANTS:
    int width = 500;
    int height = 450;
    String fbName = "p026-fireball.png";
    String kName = "p026-knight.png";
    
    // left and right edge
    CartPt start = new CartPt(30, 350);
    CartPt end = new CartPt(480, 350); // decide whether to switch to 430 later
    
    // sample knight
    Knight startKnight = new Knight(new CartPt(30, 350), this.kName, 3);
    Knight endKnight = new Knight(new CartPt(430, 350), this.kName, 3);
    Knight inKnight = new Knight(new CartPt(80, 350), this.kName, 3);
    Knight inKnightMove = new Knight(new CartPt(130, 350), this.kName, 3);
    Knight knight = new Knight(new CartPt(50, 350), this.kName, 3);
    Knight knight2 = new Knight(new CartPt(30, 350), this.kName, 2);
    Knight knight3 = new Knight(new CartPt(130, 350), this.kName, 2);
    Knight knight3M = new Knight(new CartPt(180, 350), this.kName, 2);
    Knight knight4 = new Knight(new CartPt(160, 350), this.kName, 3);
    Knight knight5 = new Knight(new CartPt(210, 350), this.kName, 3);
    Knight knight6 = new Knight(new CartPt(260, 350), this.kName, 3);
    Knight knight7 = new Knight(this.end, this.kName, 3);
    Knight knight8 = new Knight(new CartPt(250, 350), this.kName, 3);
    Knight knight9 = new Knight(new CartPt(30, 350), this.kName, 2);
    Knight deadKnight = new Knight(this.start, this.kName, 0);
    
    // sample dragon
    Dragon smaug = new Dragon(new CartPt(250, 100), 10, "p026-dragon.png");
    Dragon smaugMove = new Dragon(new CartPt(260, 100), 10, "p026-dragon.png");
    Dragon saphira = new Dragon(new CartPt(300, 100), 10, "p026-dragon.png");
    Dragon saphira2 = new Dragon(new CartPt(290, 100), -10, "p026-dragon.png");
    Dragon dragonite = new Dragon(new CartPt(200, 100), -10, "p026-dragon.png");
    Dragon dragonite2 = new Dragon(new CartPt(210, 100), 10, "p026-dragon.png");
    
    // sample fireballs
    Fireball firstfb = new Fireball(new CartPt(250, 150), this.fbName);
    Fireball secondfb = new Fireball(new CartPt(350, 150), this.fbName);
    Fireball thirdfb = new Fireball(new CartPt(150, 150), this.fbName);
    Fireball firstfbMove = new Fireball(new CartPt(250, 160), this.fbName);
    Fireball secondfbMove = new Fireball(new CartPt(350, 160), this.fbName);
    Fireball thirdfbMove = new Fireball(new CartPt(150, 160), this.fbName);
    Fireball ball1 = new Fireball(new CartPt(250, 150), this.fbName);
    Fireball ball2 = new Fireball(new CartPt(350, 200), this.fbName);
    Fireball ball3 = new Fireball(new CartPt(150, 350), this.fbName);
    
    // an error fireball
    Fireball ball4 = new Fireball(new CartPt(150, 550), this.fbName);
    
    // the above sample fireballs after 1 tick
    Fireball ball5 = new Fireball(new CartPt(250, 160), this.fbName);
    Fireball ball6 = new Fireball(new CartPt(350, 210), this.fbName);
    Fireball ball7 = new Fireball(new CartPt(150, 360), this.fbName);
    Fireball ball8 = new Fireball(new CartPt(200, 150), this.fbName);
    Fireball ball9 = new Fireball(new CartPt(250, 350), this.fbName);
    
    // sample ILoFs
    ILoF mtLoF = new MtLoF();
    ILoF fbs1 = new ConsLoF(ball1, new ConsLoF(ball2, new ConsLoF(
            ball3, new ConsLoF(ball4, this.mtLoF))));
    ILoF fbs2 = new ConsLoF(ball5, new ConsLoF(ball6, new ConsLoF(
            ball7, new ConsLoF(ball8, this.mtLoF))));
    ILoF fbs3 = new ConsLoF(firstfb, new ConsLoF(secondfb, new ConsLoF(
            thirdfb, this.mtLoF)));
    ILoF fbs4 = new ConsLoF(ball9, this.mtLoF);
    ILoF fbs5 = new ConsLoF(firstfbMove, new ConsLoF(secondfbMove, new ConsLoF(
            thirdfbMove, this.mtLoF)));
    
    // a Castle World
    Castle myWorld =
            new Castle(
                    this.startKnight, this.smaug, this.fbs3, 0);
    Castle world2 =
            new Castle(
                    this.endKnight, this.smaug, this.fbs3, 0);
    Castle worldScore =
            new Castle(
                    this.startKnight, this.smaug, this.fbs3, 5);
    Castle world3 =
            new Castle(
                    this.inKnight, this.smaug, this.fbs3, 0);
    Castle world4 =
            new Castle(
                    this.knight3, this.smaug, this.fbs3, 5);
    Castle world5 =
            new Castle(
                    this.knight3M, this.smaug, this.fbs3, 5);
    Castle myWorldMove =
            new Castle(
                    this.startKnight, this.smaugMove, this.fbs5, 0);
    
    // sample dead Castles
    Castle deadWorld =
            new Castle(
                    this.deadKnight, this.smaug, this.myWorld.fbs, 0);
    Castle deadWorld2 = new Castle(
            this.knight, this.smaug, this.myWorld.fbs, 100);
    
    // sample worldEnd
    WorldEnd deadWorldEnd = new WorldEnd(true, 
            this.deadWorld.makeImage().overlayImages(
                    new TextImage(new Posn (width / 2, height / 2),
                    "The Knight has died", 36, 1, new White())));
    WorldEnd victoryWorldEnd = new WorldEnd(true, 
                new FromFileImage(new Posn (width / 2, height / 2),
                "p026-anothercastle.png"));
    WorldEnd worldContinue = new WorldEnd(false, this.myWorld.makeImage());
    
    // tests
    boolean testAll(Tester t) {
        return
                
            // fall() tests    
            t.checkExpect(this.ball1.fall(), new Fireball(
                    new CartPt (250, 160), this.fbName)) &&
            t.checkExpect(this.ball4.fall().loc.y, 150) &&
            t.checkRange(this.ball4.fall().loc.x, 90, 430) &&
            
            // hitGround tests
            t.checkExpect(this.ball1.hitGround(height), false) &&
            t.checkExpect(this.ball4.hitGround(height), true) &&
            
            // falling() tests
            t.checkExpect(this.fbs3.falling(), this.fbs5) &&
            t.checkExpect(this.mtLoF.falling(), this.mtLoF) &&
            
            // isDead tests
            t.checkExpect(this.mtLoF.isDead(this.knight), this.knight) &&
            t.checkExpect(this.fbs3.isDead(this.knight), this.knight) &&
            t.checkExpect(this.fbs4.isDead(this.knight8), this.knight9) &&
            
            // loseLife(Fireball) tests
            t.checkExpect(this.knight8.loseLife(this.ball9), this.knight2) &&
            t.checkExpect(this.knight.loseLife(this.ball1), this.knight) &&
            
            // isHit(Fireball) tests
            t.checkExpect(this.knight3.isHit(new Fireball(new CartPt(130, 350),
                    this.fbName)), true) &&
            t.checkExpect(this.knight.isHit(this.ball1), false) &&
            
            // knight move methods (left and right movements) tests
            t.checkExpect(this.knight5.moveRight(), this.knight6) &&
            t.checkExpect(this.knight5.moveLeft(), this.knight4) &&
            
            // reachDoor(CartPt) tests
            t.checkExpect(this.knight5.reachDoor(this.end), false) &&
            t.checkExpect(this.knight7.reachDoor(this.end), true) &&
            t.checkExpect(this.knight7.reachDoor(this.start), false) &&
            t.checkExpect(this.startKnight.reachDoor(this.start), true) &&
            
            // Knight onKeyEvent tests
            t.checkExpect(this.knight5.onKeyEvent("left"), this.knight4) &&
            t.checkExpect(this.knight5.onKeyEvent("right"), this.knight6) &&
            t.checkExpect(this.knight5.onKeyEvent("q"), this.knight5) &&
            
            // dragon hitRight tests
            t.checkExpect(this.smaug.hitRight(), false) &&
            t.checkExpect(this.saphira.hitRight(), true) &&
            
            // dragon hitLeft tests
            t.checkExpect(this.smaug.hitLeft(), false) &&
            t.checkExpect(this.dragonite.hitLeft(), true) &&
            
            // dragon move tests
            t.checkExpect(this.smaug.move(), this.smaugMove) &&
            
            // dragon moveTo tests
            t.checkExpect(this.smaug.moveTo(), this.smaugMove) &&
            t.checkExpect(this.dragonite.moveTo(), this.dragonite2) &&
            t.checkExpect(this.saphira.moveTo(), this.saphira2) &&
            
            // Castle onKeyEvent
            t.checkExpect(this.myWorld.onKeyEvent("right"), 
                    new Castle(this.inKnight, myWorld.dragon, 
                            myWorld.fbs, myWorld.score)) &&
            t.checkExpect(this.world2.onKeyEvent("right"), this.worldScore) &&
            t.checkExpect(this.myWorld.onKeyEvent("left"), this.world3) &&
            t.checkExpect(this.world4.onKeyEvent("right"), this.world5) &&
            
            
            // Castle onTick
            t.checkExpect(this.myWorld.onTick(), this.myWorldMove) &&
            
            // worldEnds() tests
            t.checkExpect(this.myWorld.worldEnds(), this.worldContinue) &&
            t.checkExpect(this.deadWorld.worldEnds(), this.deadWorldEnd) &&
            t.checkExpect(this.deadWorld2.worldEnds(), this.victoryWorldEnd);
    }
}
