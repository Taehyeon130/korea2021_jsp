package site0616.model.pool;

public class UsePool {
	public static void main(String[] args) {
		PoolManager p1 = PoolManager.getInstance();
		PoolManager p2 = PoolManager.getInstance();
		PoolManager p3 = PoolManager.getInstance();
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
	}
}
