package banking.services;

import banking.ValidationException;
import banking.dao.ClientDao;
import banking.model.Client;
import banking.model.LoginModel;
import banking.utils.StringUtils;

import java.util.List;

public class ClientService {
    private ClientDao clientDao;

    public ClientService() {
        clientDao = new ClientDao();
    }

    public Client getByUserNameAndPassword(LoginModel loginModel) throws ValidationException {
        if(StringUtils.isEmpty(loginModel.getUserName()) || StringUtils.isEmpty(loginModel.getPassword())) {
            throw new ValidationException("Completati UserName si/sau Parola!");
        }
        Client client = clientDao.getByUserNameAndPassword(loginModel);
        if(client == null) {
            throw new ValidationException("Autentificare esuata!");
        }
        return client;
    }

    public List<Client> getAll(){

        return clientDao.getAll();

    }
}
