//목록요청을 처리하는 서블릿
//서블릿이란 javaEE기반의 서버에서 실행될 수 있는 클래스임
//하지만 서블릿만으로는 디자인 처리에 너무 많은 리소스가 소모됨... 즉 효율적이지 못함
//why? 클라이언트에게 전달할 컨텐츠 문자열을 문자열 처리하여 전송해야 하므로 즉 디자인에 취약함

package servlet.board;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ListServlet extends HttpServlet{
	//요청처리 메서드
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;utf-8");
//		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		//out.print("this is board list");

		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");//드라이버로드
		
			//접속
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","webmaster","1234");
			if(con!=null){
				out.print("connection ok<br>");
			//select 문 실행
			String sql = "select *from board order by board_id desc";
			pstmt = con.prepareStatement(sql);//쿼리 객체 준비
			rs = pstmt.executeQuery(); //쿼리 실행 및 레코드 반환
			//rs를 html table로 출력
			out.print("<table width='100%' border='1px'>");
			out.print("<tr>");
			out.print("<th>No</th>");
			out.print("<th>title</th>");
			out.print("<th>writer</th>");
			out.print("<th>regdate</th>");
			out.print("<th>hit</th>");
			out.print("</tr>");
			while(rs.next()){
				out.print("<tr>");
				out.print("<td>No</td>");
				out.print("<td>"+rs.getString("title")+"</td>");
				out.print("<td>"+rs.getString("writer")+"</td>");
				out.print("<td>"+rs.getString("regdate")+"</td>");
				out.print("<td>"+rs.getInt("hit")+"</td>");
				out.print("</tr>");
			}
			}else{
				out.print("connection fail<br>");
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace(); //스택 에러가 출력되는 곳은? 서버의 콘솔창 또는 log파일
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try{rs.close();}catch(SQLException e){}
			}
			if(pstmt!=null){
				try{pstmt.close();}catch(SQLException e){}
			}
			if(con!=null){
				try{con.close();}catch(SQLException e){}
			}
		
		}
	
	}
}
