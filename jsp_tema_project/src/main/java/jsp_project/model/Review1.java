package jsp_project.model;

import java.util.Date;

public class Review1 {
	private int review_num; // ±Û ¹øÈ£
	private String review_id; // ÀÛ¼ºÀÚ ID
	private String review_acmd; // ¸®ºä ¼÷¹Ú
	private String review_title; // ¸®ºä Á¦¸ñ
	private String review_content; // ¸®ºä ³»¿ë
	private int review_star; // º°Á¡
	private Date review_reg_date; // ¸®ºä ÀÛ¼ºÀÏ
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public String getReview_id() {
		return review_id;
	}
	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}

	public String getReview_acmd() {
		return review_acmd;
	}
	public void setReview_acmd(String review_acmd) {
		this.review_acmd = review_acmd;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public int getReview_star() {
		return review_star;
	}
	public void setReview_star(int review_star) {
		this.review_star = review_star;
	}
	public Date getReview_reg_date() {
		return review_reg_date;
	}
	public void setReview_reg_date(Date review_reg_date) {
		this.review_reg_date = review_reg_date;
	}
}
