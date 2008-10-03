package foo.bar.site.controller;

import foo.bar.site.domain.LoanApplication;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class ViewLoanCommandControllerCommand {

    private int loanApplicationId = -1;

    private LoanApplication loanApplication;


    public int getLoanApplicationId() {
        return loanApplicationId;
    }

    public void setLoanApplicationId(int loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }
}
