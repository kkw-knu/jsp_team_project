package jsp_project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.AcmdDao;
import jsp_project.dao.NoticeDao;
import jsp_project.dao.TravelDao;
import jsp_project.model.Acmd;
import jsp_project.model.Notice;
import jsp_project.model.Travel;

public class Main implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		NoticeDao nd = NoticeDao.getInstance();
		TravelDao td = TravelDao.getInstance();
		AcmdDao ad = AcmdDao.getInstance();
		List<Notice> noticelist = nd.newnotice();
		List<Travel> travellist = td.besttravel();
		List<Acmd> acmdlist = ad.bestacmd();
		
		request.setAttribute("noticelist", noticelist);
		request.setAttribute("travellist", travellist);
		request.setAttribute("acmdlist", acmdlist);
		return "main.jsp";
	}

}
