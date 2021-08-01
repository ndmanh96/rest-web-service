package com.manhcode.rest.demo;

import com.manhcode.rest.demo.dao.PostRespository;
import com.manhcode.rest.demo.dao.UserRepository;
import com.manhcode.rest.demo.entity.Post;
import com.manhcode.rest.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Date;
import java.util.Locale;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRespository postRepository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource bundleMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("Manh", new Date());
		User user2 = new User("Manh2", new Date());
		User user3 = new User("Manh3", new Date());

		Post post1= new Post("post1");
		Post post2= new Post("post2");
		Post post3= new Post("post3");
		Post post4= new Post("post4");
		Post post5= new Post("post5");
		Post post6= new Post("post6");

		user1.addPost(post1);
		post1.setUser(user1);
		user1.addPost(post2);
		post2.setUser(user1);

		user2.addPost(post3);
		post3.setUser(user2);
		user2.addPost(post4);
		post4.setUser(user2);

		user3.addPost(post5);
		post5.setUser(user3);
		user3.addPost(post6);
		post6.setUser(user3);


		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);

		postRepository.save(post1);
		postRepository.save(post2);
		postRepository.save(post3);
		postRepository.save(post4);
		postRepository.save(post5);
		postRepository.save(post6);

	}
}
