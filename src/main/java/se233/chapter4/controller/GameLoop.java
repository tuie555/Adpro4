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
    // Check keys for character 1 (A/D/W)
    boolean leftPressed1 = gameStage.getKeys().isPressed(gameCharacter.getLeftKey());
    boolean rightPressed1 = gameStage.getKeys().isPressed(gameCharacter.getRightKey());
    boolean upPressed1 = gameStage.getKeys().isPressed(gameCharacter.getUpKey());

    // Check keys for character 2 (Keypad arrows)
    boolean leftPressed2 = gameStage.getKeys().isPressed(gameCharacter1.getLeftKey());
    boolean rightPressed2 = gameStage.getKeys().isPressed(gameCharacter1.getRightKey());
    boolean upPressed2 = gameStage.getKeys().isPressed(gameCharacter1.getUpKey());

    // Handle character 1 movement
    if (leftPressed1 && rightPressed1) {
        gameCharacter.stop();
    } else if (leftPressed1) {
        gameCharacter.moveLeft();
        gameStage.getGameCharacter().trace();
    } else if (rightPressed1) {
        gameCharacter.moveRight();
        gameStage.getGameCharacter().trace();
    } else {
        gameCharacter.stop();
    }
    // Always tick the animation if the character is moving or jumping/falling
    if (leftPressed1 || rightPressed1 || gameCharacter.isJumping || gameCharacter.isFalling) {
        gameCharacter.getAnimatedSprite().tick();
    }

    // Handle character 2 movement
    if (leftPressed2 && rightPressed2) {
        gameCharacter1.stop();
    } else if (leftPressed2) {
        gameCharacter1.moveLeft();
        gameStage.getGameCharacter1().trace();
    } else if (rightPressed2) {
        gameCharacter1.moveRight();
        gameStage.getGameCharacter1().trace();
    } else {
        gameCharacter1.stop();
    }
    // Always tick the animation if the character is moving or jumping/falling
    if (leftPressed2 || rightPressed2 || gameCharacter1.isJumping || gameCharacter1.isFalling) {
        gameCharacter1.getAnimatedSprite().tick();
    }

    // Handle jumping for both characters
    if (upPressed1) {
        gameCharacter.jump();
    }
    if (upPressed2) {
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