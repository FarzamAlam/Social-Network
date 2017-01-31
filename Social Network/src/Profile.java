/*File : Profile.java*/
import acm.graphics.*;
import java.util.*;

public class Profile implements Constants {
	
	/* Private instance variables*/
	private String Name = "";
	private GImage Image = null;
	private String Status = "No current status";
	private ArrayList <String> friends = new ArrayList<String>();
	
	
	public Profile(String name) {
		Name = name;
	}

	public String getName() {
		return Name;
	}


	public GImage getImage() {
		if(Image == null) {
			return null;
		}
		else{
			return Image;
		}
	}

	public void setImage(GImage image) {
		Image = image;
	}
	

	public String getStatus() {
		return Status;
	}
	
	public void setStatus(String status) {
		Status = status;
	}

	
	public boolean addFriend(String friend) {
		if(friends.contains(friend)) {
			return false;
		}
		else{
			friends.add(friend);
			return true;
		}
	}

	
	public boolean removeFriend(String friend) {
		if(friends.contains(friend)) {
			friends.remove(friends.indexOf(friend));
			return true;
		}
		else{
			return false;
		}
	}

	 
	public Iterator<String> getFriends() {
		return friends.iterator();
	}

	public String toString() {
		String profile = Name + " (" + Status + "): ";
		Iterator<String>it = friends.iterator();
		while(it.hasNext()) {
			profile += it.next() + ", ";
		}
		return profile;
	}
	
}