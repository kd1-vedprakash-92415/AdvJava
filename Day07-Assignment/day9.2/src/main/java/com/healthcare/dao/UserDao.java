package com.healthcare.dao;

import java.io.IOError;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.healthcare.dtos.UserDTO;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

public interface UserDao {
//add new user details
	String addNewUserDetails(User user);

	User getUserDetails(Long userId);

	List<User> getAllUsers();

	List<User> getUserDetailsByRoleAndDate(UserRole role, LocalDate date);

	List<String> getUserLastNameByRole(UserRole role1);

	List<UserDTO> getSelectedDetails(UserRole role);

	String changePassword(String email, String oldPassword, String newPassword);

	String applyDiscount(LocalDate date, int discountAmount, UserRole role1);

	String deleteUserDetails(Long userId);

	String saveImage(String filePath, Long userId) throws IOException;

	String RestoreImage(String filePath, String email) throws IOException;
}
