package banking.services;

import banking.ValidationException;
import banking.dao.TestAccountDao;
import banking.model.TestAccount;
import banking.utils.StringUtils;

public class TestAccountService {
	
	private TestAccountDao testAccountDao;
	
	public TestAccountService() {
		testAccountDao = new TestAccountDao();
	}

	/**
	 * Returneaza contul cu ID-ul dat.
	 * Daca nu exista cont, va arunca exceptie.
	 */
	public TestAccount getAccountById(Integer id) {
		TestAccount account = testAccountDao.getAccountById(id);
		if (account == null) {
			throw new IllegalArgumentException("Nu exista cont cu ID-ul [" + id + "].");
		}
		return account;
	}

	public void update(TestAccount accountToEdit) throws ValidationException {
		
		if (StringUtils.isEmpty(accountToEdit.getName())) {
			throw new ValidationException("Completati numele.");
		}
		if (accountToEdit.getSum() == null) {
			throw new ValidationException("Completati suma.");
		}
		
		testAccountDao.update(accountToEdit);
	}
}