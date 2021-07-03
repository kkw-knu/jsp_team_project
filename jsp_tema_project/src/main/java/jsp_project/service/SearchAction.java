package jsp_project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_project.dao.TravelDao;
import jsp_project.model.Travel;

public class SearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String travel_q1 = request.getParameter("travel_q1");
		String travel_q2 = request.getParameter("travel_q2");
		String travel_q3 = request.getParameter("travel_q3");
		String travel_q4 = request.getParameter("travel_q4");
		TravelDao td = TravelDao.getInstance();
		List<Travel> travellist = td.travel_q(travel_q1, travel_q2, travel_q3, travel_q4);
		
		request.setAttribute("travellist", travellist);
		return "search.jsp";
	}

}
