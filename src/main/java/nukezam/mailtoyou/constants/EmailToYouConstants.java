package nukezam.mailtoyou.constants;

/**
 * @Package :nukezam.mailtoyou.constants
 * @Title: EmailToYouConstants.java
 * @Package nukezam.mailtoyou.constants
 * @author zekun ma burmaing@gmail.com
 * @date 2018年1月10日 下午9:14:01
 * @version V1.0
 */
public class EmailToYouConstants {
	// 查询语句
	public final static String FIND_SQL = "SELECT * FROM COMMENTS ORDER BY RAND() LIMIT 1";
	// 删除语句
	public final static String DELETE_SQL = "DELETE FROM COMMENTS WHERE ID = ?";
	// 插入另一个表
	public final static String INSERT_SQL = "INSERT INTO `wangyi2`.`comments2`(`id`,`MUSIC_NAME`,`ARTIST_NAME`,`COMMENTS`,`DETAILS`) VALUES (?, ?, ?, ?, ?)";
	// "(<{id: }>,\r\n" +
	// "<{MUSIC_NAME: }>,\r\n" +
	// "<{ARTIST_NAME: }>,\r\n" +
	// "<{COMMENTS: }>,\r\n" +
	// "<{DETAILS: }>);"
	// "INSERT COMMENTS2 SET(ID, MUSIC_NAME, ARTIST_NAME, COMMENTS, DETAILS) = (?,
	// ?, ?, ?, ?)";
}
