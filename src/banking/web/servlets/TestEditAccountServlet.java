package banking.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.ValidationException;
import banking.model.TestAccount;
import banking.services.TestAccountService;
import banking.utils.StringUtils;
import banking.web.forms.TestAccountEditForm;

@SuppressWarnings("serial")
public class TestEditAccountServlet extends HttpServlet {
	
	private TestAccountService testAccountService;
	
	public TestEditAccountServlet() {
		testAccountService = new TestAccountService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		prepareForEdit(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		edit(request, response);
	}
	
	private void prepareForEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idAsString = request.getParameter("id");
		if (StringUtils.isEmpty(idAsString)) {
			throw new IllegalArgumentException("Trebuie specificat ID-ul");
		}
		Integer id = Integer.valueOf(idAsString);
		
		TestAccount accountToEdit = testAccountService.getAccountById(id);
		TestAccountEditForm editForm = TestAccountEditForm.fromModel(accountToEdit);
		request.setAttribute("editForm", editForm);
		
		goToPage(request, response);
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TestAccountEditForm editForm = TestAccountEditForm.fromRequest(request);
		request.setAttribute("editForm", editForm);
		
		try {
			TestAccount accountToEdit = editForm.toModel();
			testAccountService.update(accountToEdit);
			request.setAttribute("message", "Contul s-a salvat cu succes.");
		} catch (ValidationException ve) {
			request.setAttribute("message", ve.getMessage());
		}
		
		goToPage(request, response);
	}
	
	private void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/JSP/testEditAccount.jsp").forward(request, response);
	}

}