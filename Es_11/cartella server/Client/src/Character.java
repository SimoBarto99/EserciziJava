
public class Character implements Comparable<Character> {

	private String name;
	private String surname;
	private String casata;
	private String role; //M or S --> M = main character | S = secondary character

	public Character(String name, String surname, String casata, String role) {
		this.name = name;
		this.surname = surname;
		this.casata = casata;
		this.role = role;
	}

	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}

	public String getCasata() {
		return casata;
	}
	
	public String getRole() {
		return role;
	}


	@Override
	public int compareTo(Character o) {
		if (role.equals(o.getRole())) {
			if(surname.equals(o.getSurname()))
				return name.compareTo(o.getName());
			else
				return (surname).compareTo(o.getSurname());
		} else if (role.equals("S")) {
			return -1;
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return getName() + " "+ getSurname() + " - "+getRole();
	}

	
}
