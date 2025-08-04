package se233.chapter4.controller;
import se233.chapter4.model.GameCharacter;
import se233.chapter4.view.GameStage;

//Imports are omitted
public class GameLoop implements Runnable {
private GameStage gameStage;
private int frameRate;
private float interval;
private boolean running;
public GameLoop(GameStage gameStage) {
        this.gameStage = gameStage;
        frameRate = 10;
        interval = 1000.0f / frameRate;
        running = true;
       }
private void update(GameCharacter gameCharacter, GameCharacter gameCharacter1) {
    boolean leftPressed = gameStage.getKeys().isPressed(gameCharacter.
            getLeftKey());
     boolean rightPressed = gameStage.getKeys().isPressed(gameCharacter.
            getRightKey());
    boolean upPressed = gameStage.getKeys().isPressed(gameCharacter.getUpKey());

             if (leftPressed && rightPressed) {
         gameCharacter.stop();
         gameCharacter1.stop();
        } else if (leftPressed) {
                 gameCharacter.getAnimatedSprite().tick();
                 gameCharacter.moveLeft();
                 gameStage.getGameCharacter().trace();
                 gameCharacter1.getAnimatedSprite1().tick();
                 gameCharacter1.moveLeft();
                 gameStage.getGameCharacter1().trace();
        } else if (rightPressed) {
                 gameCharacter.getAnimatedSprite().tick();
                 gameCharacter.moveRight();
                 gameStage.getGameCharacter().trace();
                 gameCharacter1.getAnimatedSprite1().tick();
                 gameCharacter1.moveRight();
                 gameStage.getGameCharacter1().trace();

             } else {
         gameCharacter.stop();
         gameCharacter1.stop();
         }
    if (upPressed) {
         gameCharacter.jump();
        gameCharacter1.jump();
        }

}

 @Override
 public void run() {
         while (running) {
             float time = System.currentTimeMillis();
             update(gameStage.getGameCharacter(), gameStage.getGameCharacter1());

             time = System.currentTimeMillis() - time;
             if (time < interval) {
                 try {
                     Thread.sleep((long) (interval - time));
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                     }
                } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                     } catch (InterruptedException e) {
                     e.printStackTrace();
                     }
                }
             }
         }
}