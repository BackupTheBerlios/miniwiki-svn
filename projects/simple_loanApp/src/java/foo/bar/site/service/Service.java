package foo.bar.site.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import foo.bar.site.domain.User;
import foo.bar.site.domain.LoanApplication;
import foo.bar.site.controller.LoginFormControllerCommand;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class Service {

    private JdbcTemplate template;

    Service() {}
    public Service(JdbcTemplate template) {
        this.template = template;    
    }

    public User authenticate(final LoginFormControllerCommand command) throws AuthenticationException {
        User ok = (User) template.query(
                "SELECT ID, USERNAME, PASSWORD FROM TBL_USER WHERE USERNAME = ? AND PASSWORD = ? ",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, command.getUsername());
                        preparedStatement.setString(2, command.getPassword());
                    }
                },
                new ResultSetExtractor() {
                    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        User user = new User();
                        if (resultSet.next()) {
                            user.setId(resultSet.getInt(1));
                            user.setUsername(resultSet.getString(2));
                            user.setPassword(resultSet.getString(3));
                            return user;
                        }
                        return null;
                    }
                }
        );
        if (ok == null) {
            throw new AuthenticationException();
        }
        return ok;
    }

    public void deleteUser(final List<Integer> userIds) {
        for (final Integer userId: userIds) {
            template.update("DELETE FROM TBL_USER WHERE ID = ?",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, userId);
                    }
                });
        }
    }

    public User getUser(final int userId) {
        return (User) template.query(
                "SELECT ID, USERNAME, PASSWORD FROM TBL_USER WHERE ID = ?",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, userId);
                    }
                },
                new ResultSetExtractor() {
                    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        if (resultSet.next()) {
                            User user = new User();
                            user.setId(resultSet.getInt(1));
                            user.setUsername(resultSet.getString(2));
                            user.setPassword(resultSet.getString(3));
                            return user;
                        }
                        return null;
                    }
                }
        );
    }

    public List<User> getAllUsers() {
        return (List<User>) template.query(
                "SELECT ID, USERNAME, PASSWORD FROM TBL_USER",
                new PreparedStatementSetter(){
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    }
                },
                new ResultSetExtractor(){
                    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        List<User> users = new ArrayList<User>();
                        while(resultSet.next()) {
                            User user = new User();
                            user.setId(resultSet.getInt(1));
                            user.setUsername(resultSet.getString(2));
                            user.setPassword(resultSet.getString(3));
                            users.add(user);
                        }
                        return users;
                    }
                }
        );
    }

    public void updateUser(final User user) {
        template.update(
                "UPDATE TBL_USER SET PASSWORD=? WHERE ID=?", 
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, user.getPassword());
                        preparedStatement.setInt(2, user.getId());
                    }
                });
    }

    public void addUser(final User user) throws DuplicateUserException {
        boolean exists = (Boolean) template.query(
                "SELECT ID, USERNAME, PASSWORD FROM TBL_USER WHERE USERNAME = ? ",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, user.getUsername());
                    }
                },
                new ResultSetExtractor() {
                    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        return resultSet.next();
                    }
                }
        );
        if (exists) {
            throw new DuplicateUserException();
        }
        template.update(
                "INSERT INTO TBL_USER (USERNAME, PASSWORD) VALUES (?, ?) ",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, user.getUsername());
                        preparedStatement.setString(2, user.getPassword());
                    }
                });
    }


    public void addLoanApplication(final LoanApplication loanApplication) {
        template.update(
                "INSERT INTO TBL_LOAN_APPLICATION (SURNAME, FIRSTNAME, AGE, ADDRESS, IC_NUMBER, EMAIL, " +
                        "TELEPHONE_NUMBER, HANDPHONE_NUMBER, ANNUAL_INCOME, LOAN_AMOUNT, " +
                        "REFERRAL_HP_NO, APPLICATION_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, loanApplication.getSurName());
                        preparedStatement.setString(2, loanApplication.getFirstName());
                        if (loanApplication.getAge() != null)
                            preparedStatement.setInt(3, loanApplication.getAge());
                        preparedStatement.setString(4, loanApplication.getAddress());
                        preparedStatement.setString(5, loanApplication.getIcNumber());
                        preparedStatement.setString(6, loanApplication.getEmail());
                        preparedStatement.setString(7, loanApplication.getTelephoneNumber());
                        preparedStatement.setString(8, loanApplication.getHandphoneNumber());
                        if (loanApplication.getAnualIncome() != null)
                            preparedStatement.setDouble(9, loanApplication.getAnualIncome());
                        if (loanApplication.getLoanAmount() != null)
                            preparedStatement.setDouble(10, loanApplication.getLoanAmount());
                        preparedStatement.setString(11, loanApplication.getReferralHpNumber());
                    }
                });
    }

    public void deteLoanApplication(final List<Integer> loanIds) {
        for (final Integer loanId: loanIds) {
            template.update(
                "DELETE FROM TBL_LOAN_APPLICATION WHERE ID = ? ",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, loanId);
                    }
                });
        }
    }

    public LoanApplication getLoanApplication(final int loanApplicationId) {
        return (LoanApplication) template.query(
                "SELECT ID, SURNAME, FIRSTNAME, AGE, ADDRESS, IC_NUMBER, EMAIL, " +
                        "TELEPHONE_NUMBER, HANDPHONE_NUMBER, ANNUAL_INCOME, LOAN_AMOUNT, " +
                        "REFERRAL_HP_NO, APPLICATION_DATE FROM TBL_LOAN_APPLICATION WHERE ID = ? ", 
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, loanApplicationId);
                    }
                },
                new ResultSetExtractor(){
                    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        LoanApplication loan= null;

                        if (resultSet.next()) {
                            loan = new LoanApplication();
                            loan.setId(resultSet.getInt(1));
                            loan.setSurName(resultSet.getString(2));
                            loan.setFirstName(resultSet.getString(3));
                            loan.setAge(resultSet.getInt(4));
                            loan.setAddress(resultSet.getString(5));
                            loan.setIcNumber(resultSet.getString(6));
                            loan.setEmail(resultSet.getString(7));
                            loan.setTelephoneNumber(resultSet.getString(8));
                            loan.setHandphoneNumber(resultSet.getString(9));
                            loan.setAnualIncome(resultSet.getDouble(10));
                            loan.setLoanAmount(resultSet.getDouble(11));
                            loan.setReferralHpNumber(resultSet.getString(12));
                            loan.setApplicationDate(resultSet.getTimestamp(13));
                        }
                        return loan;
                    }
                });
    }

    public List<LoanApplication> getAllLoanApplications() {
        return (List<LoanApplication>) template.query(
                "SELECT ID, SURNAME, FIRSTNAME, AGE, ADDRESS, IC_NUMBER, EMAIL, " +
                        "TELEPHONE_NUMBER, HANDPHONE_NUMBER, ANNUAL_INCOME, LOAN_AMOUNT, " +
                        "REFERRAL_HP_NO, APPLICATION_DATE FROM TBL_LOAN_APPLICATION ORDER BY APPLICATION_DATE DESC ",
                new PreparedStatementSetter(){
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    }
                },
                new ResultSetExtractor(){
                    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        List<LoanApplication> loans = new ArrayList<LoanApplication>();
                        while(resultSet.next()) {
                            LoanApplication loan = new LoanApplication();
                            loan.setId(resultSet.getInt(1));
                            loan.setSurName(resultSet.getString(2));
                            loan.setFirstName(resultSet.getString(3));
                            loan.setAge(resultSet.getInt(4));
                            loan.setAddress(resultSet.getString(5));
                            loan.setIcNumber(resultSet.getString(6));
                            loan.setEmail(resultSet.getString(7));
                            loan.setTelephoneNumber(resultSet.getString(8));
                            loan.setHandphoneNumber(resultSet.getString(9));
                            loan.setAnualIncome(resultSet.getDouble(10));
                            loan.setLoanAmount(resultSet.getDouble(11));
                            loan.setReferralHpNumber(resultSet.getString(12));
                            loan.setApplicationDate(resultSet.getTimestamp(13));
                            loans.add(loan);
                        }
                        return loans;
                    }
                }
        );
    }

}
