package foo.bar.site.controller;

import foo.bar.site.domain.LoanApplication;

import java.util.List;
import java.util.ArrayList;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class DeleteLoanCommandControllerCommand {

    private List<Integer> loanIds = new ArrayList<Integer>();
    private List<LoanApplication> loanApplications = new ArrayList<LoanApplication>();

    public List<LoanApplication> getLoanApplications() {
        return loanApplications;
    }

    public void setLoanApplications(List<LoanApplication> loanApplications) {
        this.loanApplications = loanApplications;
    }

    public List<Integer> getLoanIds() {
        return loanIds;
    }

    public void setLoanIds(List<Integer> loanIds) {
        this.loanIds = loanIds;
    }
}
