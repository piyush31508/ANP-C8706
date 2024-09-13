package com.LMS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Author;
import model.Book;
import model.Librarian;
import model.Loan;
import model.Member;
import service.AuthorImp;
import service.BookImp;
import service.LibrarianImp;
import service.LoanImp;
import service.MemberImp;

public class App {

	Scanner sc = new Scanner(System.in);
	MemberImp mI = new MemberImp();
	LoanImp lI = new LoanImp();
	AuthorImp aI = new AuthorImp();
	BookImp bI = new BookImp();
	LibrarianImp lI2 = new LibrarianImp();

	public void mainMenu() {
		while (true) {
			System.out.println("Hello User, welcome to the Library Management System");
			System.out.println(
					"Enter 1 for Author functions\nEnter 2 for Member functions\nEnter 3 for Librarian functions\nEnter 4 to exit the System");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				authorOperation();
				break;
			case 2:
				memberOperation();
				break;
			case 3:
				librarianOperation();
			case 4:
				System.out.println("Exiting Library Management System.......");
				System.exit(0);
				break;
			default:
				System.out.println("Wrong Choice");
			}
		}
	}

	public void librarianOperation() {
		while (true) {
			System.out.println("Welcome to the Librarian Function");
			System.out.println(
					"Enter 1 to add new Librarian\nEnter 2 to update librarian details\nEnter 3 to check the borrowing history\nEnter 4 to check whether a member is eligible to pay the overdue fees\nEnter 5 to check whether a member have currently borrowed a book or not\nEnter 6to exit Librarian System");

			int choice = sc.nextInt();
			Librarian librarian = null;
			long librarianId, memberId;
			String email;
			switch (choice) {
			case 1:
				// Add new Librarian
				librarian = new Librarian();
				System.out.println("Enter Librarian's name:");
				String name = sc.next();
				librarian.setName(name);

				System.out.println("Create password for the librarian:");
				String password = sc.next();
				librarian.setPassword(password);

				lI2.save(librarian);
				System.out.println("New Librarian added successfully!");
				break;

			case 2:
				// Update Librarian details
				System.out.println("Enter Librarian ID to update details:");
				librarianId = sc.nextLong();
				librarian = lI2.get(librarianId); // Assuming get method in LibrarianImp

				if (librarian != null) {
					System.out.println("Do you want to change name? (Y/N)");
					if (sc.next().equalsIgnoreCase("Y")) {
						System.out.println("Enter new name:");
						name = sc.next();
						librarian.setName(name);
					}

					System.out.println("Do you want to change password? (Y/N)");
					if (sc.next().equalsIgnoreCase("Y")) {
						System.out.println("Enter current password:");
						password = sc.next();
						if (password.equals(librarian.getPassword())) {
							System.out.println("Enter new password:");
							String newPassword = sc.next();
							librarian.setPassword(newPassword);
						} else {
							System.out.println("Incorrect current password!");
						}
					}

					lI2.update(librarian); // Assuming update method in LibrarianImp
					System.out.println("Librarian details updated successfully!");
				} else {
					System.out.println("Librarian not found!");
				}
				break;

			case 3:
				System.out.println("Enter Member ID to check borrowing history:");
				memberId = sc.nextLong();
				List<Loan> history = lI.borrowingHistory(memberId); 

				if (history != null && !history.isEmpty()) {
					System.out.println("Borrowing history for Member ID: " + memberId);
					for (Loan entry : history) {
						System.out.println("Book: " + entry.getBook() + ", Borrowed on: " + entry.getLoanDate()
								+ ", Returned on: " + entry.getReturnDate());
					}
				} else {
					System.out.println("No borrowing history found for this member.");
				}
				break;

			case 4:
				// Check if member is eligible to pay overdue fees
				System.out.println("Enter Member ID to check overdue fees eligibility:");
				memberId = sc.nextLong();
				double overdueFees = lI.dueFee(memberId); 

				if (overdueFees > 0) {
					System.out.println("Member is eligible to pay overdue fees of: $" + overdueFees);
				} else {
					System.out.println("Member has no overdue fees.");
				}
				break;
				
			case 5:
			    
			    // Check if the member has currently borrowed a book
			    List<Loan> borrowedBooks = lI.listLoans();
			    
			    if (borrowedBooks != null && !borrowedBooks.isEmpty()) {
			        System.out.println("Member has currently borrowed the following books:");
			        for (Loan book : borrowedBooks) {
			            System.out.println("Book Title: " + book.getBook().getTitle() + ", Borrowed Date: " + book.getLoanDate());
			        }
			    } else {
			        System.out.println("Member has not currently borrowed any books.");
			    }
			    break;

			case 6:
				// Exit Librarian System
				System.out.println("Exiting Librarian System...");
				return;

			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		}
	}

	public void authorOperation() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("Welcome to Author Function");
			System.out.println(
					"Enter 1 to add new author\nEnter 2 to add new Book\nEnter 3 to update author details\n\"Enter 4 to view author details\nEnter 5 to view books by an author\nEnter 6 to exit Author Functions");
			int choice = sc.nextInt();
			Author a = null;
			if (choice > 0 && choice < 7) {
				a = new Author();
			}
			String name, d, isbn, password;
			Date date;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long id;
			switch (choice) {
			case 1:
				System.out.println("Enter Author's name");
				name = sc.next();
				a.setName(name);
				System.out.println("Enter Birthdaye (YYYY-MM-DD):");
				d = sc.next();
				date = null;
				try {
					date = sdf.parse(d);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a.setBirthDate(date);
				System.out.println("Enter author's nationallity:");
				String nation = sc.next();
				a.setNationality(nation);
				aI.save(a);
				System.out.println("Create password");
				password = sc.next();
				a.setPassword(password);
				System.out.println(
						a.getId() + " this is your author id remember this, it will be useful for future operation");
				break;
			case 2:
				Book b = null;
				System.out.println("Enter Book's title");
				name = sc.next();
				System.out.println("Enter Author's Id");
				id = sc.nextLong();
				a = aI.get(id);
				if (a == null) {

					throw new NoSuchElementException("Author with ID " + id + " does not exist.");
				}
				b.setAuthor(a);
				System.out.println("Enter publisheddate (YYYY-MM-DD):");
				d = sc.next();
				date = null;
				try {
					date = sdf.parse(d);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				b.setPublishedDate(date);
				System.out.println("Enter ISBN");
				isbn = sc.next();
				if (isbn.length() == 10 || isbn.length() == 13)
					b.setIsbn(isbn);
				else
					throw new IllegalArgumentException("ISBN length should be 10(old) or 13(new)");
				bI.save(b);
				break;
			case 3:
				System.out.println("Enter Author's ID");
				id = sc.nextLong();
				a = aI.get(id);
				if (a == null) {
					throw new NoSuchElementException("Author with ID " + id + " does not exist.");
				}
				System.out.println("Do you want to change Author's name? (Y/N) ");
				String newData, c1 = sc.next();
				if (c1.equals("Y")) {
					System.out.println("Enter new Name:");
					newData = sc.next();
					a.setName(newData);
				}
				System.out.println("Do you want to change Author's birthdate? (Y/N) ");
				c1 = sc.next();
				if (c1.equals("Y")) {
					System.out.println("Enter new birthdate(YYYY-MM-DD):");
					d = sc.next();
					date = null;
					try {
						date = sdf.parse(d);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					a.setBirthDate(date);
				}
				System.out.println("Do you want to change Author's nationality? (Y/N) ");
				c1 = sc.next();
				if (c1.equals("Y")) {
					System.out.println("Enter new nationality:");
					newData = sc.next();
					a.setNationality(newData);
				}
				System.out.println("Do you want to change Author's password? (Y/N) ");
				c1 = sc.next();
				if (c1.equals("Y")) {
					System.out.println("Enter old password");
					String old = sc.next();
					if (old.equals(a.getPassword())) {
						System.out.println("Enter new password:");
						newData = sc.next();
						a.setPassword(newData);
					} else
						throw new IllegalArgumentException("Wrong password");
				}
				aI.update(a);
				break;
			case 4:
				System.out.println("Enter Author's Id to get there information");
				id = sc.nextLong();
				a = aI.get(id);
				if (a != null) {
					System.out.println("Name :" + a.getName() + " Birthdate :" + a.getBirthDate() + " Nationality :"
							+ a.getNationality());
				} else
					System.out.println("No author exists with " + id + " this id");
				break;
			case 5:
				System.out.println("Enter author's name to get there books");
				name = sc.next();
				List<Book> list = new ArrayList<>();
				list = aI.authorWorks(name);
				if (!list.isEmpty()) {
					for (Book books : list) {
						System.out.println(books.getId() + " :ID " + books.getTitle() + " :Title "
								+ books.getBookStatus() + " :Availability Status" + books.getIsbn() + " :ISBN");
					}
				} else {
					System.out.println("No book found under this author");
				}
				break;
			case 6:
				System.out.println("Exiting Author Function.......");
				return;
			default:
				System.out.println("Wrong Choice");
			}
		}
	}

	public void memberOperation() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("Welcome to Member Function");
			System.out.println(
					"Enter 1 to add new member\nEnter 2 to borrow Book\nEnter 3 to return book\nEnter 4 to change details\nEnter 5 to exit Member Functions");
			int choice = sc.nextInt();
			long id;
			String name, password;
			Member m = null;
			Date date;
			long bookId;
			if (choice > 0 && choice < 6)
				m = new Member();
			switch (choice) {
			case 1:
				System.out.println("Enter Member's name");
				name = sc.next();
				m.setName(name);
				date = new Date();

				m.setMembershipDate(date);
				System.out.println("Enter Email:");
				String email = sc.next();
				m.setEmail(email);
				System.out.println("Create password");
				password = sc.next();
				m.setPassword(password);
				mI.save(m);
				System.out.println(m.getId() + " this is your Id");
				break;
			case 2:
				System.out.println("Enter Member ID:");
				id = sc.nextLong();

				System.out.println("Enter Book ID to borrow:");
				bookId = sc.nextLong();
				
				mI.bookBorrow(bookId, id);
]				break;
			case 3:
				System.out.println("Enter Member Id:");
				id = sc.nextLong();

				System.out.println("Enter Book ID to borrow:");
				bookId = sc.nextLong();
				
				mI.bookReturn(bookId, id);
				break;
			case 4:
				System.out.println("Enter Member's ID");
				id = sc.nextLong();
				m = mI.get(id);
				if (m == null) {
					throw new NoSuchElementException("Author with ID " + id + " does not exist.");
				}
				System.out.println("Do you want to change name? (Y/N) ");
				String newData, c1 = sc.next();
				if (c1.equals("Y")) {
					System.out.println("Enter new Name:");
					newData = sc.next();
					m.setName(newData);
				}
				
				System.out.println("Do you want to change E-mail? (Y/N) ");
				c1 = sc.next();
				if (c1.equals("Y")) {
					System.out.println("Enter new email:");
					newData = sc.next();
					m.setEmail(newData);
				}
				System.out.println("Do you want to change password? (Y/N) ");
				c1 = sc.next();
				if (c1.equals("Y")) {
					System.out.println("Enter old password");
					String old = sc.next();
					if (old.equals(m.getPassword())) {
						System.out.println("Enter new password:");
						newData = sc.next();
						m.setPassword(newData);
					} else
						throw new IllegalArgumentException("Wrong password");
				}
				mI.update(m);
				break;
			case 5:
				System.out.println("Exiting Member Function.......");
				System.exit(0);
				break;
			default:
				System.out.println("Wrong Choice");
			}
		}
	}

	public static void main(String[] args) {
		App ob = new App();
		ob.mainMenu();
	}
}
