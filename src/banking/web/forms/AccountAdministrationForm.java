package banking.web.forms;

import banking.ValidationException;
import banking.model.NewAccountModel;
import banking.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class AccountAdministrationForm {

    private String accountNumberAsString;

    public static AccountAdministrationForm fromRequest(HttpServletRequest req) {

        AccountAdministrationForm form = new AccountAdministrationForm();

        form.accountNumberAsString = req.getParameter("accountNumber");

        return form;
    }

    public NewAccountModel toModel() throws ValidationException {
        NewAccountModel account = new NewAccountModel();

        Integer accountNumber=null;
        if(StringUtils.isNotEmpty(accountNumberAsString)) {
            try {
                accountNumber=Integer.valueOf(accountNumberAsString);
            }catch (NumberFormatException ex) {
                throw new ValidationException("Numarul contului trebuie sa fie numeric!");
            }
        } else {
            throw new ValidationException("Completati numarul contului!");
        }
        account.setAccountNumber(accountNumber);

        return account;
    }

    public String getAccountNumberAsString() {
        return accountNumberAsString;
    }
}
