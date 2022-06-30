package jp.co.internous.cony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import jp.co.internous.cony.model.domain.MstUser;
import jp.co.internous.cony.model.form.UserForm;
import jp.co.internous.cony.model.mapper.MstUserMapper;
import jp.co.internous.cony.model.mapper.TblCartMapper;
import jp.co.internous.cony.model.session.LoginSession;

@RestController
@RequestMapping( "/cony/auth")
public class AuthController {
	
	@Autowired
	private LoginSession loginSession;
	@Autowired
	private MstUserMapper mstUserMapper;
	@Autowired
	private TblCartMapper tblCartMapper;
	
	Gson gson = new Gson();
	
	@PostMapping("/login")
	public String login(@RequestBody UserForm form) {
		
		MstUser mstUser = mstUserMapper.findByUserNameAndPassword(form.getUserName(), form.getPassword());
		
		int tempUserId = loginSession.getTempUserId();
		if(mstUser != null && tempUserId != 0) {
			tblCartMapper.updateUserId(mstUser.getId(), tempUserId);
		}
		
		if(mstUser != null) {
			loginSession.setUserId(mstUser.getId());
			loginSession.setTempUserId(0);
			loginSession.setUserName(mstUser.getUserName());
			loginSession.setPassword(mstUser.getPassword());
			loginSession.setLoginFrag(true);
		} else {
			loginSession.setUserId(0);
			loginSession.setUserName(null);
			loginSession.setPassword(null);
			loginSession.setLoginFrag(false);
		}
		return gson.toJson(mstUser);
	
	}
	
	@RequestMapping("/logout")
	public String logout() {
		
		loginSession.setUserId(0);
		loginSession.setTempUserId(0);
		loginSession.setUserName(null);
		loginSession.setPassword(null);
		loginSession.setLoginFrag(false);
		
		return "";
	}
	
}
