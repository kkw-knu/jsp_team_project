package jsp_project.acmdservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.AcmdDao;
import jsp_project.service.CommandProcess;

public class Acmd_DeleteAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
			int acmd_num = Integer.parseInt(request.getParameter("acmd_num"));
			String pageNum = request.getParameter("pageNum");
			AcmdDao ad = AcmdDao.getInstance();
			int result = ad.delete(acmd_num);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
	
			return "acmd_delete.jsp";
	}
}
