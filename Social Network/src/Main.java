/* Made By Farzam Alam
 *
 * Dated : December 1, 2016.
 *
 * */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends Program 
					implements Constants {


	private JTextField name;
	private JTextField status;
	private JTextField picture;
	private JTextField friend;
	
	
	private Database profileInfo = new Database();
	private Canvas canvas = new Canvas();
	
	
	private Profile currentProfile = null;
	
	
	public void init() {
		
	
		add(new JLabel("Name "), NORTH); 
		
		name = new JTextField(TEXT_FIELD_SIZE);
		add(name, NORTH);
		
		add(new JButton("Add"), NORTH);
		
		add(new JButton("Delete"), NORTH);
		
		add(new JButton("Lookup"), NORTH);
		
		//fields on the West Side of the screen
		status = new JTextField(TEXT_FIELD_SIZE);
		add(status, WEST);
		
		add(new JButton("Change Status"), WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST); //space holder
		
		picture = new JTextField(TEXT_FIELD_SIZE);
		add(picture, WEST);
		
		add(new JButton("Change Picture"), WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST); //space holder
		
		friend = new JTextField(TEXT_FIELD_SIZE);
		add(friend, WEST);
		
		add(new JButton("Add Friend"), WEST);
		
		//Action listeners
		addActionListeners();
		status.addActionListener(this);
		picture.addActionListener(this);
		friend.addActionListener(this);
		
		add(canvas);
    }
  
  
    public void actionPerformed(ActionEvent e) {
    	
    	String enteredName = name.getText();
    	
    	//Add button is clicked
    	if(e.getActionCommand().equals("Add") && !name.getText().equals("")) {
    		if(profileInfo.containsProfile(enteredName) == false) {
    			Profile profile = new Profile(enteredName);
    			profileInfo.addProfile(profile);
    			canvas.displayProfile(profile);
    			canvas.showMessage("New profile created");
    			currentProfile = profile;
    		}
    		else{
    			Profile profile = profileInfo.getProfile(enteredName);
    			canvas.displayProfile(profile);
    			canvas.showMessage("A profile with name " + enteredName + " already exists.");
    			currentProfile = profile;
    		}
    	}
    	
    	else if (e.getActionCommand().equals("Delete") && !name.getText().equals("")){
    		canvas.removeAll();
    		currentProfile = null;
    		    		if(profileInfo.containsProfile(enteredName) == true) {
    			profileInfo.deleteProfile(enteredName);
    			canvas.showMessage("Profile of " + enteredName + " deleted");
    		}
    		else{
    			canvas.showMessage("A profile with name " + enteredName + " does not exist.");
    		}
    	}
    	
    	else if (e.getActionCommand().equals("Lookup") && !name.getText().equals("")){
    		canvas.removeAll(); //clears everything off the canvas
    		if(profileInfo.containsProfile(enteredName) == true) {
    			Profile profile = profileInfo.getProfile(enteredName);
    			canvas.displayProfile(profile);
    			canvas.showMessage("Displaying " + enteredName);
    			currentProfile = profile;
    		}    		
    		else{
    			canvas.showMessage("A profile with name " + enteredName + " does not exist.");
    			currentProfile = null;
    		}
    	}
    	
    	else if (e.getActionCommand().equals("Change Status") || e.getSource() == status && !status.getText().equals("")){
    		String statusMessage = status.getText();
    		if(currentProfile != null) {
    			Profile profile = profileInfo.getProfile(currentProfile.getName());
    			profile.setStatus(profile.getName() + " is " + statusMessage);
    			canvas.displayProfile(profile);
    			canvas.showMessage("Status updated to " + statusMessage);
    		}
    		else{
    			canvas.showMessage("Please select a profile to change status");
    		}
    	}
    	
    	else if (e.getActionCommand().equals("Change Picture") || e.getSource() == picture && !picture.getText().equals("")){
    		String filename = picture.getText();
    		if(currentProfile != null) {
    			Profile profile = profileInfo.getProfile(currentProfile.getName());
    			GImage image = null;
    			try {
    				image = new GImage(filename);
    				profile.setImage(image);
    			} catch (ErrorException ex) {
    				image = null;
    			}
    			canvas.displayProfile(profile);
    			if(image == null) {
    				canvas.showMessage("Unable to open image file: " + filename);
    			}
    			else{
    				canvas.showMessage("Picture updated");
    			}
    		}
    		else{
    			println("Please select a profile to change picture");
    		}
    	}
    	
    	else if (e.getActionCommand().equals("Add Friend") || e.getSource() == friend && !friend.getText().equals("")){
    		String friendName = friend.getText();
    		if(currentProfile != null) {
    			Profile profile = profileInfo.getProfile(currentProfile.getName());    			if(profile.getName().equals(friendName)) {
    				canvas.showMessage("You cannot friend yourself");
    			}
    			//checks to see if the friend exists in the database
    			else if(profileInfo.containsProfile(friendName)) {
    				Profile friendProfile = profileInfo.getProfile(friendName);
    				
    				if(profile.addFriend(friendName) == true) {
    					profile.addFriend(friendName);
    					friendProfile.addFriend(enteredName);
    					canvas.displayProfile(profile);
    					canvas.showMessage(friendName + " added as a friend.");
    				}
    				else {
    					canvas.showMessage(profile.getName() + " already has " + friendName + " as a friend.");
    				}
    			}
    			else{
    				canvas.showMessage(friendName + " does not exist.");
    			}
    		}	    		else{
    			canvas.showMessage("Please select a profile to add friend");
    		}	
    	}		
    }
}