package foo.bar.site.controller;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class SubmitLoanCommandControllerCommand {

    private String surName;
    private String firstName;
    private Integer age;
    private String address;
    private String icNumber;
    private String email;
    private String telephoneNumber;
    private String handphoneNumber;
    private Double anualIncome;
    private Double loanAmount;
    private String referralHpNumber;


    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getHandphoneNumber() {
        return handphoneNumber;
    }

    public void setHandphoneNumber(String handphoneNumber) {
        this.handphoneNumber = handphoneNumber;
    }

    public Double getAnualIncome() {
        return anualIncome;
    }

    public void setAnualIncome(Double anualIncome) {
        this.anualIncome = anualIncome;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getReferralHpNumber() {
        return referralHpNumber;
    }

    public void setReferralHpNumber(String referralHpNumber) {
        this.referralHpNumber = referralHpNumber;
    }

    public void reset() {
        surName = null;
        firstName = null;
        age = null;
        address = null;
        icNumber = null;
        email = null;
        telephoneNumber = null;
        handphoneNumber = null;
        anualIncome = null;
        loanAmount = null;
        referralHpNumber = null;
    }
}
