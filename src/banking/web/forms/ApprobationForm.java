package banking.web.forms;

import banking.model.PendingAccountModel;
import banking.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class ApprobationForm {
    private String accountIdAsString;
    private String stateIdAsString;

    public static ApprobationForm fromRequest(HttpServletRequest req) {
        ApprobationForm form = new ApprobationForm();

        form.accountIdAsString = req.getParameter("accountId");
        form.stateIdAsString = req.getParameter("state");

        return form;
    }

    public PendingAccountModel toModel(){
        PendingAccountModel model = new PendingAccountModel();

        Integer accountId = null;
        if(StringUtils.isNotEmpty(accountIdAsString)) {
            accountId = Integer.valueOf(accountIdAsString);
        }
        model.setId(accountId);

        Integer stateId = null;
        if(StringUtils.isNotEmpty(stateIdAsString)) {
            stateId = Integer.valueOf(stateIdAsString);
        }
        model.setStateId(stateId);

        return model;
    }

}
