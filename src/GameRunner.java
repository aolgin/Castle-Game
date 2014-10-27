import javalib.worldimages.*;
import javalib.worldcanvas.*;

//assignment 4
//pair 026
//Olgin, Adam
//aolgin
//Levine, Ben
//levinebe

// test the display of images for the castle game and run the game
public class GameRunner{
  
  ExamplesFundies2Game efg = new ExamplesFundies2Game();

  WorldCanvas c1 = new WorldCanvas(500, 500);
  WorldCanvas c2 = new WorldCanvas(500, 500);

  WorldImage knightImage = this.efg.knight.knightImage();
  WorldImage dragonImage = this.efg.smaug.dragonImage();
  WorldImage fireballImage = this.efg.ball1.fireballImage();

  WorldImage worldImage = this.efg.myWorld.makeImage();
  
  // show the knight, dragon, and fireballs on one canvas 
  /*boolean makeDrawing1 = 
      c1.show() && 
      c1.drawImage(this.knightImage) &&
      c1.drawImage(this.dragonImage) &&
      c1.drawImage(this.fireballImage);
  */
  // show the knight, dragon, and fireballs on one canvas 
  /*boolean makeDrawing2 = 
      c2.show() && 
      c2.drawImage(this.worldImage);
*/
  // run the game
  boolean run() {
    return this.efg.myWorld.bigBang(500, 500, 0.1);    
  }
  
  // invoke this method to run the castle game
  public static void main(String [] argv) {
    GameRunner gr = new GameRunner();
    gr.run();

  }
}