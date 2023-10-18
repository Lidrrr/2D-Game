package entity;

import main.GamePanel;

public class Projectiles extends Entity{

	Entity entity;
	public Projectiles(GamePanel gameP) {
		super(gameP);
	}
	
	public void set(int x, int y, String direction, boolean living, Entity e) {
		worldX = x;
		worldY = y;
		this.direction = direction;
		this.living = living;
		entity = e;
		this.currentLife = maxLife;
	}
	public void update() {
		if(entity == gameP.player) {
			int index = gameP.collisonC.checkEntity(this, gameP.itemC.monsters);
			if(index != 999) {
				gameP.player.damageMonster(index, attack);
				living = false;
			}
		}
		switch (direction){
		case "up":
			worldY-=speed;
			break;
		case "down":
			worldY+=speed;
			break;
		case "left":
			worldX-=speed;
			break;
		case "right":
			worldX+=speed;
			break;
		}

		if(currentLife-- <= 0) {
			living =false;
		}
		animate();
	}
}
