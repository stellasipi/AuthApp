package hu.tbs.authApp;

import hu.tbs.authApp.model.Page;
import hu.tbs.authApp.model.Role;
import hu.tbs.authApp.model.User;
import hu.tbs.authApp.repository.PageRepository;
import hu.tbs.authApp.repository.RoleRepository;
import hu.tbs.authApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthAppApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PageRepository pageRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ROLES
		Set<Role> roles=new HashSet<>();
		Role administrator=Role.builder()
				.name("ROLE_ADMIN")
				.description("Adminisztrátor")
				.build();
		roles.add(administrator);

		Role contentEditor=Role.builder()
				.name("ROLE_EDITOR")
				.description("Tartalomszerkesztő")
				.build();
		roles.add(contentEditor);

		Role loggedInUser=Role.builder()
				.name("ROLE_USER")
				.description("Bejelentkezett felhasználó")
				.build();
		roles.add(loggedInUser);

		roleRepository.saveAll(roles);

		//USERS
		User admin=User.builder()
				.username("Admin")
				.password("admin")
				.build();
		admin.addRole(administrator);
		userRepository.saveAndFlush(admin);

		User user1=User.builder()
				.username("User 1")
				.password("user1")
				.build();
		user1.addRole(contentEditor);
		user1.addRole(loggedInUser);
		userRepository.saveAndFlush(user1);

		User user2=User.builder()
				.username("User 2")
				.password("user2")
				.build();
		user2.addRole(contentEditor);
		userRepository.saveAndFlush(user2);

		User user3=User.builder()
				.username("User 3")
				.password("user3")
				.build();
		user3.addRole(loggedInUser);
		userRepository.saveAndFlush(user3);

		//PAGES
		Set<Page> pages=new HashSet<>();
		Page adminPage=Page.builder()
				.name("admin")
				.message("Ez a adminisztrátorok aloldala.")
				.build();
		pages.add(adminPage);

		Page loggedInUserPage=Page.builder()
				.name("loggedInUser")
				.message("Ez a bejelentkezett felhasználók aloldala.")
				.build();
		pages.add(loggedInUserPage);

		Page contentEditorPAge=Page.builder()
				.name("contentEditor")
				.message("Ez a tartalomszerkesztők aloldala.")
				.build();
		pages.add(contentEditorPAge);

		pageRepository.saveAll(pages);
	}
}
