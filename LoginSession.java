package jp.co.internous.cony.model.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginSession implements Serializable {
	private static final long serialVersionUID = -3505502245004221761L;
	
	private int userId;
	private int tempUserId;
	private String userName;
	private String password;
	private boolean loginFrag;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTempUserId() {
		return tempUserId;
	}
	public void setTempUserId(int tempUserId) {
		this.tempUserId = tempUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getLoginFrag() {
		return loginFrag;
	}
	public void setLoginFrag(boolean loginFrag) {
		this.loginFrag = loginFrag;
	}

}
