package jsp_project.noticeservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.NoticeDao;
import jsp_project.model.Notice;
import jsp_project.service.CommandProcess;

public class NoticeAction implements CommandProcess {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		final int ROW_PER_PAGE = 10;     // ���������� 10����
		final int PAGE_PER_BLOCK = 10;   // �Ѻ����� 10������
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		NoticeDao nd = NoticeDao.getInstance();
		int total = nd.getTotal();  
		// ���۹�ȣ	(��������ȣ - 1) * �������� ����+ 1				
		int startRow = (currentPage - 1) * ROW_PER_PAGE + 1;
		// ����ȣ 	���۹�ȣ + �������簳�� - 1			
		int endRow = startRow + ROW_PER_PAGE - 1;
		List<Notice> list = nd.list(startRow, endRow);
		// ��ȣ�� ���� ���� ����
		int number = total - startRow + 1;
		int totalPage = (int)Math.ceil((double)total/ROW_PER_PAGE);   // �� ������ ��
		// ����������	���������� - (���������� - 1)%10			
		int startPage = currentPage - (currentPage - 1)%PAGE_PER_BLOCK;
		// ��������	���������� + ���ϴ������� �� - 1
		int endPage = startPage + PAGE_PER_BLOCK - 1;
		// �� ���������� ū endPage���� �� ����
		if (endPage > totalPage) endPage = totalPage;
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("PAGE_PER_BLOCK", PAGE_PER_BLOCK);
		request.setAttribute("number", number);
		request.setAttribute("list", list);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		
		return "notice.jsp";
	}

}