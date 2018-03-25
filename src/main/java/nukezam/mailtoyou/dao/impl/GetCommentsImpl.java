package nukezam.mailtoyou.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import nukezam.mailtoyou.bean.Comments;
import nukezam.mailtoyou.constants.EmailToYouConstants;
import nukezam.mailtoyou.dao.GetComments;
import nukezam.mailtoyou.utils.JdbcUtil;

/**
 * @Package :nukezam.mailtoyou.dao.impl
 * @Title: GetCommentsImpl.java
 * @Package nukezam.mailtoyou.dao.impl
 * @author zekun ma burmaing@gmail.com
 * @date 2018年1月10日 下午9:04:46
 * @version V1.0
 */
public class GetCommentsImpl implements GetComments {

	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	Comments comments;
	
	public GetCommentsImpl() {
		try {
			this.connection = JdbcUtil.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.preparedStatement = null;
		this.resultSet = null;
		this.comments = null;
	}

	public Comments getComments() {

		// 如果将来要有需求取多条
		// List<Comments> listComments = new ArrayList<Comments>();
		String sql = EmailToYouConstants.FIND_SQL;
		try {
			// connection = JdbcUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				comments = new Comments();
				comments.setArtistName(resultSet.getString("artist_name"));
				comments.setComments(resultSet.getString("comments"));
				comments.setDetails(resultSet.getString("details").split("\n\n"));
				comments.setId(resultSet.getInt("id"));
				comments.setMusicName(resultSet.getString("music_name"));
			}
			// JdbcUtil.connClose(connection, preparedStatement, resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comments;
	}

	// 删除操作
	public void deleteComments(Comments comments) {
		String sql = EmailToYouConstants.DELETE_SQL;
		int i = 0;
		try {
			//connection = JdbcUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
	    	preparedStatement.setInt(1, comments.getId());
			i = preparedStatement.executeUpdate();
			System.out.println("resutl: " + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void insertOtherComments(Comments comments) {
		StringBuffer stringBuffer = new StringBuffer();
		for(int i = 0; i < comments.getDetails().length;i++){
			stringBuffer.append(comments.getDetails()[i]+"\n");
		}

		String s = stringBuffer.toString();
	    int i = 0;
	    try {
			// connection = JdbcUtil.getConnection();
	    	preparedStatement = (PreparedStatement) connection.prepareStatement(EmailToYouConstants.INSERT_SQL);
	    	preparedStatement.setInt(1, comments.getId());
	    	preparedStatement.setString(2, comments.getMusicName());
	    	preparedStatement.setString(3, comments.getArtistName());
	    	preparedStatement.setString(4, comments.getComments());
	    	preparedStatement.setString(5, s);
	        i = preparedStatement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

}
