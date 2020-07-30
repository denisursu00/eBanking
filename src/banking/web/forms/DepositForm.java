package banking.web.forms;

import banking.ValidationException;
import banking.model.DepositModel;
import banking.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class DepositForm {

    private String accountIdAsString;
    private String depositSumAsString;

    public static DepositForm fromRequest(HttpServletRequest req) {
        DepositForm depositForm = new DepositForm();
        depositForm.accountIdAsString = req.getParameter("accountId");
        depositForm.depositSumAsString = req.getParameter("depositSum");

        return depositForm;
    }

    public DepositModel toModel() throws ValidationException {
        DepositModel depositModel = new DepositModel();

        Integer accountId = null;
        if(StringUtils.isNotEmpty(accountIdAsString)) {
            accountId = Integer.valueOf(accountIdAsString);
        }
        depositModel.setAccountId(accountId);


        BigDecimal depositSum = null;
        if(StringUtils.isNotEmpty(depositSumAsString)) {
            try {
                depositSum = new BigDecimal(depositSumAsString);
                if(depositSum.compareTo(new BigDecimal(0))==-1){
                    throw new ValidationException("Suma trebuie sa fie mai mare ca 0!");
                }
            } catch (NumberFormatException ex) {
                throw new ValidationException("Suma incorecta!");
            }
        }
        depositModel.setDepositSum(depositSum);

        return depositModel;
    }

    public String getAccountIdAsString() { return accountIdAsString; }

    public String getDepositSumAsString() { return depositSumAsString; }
}
