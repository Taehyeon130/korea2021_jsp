package site0616.model.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//커넥션을 모아놓고 관리하는 커넥션 풀 객체는 메모리에 인스턴스를 오직 1개만 두고 사용하기 위해 싱글톤으로 처리해보자(SingleTon)
public class PoolManager {
	InitialContext context;
	DataSource ds;
	private static PoolManager instance;
	
	private PoolManager() {
		try {
			context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jndi/oracle");
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
	}
	
	//생성자를 아무도 못쓰게 막아놓았으므로 외부의 클래스들이 PoolManager의 인스턴스를 반환받아갈 수 있도록 책임을 지자!
	public static PoolManager getInstance() {
		if(instance ==null) {
			instance = new PoolManager();
		}
		return instance;
	}
	
	//누구든지 쿼리문을 수행하기 위해 Connection이 필요하다면 아래의 메서드를 호출하여 Connection을 가져가지만 하면 됨
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//Connection 반납
	public void release(Connection  con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Connection, PreparedStatement 반납
	public void release(Connection  con,PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Connection , PreparedStatement, Resultset반납
	public void release(Connection  con,PreparedStatement pstmt,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
