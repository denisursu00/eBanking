package banking.web.forms;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import banking.ValidationException;
import banking.model.TestAccount;
import banking.utils.StringUtils;

public class TestAccountEditForm {

	private String idAsString;
	private String nameAsString;
	private String sumAsString;
	
	public static TestAccountEditForm fromModel(TestAccount account) {
		
		TestAccountEditForm form = new TestAccountEditForm();
		
		form.idAsString = "";
		if (account.getId() != null) {
			form.idAsString = account.getId().toString();
		}
		
		form.nameAsString = account.getName();
		
		form.sumAsString = "";
		if (account.getSum() != null) {
			form.sumAsString = account.getSum().toString();
		}
		
		return form;
	}
	
	public static TestAccountEditForm fromRequest(HttpServletRequest request) {
		
		TestAccountEditForm form = new TestAccountEditForm();
		
		form.idAsString = request.getParameter("id");
		form.nameAsString = request.getParameter("name");
		form.sumAsString = request.getParameter("sum");
		
		return form;
	}
	
	public TestAccount toModel() throws ValidationException {
		
		TestAccount account = new TestAccount();
		
		Integer id = null;
		if (StringUtils.isNotEmpty(idAsString)) {
			try {
				id = Integer.valueOf(idAsString);
			} catch (NumberFormatException nfe) {
				throw new ValidationException("ID-ul trebuie sa fie numeric.", nfe);
			}
		}
		account.setId(id);
		
		String name = nameAsString;
		account.setName(name);
		
		BigDecimal sum = null;
		if (StringUtils.isNotEmpty(sumAsString)) {
			try {
				sum = new BigDecimal(sumAsString);
			} catch (NumberFormatException nfe) {
				throw new ValidationException("Suma trebuie sa fie numerica.", nfe);
			}
		}
		account.setSum(sum);
		
		return account;
	}
	
	public String getIdAsString() {
		return idAsString;
	}
	public String getNameAsString() {
		return nameAsString;
	}
	public String getSumAsString() {
		return sumAsString;
	}
}