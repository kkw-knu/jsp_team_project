package jsp_project.model;

import java.util.Date;

public class Review {
	private int review_num; // 글 번호
	private String review_id; // 작성자 ID
	private String review_travel; // 리뷰 여행지
	private String review_title; // 리뷰 제목
	private String review_content; // 리뷰 내용
	private int review_star; // 별점
	private Date review_reg_date; // 리뷰 작성일
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
	public String getReview_travel() {
		return review_travel;
	}
	public void setReview_travel(String review_travel) {
		this.review_travel = review_travel;
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
