/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipd.login;

import java.io.Serializable;

/**
 *
 * @author haiph
 */
public class RegistrationCreateError implements Serializable {

    private String usernameLengErr;
    private String passwordLengErr;
    private String cofirmNotMatchPassword;
    private String fullnameLengErr;
    private String usernameIsExisted;

    public RegistrationCreateError() {
    }

    /**
     * @return the usernameLengErr
     */
    public String getUsernameLengErr() {
        return usernameLengErr;
    }

    /**
     * @param usernameLengErr the usernameLengErr to set
     */
    public void setUsernameLengErr(String usernameLengErr) {
        this.usernameLengErr = usernameLengErr;
    }

    /**
     * @return the passwordLengErr
     */
    public String getPasswordLengErr() {
        return passwordLengErr;
    }

    /**
     * @param passwordLengErr the passwordLengErr to set
     */
    public void setPasswordLengErr(String passwordLengErr) {
        this.passwordLengErr = passwordLengErr;
    }

    /**
     * @return the cofirmNotMatchPassword
     */
    public String getCofirmNotMatchPassword() {
        return cofirmNotMatchPassword;
    }

    /**
     * @param cofirmNotMatchPassword the cofirmNotMatchPassword to set
     */
    public void setCofirmNotMatchPassword(String cofirmNotMatchPassword) {
        this.cofirmNotMatchPassword = cofirmNotMatchPassword;
    }

    /**
     * @return the fullnameLengErr
     */
    public String getFullnameLengErr() {
        return fullnameLengErr;
    }

    /**
     * @param fullnameLengErr the fullnameLengErr to set
     */
    public void setFullnameLengErr(String fullnameLengErr) {
        this.fullnameLengErr = fullnameLengErr;
    }
    
    /**
     * @return the usernameIsExisted
     */
    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    /**
     * @param usernameIsExisted the usernameIsExisted to set
     */
    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
}
