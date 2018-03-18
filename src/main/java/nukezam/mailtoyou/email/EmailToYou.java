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

import nukezam.mailtoyou.bean.Comments;
import nukezam.mailtoyou.bean.People;
import nukezam.mailtoyou.utils.JdbcUtil;

public class EmailToYou implements Runnable {
	List<People> people = new ArrayList<People>();
	// 收件人邮箱
	public static String[] receiveMailAccount;

	public static String myEmailAccount = "ma.zekun@qq.com";
	public static String myEmailPassword = "ychthjmyopqlcahe";

	EmailToYou emailToYou;
	
	public EmailToYou() {
	}
	
	public EmailToYou(List<People> people) {
		//EmailToYou emailToYou = new EmailToYou();
		emailToYou = new EmailToYou();
		this.people = people;
	}

	public static String myEmailSMTPHost = "smtp.qq.com";

	public void postCommentToYou(People peopleReceiveMailAccount) throws Exception {

		Properties props = new Properties(); 
		props.setProperty("mail.transport.protocol", "smtp"); 
		props.setProperty("mail.smtp.host", myEmailSMTPHost); 
		props.setProperty("mail.smtp.auth", "true"); 

		final String smtpPort = "465";
		props.setProperty("mail.smtp.port", smtpPort);
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", smtpPort);


		Session session = Session.getInstance(props);
		session.setDebug(true); 

		// 创建一封邮件
		MimeMessage message = createMimeMessage(session, myEmailAccount, peopleReceiveMailAccount.getEmail());

		// 根据 Session 获取邮件传输对象
		Transport transport = session.getTransport();

		// 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
		transport.connect(myEmailAccount, myEmailPassword);

		// 6. 发送邮件
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
		// 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// From: 发件人
		message.setFrom(new InternetAddress(sendMail, "Nukezam", "UTF-8"));

		// To: 收件人
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, " ", "UTF-8"));

		// Subject: 邮件主题
		message.setSubject("雯妃晚上好", "UTF-8");
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
		// Content: 邮件正文（可以使用html标签）

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
			message.setContent("祝小仙女晚上好 说实话 推送的那首歌 我也不知道 <br>" + commentFinal, "text/html;charset=UTF-8");
		}
		// 设置发件时间
		message.setSentDate(new Date());

		// 保存设置
		message.saveChanges();

		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			for (int i = 0; i < people.size(); i++) {
				emailToYou.postCommentToYou(people.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
