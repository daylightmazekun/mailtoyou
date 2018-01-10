package nukezam.mailtoyou.dao;

import nukezam.mailtoyou.bean.Comments;

/**
 * @Package :nukezam.mailtoyou.dao
 * @Title: GetComments.java
 * @Package nukezam.mailtoyou.dao
 * @author zekun ma burmaing@gmail.com
 * @date 2018年1月10日 下午8:40:36
 * @version V1.0
 */
public interface GetComments {
	
	public abstract Comments getComments();

	public abstract void deleteComments();

	public abstract void insertOtherComments(Comments comments);
}
