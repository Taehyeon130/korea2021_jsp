package com.koreait.model2app.model.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.model2app.model.domain.Board;
import com.koreait.model2app.util.pool.PoolManager;

public class JdbcBoardDAO implements BoardDAO{
	PoolManager pool = PoolManager.getInstance();
	
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = new ArrayList<Board>();
		
		con = pool.getConnection();
		String sql = "select *from board order by board_id desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				board.setBoard_id(rs.getInt("board_id"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con,pstmt,rs);
		}
			return list;
	}

	public int insert(Board board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = pool.getConnection();
		int result =0;
		
		String sql ="insert into board(board_id, title, writer, content) values(seq_board.nextval,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con,pstmt);
		}
		return result;
	}

	
	public Board select(int board_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board board=null;
		
		con=pool.getConnection();
		String sql="select * from board where board_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs=pstmt.executeQuery(); //쿼리수행
			if(rs.next()) {
				board = new Board();
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


	
	public int update(Board board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		con = pool.getConnection();
		String sql ="update board set title=?, writer=?, content=? where board_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getBoard_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
		return result;
	}

	@Override
	public int delete(int board_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
