package banking.web.forms;

import banking.ValidationException;
import banking.model.TransferModel;
import banking.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class TransferForm {
    private String sourceAccountIdAsString;
    private String sourceAccountSumAsString;
    private String destinationAccountIdAsString;
    private String transferSumAsString;

    public static TransferForm fromRequest(HttpServletRequest req) {
        TransferForm transferForm = new TransferForm();

        transferForm.sourceAccountIdAsString = req.getParameter("sourceAccountId");
        transferForm.sourceAccountSumAsString = req.getParameter("sourceAccountSum");
        transferForm.destinationAccountIdAsString = req.getParameter("destinationAccountId");
        transferForm.transferSumAsString = req.getParameter("transferSum");

        return transferForm;
    }

    public TransferModel toModel() throws ValidationException {
        TransferModel transferModel = new TransferModel();

        Integer sourceAccountId = null;
        if(StringUtils.isNotEmpty(sourceAccountIdAsString)) {
            sourceAccountId = Integer.valueOf(sourceAccountIdAsString);
        }
        transferModel.setSourceAccountId(sourceAccountId);

        Integer destinationAccountId = null;
        if(StringUtils.isNotEmpty(destinationAccountIdAsString)) {
            destinationAccountId = Integer.valueOf(destinationAccountIdAsString);
        } else {
            throw new ValidationException("Alegeti contul destinatar!");
        }
        transferModel.setDestinationAccountId(destinationAccountId);

        BigDecimal transferSum = null;
        if(StringUtils.isNotEmpty(transferSumAsString)) {
            try {
                transferSum = new BigDecimal(transferSumAsString);
                if(transferSum.compareTo(new BigDecimal(0))<=0){
                    throw new ValidationException("Suma transfer trebuie sa fie mai mare ca 0!");
                }
            } catch (NumberFormatException ex) {
                throw new ValidationException("Suma transfer incorecta!");
            }
        } else {
            throw new ValidationException("Completati suma de transfer!");
        }
        transferModel.setTransferSum(transferSum);

        BigDecimal sourceAccountSum = null;
        if(StringUtils.isNotEmpty(sourceAccountSumAsString)) {
            sourceAccountSum = new BigDecimal(sourceAccountSumAsString);
            if(sourceAccountSum.compareTo(transferSum)<0) {
                throw new ValidationException("Resurse insuficiente!");
            }
        }

        return transferModel;
    }

}
