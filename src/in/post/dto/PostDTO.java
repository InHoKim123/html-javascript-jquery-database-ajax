package in.post.dto;

public class PostDTO {
	private int postnum;
	private String posttitle;
	private String passwd;
	private String postcontent;
	private String postwriter;
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	private String postwriteday;
	private int views;
	private String postkind;
	
	public int getPostnum() {
		return postnum;
	}
	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}
	public String getPosttitle() {
		return posttitle;
	}
	public void setPosttitle(String posttitle) {
		this.posttitle = posttitle;
	}
	public String getPostcontent() {
		return postcontent;
	}
	public void setPostcontent(String postcontent) {
		this.postcontent = postcontent;
	}
	public String getPostwriter() {
		return postwriter;
	}
	public void setPostwriter(String postwriter) {
		this.postwriter = postwriter;
	}
	public String getPostwriteday() {
		return postwriteday;
	}
	public void setPostwriteday(String postwriteday) {
		this.postwriteday = postwriteday;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getPostkind() {
		return postkind;
	}
	public void setPostkind(String postkind) {
		this.postkind = postkind;
	}
	@Override
	public String toString() {
		return "PostDTO [postnum=" + postnum + ", posttitle=" + posttitle + ", passwd=" + passwd + ", postcontent="
				+ postcontent + ", postwriter=" + postwriter + ", postwriteday=" + postwriteday + ", views=" + views
				+ ", postkind=" + postkind + "]";
	}
	
	
	
}
