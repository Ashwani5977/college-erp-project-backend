🏫 College ERP Backend – API Documentation

The College ERP Backend is a Java Servlet-based REST API that manages all aspects of a college management system, including students, teachers, courses, marks, attendance, and enrollment. This backend is designed to work with a MySQL database and can be consumed by any frontend (React, Angular, etc.).

🌐 Base URL
http://localhost:8080/college-erp-project-java
🔐 Authentication APIs
1️⃣ Login

POST /LoginServlet

Request Body:

{
  "email": "admin@gmail.com",
  "password": "123456"
}

Success Response (200):

{
  "id": 1,
  "name": "Admin",
  "email": "admin@gmail.com",
  "role": "ADMIN",
  "password": null
}

Failure Response (401):

"Invalid Email or Password"
2️⃣ Register

POST /RegisterServlet

Request Body:

{
  "name": "John",
  "email": "john@gmail.com",
  "password": "123456",
  "role": "STUDENT"
}

Success Response (201):

"User registered successfully with ID: 10"

Failure Response (409):

"Email already exists!"
3️⃣ Logout

POST /LogoutServlet

"Logged Out Successfully"
👨‍🎓 Student APIs

Base Path: /api/student

Method	Endpoint	Description
GET	/all	Get all students
GET	/id?id=1	Get student by ID
GET	/user?userId=5	Get student by User ID
POST	/api/student	Add a new student

Request Body Example:

{
  "userId": 5,
  "rollNumber": "CS101",
  "department": "ComputerScience"
}

| DELETE | /api/student?id=1 | Delete student by ID |

👩‍🏫 Teacher APIs

Base Path: /api/teacher

Method	Endpoint	Description
GET	/all	Get all teachers
GET	/id?id=1	Get teacher by ID
GET	/user?userId=5	Get teacher by User ID
POST	/api/teacher	Add a new teacher

Request Body Example:

{
  "userId": 6,
  "department": "ComputerScience"
}

| DELETE | /api/teacher?id=1 | Delete teacher by ID |

📚 Course APIs

Base Path: /api/course

Method	Endpoint	Description
GET	/all	Get all courses
GET	/id?id=1	Get course by ID
GET	/code?courseCode=CS101	Get course by code
GET	/department?department=ComputerScience	Get courses by department
POST	/api/course	Add course

Request Body Example:

{
  "courseCode": "CS101",
  "courseName": "Data Structures",
  "department": "ComputerScience"
}

| PUT | /api/course | Update course |

Request Body Example:

{
  "id": 1,
  "courseCode": "CS101",
  "courseName": "Advanced DS",
  "department": "ComputerScience"
}

| DELETE | /api/course?id=1 | Delete course |

📝 Marks APIs

Base Path: /api/marks

Method	Endpoint	Description
GET	/all	Get all marks
GET	/id?id=1	Get marks by ID
GET	/student?studentId=1	Get marks by student
GET	/course?courseId=1	Get marks by course
GET	/student-course?studentId=1&courseId=2	Get marks by student + course
GET	/student-course-exam?studentId=1&courseId=2&examType=Midterm	Get marks by student + course + exam
POST	/api/marks	Add marks

Request Body Example:

{
  "studentId": 1,
  "courseId": 2,
  "examType": "Midterm",
  "marks": 85
}

| PUT | /api/marks | Update marks |
| DELETE | /api/marks?id=1 | Delete marks by ID |

📅 Attendance APIs

Base Path: /api/attendance

Method	Endpoint	Description
GET	/all	Get all attendance records
GET	/id?id=1	Get attendance by ID
GET	/student?studentId=1	Get attendance by student
GET	/course?courseId=1	Get attendance by course
GET	/student-course?studentId=1&courseId=1	Get attendance by student + course
GET	/student-course-date?studentId=1&courseId=1&date=2026-02-27	Get attendance by student + course + date
POST	/api/attendance	Add attendance

Request Body Example:

{
  "studentId": 1,
  "courseId": 1,
  "date": "2026-02-27",
  "status": "Present"
}

| PUT | /api/attendance | Update attendance |
| DELETE | /api/attendance?id=1 | Delete attendance |

📌 Student-Course (Enrollment) APIs

Base Path: /api/student-course

Method	Endpoint	Description
GET	/all	Get all enrollments
GET	/student?studentId=1	Get enrollments by student
GET	/course?courseId=1	Get enrollments by course
POST	/api/student-course	Enroll student

Request Body Example:

{
  "studentId": 1,
  "courseId": 2
}

| DELETE | /api/student-course?studentId=1&courseId=2 | Remove enrollment |

👨‍🏫 Teacher-Course APIs

Base Path: /api/teacher-course

Method	Endpoint	Description
GET	/all	Get all teacher-course assignments
GET	/teacher?teacherId=1	Get assignments by teacher
GET	/course?courseId=1	Get assignments by course
POST	/api/teacher-course	Assign course to teacher

Request Body Example:

{
  "teacherId": 1,
  "courseId": 2
}

| DELETE | /api/teacher-course?teacherId=1&courseId=2 | Delete assignment |

🏗 Project Structure
src/
 ├── controller/     → Servlets for handling HTTP requests
 ├── dao/            → Data Access Objects for DB operations
 ├── dto/            → Models/DTOs for database tables
 ├── service/        → Business logic layer
 └── utils/          → Utility classes (DB connection, etc.)
WEB-INF/
 └── web.xml         → Servlet mappings & configuration
lib/
 └── *.jar            → External libraries (MySQL, HikariCP, Gson, etc.)
🎯 Project Objective

The College ERP Backend provides a full-featured API to manage students, teachers, courses, marks, attendance, and enrollments efficiently. It serves as a foundation for frontend applications and ensures secure role-based access for admins, teachers, and students.

🔮 Future Enhancements

Role-based Authentication for Teachers & Students

Admin Dashboard for Managing Courses & Users

Automated Email Notifications

Reporting & Analytics

Integration with Frontend React App

💡 Highlights

Java Servlet-based REST API

MySQL Database Integration

Full CRUD Support for All Modules

Modular Project Architecture (Controller → Service → DAO → DTO)

Ready for Production Deployment on Tomcat Server
