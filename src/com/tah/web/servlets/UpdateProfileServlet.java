package com.tah.web.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.tah.beans.TalkerBean;
import com.tah.dao.TalkerDAO;
import com.tah.util.CommonUtil;

/**
 * Servlet implementation class for Servlet: Login
 *
 */
public class UpdateProfileServlet extends HttpServlet {
	
	static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("changepassword".equalsIgnoreCase(action)) {
			changePassword(request, response);
		}
		else if ("uploadimage".equalsIgnoreCase(action)) {
			uploadImage(request, response);
		}
		else {
			String result = updateTalker(request);
			response.sendRedirect("EditProfile.jsp?result="+result);
		}
		
	}

	private void uploadImage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		TalkerBean talker = (TalkerBean)request.getSession().getAttribute("talker");
		
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
			    if (item.isFormField() && item.getFieldName().equals("submitaction")) {
			    	if ("Remove current image".equals(item.getString())) {
						talker.setImagePath(null);
						TalkerDAO.updateTalker(talker);
					}
			    } else {
			    	//TODO: move it to separate directory or db?
			    	String dirPath = getServletContext().getRealPath("/images/pictures/");
			    	int dotIndex = item.getName().lastIndexOf('.');
			    	String extension = null;
			    	if (dotIndex != -1) {
			    		extension = item.getName().substring(dotIndex);
			    	}
			    	else {
			    		//default
			    		extension = ".gif";
			    	}
			    	String fileName = talker.getId()+extension;
			    	File uploadedFile = new File(dirPath, fileName);
			        item.write(uploadedFile);
			        
			        talker.setImagePath("images/pictures/"+fileName);
			        TalkerDAO.updateTalker(talker);
			    }
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect("UploadImage.jsp");
	}

	private void changePassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//change pass
		TalkerBean talker = (TalkerBean)request.getSession().getAttribute("talker");
		
		String curPassword = request.getParameter("curpassword");
		String hashedPassword = CommonUtil.hashPassword(curPassword);
		String newPassword = request.getParameter("newpassword");
		String confirmPassword = request.getParameter("confirmpassword");
		
		if (talker.getPassword().equals(hashedPassword)) {
			if (newPassword != null && newPassword.equals(confirmPassword)) {
				talker.setPassword(CommonUtil.hashPassword(newPassword));
				TalkerDAO.updateTalker(talker);
				response.sendRedirect("EditProfile.jsp?result=okpassword#passwordform");
				return;
			}
			else {
				response.sendRedirect("EditProfile.jsp?result=differentpass#passwordform");
				return;
			}
		}
		else {
			response.sendRedirect("EditProfile.jsp?result=badpass#passwordform");
			return;
		}
	}

	public String updateTalker(HttpServletRequest request) {
		// getting session and TalkerBean
		HttpSession session = request.getSession();
		TalkerBean talker = (TalkerBean) session.getAttribute("talker");
		String oldUserName = talker.getUserName();
		String oldEmail = talker.getEmail();
		
		//  get parameters sent through post
		String uname = request.getParameter("username");
		String email = request.getParameter("email");
		
		//check email and username
		if (!oldUserName.equals(uname) && TalkerDAO.getByUserName(uname) != null) {
			return "sameusername";
		}
		if (!oldEmail.equals(email) && TalkerDAO.getByEmail(email) != null) {
			return "sameemail";
		}

		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String category = request.getParameter("selection");
		String maritalstatus = request.getParameter("maritalstatus");
		String gender = request.getParameter("gender");
		int childrenNum = 0;
		try {
			childrenNum = Integer.parseInt(request.getParameter("children"));
		}
		catch(NumberFormatException nfe){};

		
		Date dateOfBirth = CommonUtil.parseDate(request.getParameter("birthdate"));
		if (dateOfBirth == null) {
			//user entered bad birthdate
			return "baddate";
		}
		
		talker.setUserName(uname);
		talker.setEmail(email);
		talker.setDob(dateOfBirth);
		talker.setGender(gender);
		talker.setMariStat(maritalstatus);
		talker.setCity(city);
		talker.setState(state);
		talker.setCountry(country);
		talker.setCategory(category);
		talker.setChildrenNum(childrenNum);
		
		TalkerDAO.updateTalker(talker);
		
		return "okprofile";
	}
	
}