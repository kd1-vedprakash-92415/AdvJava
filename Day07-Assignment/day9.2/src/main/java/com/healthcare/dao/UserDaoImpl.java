package com.healthcare.dao;

import com.healthcare.dtos.UserDTO;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

import org.apache.commons.io.FileUtils;
import org.hibernate.*;
import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class UserDaoImpl implements UserDao {

	@Override
	public String addNewUserDetails(User user) {
		// user - TRANSIENT
		String mesg = "Adding user details failed !!!!!!!";
		// 1. Get Hibernate Session from SessionFactory (SF)
		/*
		 * Methods of SessionFactory public Session getCurrentSession() throws
		 * HibernateException OR public Session openSession() throws HibernateException
		 */
		Session session = getSessionFactory().getCurrentSession();// new hib session
		Session session2 = getSessionFactory().getCurrentSession();// rets existing session
		System.out.println(session == session2);// true

		// 2. Begin Hibernate transaction (Tx)
		/*
		 * Session API public Transaction beginTransaction() throws HibernateException
		 */
		Transaction tx = session.beginTransaction();
		System.out.println("session open " + session.isOpen() + " is connected " + session.isConnected()); // t t

		try {
			// 3. CRUD API : Session
			session.persist(user);
			// user - PERSISTENT
			// 4 . Commit tx
			tx.commit(); // session is auto closed - DML - insert in DB
			/*
			 * What happens internally upon commit ? 1. Hibernate invokes session.flush() ->
			 * It performs auto dirty checking (i.e it will compare the state of L1 cache
			 * with that of DB -> new entity -> DML insert OR -> existing entity with
			 * updated state -> update OR -> entity marked for removal -> delete OR no
			 * changes -> no DML => state of DB is synchronized with state of L1 cache 2.
			 * session.close() -> L1 cache is destroyed -> Pooled out db cn rets to DBCP
			 * (for reusability -> better scalability) -> session object is destroyed.
			 * 
			 */
			System.out.println("session open " + session.isOpen() + " is connected " + session.isConnected()); // f f
			mesg = "Added user details successsfully with ID" + user.getId();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				/*
				 * What happens upon rollback ? session.close() -> L1 cache is destroyed ->
				 * Pooled out db cn rets to DBCP (for reusability -> better scalability) ->
				 * session object is destroyed.
				 */
			}
			// 5. re throw the same exception to the caller
			throw e;
		}
		// user - DETACHED - detached from L1 cache.
		return mesg;
	}

	@Override
	public User getUserDetails(Long userId) {
		User user = null;// user - does not exist in heap
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			user = session.find(User.class, userId);// select -> load entity in L1 cache
			user = session.find(User.class, userId);
			user = session.find(User.class, userId);
			user = session.find(User.class, userId);
			// in case of valid id - user : PERSISTENT
			// tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return user;// user - DETACHED from L1 cache
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = null;
		String jpql = "select u from User u";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class)// Query<User>
					.getResultList();
			// users - list of persistent entities
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return users;// users - list of detached entities
	}

	@Override
	public List<User> getUserDetailsByRoleAndDate(UserRole role, LocalDate date1) {
		List<User> users = null;
		String jpql = "select u from User u where u.userRole=:rl and u.dob > :date";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class).setParameter("rl", role).setParameter("date", date1)
					.getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return users;
	}

	@Override
	public List<String> getUserLastNameByRole(UserRole role1) {
		List<String> list = null;
		String jpql = "select u.lastName from User u where u.userRole=:role";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			list = session.createQuery(jpql, String.class)// creates Query
					.setParameter("role", role1)// setting named IN param
					.getResultList();// exec query -> RST -> List
			tx.commit();// 1. session.flush() -> dirty checking -> no dmls -> session.close -> DB cn
						// rets to DBCP , L1 cache & sesison is destroyed !
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return list;
	}

	@Override
	public List<UserDTO> getSelectedDetails(UserRole role2) {
		List<UserDTO> dtos = null;
//		String jpql="select new com.healthcare.dtos.UserDTO(u.firstName,u.lastName,u.dob) from User u where u.userRole=:role1";
		String jpql = "select u.firstName,u.lastName,u.dob from User u where u.userRole=:role1";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			dtos = session.createQuery(jpql, UserDTO.class).setParameter("role1", role2).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return dtos;
	}

	@Override
	public String changePassword(String email1, String oldPassword1, String newPassword1) {
		User user = null;
		String message = "Password updation failed....";
		String jpql = "select u from User u where u.email=:em and u.password=:pass";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			user = session.createQuery(jpql, User.class).setParameter("em", email1).setParameter("pass", oldPassword1)
					.getSingleResult();// select
			// user - PERSISTENT (exists in L1 cache - yes , exists in DB - yes
			user.setPassword(newPassword1);// changing the state of persistent entity
			// session.evict(user);//persistent -> Detached
			tx.commit();
			/*
			 * 1. session.flush() -> auto dirty checking -> DML - update 2. session.close()
			 * -> db cn rets to DBCP , L1 & session object destroyed
			 */
			message = "Password updated .... ";
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		user.setPassword("1122334455");// modify state of DETACHED entity -> hibernate WILL NOT sync up the state of L1
										// cache with DB - no queries!!!!!!
		return message;
	}

	@Override
	public String applyDiscount(LocalDate date1, int discountAmount, UserRole role1) {
		String mesg = "Applying discount failed !!!!";
		String updateJpql = "update User u set u.regAmount=u.regAmount-:discount where u.dob < :date and u.userRole=:role";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			int updateCount = session.createMutationQuery(updateJpql).setParameter("discount", discountAmount)
					.setParameter("date", date1).setParameter("role", role1).executeUpdate();
			tx.commit();// single update query - bulk updates
			mesg = updateCount + " users updated.....";
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return mesg;
	}

	@Override
	public String deleteUserDetails(Long userId) {
		String mesg = "Deletion failed ";
		User user = null;
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
			// 3. get user from its id
			user = session.find(User.class, userId);
			// 4 validate if exists
			if (user != null) {
				// user : PERSISTENT
				// 5. mark it for removal
				session.remove(user);
				// user : REMOVED - marked for removal
				mesg = "User details will be deleted upon commit.....";
			}
			tx.commit();
			/*
			 * 1. session.flush() -> dirty chking -> DML : delete 2. session.close() -> db
			 * cn rets to DBCP , L1 & session : destroyed
			 */
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// inform caller - by re throwing the same exception
			throw e;
		}
		// user - TRANSIENT
		return mesg;
	}// method rets - user : elligible for GC -> Does not exist

	@Override
	public String saveImage(String path, Long userId) throws IOException {
		String mesg = "Saving Image failed ";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
			// 3. get user from its id
			User user = session.find(User.class, userId);
			if (user != null) {
				// user - persistent , validate file
				File file = new File(path);
				if (file.isFile() && file.canRead()) {
					// use FileUtils class API
					byte[] imgData = FileUtils.readFileToByteArray(file);
					// setImage
					user.setImage(imgData);
					mesg = "Image saved in DB !!!!";
				}
			}
			tx.commit();// DML - update
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// inform caller - by re throwing the same exception
			throw e;
		}
		return mesg;
	}

	@Override
	public String RestoreImage(String filePath, String email1) throws IOException {
		String mesg = "Restoring image failed !!!!!!!!!";
		String jpql="select u from User u where u.email=:em";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
//3. Get user from the email
			User user=session.createQuery(jpql, User.class)
					.setParameter("em", email1)
					.getSingleResult();
			//4 - user : PERSISTENT , chk if image exists
			byte[] imgData=user.getImage();
			if(imgData != null)
			{
				FileUtils.writeByteArrayToFile(new File(filePath), imgData);
				mesg="Restored image BLOB from DB....";
			}
			
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// inform caller - by re throwing the same exception
			throw e;
		}
		return mesg;
	}

}
