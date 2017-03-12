package clarity.graphics.entity.enemy;

import clarity.graphics.entity.SpriteSheet;

public class Ghost extends Enemy {

  private static final int DAMAGE = 40;

  public Ghost(int mobId) {
    super(mobId, DAMAGE);
    isRight = true;
  }

  /**
   * Enemy movement is walking back and forth.
   */
  @Override
  public void move() {
    if (checkTileCollision() && dy == 0) {
      if (isLeft) {
        isLeft = false;
        isRight = true;
      } else {
        isLeft = true;
        isRight = false;
      }
    }

  }

  @Override
  protected void init() {
    spriteSheet = SpriteSheet.GHOST;
    collisionWidth = 25;
    collisionHeight = 30;
    // movement speeds
    moveSpeed = 0.2;
    maxSpeed = 1.5;
    stopSpeed = 0.4;
    // falling speeds
    fallSpeed = 0.15;
    maxFallSpeed = 4.0;
    // jumping speeds
    jumpStart = -6.0;
    stopJumpSpeed = 0.5;
    facingRight = false;
    currentHealth = maxHealth = 100;
    currentEnergy = maxEnergy = 100;
  }

}