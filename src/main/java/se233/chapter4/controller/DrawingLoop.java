package se233.chapter4.controller;
import se233.chapter4.model.GameCharacter;
import se233.chapter4.view.GameStage;

//Imports are omitted
 public class DrawingLoop implements Runnable {
 private GameStage gameStage;
 private int frameRate;
 private float interval;
 private boolean running;
 public DrawingLoop(GameStage gameStage) {
         this.gameStage = gameStage;
         frameRate = 60;
         interval = 1000.0f / frameRate; // 1000 ms = 1 second
         running = true;
         }
 private void checkDrawCollisions(GameCharacter gameCharacter,GameCharacter gameCharacter1) {
         gameCharacter.checkReachGameWall();
         gameCharacter.checkReachHighest();
         gameCharacter.checkReachFloor();
     gameCharacter1.checkReachGameWall();
     gameCharacter1.checkReachHighest();
     gameCharacter1.checkReachFloor();
         }
 private void paint(GameCharacter gameCharacter,GameCharacter gameCharacter1) {

         // Repaint the characters
         gameCharacter.repaint();
         gameCharacter1.repaint();
         }
 @Override
public void run() {
         while (running) {
             float time = System.currentTimeMillis();
             checkDrawCollisions(gameStage.getGameCharacter(),gameStage.getGameCharacter1());
             paint(gameStage.getGameCharacter(),gameStage.getGameCharacter1());
             time = System.currentTimeMillis() - time;
             if (time < interval) {
                try {
                     Thread.sleep((long) (interval - time));
                     } catch (InterruptedException e) {
                     }
                 } else {
                 try {
                     Thread.sleep((long) (interval - (interval % time)));
                     } catch (InterruptedException e) {
                     }
                }
             }
         }
 }