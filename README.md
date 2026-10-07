# 📚 Library Management System

A simple **Java-based Library Management System** that runs in the console.  
This project allows users to manage books and library members, search records, issue and return books, and update or remove books.

## ✨ Features

- 📖 Add new books
- 👤 Add library members
- 🔍 Search books by ID or title
- ✏️ Update book details
- 🗑️ Remove books
- 📤 Issue books
- 📥 Return books
- 📚 View all books
- 🔎 Search members by ID or name
- 👥 View all members
- ❌ Exit the application
- ⚠️ Input validation and exception handling
- 🛡️ Custom `LibraryException` for library-specific errors

The application provides an interactive menu with 11 options.

## 🛠️ Technologies Used

- **Java**
- `ArrayList`
- `Scanner`
- Exception Handling
- Custom Exception
- Object-Oriented Programming (OOP)

## 📂 Project Structure

```text
Library-Management-System/
│
├── LibraryManagementSystem.java
└── README.md
```

## 🧩 Classes

### `Book`

Represents a book in the library.

It stores:

- Book ID
- Book title
- Author name
- Availability status

New books are automatically marked as available.

### `Member`

Represents a library member.

It stores:

- Member ID
- Member name



### `LibraryException`

A custom exception used for library-specific errors such as:

- Book not found
- Duplicate book ID
- Book already borrowed
- Book already returned



### `LibraryManagementSystem`

The main class that handles:

- User interaction
- Book management
- Member management
- Searching
- Book issuing and returning
- Exception handling



## 🚀 How to Run

### 1. Install Java

Make sure Java is installed on your computer.

Check the Java version:

```bash
java -version
```

Check the Java compiler:

```bash
javac -version
```

### 2. Compile the Program

Open a terminal in the project directory and run:

```bash
javac LibraryManagementSystem.java
```

### 3. Run the Program

```bash
java LibraryManagementSystem
```

## 📋 Main Menu

When the program starts, it displays:

```text
=== Library Management System ===

1. Add Book
2. Add Member
3. Search Book
4. Update Book
5. Remove Book
6. Issue Book
7. Return Book
8. View All Books
9. Search Member
10. View All Members
11. Exit
```

## 📖 Book Management

### Add Book

Enter:

```text
Book ID
Book Title
Author Name
```

The system checks for duplicate book IDs before adding a book.

### Search Book

Books can be searched using:

- Book ID
- Part of the book title



### Update Book

You can update:

- Book title
- Author name

Leaving a field blank keeps its current value.

### Remove Book

Books can be removed using their Book ID.

## 📤 Issue and Return Books

### Issue Book

A book can only be issued if it exists and is currently available.

```text
Book issued successfully!
```



### Return Book

A borrowed book can be returned and its availability status is changed back to available.

## 👥 Member Management

### Add Member

Enter:

```text
Member ID
Member Name
```

The member is then added to the library system.

### Search Member

Members can be searched using:

- Member ID
- Member name



### View All Members

Displays all registered members in the library.

## ⚠️ Exception Handling

The application handles invalid input using Java's `InputMismatchException`.

It also uses a custom `LibraryException` for library-related errors.

Example:

```text
Error: Book not found.
```

## 💾 Data Storage

This version stores books and members using Java `ArrayList` collections:

```java
private ArrayList<Book> books;
private ArrayList<Member> members;
```

Data is stored **in memory while the program is running**. It is not currently connected to a database or file for permanent storage.

## 🎯 Learning Objectives

This project demonstrates important Java concepts:

- Classes and Objects
- Encapsulation
- Constructors
- Getters and Setters
- Inheritance of `Exception`
- Custom Exceptions
- `ArrayList`
- Loops
- Switch expressions
- Methods
- Conditional statements
- Exception handling
- User input using `Scanner`

## 🔮 Future Improvements

Possible improvements include:

- Database integration using MySQL
- Login system for librarians and members
- Book borrowing history
- Due dates and fine calculation
- Admin dashboard
- GUI using JavaFX or Swing
- File-based data storage
- Password authentication
- Book categories and ISBN
- Member borrowing limits
- Automatic backup

## 👨‍💻 Author

**Mahendra LB**

## 📄 License

This project is created for **educational and learning purposes**.