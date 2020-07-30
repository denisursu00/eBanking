package banking.services;

import banking.ValidationException;
import banking.dao.AccountDao;
import banking.model.Account;
import banking.model.DepositModel;
import banking.model.NewAccountModel;
import banking.model.PendingAccountModel;

import java.math.BigDecimal;
import java.util.List;

public class AccountService {
    private AccountDao accountDao;

    public AccountService() { accountDao = new AccountDao(); }

    public void insert(NewAccountModel newAccountModel) throws ValidationException {
        Boolean exists = accountDao.checkAccountNumber(newAccountModel);
        if(exists) {
            throw new ValidationException("Numar account egal cu unul din cele existente!");
        }
        accountDao.insert(newAccountModel);
    }

    public BigDecimal updateSum(Integer accountId, BigDecimal depositSum) {
        return accountDao.updateSum(accountId,depositSum);
    }

    public List<Account> getAllByClientId(Integer clientId) {

        return accountDao.getAllByClientId(clientId);

    }

    public List<Account> getAllActive() {

        return accountDao.getAllActive();

    }

    public List<Account> getActiveByClientId(Integer clientId) {

        return accountDao.getActiveByClientId(clientId);

    }

    public List<PendingAccountModel> getPending() {

        return accountDao.getPending();

    }

    public void updatePending(PendingAccountModel model) {
        accountDao.updatePending(model);
    }
}
