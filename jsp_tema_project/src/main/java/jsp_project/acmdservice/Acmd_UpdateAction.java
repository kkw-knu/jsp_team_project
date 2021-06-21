package jsp_project.acmdservice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp_project.dao.AcmdDao;
import jsp_project.model.Acmd;
import jsp_project.service.CommandProcess;

public class Acmd_UpdateAction implements CommandProcess {

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
		Acmd acmd = new Acmd();
		
		String fileName = mr.getFilesystemName("acmd_img");
		String pageNum = mr.getParameter("pageNum");
		int	acmd_num = Integer.parseInt(mr.getParameter("acmd_num"));
		String acmd_name = mr.getParameter("acmd_name");
		String acmd_mini  = mr.getParameter("acmd_mini");
		String acmd_content  = mr.getParameter("acmd_content");
		String acmd_local  = mr.getParameter("acmd_local");
		String acmd_travel  = mr.getParameter("acmd_travel");
		String acmd_q  = mr.getParameter("acmd_q");
		String acmd_img = fileName;

		
		acmd.setAcmd_content(acmd_content);
		acmd.setAcmd_img(acmd_img);
		acmd.setAcmd_local(acmd_local);
		acmd.setAcmd_mini(acmd_mini);
		acmd.setAcmd_name(acmd_name);
		acmd.setAcmd_num(acmd_num);
		acmd.setAcmd_q(acmd_q);
		acmd.setAcmd_travel(acmd_travel);
		
		AcmdDao ad = AcmdDao.getInstance();
		int result = ad.update(acmd);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);		
		request.setAttribute("acmd_num", acmd_num);
		return "acmd_update.jsp";
	}

}
