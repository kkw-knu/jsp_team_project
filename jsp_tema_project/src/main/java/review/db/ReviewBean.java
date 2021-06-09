package review.db;

import java.sql.Timestamp;

public class ReviewBean {
	private int review_no;
	private String review_pw;
	private int review_category;
	private String review_subject;
	private String review_content;
	private String review_imgfile;
	private int review_count;
	private int review_recommend;
	private Timestamp review_date;

	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public String getReview_pw() {
		return review_pw;
	}
	public void setReview_pw(String review_pw) {
		this.review_pw = review_pw;
	}
	public String getReview_subject() {
		return review_subject;
	}
	public void setReview_subject(String review_subject) {
		this.review_subject = review_subject;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_imgfile() {
		return review_imgfile;
	}
	public void setReview_imgfile(String review_imgfile) {
		this.review_imgfile = review_imgfile;
	}
	public int getReview_count() {
		return review_count;
	}
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}
	public Timestamp getReview_date() {
		return review_date;
	}
	public void setReview_date(Timestamp review_date) {
		this.review_date = review_date;
	}
	public int getReview_category() {
		return review_category;
	}
	public void setReview_category(int review_category) {
		this.review_category = review_category;
	}
	public int getReview_recommend() {
		return review_recommend;
	}
	public void setReview_recommend(int review_recommend) {
		this.review_recommend = review_recommend;
	}
	
}
