package foo.bar.site.controller;

import foo.bar.site.domain.LoanApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ListLoanApplicationCommandControllerCommand extends DefaultCommandController {

    private List<LoanApplication> loanApplications = new ArrayList<LoanApplication>();

    public List<LoanApplication> getLoanApplications() {
        return loanApplications;
    }

    public void setLoanApplications(List<LoanApplication> loanApplications) {
        this.loanApplications = loanApplications;
    }
}
