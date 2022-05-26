package in.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import in.member.dto.MemberDTO;
import in.member.service.MemberService;


public class MemberDAO implements MemberService {

	private static final Log log = LogFactory.getLog(MemberDAO.class);

	@Override
	public ArrayList<MemberDTO> memberSelectAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "select id, passwd, name, email, nicname, managernum, to_char(memberday, 'YY/MM/DD') as memberday, membertech from member ";
			log.info("SQL - " + sql);

			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setId(resultSet.getString("id"));
				memberDTO.setPasswd(resultSet.getString("passwd"));
				memberDTO.setName(resultSet.getString("name"));
				memberDTO.setEmail(resultSet.getString("email"));
				memberDTO.setNicname(resultSet.getString("nicname"));
				memberDTO.setManagernum(resultSet.getInt("managernum"));
				memberDTO.setMemberday(resultSet.getString("memberday"));
				memberDTO.setMembertech(resultSet.getString("membertech"));

				arrayList.add(memberDTO);
			}
			// 테이블에 있는 모든 행을 검색한다.
			resultSet.getRow();

			if (resultSet.getRow() == 0) {
				System.out.println("등록한 부서가 없습니다.");
			}

		} catch (Exception e) {
			log.info("부서등록 실패 - " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return arrayList;
	}
	

	@Override
	public MemberDTO memberSelect(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MemberDTO memberDTO = new MemberDTO();

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "select id, passwd, name, email, nicname, managernum, to_char(memberday, 'YY/MM/DD') as memberday, membertech from member ";
			sql += " where id = ? ";
			log.info("SQL - " + sql);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				memberDTO.setId(resultSet.getString("id"));
				memberDTO.setPasswd(resultSet.getString("passwd"));
				memberDTO.setName(resultSet.getString("name"));
				memberDTO.setEmail(resultSet.getString("email"));
				memberDTO.setNicname(resultSet.getString("nicname"));
				memberDTO.setManagernum(resultSet.getInt("managernum"));
				memberDTO.setMemberday(resultSet.getString("memberday"));
				memberDTO.setMembertech(resultSet.getString("membertech"));

			}

		} catch (Exception e) {
			log.info("예외 확인 - " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				
			}
		}
		
		return memberDTO;
	}

	@Override
	public MemberDTO memberUpdate(String id, String passwd, String name, String email, String nicname, int managernum,
			String membertech) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setPasswd(passwd);
		memberDTO.setName(name);
		memberDTO.setEmail(email);
		memberDTO.setNicname(nicname);
		memberDTO.setManagernum(managernum);
		memberDTO.setMembertech(membertech);
		
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			
			String sql = "update member set id = ?, passwd = ?, name = ?, email = ?, nicname = ?, managernum = ?, membertech =? ";
			sql += " where id = ? ";
			log.info("SQL - " + sql);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getId());
			preparedStatement.setString(2, memberDTO.getPasswd());
			preparedStatement.setString(3, memberDTO.getName());
			preparedStatement.setString(4, memberDTO.getEmail());
			preparedStatement.setString(5, memberDTO.getNicname());
			preparedStatement.setInt(6, memberDTO.getManagernum());
			preparedStatement.setString(7, memberDTO.getMembertech());
			preparedStatement.setString(8, memberDTO.getId());
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			log.info("예외 확인 - " + e);
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return memberDTO;
	}

	@Override
	public MemberDTO memberDelete(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "delete from member ";
			sql += " where id = ? ";
			log.info("SQL - " + sql);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getId());
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			log.info("예외 확인 - " + e);
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return memberDTO;
	}

	@Override
	public MemberDTO memberInsert(String id, String passwd, String name, String email, String nicname, int managernum,
			String memberday, String membertech) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setPasswd(passwd);
		memberDTO.setName(name);
		memberDTO.setEmail(email);
		memberDTO.setNicname(nicname);
		memberDTO.setManagernum(managernum);
		memberDTO.setMemberday(memberday);
		memberDTO.setMembertech(membertech);

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "insert into member(id, passwd, name, email, nicname, managernum, memberday, membertech) ";
			sql += " values (?, ?, ?, ?, ?, ?, sysdate, ?) ";
			log.info("SLQ - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getId());
			preparedStatement.setString(2, memberDTO.getPasswd());
			preparedStatement.setString(3, memberDTO.getName());
			preparedStatement.setString(4, memberDTO.getEmail());
			preparedStatement.setString(5, memberDTO.getNicname());
			preparedStatement.setInt(6, memberDTO.getManagernum());
			//preparedStatement.setString(7, memberDTO.getMemberday());
			preparedStatement.setString(7, memberDTO.getMembertech());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			log.info("예외 확인 - " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return memberDTO;
	}


	@Override
	public MemberDTO memberLogin(String id, String passwd) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MemberDTO memberDTO = new MemberDTO();
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			String sql = "select * from member ";
			sql += " where id = ? and passwd = ? ";
			log.info("SQL - " + sql);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, passwd);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				if (resultSet.getString("id").equals(id) && resultSet.getString("passwd").equals(passwd)) {
					memberDTO.setId(resultSet.getString("id"));
					memberDTO.setPasswd(resultSet.getString("passwd"));
					memberDTO.setNicname(resultSet.getString("nicname"));				
				}
			}
		} catch (Exception e) {
			log.info("로그인 실패 - " + e);
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return memberDTO;
		
	}


	@Override
	public int memberCheckedId(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		int idcheck = 0;
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			String sql = "select * from member ";
			sql += " where id = ? ";	
			
			log.info("SQL - " + sql);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next() || id.equals("")) {
				idcheck = 0; //이미 아이디가 존재하면 생성 불가
			}else {
				idcheck = 1; //일치하는게 없으면 생성 가능
			}
			
		} catch (Exception e) {
			log.info("중복검사 실패 - " + e);
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return idcheck;
	}







}
