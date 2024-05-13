package easyBallot.model;

import java.time.LocalDate;

public class Voter {
	protected int id,voterID;
	LocalDate DOB;
	protected boolean voteStatus;
	protected String name,email,address;

	
	

	public Voter () {
	}
	
	public Voter (int id ,String name,boolean voteStatus) {
		super();
		this.id = id;
		this.name= name;
		this.voteStatus = voteStatus;
		
	}
	
	public Voter (int voterID, LocalDate DOB ,boolean voteStatus,String name,String email,String address)
	{
	super();	
	this.voterID = voterID;
	this.DOB = DOB;
	this.voteStatus = voteStatus;
	this.name = name;
	this.email = email;
	this.address = address;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public int getVoterID( ) {
		return voterID;
	}
	public void setVoterID(int voterID) {
		this.voterID = voterID;
	}
	public LocalDate getDOB() {
		return DOB;
	}
	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}
	public boolean isVoteStatus() {
		return voteStatus;
	}
	public void setVoteStatus(boolean voteStatus) {
		this.voteStatus = voteStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
