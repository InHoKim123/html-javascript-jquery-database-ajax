package in.post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;

import in.post.dto.PostDTO;
import in.post.service.PostService;

public class PostDAO implements PostService{
	
	private static final Log log = LogFactory.getLog(PostDAO.class);
	@Override
	public ArrayList<PostDTO> postSelectAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		ArrayList<PostDTO> arraylist = new ArrayList<PostDTO>();
		
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select postnum, postname, postpasswd, postcontent, postwriter, to_char(postwriteday, 'YY/MM/DD') as postwriteday, views, postkind from post ";
			sql += "order by postnum desc";
			log.info("SQL - " + sql);
			
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				PostDTO postDTO = new PostDTO();
				postDTO.setPostnum(resultSet.getInt("postnum"));
				postDTO.setPosttitle(resultSet.getString("postname"));
				postDTO.setPasswd(resultSet.getString("postpasswd"));
				postDTO.setPostcontent(resultSet.getString("postcontent"));
				postDTO.setPostwriter(resultSet.getString("postwriter"));
				postDTO.setPostwriteday(resultSet.getString("postwriteday"));
				postDTO.setViews(resultSet.getInt("views"));
				postDTO.setPostkind(resultSet.getString("postkind"));
				
				arraylist.add(postDTO);
				log.info("데이터 확인 - " + postDTO);
			}
			if (resultSet.getRow() == 0) {
				log.info("등록된 글이 없습니다.");
			}
		} catch (Exception e) {
			log.info("전체 글 조회 실패 - " + e);
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
		return arraylist;
	}

	@Override
	public PostDTO postSelect(int postnum) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PostDTO postDTO = new PostDTO();
		
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			String sql = "select postnum, postname, postpasswd, postcontent, postwriter, to_char(postwriteday, 'YY/MM/DD') as postwriteday, views, postkind from post ";
			sql += " where postnum = ? ";
			log.info("SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postnum);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				postDTO = new PostDTO();
				postDTO.setPostnum(resultSet.getInt("postnum"));
				postDTO.setPosttitle(resultSet.getString("postname"));
				postDTO.setPasswd(resultSet.getString("postpasswd"));
				postDTO.setPostcontent(resultSet.getString("postcontent"));
				postDTO.setPostwriter(resultSet.getString("postwriter"));
				postDTO.setPostwriteday(resultSet.getString("postwriteday"));
				postDTO.setViews(resultSet.getInt("views"));
				postDTO.setPostkind(resultSet.getString("postkind"));
				log.info("데이터 확인 - " + postDTO);
				System.out.println();
			}
			
		} catch (Exception e) {
			log.info("상세조회 실패 - " + e);
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
		
		return postDTO;
	}
	
	@Override
	public PostDTO postSelectWriter(String postwriter) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PostDTO postDTO = new PostDTO();
		
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			String sql = "select * from post ";
			sql += " where postwriter = ? ";
			log.info("SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, postwriter);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				postDTO = new PostDTO();
				postDTO.setPostnum(resultSet.getInt("postnum"));
				postDTO.setPosttitle(resultSet.getString("posttitle"));
				postDTO.setPostcontent(resultSet.getString("postcontent"));
				postDTO.setPostwriter(resultSet.getString("postwriter"));
				postDTO.setPostwriteday(resultSet.getString("postwriteday"));
				postDTO.setViews(resultSet.getInt("views"));
				postDTO.setPostkind(resultSet.getString("postkind"));
				log.info("데이터 확인 - " + postDTO);
				System.out.println();
			}
			
		} catch (Exception e) {
			log.info("상세조회 실패 - " + e);
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
		
		return postDTO;
	}

	@Override
	public PostDTO postDelete(int postnum) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PostDTO postDTO = new PostDTO();
		postDTO.setPostnum(postnum);
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			String sql = "delete post ";
			sql += "where postnum = ? ";
			log.info("SQL - " + sql);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, postnum);
			int count = preparedStatement.executeUpdate();
			
			if (count > 0) {
				log.info("커밋되었습니다.");
			}else {
				log.info("롤백되었습니다.");
			}
			
		} catch (Exception e) {
			log.info("글 삭제 실패 - " + e);
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return postDTO;
	}

	@Override
	public void postInsert(PostDTO postDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			
			String sql = "insert into post ";
			sql += " values(post_seq.nextval, ?, ?, ?, ?, sysdate, 0, ?) ";
			log.info("SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, postDTO.getPosttitle());
			preparedStatement.setString(2, postDTO.getPasswd());
			preparedStatement.setString(3, postDTO.getPostcontent());
			preparedStatement.setString(4, postDTO.getPostwriter());
			preparedStatement.setString(5, postDTO.getPostkind());
			int count = preparedStatement.executeUpdate();
			log.info("데이터 확인 - " + postDTO);
			if (count > 0) {
				log.info("커밋되었습니다");
			}else {
				log.info("롤백되었습니다.");
			}
		} catch (Exception e) {
			log.info("글 등록 실패 - " + e);
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public PostDTO postUpdate(PostDTO postDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "update post ";
			sql += " set postname = ?, postcontent = ?, postkind = ? ";
			sql += " where postnum = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, postDTO.getPosttitle());
			preparedStatement.setString(2, postDTO.getPostcontent());
			preparedStatement.setString(3, postDTO.getPostkind());
			preparedStatement.setInt(4, postDTO.getPostnum());
			int count = preparedStatement.executeUpdate();
			log.info("수정 데이터 확인 - " + postDTO);
			if (count > 0) {
				connection.commit();
				log.info("커밋 되었습니다.");
			}else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}
			
		} catch (Exception e) {
			log.info("글 수정 실패 - " + e);
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return postDTO;
		
	}

	@Override
	public PostDTO postSelectMaxView() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PostDTO postDTO = new PostDTO();
		
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			String sql = "select postnum, postname, postpasswd, postcontent, postwriter, to_char(postwriteday, 'YY/MM/DD') as postwriteday, views, postkind from post ";
			sql += " where views = (select max(views) from post) ";
			log.info("SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				postDTO = new PostDTO();
				postDTO.setPostnum(resultSet.getInt("postnum"));
				postDTO.setPosttitle(resultSet.getString("postname"));
				postDTO.setPasswd(resultSet.getString("postpasswd"));
				postDTO.setPostcontent(resultSet.getString("postcontent"));
				postDTO.setPostwriter(resultSet.getString("postwriter"));
				postDTO.setPostwriteday(resultSet.getString("postwriteday"));
				postDTO.setViews(resultSet.getInt("views"));
				postDTO.setPostkind(resultSet.getString("postkind"));
				log.info("데이터 확인 - " + postDTO);
				System.out.println();
			}
			
		} catch (Exception e) {
			log.info("상세조회 실패 - " + e);
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
		
		return postDTO;
	}

	
	
}
