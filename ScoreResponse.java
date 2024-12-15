package com.project.quiz.dto;

public class ScoreResponse {
	 private int updatedScore;

	    public ScoreResponse(int updatedScore) {
	        this.updatedScore = updatedScore;
	    }

	    public int getUpdatedScore() {
	        return updatedScore;
	    }

	    public void setUpdatedScore(int updatedScore) {
	        this.updatedScore = updatedScore;
	    }
}
