package jsp_project.model;
import java.sql.Date;
public class Qna {
	private int qna_num;
	private String qna_title;
	private String qna_content;
	private String qna_writer;
	private int qna_readcount;
	private int qna_ref;
	private int qna_re_step;
	private int qna_re_level;
	private Date qna_reg_date;
	private String qna_del;
	public int getQna_num() {
		return qna_num;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public String getQna_writer() {
		return qna_writer;
	}
	public void setQna_writer(String qna_writer) {
		this.qna_writer = qna_writer;
	}
	public int getQna_readcount() {
		return qna_readcount;
	}
	public void setQna_readcount(int qna_readcount) {
		this.qna_readcount = qna_readcount;
	}
	public int getQna_ref() {
		return qna_ref;
	}
	public void setQna_ref(int qna_ref) {
		this.qna_ref = qna_ref;
	}
	public int getQna_re_step() {
		return qna_re_step;
	}
	public void setQna_re_step(int qna_re_step) {
		this.qna_re_step = qna_re_step;
	}
	public int getQna_re_level() {
		return qna_re_level;
	}
	public void setQna_re_level(int qna_re_level) {
		this.qna_re_level = qna_re_level;
	}
	public Date getQna_reg_date() {
		return qna_reg_date;
	}
	public void setQna_reg_date(Date qna_reg_date) {
		this.qna_reg_date = qna_reg_date;
	}
	public String getQna_del() {
		return qna_del;
	}
	public void setQna_del(String qna_del) {
		this.qna_del = qna_del;
	}
}
