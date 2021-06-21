package jsp_project.travelservice;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp_project.dao.TravelDao;
import jsp_project.model.Travel;
import jsp_project.service.CommandProcess;

public class Travel_WriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (java.io.UnsupportedEncodingException e) {
			System.out.println("에러 qna_writeaction : "+e.getMessage());
		}
		String real = this.getClass().getResource("").getPath();
		real = real.substring(1,real.indexOf("wtpwebapps")) + "wtpwebapps/jsp_project/filesave";//실제는 meta데이터에 저장
		int maxSize = 1024 * 1024 * 10; // 10MB까지 가능
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(request, real,
				maxSize, "utf-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			System.out.println("사진저장 에러 : " + e.getMessage());
		} 
		Travel travel = new Travel();
		
		String fileName = mr.getFilesystemName("travel_img");
		String pageNum = mr.getParameter("pageNum");
		int	travel_num = Integer.parseInt(mr.getParameter("travel_num"));
		String travel_name = mr.getParameter("travel_name");
		String travel_mini  = mr.getParameter("travel_mini");
		String travel_content  = mr.getParameter("travel_content");
		String travel_local  = mr.getParameter("travel_local");
		String travel_q1  = mr.getParameter("travel_q1");
		String travel_q2  = mr.getParameter("travel_q2");
		String travel_q3  = mr.getParameter("travel_q3");
		String travel_q4  = mr.getParameter("travel_q4");
		String travel_img = fileName;

		
		travel.setTravel_content(travel_content);
		travel.setTravel_img(travel_img);
		travel.setTravel_local(travel_local);
		travel.setTravel_mini(travel_mini);
		travel.setTravel_name(travel_name);
		travel.setTravel_num(travel_num);
		travel.setTravel_q1(travel_q1);
		travel.setTravel_q2(travel_q2);
		travel.setTravel_q3(travel_q3);
		travel.setTravel_q4(travel_q4);
		
		TravelDao td = TravelDao.getInstance();
		int result = td.insert(travel);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		
		return "travel_write.jsp";
	}

}
