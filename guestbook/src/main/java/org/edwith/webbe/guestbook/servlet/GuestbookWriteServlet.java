package org.edwith.webbe.guestbook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;
@WebServlet("guestbooks/write")
public class GuestbookWriteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 코드를 작성하세요.
    	response.setContentType("text/html; charset=utf-8");
    	
    	PrintWriter out = response.getWriter();
    	String name = request.getParameter("name");
    	String content = request.getParameter("content");
    	Guestbook guestbook = new Guestbook( name, content);
 
    	
    	GuestbookDao dao = new GuestbookDao();
    	dao.addGuestbook(guestbook);
    	List<Guestbook> list = dao.getGuestbooks();
    	request.setAttribute("list", list);
    	out.print(guestbook.toString());
    	
    	
    	
    	
    	
    	response.sendRedirect("http://localhost:8080/guestbooks/guestbooks.jsp");
    	out.close();
    }

	
}
