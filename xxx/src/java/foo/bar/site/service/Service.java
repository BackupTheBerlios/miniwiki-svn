package foo.bar.site.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import foo.bar.site.domain.LoginToken;
import foo.bar.site.domain.LoanApplication;

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

    public LoginToken authenticate(final LoginToken loginToken) throws AuthenticationException {
        LoginToken ok = (LoginToken) template.query(
                "SELECT ID, USERNAME, PASSWORD FROM TBL_USER WHERE USERNAME = ? AND PASSWORD = ? ",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, loginToken.getUsername());
                        preparedStatement.setString(2, loginToken.getPassword());
                    }
                },
                new ResultSetExtractor() {
                    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        if (resultSet.next()) {
                            loginToken.setId(resultSet.getInt(1));
                            loginToken.setUsername(resultSet.getString(2));
                            loginToken.setPassword(resultSet.getString(3));
                            return loginToken;
                        }
                        return null;
                    }
                }
        );
        if (ok == null) {
            throw new AuthenticationException();
        }
        return loginToken;
    }

    public void deleteUser(final int userId) {
        template.update("DELETE FROM TBL_USER WHERE ID = ?",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, userId);
                    }
                });
    }

    public List<LoginToken> getAllUsers() {
        return (List<LoginToken>) template.query(
                "SELECT ID, USERNAME, PASSWORD FROM TBL_USER",
                new PreparedStatementSetter(){
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    }
                },
                new ResultSetExtractor(){
                    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        List<LoginToken> users = new ArrayList<LoginToken>();
                        while(resultSet.next()) {
                            LoginToken user = new LoginToken();
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

    public void updateUser(final LoginToken user) {
        template.update(
                "UPDATE TBL_USER SET PASSWORD=? WHERE ID=?", 
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, user.getPassword());
                        preparedStatement.setInt(2, user.getId());
                    }
                });
    }

    public void addUser(final LoginToken loginToken) throws DuplicateUserException {
        boolean exists = (Boolean) template.query(
                "SELECT ID, USERNAME, PASSWORD FROM TBL_USER WHERE USERNAME = ? ",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, loginToken.getUsername());
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
                        preparedStatement.setString(1, loginToken.getUsername());
                        preparedStatement.setString(2, loginToken.getPassword());
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
                        preparedStatement.setInt(3, loanApplication.getAge());
                        preparedStatement.setString(4, loanApplication.getAddress());
                        preparedStatement.setString(5, loanApplication.getIcNumber());
                        preparedStatement.setString(6, loanApplication.getEmail());
                        preparedStatement.setString(7, loanApplication.getTelephoneNumber());
                        preparedStatement.setString(8, loanApplication.getHandphoneNumber());
                        preparedStatement.setDouble(9, loanApplication.getAnualIncome());
                        preparedStatement.setDouble(10, loanApplication.getLoanAmount());
                        preparedStatement.setString(11, loanApplication.getReferralHpNumber());
                    }
                });
    }

    public void deteLoanApplication(final int loanId) {
        template.update(
                "DELETE FROM TBL_LOAN_APPLICATION WHERE ID = ? ",
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, loanId);
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
