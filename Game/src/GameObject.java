
public abstract class GameObject {
	//한번의 이동하는 거리 
	protected int distance;
	//현재 위치
	protected int x, y;
	
	public GameObject(int x, int y, int distance, int appear) {
		super();
		this.x = x;
		this.y = y;
		this.distance = distance;
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean collide(GameObject p) {
		
		if(this.x == p.getX() && this.y == p.getY()) {
			return true;
		}else {
			return false;
		}
	}
	
	protected abstract void move();
	protected abstract char getShape();
	
	
	
	
}
