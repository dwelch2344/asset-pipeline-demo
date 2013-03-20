package co.ntier.example.asset.svc;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import co.ntier.example.asset.model.SimpleUser;
import co.ntier.example.asset.repo.SimpleUserRepository;

@Service
@Transactional(propagation=Propagation.MANDATORY)
public class UserService implements UserDetailsService{ //implements IUserService{
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private SimpleUserRepository repo;
	
	public SimpleUser create(String name, String email, String password){
		SimpleUser user = new SimpleUser(name, email, BCrypt.hashpw(password, BCrypt.gensalt()));
		user = repo.save(user);
		return user;
	}
	
	public List<SimpleUser> findAll() {
		List<SimpleUser> users = repo.findAll();
		return users;
	}
	
	public void simpleUserTest(){
		SimpleUser user = new SimpleUser("someguy", "email", BCrypt.hashpw("password", BCrypt.gensalt()));
		em.persist(user);
		em.flush();
		List<SimpleUser> users = em.createQuery("From SimpleUser", SimpleUser.class).getResultList();
		System.out.println(users.size() + " users");
	}

	 
	/**
	 * Look up a user by email (which is our "username" in this case)
	 */
	@Override
	// We need to support new transactions here because Spring Security doesn't go through our annotated controller
	@Transactional(readOnly = true, propagation=Propagation.REQUIRED)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<SimpleUser> results = em.createQuery("from SimpleUser where email=:email", SimpleUser.class)
				.setParameter("email", email)
				.getResultList();
		if( results.isEmpty()){
			throw new UsernameNotFoundException("Could not locate username: " + email);
		}
		
		Assert.isTrue( results.size() == 1, "Multiple results for username: " + email);
		
		return results.get(0);
	}

	
	
}
