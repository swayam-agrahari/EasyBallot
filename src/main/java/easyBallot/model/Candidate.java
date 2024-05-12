package easyBallot.model;

public class Candidate {
	
	protected int id;
	protected String name,party;
	protected boolean approved;
	protected boolean rejected;
	
	
	public Candidate( ) {
		 
	}
	public Candidate( String name, String party, boolean approved,boolean rejected) {
		super();
		this.name = name;
		this.party = party;
		this.approved = approved;
		this.rejected = rejected;
	}
	public Candidate (int id,boolean approved,boolean rejected) {
		super();
		this.id = id;
		this.approved = approved;
		this.rejected = rejected;
	}
	
	public Candidate(int id, String name, String party, boolean approved,boolean rejected) {
		super();
		this.id = id;
		this.name = name;
		this.party = party;
		this.approved = approved;
		this.rejected = rejected;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isRejected() {
		return rejected;
	}
	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	
}
