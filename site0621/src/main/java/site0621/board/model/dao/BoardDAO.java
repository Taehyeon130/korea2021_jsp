package site0621.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site0621.board.model.domain.Board;
import site0621.util.PoolManager;

public class BoardDAO {
	PoolManager pool = PoolManager.getInstance();
	
	//insert
	public int insert(Board board) {
		Connection con=null;
		PreparedStatement pstmt=null;
		con=pool.getConnection();
		int result=0;
		
		String sql="insert into board(title, writer, content) values(?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
		return result;
	}
	
	//목록 (5분)
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Board> list=new ArrayList<Board>();
		
		con=pool.getConnection();
		String sql="select * from board order by board_id desc";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Board board=new Board();
				board.setBoard_id(rs.getInt("board_id"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				
				list.add(board); //리스트에 담기
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt, rs);
		}
		return list;
	}
	
	//한건 가져오기 (5분)
	public Board select(int board_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board board=null; //한건만 받아야 하므로
		
		con=pool.getConnection();
		String sql="select * from board where board_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//레코드가 있다면..
				board=new Board();
				board.setBoard_id(rs.getInt("board_id"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt, rs);
		}		
		return board;
	}
	
	//수정하기 (5분)
	public int update(Board board) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		con=pool.getConnection();
		String sql="update board set title=?, writer=?, content=? where board_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getBoard_id());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con,pstmt);
		}
		return result;
	}
	
	//삭제하기 (5분)
	public int delete(int board_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=pool.getConnection();
		String sql="delete from board where board_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
		return result;
	}	
	
}