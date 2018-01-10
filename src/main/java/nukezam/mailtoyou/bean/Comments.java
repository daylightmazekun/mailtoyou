package nukezam.mailtoyou.bean;

/**
 * @Package :nukezam.mailtoyou
 * @Title: Comments.java
 * @Package nukezam.mailtoyou
 * @author zekun ma burmaing@gmail.com
 * @date 2018年1月8日 下午9:01:36
 * @version V1.0
 */
public class Comments {
	private Integer id;
	private String musicName;
	private String artistName;
	private String comments;
	private String details;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
