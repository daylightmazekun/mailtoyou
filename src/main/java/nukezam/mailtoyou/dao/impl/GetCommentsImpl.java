package nukezam.mailtoyou.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
public class GetCommentsImpl implements GetComments{

	/* (non-Javadoc)
	 * @see nukezam.mailtoyou.dao.GetComments#getComments()
	 */
	public Comments getComments() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Comments comments = null;
		// 如果将来要有需求取多条
		// List<Comments> listComments = new ArrayList<Comments>();
		String sql = EmailToYouConstants.FIND_SQL;
		try {
			connection = JdbcUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				comments = new Comments();
				comments.setArtistName(resultSet.getString("artist_name"));
				comments.setComments(resultSet.getString("comments"));
				comments.setDetails(resultSet.getString("details"));
				comments.setId(resultSet.getInt("id"));
				comments.setMusicName(resultSet.getString("music_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.connClose(connection, preparedStatement, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return comments;
	}

	/* (non-Javadoc)
	 * @see nukezam.mailtoyou.dao.GetComments#deleteComments()
	 */
	public void deleteComments() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nukezam.mailtoyou.dao.GetComments#insertOtherComments(nukezam.mailtoyou.bean.Comments)
	 */
	public void insertOtherComments(Comments comments) {
		// TODO Auto-generated method stub
		
	}


}
