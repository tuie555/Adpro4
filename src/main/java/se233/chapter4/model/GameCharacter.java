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
private Image gameCharacterImg;

    public ImageView getImageView() {
        return imageView;
    }

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
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;
    boolean isMoveLeft= false;
    boolean isMoveRight= false;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 7;
    int yMaxVelocity = 17;


    private static final Logger logger = LogManager.getLogger(GameCharacter.class);

    public GameCharacter(int x, int y, int offsetX, int offsetY, KeyCode leftKey,
                         KeyCode rightKey, KeyCode upKey) {
         this.x = x;
        this.y = y;
        this.setTranslateX(x);
         this.setTranslateY(y);
        this.gameCharacterImg = new Image(Launcher.class.getResourceAsStream("assets/MarioSheet.png"));


        this.imageView = new AnimatedSprite(gameCharacterImg, 4, 4, 1, offsetX,
                offsetY, 16, 32);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.leftKey = leftKey;
       this.rightKey = rightKey;
         this.upKey = upKey;
         this.getChildren().addAll(this.imageView);
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
         if(x <= 0) {
            x = 0;
             } else if( x+getWidth() >= GameStage.WIDTH) {
            x = GameStage.WIDTH-(int)getWidth();
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