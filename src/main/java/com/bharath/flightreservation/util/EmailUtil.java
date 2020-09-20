package com.bharath.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bharath.flightreservation.controller.ReservationController;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender sender;
	@Autowired
	private static final Logger LOGGER= org.slf4j.LoggerFactory.getLogger(EmailUtil.class);
	
	public void sendItinerary(String toAddress, String filePath)
	{
		LOGGER.info("sendItinerary()");
		
	MimeMessage message=sender.createMimeMessage();
	
	try {
		MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(message,true);
		mimeMessageHelper.setTo(toAddress);
		mimeMessageHelper.setSubject("Itinerary for your Flight");
		mimeMessageHelper.setText("Please find your Itinerary attached.");
		mimeMessageHelper.addAttachment("Itinerary", new File(filePath));
		sender.send(message);
	} catch (MessagingException e) {
		LOGGER.error("Exception inside sendItinerary()"+e);
		e.printStackTrace();
	}
		
	}

}
