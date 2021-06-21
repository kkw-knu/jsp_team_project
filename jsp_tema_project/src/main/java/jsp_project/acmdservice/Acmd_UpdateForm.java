package jsp_project.acmdservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.AcmdDao;
import jsp_project.model.Acmd;
import jsp_project.service.CommandProcess;

public class Acmd_UpdateForm implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int acmd_num = Integer.parseInt(request.getParameter("acmd_num"));
		AcmdDao ad = AcmdDao.getInstance();
		Acmd acmd = ad.select(acmd_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("acmd", acmd);
		return "acmd_updateForm.jsp";
	}

}
