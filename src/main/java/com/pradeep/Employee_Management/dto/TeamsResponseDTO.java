package com.pradeep.Employee_Management.dto;

public class TeamsResponseDTO {
	private Integer teamsId;
	private String teamName;
	private String managerName;

	public Integer getTeamsId() {
		return teamsId;
	}

	public void setTeamsId(Integer teamsId) {
		this.teamsId = teamsId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
}
