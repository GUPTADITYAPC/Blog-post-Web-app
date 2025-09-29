package org.study.SpringStarter.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.study.SpringStarter.models.Account;
import org.study.SpringStarter.models.Authority;
import org.study.SpringStarter.models.Post;
import org.study.SpringStarter.services.AccountService;
import org.study.SpringStarter.services.AuthorityService;
import org.study.SpringStarter.services.PostService;
import org.study.SpringStarter.util.constants.Privillages;
import org.study.SpringStarter.util.constants.Roles;

@Component
public class SeedData
        implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {

        for (Privillages auth : Privillages.values()) {
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivillage());
            authorityService.save(authority);

        }

        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();

        account01.setEmail("guptadityapc@gmail.com");
        account01.setFirstname("user");
        account01.setPassword("password");
        account01.setLastname("customer");
        account01.setAge(25);
        account01.setDate_of_birth(LocalDate.parse("1998-01-01"));
        account01.setGender("Male");

        account02.setEmail("admin@gmail.org");
        account02.setFirstname("admin_user");
        account02.setPassword("password");
        account02.setLastname("admin");
        account02.setRole(Roles.ADMIN.getRole());
        account02.setAge(25);
        account02.setDate_of_birth(LocalDate.parse("1998-01-01"));
        account02.setGender("Male");

        account03.setEmail("editor@gmail.org");
        account03.setFirstname("editor_user");
        account03.setPassword("password");
        account03.setLastname("editor");
        account03.setRole(Roles.EDITOR.getRole());
        account03.setAge(25);
        account03.setDate_of_birth(LocalDate.parse("1998-01-01"));
        account03.setGender("Male");

        account04.setEmail("super_ediitor@gmail.org");
        account04.setFirstname("super_editor");
        account04.setPassword("gupta");
        account04.setLastname("Raj");
        account04.setRole(Roles.EDITOR.getRole());
        account04.setAge(25);
        account04.setDate_of_birth(LocalDate.parse("1998-01-01"));
        account04.setGender("Female");

        Set<Authority> authorities = new HashSet<>();
        authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
        authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
        account04.setAuthorities(authorities);

        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);

        List<Post> posts = postService.findAll();
        if (posts.size() == 0) {
            Post post01 = new Post();
            post01.setTitle("ABOUT  GIT");
            post01.setBody(
                    """
                            Git is a distributed version control system that allows multiple developers to work on a project simultaneously. It tracks changes in source code during software development, enabling collaboration and version management.
                            Git provides features like branching, merging, and history tracking, making it easier to manage code changes and collaborate effectively. It is widely used in software development for its efficiency and flexibility.""");
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("MVC ARCHITECTURE");
            post02.setBody(
                    """
                                    MVC (Model-View-Controller) is a software architectural pattern used for developing user interfaces. It separates an application into three interconnected components:
                                    1. Model: Represents the data and business logic.
                                    2. View: Displays the data to the user.
                                    3. Controller: Handles user input and updates the model and view accordingly.
                                    This separation allows for better organization, maintainability, and scalability of applications. MVC is commonly used in web frameworks and desktop applications to create interactive user interfaces.
                            """);
            post02.setAccount(account03);
            postService.save(post02);

            Post post03 = new Post();
            post03.setTitle("MVCS ARCHITECTURE");
            post03.setBody(
                    """
                                       MVCS (Model-View-Controller-Service) is an extension of the MVC pattern that adds a Service layer to handle business logic and data access. It separates concerns further by introducing a Service component that interacts with the Model and provides data to the Controller.
                                       The components are:
                                    1. Model: Represents the data and business logic.
                                    2. View: Displays the data to the user.
                                    3. Controller: Handles user input and updates the model and view accordingly.
                                    4. Service: Contains business logic and data access methods, acting as an intermediary between the Controller and Model.
                                    This architecture promotes better organization, testability, and maintainability of applications, especially in complex systems where business logic needs to be separated from presentation logic.
                                    MVCS is commonly used in modern web frameworks and enterprise applications to create scalable and maintainable software solutions.
                            """);
            post03.setAccount(account03);
            postService.save(post03);

            Post post04 = new Post();
            post04.setTitle("Thymeleaf ");
            post04.setBody(
                    """
                                    Thymeleaf is a modern server-side Java template engine for web applications. It allows developers to create dynamic HTML pages by embedding Java code within HTML templates. Thymeleaf supports natural templating, meaning that the templates can be displayed in a web browser without being processed by the server, making it easy to design and test.
                                    It integrates seamlessly with Spring Framework, providing features like form handling, internationalization, and conditional rendering. Thymeleaf templates are easy to read and maintain, making it a popular choice for building web applications in Java.
                                    It is often used in conjunction with Spring Boot to create robust and scalable web applications.

                            """);
            post04.setAccount(account02);
            postService.save(post04);

            Post post05 = new Post();
            post05.setTitle("spring boot");
            post05.setBody(
                    """
                                    Spring Boot is an open-source Java-based framework used to create stand-alone, production-grade Spring applications. It simplifies the setup and development of new Spring applications by providing a range of pre-configured templates and defaults.
                                    Key features of Spring Boot include:
                                    1. Auto-configuration: Automatically configures Spring applications based on the dependencies present in the classpath.
                                    2. Embedded servers: Allows developers to run applications with embedded servers like Tomcat or Jetty, eliminating the need for external server deployment.
                                    3. Starter dependencies: Provides a set of pre-defined dependencies for common functionalities, making it easier to get started with Spring applications.
                                    4. Actuator: Offers built-in endpoints for monitoring and managing applications in production.
                                    Spring Boot is widely used for building microservices, RESTful APIs, and web applications due to its simplicity, flexibility, and rapid development capabilities.

                            """);
            post05.setAccount(account04);
            postService.save(post05);

            Post post06 = new Post();
            post06.setTitle("Data structure and algorithms");
            post06.setBody(
                    """
                                            Data structures and algorithms are fundamental concepts in computer science that deal with organizing and manipulating data efficiently.
                                            Data structures are ways of organizing and storing data in a computer so that it can be accessed and modified efficiently. Common data structures include arrays, linked lists, stacks, queues, trees, and graphs. Each data structure has its own strengths and weaknesses, making it suitable for different types of applications.
                                            Algorithms are step-by-step procedures or formulas for solving problems or performing tasks. They define how data is processed, transformed, and manipulated. Common algorithms include sorting (e.g., quicksort, mergesort), searching (e.g., binary search), and graph algorithms (e.g., Dijkstra's algorithm).
                                            Understanding data structures and algorithms is crucial for writing efficient code, optimizing performance, and solving complex problems in software development. They form the backbone of computer science and are essential for building robust and scalable applications.

                            """);
            post06.setAccount(account02);
            postService.save(post06);

        }
    }

}
