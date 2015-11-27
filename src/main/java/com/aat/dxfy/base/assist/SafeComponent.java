package com.aat.dxfy.base.assist;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aat.utils.AATEmail;

@Component
public class SafeComponent {

	@Value("#{configProperties['email.username']}")
	private String username;
	@Value("#{configProperties['email.password']}")
	private String password;

	public void sendEmailCode(String toUser, String code)
			throws MessagingException {

		AATEmail.sendCode(username, password, toUser, code);
	}

	public void sendEmailLink(String toUser, String link)
			throws MessagingException {

		AATEmail.sendSafeLink(username, password, toUser, link);
	}

}
