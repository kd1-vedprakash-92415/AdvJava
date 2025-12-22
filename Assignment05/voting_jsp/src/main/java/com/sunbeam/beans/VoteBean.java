package com.sunbeam.beans;

import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

public class VoteBean {
    private int candidateId;
    private User user;
    private boolean status; 

    public void registerVote() {
        status = false; 
        if (user == null || user.getId() <= 0) {
            return;
        }
        try (CandidateDaoImpl candDao = new CandidateDaoImpl();
             UserDaoImpl userDao = new UserDaoImpl()) {

            if (user.isStatus()) {
                status = false;
                return;
            }

            int rows = candDao.incrVote(candidateId);
            if (rows > 0) {
                user.setStatus(true);
                int updated = userDao.update(user);
                if (updated > 0) {
                    status = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCandidateId() { return candidateId; }
    public void setCandidateId(int candidateId) { this.candidateId = candidateId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public boolean isStatus() { return status; }
}