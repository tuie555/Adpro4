package se233.chapter4.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se233.chapter4.Launcher;
import se233.chapter4.view.GameStage;

public class GameCharacter extends Pane {
    public static final int CHARACTER_WIDTH = 32;
    public static final int CHARACTER_HEIGHT = 64;
    public enum CharacterType {
        MARIO,
        ROCKMAN
    }

    private final CharacterType characterType;
    private Image gameCharacterImg;
    private ImageView imageView;

    public AnimatedSprite getAnimatedSprite() {
        return animatedSprite;
    }

    private AnimatedSprite animatedSprite;
private int x;
private int y;

    public KeyCode getLeftKey() {
        return leftKey;
    }

    private KeyCode leftKey;
private KeyCode rightKey;
    public KeyCode getRightKey() {
        return rightKey;
    }
private KeyCode upKey;
    public KeyCode getUpKey() {
        return upKey;
    }
    int xVelocity = 0;
    int yVelocity = 0;
    int xMaxVelocity;
    int yMaxVelocity;


    public boolean isFalling = true;
    boolean canJump = false;
    public boolean isJumping = false;
    boolean isMoveLeft= false;
    boolean isMoveRight= false;
    int xAcceleration = 1;
    int yAcceleration = 1;


    private static final Logger logger = LogManager.getLogger(GameCharacter.class);

    public GameCharacter(CharacterType type, int x, int y, int offsetX, int offsetY, KeyCode leftKey,
                         KeyCode rightKey, KeyCode upKey) {
        this.characterType = type;
        this.x = x;
        this.y = y;
        this.setTranslateX(x);
        this.setTranslateY(y);

        if (type == CharacterType.MARIO) {
            this.gameCharacterImg = new Image(Launcher.class.getResourceAsStream("assets/MarioSheet.png"));
            this.animatedSprite = new AnimatedSprite(gameCharacterImg, 4, 4, 1, offsetX, offsetY, 16, 32);
            this.animatedSprite.setFitWidth(CHARACTER_WIDTH);
            this.animatedSprite.setFitHeight(CHARACTER_HEIGHT);
            // Default velocities for Mario
            this.xMaxVelocity = 7;
            this.yMaxVelocity = 17;
        } else { // ROCKMAN
            this.gameCharacterImg = new Image(Launcher.class.getResourceAsStream("assets/rockman.png"));
            this.animatedSprite = new AnimatedSprite(gameCharacterImg, 5, 5, 1, offsetX, offsetY, 541, 604);
            this.animatedSprite.setFitWidth(64);
            this.animatedSprite.setFitHeight(64);
            // Different velocities for Rockman
            this.xMaxVelocity = 10;  // Faster horizontal movement
            this.yMaxVelocity = 20;   // Higher jump
        }

        this.imageView = this.animatedSprite;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;

        this.getChildren().add(this.imageView);
    }

    public void moveY() {
        setTranslateY
                (y);

         if(isFalling) {
             yVelocity = yVelocity >= yMaxVelocity? yMaxVelocity : yVelocity+yAcceleration;
             y = y + yVelocity;
         } else if(isJumping) {
             yVelocity = yVelocity <= 0 ? 0 : yVelocity-yAcceleration;
              y = y - yVelocity;
              }

    }
    public void moveX() {
         setTranslateX
                (x);
        if
        (isMoveLeft) {
            xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x=x - xVelocity; }
         if
         (isMoveRight) {
             xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
             x=x + xVelocity; }

    }
    public void checkReachGameWall() {
         if(x <= 0)
         {
            x = 0;
             } else if( x+getWidth() >= GameStage.WIDTH) {
            x = GameStage.WIDTH-(int)getWidth();
            logger.debug("Character collided with boundary at X: " + x);
            }
         }
    public void jump() {
         if (canJump) {
             yVelocity = yMaxVelocity;

             canJump = false;
            isJumping = true;
             isFalling = false;
             }
         }
 public void checkReachHighest () {
     if(isJumping && yVelocity <= 0) {

         isJumping = false;
             isFalling = true;
         yVelocity = 0;
             }
         }
 public void checkReachFloor() {
         if(isFalling && y >= GameStage.GROUND - CHARACTER_HEIGHT) {
             isFalling = false;
             canJump = true;
             yVelocity = 0;
             }
         }
 public void repaint() {
     moveX();
     moveY();
 }
    public void moveLeft() {
         setScaleX(-1);
        isMoveLeft= true;
        isMoveRight= false;
         }
 public void moveRight() {
       setScaleX(1);
     isMoveLeft= false;
     isMoveRight= true;
        }

 public void stop() {
         isMoveLeft= false;
         isMoveRight= false;
         }
    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}",x,y,xVelocity,yVelocity);
         }

}