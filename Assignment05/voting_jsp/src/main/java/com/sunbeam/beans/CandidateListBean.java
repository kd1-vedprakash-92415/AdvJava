package com.sunbeam.beans;

import java.util.ArrayList;
import java.util.List;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;

public class CandidateListBean {
	private List<Candidate> candidateList;
	public CandidateListBean() {
		this.candidateList = new ArrayList<>();
	}
	public List<Candidate> getCandidateList() {
		return candidateList;
	}
	public void setCandidateList(List<Candidate> candidateList) {
		this.candidateList = candidateList;
	}
	public void fetchCandidates() {
		try(CandidateDao candDao = new CandidateDaoImpl()) {
			this.candidateList = candDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
