package web.member.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import web.member.dao.MemberDao;
import web.member.vo.Member;

public class MemberDaoImpl implements MemberDao {
	private static final String URL = "jdbc:mysql://localhost:3306/cfa104g1db?serverTimezone=Asia/Taipei";
	private static final String USER = "root";
	private static final String PWD = "12345678";
	
	@Override
	public Member selectByUsernameAndPassowrd(String username, String password) {
		String sql = "select * from cfa104g1db.member where USERNAME = ?  and PASSWORD = ?";
		try (
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
		)
		{
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			try (ResultSet rs =  pstmt.executeQuery()) {
				if (rs.next()) {
					Member member = new Member();
					member.setId(rs.getInt("id"));
					member.setUsername(rs.getString("username"));
					member.setPassword(rs.getString("password"));
					member.setNickname(rs.getString("nickname"));
					return member;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
