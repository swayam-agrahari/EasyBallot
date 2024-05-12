package easyBallot.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import easyBallot.dao.CandidateDAO;
import easyBallot.model.Candidate;

/**
 * Servlet implementation class Candidate
 */
@WebServlet("/")
public class CandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CandidateDAO candidateDAO;

	public CandidateServlet() {
		this.candidateDAO = new CandidateDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {

			switch (action) {
			case "/candidate": {
				listUser(request, response);
				break;
			}

			case "/update": {
				updateUser(request, response);
				break;
			}
			case "/reject":{
				rejectUser(request,response);
				break;
			}
			case "/edit":
				showEditForm(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		boolean approved = true;
		boolean rejected = false;

		Candidate candidate = new Candidate(id, approved,rejected);
		candidateDAO.updateUser(candidate);

		response.sendRedirect("/EasyBallot/list");
	}
	
	private void rejectUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		boolean approved = false;	
		boolean rejected = true;

		Candidate candidate = new Candidate(id, approved,rejected);
		candidateDAO.updateUser(candidate);

		response.sendRedirect("/EasyBallot/list");
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Candidate> listUser = candidateDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("candidate.jsp");
		dispatcher.forward(request, response);

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Candidate existingUser = candidateDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("candidate.jsp");
		request.setAttribute("candidate", existingUser);
		dispatcher.forward(request, response);

	}

}
