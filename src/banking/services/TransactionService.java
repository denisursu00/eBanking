package banking.services;

import banking.ValidationException;
import banking.dao.TransactionDao;
import banking.model.DepositModel;
import banking.model.ReportModel;
import banking.model.Transaction;
import banking.model.TransferModel;

import java.math.BigDecimal;
import java.util.List;

public class TransactionService {
    private TransactionDao transactionDao;
    private AccountService accountService;

    public TransactionService() {
        transactionDao = new TransactionDao();
        accountService = new AccountService();
    }

    public void deposit(DepositModel depositModel) throws ValidationException {
        if(depositModel.getAccountId()==null) {
            throw new ValidationException("Alegeti contul!");
        }
        if(depositModel.getDepositSum()==null) {
            throw new ValidationException("Completati suma de alimentare!");
        }
        Integer accountId = depositModel.getAccountId();
        BigDecimal depositSum = depositModel.getDepositSum();

        BigDecimal updatedSum = accountService.updateSum(accountId,depositSum);
        depositModel.setAccountSum(updatedSum);

        transactionDao.deposit(depositModel);
    }

    public void transfer(TransferModel transferModel) throws ValidationException {
        if(transferModel.getSourceAccountId()==null) {
            throw new ValidationException("Alegeti contul sursa!");
        } else {
            Integer sourceAccountId = transferModel.getSourceAccountId();
            Integer destinationAccountId = transferModel.getDestinationAccountId();
            BigDecimal transferSum = transferModel.getTransferSum();

            BigDecimal updatedSum = accountService.updateSum(sourceAccountId,transferSum.negate());
            transferModel.setSourceAccountSum(updatedSum);

            updatedSum = accountService.updateSum(destinationAccountId,transferSum);
            transferModel.setDestinationAccountSum(updatedSum);

            transactionDao.transfer(transferModel);
        }
    }

    public List<Transaction> getByClientId(ReportModel reportModel) throws ValidationException {
        if(reportModel.getAccountId() == null) {
            throw new ValidationException("Alegeti contul dorit!");
        }
        if(reportModel.getStartDate() == null) {
            throw new ValidationException("Introduceti data de start!");
        }
        if(reportModel.getEndDate() == null) {
            throw new ValidationException("Introduceti data de sfarsit!");
        }
        return transactionDao.getByClientId(reportModel);
    }

}
