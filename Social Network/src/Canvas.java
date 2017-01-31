/*
 * File: Canvas.java
 * -----------------------------
 * 
 */


import acm.graphics.*;
import java.awt.*;
import java.awt.Image.*;
import java.util.*;

public class Canvas extends GCanvas 
					implements Constants {
	
	double nameHeight = 0;
	double lastX = 0;
	double lastY = 0;
	
	
	public Canvas() {
	}

	public void showMessage(String msg) {
		GLabel message = new GLabel(msg);
		double x = getWidth()/2 - message.getWidth()*3/4;
		double y = getHeight() - BOTTOM_MESSAGE_MARGIN;
		if(getElementAt(lastX, lastY) != null) {
			remove(getElementAt(lastX, lastY));
		}
		lastX = x;
		lastY = y;
		message.setFont(MESSAGE_FONT);
		add(message, x, y);
	}
	
	

	public void displayProfile(Profile profile) {
		removeAll();
		addName(profile.getName());
		addImage(profile.getImage());
		addStatus(profile.getStatus());
		addFriends(profile.getFriends());
	}
	
	private void addName(String name) {
		GLabel Name = new GLabel(name);
		Name.setFont(PROFILE_NAME_FONT);
		Name.setColor(Color.BLUE);
		double x = LEFT_MARGIN;
		nameHeight = Name.getHeight();
		double y = TOP_MARGIN + nameHeight;
		add(Name, x, y);
	}
	
	private void addImage(GImage image) {
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN + nameHeight + IMAGE_MARGIN; 
		if(image != null) {
			image.setBounds(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image);
		}
		else {
			GRect imageRect = new GRect(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(imageRect);
			GLabel noImage = new GLabel("No Image");
			noImage.setFont(PROFILE_IMAGE_FONT);
			double labelWidth = x + IMAGE_WIDTH/2 - noImage.getWidth()/2;
			double labelHeight = y + IMAGE_HEIGHT/2;
			add(noImage, labelWidth, labelHeight);
		}
	}
	
	private void addStatus(String status) {
		GLabel Status = new GLabel(status);
		Status.setFont(PROFILE_STATUS_FONT);
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN + nameHeight + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + Status.getHeight();
		if(getElementAt(x, y) != null) {
			remove(getElementAt(x, y));
		}
		add(Status, x, y);
	}

	private void addFriends(Iterator<String>friends) {
		GLabel Friends = new GLabel("Friends:");
		Friends.setFont(PROFILE_FRIEND_LABEL_FONT);
		double x = getWidth()/2;
		double y = TOP_MARGIN + nameHeight;
		add(Friends, x, y);
		Iterator<String> it = friends;
		for(int i = 1; it.hasNext(); i++) {
			GLabel friendName = new GLabel(it.next());
			friendName.setFont(PROFILE_FRIEND_FONT);
			double height = y + Friends.getHeight() * i;
			add(friendName, x, height);
		}
	}
	
}