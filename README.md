Spring Blog Post Web App
A Spring Boot + Thymeleaf blog platform with user accounts, role-based access, rich-text posts, and email-based password reset. This README documents the features and functionality based on the current codebase.

Overview

Server-side rendered blog UI using Thymeleaf templates
Spring Security login with remember-me
CRUD for blog posts
Profile management with photo upload
Password reset via email token
H2 file-based database with seed data
REST endpoint for posts
Tech Stack

Java 17
Spring Boot 3.5.4
Spring MVC + Thymeleaf
Spring Security
Spring Data JPA
H2 Database (file-based)
Lombok
JavaMailSender (SMTP)
Key Features And Functionality

User registration
User login with remember-me
User profile edit (name, age, gender, DOB, password)
Profile photo upload
Post creation, edit, and delete
Rich-text editor for post body (CKEditor)
Pagination and sorting for posts list
Role-based navigation and admin view
Password reset via email token with expiration
REST API endpoint that returns all posts
Routes And Screens

GET /
Home feed of posts with pagination and sorting
Sorting: createdAt, updatedAt
Pagination: page, per_page
GET /register, POST /register
Registration form and submission
GET /login, POST /login
Login with remember-me
GET /profile, POST /profile
View and edit profile (authenticated)
POST /update_photo
Upload profile image (authenticated)
GET /posts/add, POST /posts/add
Create new post (authenticated)
GET /post/{id}
View a post
GET /posts/{id}/edit, POST /posts/{id}/edit
Edit post (authenticated)
GET /posts/{id}/delete
Delete post (authenticated)
GET /forgot-password, POST /reset-password
Request password reset link via email
GET /change-password, POST /change-password
Reset password using token
GET /admin
Admin-only view
GET /api/v1/
REST endpoint: JSON list of posts
Data Model

Account
Email, password (hashed), profile fields, role, photo
Many-to-many authorities
One-to-many posts
Password reset token + expiry
Post
Title, body, createdAt, updatedAt
Many-to-one account
Authority
Privileges like ACCESS_ADMIN_PANEL
Security And Roles

Passwords are BCrypt-hashed in AccountService
Role constants in Roles
Privileges in Privillages
Route protection in WebSecurityConfig
/profile/** requires authentication
/admin/** requires role ADMIN and authority ACCESS_ADMIN_PANEL
/editor/** requires role ADMIN or EDITOR
CSRF disabled and frame options disabled for H2 console
Email Password Reset

Uses SMTP settings from application.properties and secret.properties
Reset token stored in Account with expiration
Token timeout configured via password.reset.token.timeout.minutes
File Uploads

Profile photo stored in src/main/resources/static/uploads
File path created in AppUtil.get_upload_path
Uploaded file name is randomized to reduce collisions
Pagination And Sorting

Home uses Spring Data PageRequest
Sort field is configurable with query param sort_by
Page size configurable via per_page
Seed Data On application startup, sample data is inserted:

Privileges are seeded
Four sample accounts
Sample blog posts (if none exist)
Configuration

src/main/resources/application.properties
H2 database, logging, port, and email defaults
site.domain used for password reset links
spring.mvc.static-path-pattern used for file URL prefix
src/main/resources/secret.properties
SMTP username/password (required for email)
Run Locally

Ensure Java 17 is installed
Start the app
./mvnw spring-boot:run
Open in browser
http://localhost:8080/
H2 Console

Enabled at http://localhost:8080/h2-console
JDBC URL: jdbc:h2:file:./db/blogdb
Username: admin
Password: password
Project Layout

src/main/java/org/study/SpringStarter/Controller
MVC controllers and REST controller
src/main/java/org/study/SpringStarter/models
JPA entities
src/main/java/org/study/SpringStarter/services
Business logic and user details service
src/main/java/org/study/SpringStarter/repositories
Spring Data JPA repositories
src/main/java/org/study/SpringStarter/security
Spring Security config
src/main/resources/templates
Thymeleaf views
src/main/resources/static
CSS, JS, images, and uploads
Notes

The secret.properties file contains SMTP credentials. Consider moving this to environment variables or a secrets manager for production.
Image upload path currently uses Windows-style separators in AppUtil. If you run on macOS or Linux, consider updating this to use Paths.get(...) for portability.
