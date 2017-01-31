
/*File : Database.java*/
import java.util.*;

public class Database implements Constants {
	
	/* private instance variables */
	private Map<String, Profile> profiles = new HashMap<String, Profile>();
	
	
	public Database() {
		
	}
	
	
	
	public void addProfile(Profile profile) {
		if(!profiles.containsKey(profile.getName())) {
			profiles.put(profile.getName(), profile);
		}
		else{
			profiles.remove(profile.getName());
			profiles.put(profile.getName(), profile);
		}
	}

	
	public Profile getProfile(String name) {
		if(profiles.containsKey(name)) {
			return profiles.get(name);
		}
		else{
			return null;
		}
		
	}
	
	
	
	public void deleteProfile(String name) {
		if(profiles.containsKey(name)) {
			Profile profileToRemove = profiles.get(name);
			Iterator<String>it = profileToRemove.getFriends();
			while(it.hasNext()) {
				String friendName = it.next();
				Profile friendsProfile = profiles.get(friendName);
				friendsProfile.removeFriend(name);
			}
			profiles.remove(name);
		}
	}

	
	
	public boolean containsProfile(String name) {
		if(profiles.containsKey(name)) {
			return true;
		}
		else {
			return false;
		}
	}

}