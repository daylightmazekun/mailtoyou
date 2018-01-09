package nukezam.mailtoyou.email;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import nukezam.mailtoyou.Comments;
import nukezam.mailtoyou.dao.JdbcUtil;

/**
 * @Package :nukezam.mailtoyou.email
 * @Title: EmailToYou.java
 * @Package nukezam.mailtoyou.email
 * @author zekun ma burmaing@gmail.com
 * @date 2018年1月8日 下午8:12:34
 * @version V1.0 , "603313615@qq.com", "1157445336@qq.com"
 */
public class EmailToYou implements Runnable {
	// 收件人邮箱（替换为自己知道的有效邮箱）
	public static String[] receiveMailAccount = { "mazekun@outlook.com" };
	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
	// PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
	// 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
	public static String myEmailAccount = "ma.zekun@qq.com";
	public static String myEmailPassword = "ychthjmyopqlcahe";

	// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
	// 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
	public static String myEmailSMTPHost = "smtp.qq.com";

	public void postCommentToYou(String receiveMailAccount) throws Exception {
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties props = new Properties(); // 参数配置
		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.auth", "true"); // 需要请求认证

		final String smtpPort = "465";
		props.setProperty("mail.smtp.port", smtpPort);
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", smtpPort);

		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getInstance(props);
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log

		// 3. 创建一封邮件
		MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

		// 4. 根据 Session 获取邮件传输对象
		Transport transport = session.getTransport();

		// 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
		// PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
		transport.connect(myEmailAccount, myEmailPassword);

		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人,
		// 密送人
		transport.sendMessage(message, message.getAllRecipients());

		// 7. 关闭连接
		transport.close();
	}

	/**
	 * 创建一封只包含文本的简单邮件
	 *
	 * @param session
	 *            和服务器交互的会话
	 * @param sendMail
	 *            发件人邮箱
	 * @param receiveMail
	 *            收件人邮箱
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
		message.setFrom(new InternetAddress(sendMail, "Nukezam", "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, " ", "UTF-8"));

		// 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
		message.setSubject("Morning", "UTF-8");
		// JdbcUtil
		JdbcUtil jdbcUtil = new JdbcUtil();
		Connection conn = jdbcUtil.getConnection();
		String sql = "SELECT * FROM comments order by rand() limit 1";
		List<Comments> commentsList = new ArrayList<Comments>();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Comments comments = new Comments();
		String[] ss = null;
		while (rs.next()) {
			comments.setId(rs.getInt("id"));
			comments.setMusicName(rs.getString("music_name"));
			comments.setArtistName(rs.getString("artist_name"));
			ss = rs.getString("details").split("\n\n");
			comments.setComments(rs.getString("comments"));
			comments.setDetails(rs.getString("details"));
		}
		// 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）

		if (comments == null || comments.getComments().isEmpty()) {
			message.setContent("Today is only Good Morning" + "<br>" + "      ---Daylighemazekun",
					"text/html;charset=UTF-8");
		} else {
			String commentFinal = " ";
			Pattern pattern = Pattern.compile("\\d+$");

			for (int i = 0; i < ss.length; i++) {
				Matcher matcher = pattern.matcher(ss[i]);
				if (matcher.find()) {
					ss[i] = ss[i].replace(matcher.group(), "");
				}
				commentFinal = commentFinal + ss[i] + "<br>" + "<br>";
			}
			commentFinal = commentFinal + "<br><br>" + "<p>     ----Form" + comments.getArtistName() + "  "
					+ comments.getMusicName() + "</p>";
			message.setContent("Good Morning <br>" + commentFinal, "text/html;charset=UTF-8");
		}
		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		EmailToYou emailToYou = new EmailToYou();
		try {
			for (int i = 0; i < receiveMailAccount.length; i++) {
				emailToYou.postCommentToYou(receiveMailAccount[i]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
