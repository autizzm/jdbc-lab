package example.com.jdbc.lab.statement.action;

import java.util.*;
import java.util.logging.Logger;

import example.com.jdbc.lab.statement.dao.AuthorDao;
import example.com.jdbc.lab.statement.dao.BookDao;
import example.com.jdbc.lab.statement.dao.ShopBookDao;
import example.com.jdbc.lab.statement.dao.ShopDao;
import example.com.jdbc.lab.statement.model.Author;
import example.com.jdbc.lab.statement.model.Book;
import example.com.jdbc.lab.statement.model.Shop;
import example.com.jdbc.lab.statement.model.ShopBook;
import example.com.jdbc.lab.utils.InputManager;

public class StatementAction {
	private static final Logger logger = Logger.getLogger(StatementAction.class.getName());
	AuthorDao authorDao = new AuthorDao();
	BookDao bookDao = new BookDao();
	ShopDao shopDao = new ShopDao();
	ShopBookDao shopBookDao = new ShopBookDao();

	public void deleteAllInfo() {
		System.out.print("Подтвердите удаление всей информации (y / Y): ");
		final String choice = InputManager.getNextLine();
		if (!choice.equals("Y") && !choice.equals("y")) {
			System.out.println("Удаление было отменено!");
			return;
		}
		try {
			shopBookDao.clearTable();
			bookDao.clearTable();
			authorDao.clearTable();
			shopDao.clearTable();
			System.out.println("Информация из таблиц была успешно удалена!");
		} catch (RuntimeException e) {
			logger.severe("При удалении информации из таблиц ошибка: " + e.getMessage());
		}
	}

	public void addDefaultInfo() {
		try {
			if (authorDao.getCount() != 0 || bookDao.getCount() != 0 || shopDao.getCount() != 0
					|| shopBookDao.getCount() != 0) {
				System.out.println("Таблицы не пусты. Вставка информации по умолчанию невозможна!");
				return;
			}
			List<Author> authors = getDefaultAuthors();
			for (Author author : authors) {
				authorDao.insert(author);
			}
			authors = authorDao.getAll();
			List<Book> books = getDefaultBooks(authors);
			for (Book book : books) {
				bookDao.insert(book);
			}
			books = bookDao.getAll();
			List<Shop> shops = getDefaultShops();
			for (Shop shop : shops) {
				shopDao.insert(shop);
			}
			shops = shopDao.getAll();
			List<ShopBook> shopBooks = getDefaultShopBooks(shops, books);
			for (ShopBook shopBook : shopBooks) {
				shopBookDao.insert(shopBook);
			}
			System.out.println("Информация по умолчанию вставлена успешно!");
		} catch (RuntimeException e) {
			logger.severe("При добавлении информации в таблицы возникла ошибка: " + e.getMessage());
		}
	}

	public void viewAllInfo() {
		try {
			List<Author> authors = authorDao.getAll();
			if (authors.isEmpty()) {
				System.out.println("Таблица авторов пуста!");
			} else {
				System.out.println("[Авторы]");
				for (Author author : authors) {
					System.out.println(author);
				}
			}
			List<Shop> shops = shopDao.getAll();
			if (shops.isEmpty()) {
				System.out.println("Таблица магазинов пуста!");
			} else {
				System.out.println("[Магазины]");
				for (Shop shop : shops) {
					System.out.println(shop);
				}
			}
			List<Book> books = bookDao.getAll();
			if (books.isEmpty()) {
				System.out.println("Таблица книг пуста!");
			} else {
				System.out.println("[Книги]");
				for (Book book : books) {
					System.out.println(book);
				}
			}
			List<ShopBook> shopBooks = shopBookDao.getAll();
			if (shopBooks.isEmpty()) {
				System.out.println("Таблица магазинов пуста!");
			} else {
				System.out.println("[Магазины]");
				for (ShopBook shopBook : shopBooks) {
					System.out.println(shopBook);
				}
			}
		} catch (RuntimeException e) {
			logger.severe("При просмотре информации возникла ошибка: " + e.getMessage());
		}
	}

	public void viewAuthorInfoSorted(Author.SortingType sortingType) {
		try {
			Comparator<Author> comparator = null;
			List<Author> authors = authorDao.getAll();
			if (authors.isEmpty()) {
				System.out.println("Таблица авторов пуста!");
			} else {
				switch(sortingType) {
					case BY_FIRST_NAME:
						comparator = new Comparator<Author>() {
						@Override
						public int compare(Author o1, Author o2) {
							return o1.getFirstName().compareTo(o2.getFirstName());
						}
					};
						break;
					case BY_LAST_NAME:
						comparator = new Comparator<Author>() {
							@Override
							public int compare(Author o1, Author o2) {
								return o1.getLastName().compareTo(o2.getLastName());
							}
						};
				}
				Collections.sort(authors, comparator);
				System.out.println("[Авторы]");
				for (Author author : authors) {
					System.out.println(author);
				}
			}
		} catch (RuntimeException e) {
			logger.severe("При просмотре информации возникла ошибка: " + e.getMessage());
		}
	}

	public void viewBookInfoFiltered(int year) {
		try {
			Comparator<Book> comparator = new Comparator<Book>() {
				@Override
				public int compare(Book o1, Book o2) {
					return o1.getYearOfPublication().compareTo(o2.getYearOfPublication());
				}
			};
			List<Book> books = bookDao.getAll();
			if(books.isEmpty())
			{
				System.out.println("Таблица авторов пуста!");
			} else
			{
				Collections.sort(books, comparator);
				System.out.println("[Книги]");
				for (Book book : books) {
					if(book.getYearOfPublication() > year)
						System.out.println(book);
				}
			}
		} catch(RuntimeException e){
			logger.severe("При просмотре информации возникла ошибка: " + e.getMessage());
		}
	}

	public void changeRandomBookName(){
		List<Shop> shops = shopDao.getAll();
		int choice = -1;
		String newBookName = "";

		System.out.println("Выберите магазин:");
		if (shops.isEmpty()) {
			System.out.println("Таблица магазинов пуста!");
		} else {
			while(choice < 1 || choice > shops.size()) {
				System.out.println("[Магазины]");
				for (int i = 0; i < shops.size(); i++) {
					System.out.println(i + 1 + " - " + shops.get(i));
				}
				choice = InputManager.getNextInt();
			}
			System.out.println("Введите название для книги:\n");
			newBookName = InputManager.getNextLine();

			long shopId = shops.get(choice-1).getId();
			List<ShopBook> shopBooks = shopBookDao.getAll();
			List<Long> bookIds = new ArrayList<>();
			Random random = new Random();

			for(ShopBook shopBook : shopBooks){
				if(shopBook.getShopId() == shopId){
					bookIds.add(shopBook.getBookId());
				}
			}
			int bookToBeAlteredIndex = Math.abs(random.nextInt() % bookIds.size());
			bookDao.update(bookIds.get(bookToBeAlteredIndex), newBookName);
		}
	}

	public void viewAmtOfBooksPerAuthor(){
		Map<String, Integer> authors = bookDao.getAmtOfBooksPerAuthor();
		for(String authorName : authors.keySet()){
			System.out.println(authorName + " - " + authors.get(authorName));
		}
	}

	public void changeAuthorNames() {
		List<Shop> shops = shopDao.getAll();
		String newFirstName = "";
		String newLastName = "";
		int choice = -1;

		System.out.println("Выберите магазин:");
		if (shops.isEmpty()) {
			System.out.println("Таблица магазинов пуста!");
		} else {
			while (choice < 1 || choice > shops.size()) {
				System.out.println("[Магазины]");
				for (int i = 0; i < shops.size(); i++) {
					System.out.println(i + 1 + " - " + shops.get(i));
				}
				choice = InputManager.getNextInt();
			}
			System.out.println("Введите имя:\n");
			newFirstName = InputManager.getNextLine();
			System.out.println("Введите фамилию:\n");
			newLastName = InputManager.getNextLine();
			long shopId = shops.get(choice).getId();
			authorDao.updateNamesByShopId(shopId, newFirstName, newLastName);
		}
	}

	public void viewBooksWithAuthorNameStartingFrom(){
		System.out.println("Введите букву:");
		String str = InputManager.getNextLine();
		char c = str.charAt(0);
		List<Book> books = bookDao.getBooksWithAuthorNameStartingFrom(c);
		if(books.isEmpty()){
			System.out.println("Подходящих книг нет!");
		} else {
			System.out.println("[Книги]");
			for (Book book : books) {
				System.out.println(book);
			}
		}
	}

	private List<Author> getDefaultAuthors() {
		List<Author> authors = new ArrayList<>();
		authors.add(new Author("Лев", "Толстой"));
		authors.add(new Author("Фёдор", "Достоевский"));
		authors.add(new Author("Александр", "Пушкин"));
		authors.add(new Author("Антон", "Чехов"));
		return authors;
	}

	private List<Book> getDefaultBooks(List<Author> authors) {
		List<Book> books = new ArrayList<>();
		books.add(new Book("Война и мир",
				authors.stream().filter(x -> x.getLastName().equals("Толстой")).findFirst().orElseThrow().getId(),
				1869));
		books.add(new Book("Анна Каренина",
				authors.stream().filter(x -> x.getLastName().equals("Толстой")).findFirst().orElseThrow().getId(),
				1877));
		books.add(new Book("Преступление и наказание",
				authors.stream().filter(x -> x.getLastName().equals("Достоевский")).findFirst().orElseThrow().getId(),
				1866));
		books.add(new Book("Идиот",
				authors.stream().filter(x -> x.getLastName().equals("Достоевский")).findFirst().orElseThrow().getId(),
				1869));
		books.add(new Book("Евгений Онегин",
				authors.stream().filter(x -> x.getLastName().equals("Пушкин")).findFirst().orElseThrow().getId(),
				1833));
		books.add(new Book("Капитанская дочка",
				authors.stream().filter(x -> x.getLastName().equals("Пушкин")).findFirst().orElseThrow().getId(),
				1836));
		books.add(new Book("Чайка",
				authors.stream().filter(x -> x.getLastName().equals("Чехов")).findFirst().orElseThrow().getId(), 1896));
		books.add(new Book("Вишнёвый сад",
				authors.stream().filter(x -> x.getLastName().equals("Чехов")).findFirst().orElseThrow().getId(), 1904));
		return books;
	}

	private List<Shop> getDefaultShops() {
		List<Shop> shops = new ArrayList<>();
		shops.add(new Shop("Белкнига", "проспект Независимости, 14"));
		shops.add(new Shop("Букинист", "проспект Независимости, 53"));
		shops.add(new Shop("Oz", "улица Сурганова, 21"));
		return shops;
	}

	private List<ShopBook> getDefaultShopBooks(List<Shop> shops, List<Book> books) {
		List<ShopBook> shopBooks = new ArrayList<>();
		shopBooks.add(new ShopBook(
				shops.stream().filter(x -> x.getName().equals("Белкнига")).findFirst().orElseThrow().getId(),
				books.stream().filter(x -> x.getTitle().equals("Война и мир")).findFirst().orElseThrow().getId()));
		shopBooks.add(new ShopBook(
				shops.stream().filter(x -> x.getName().equals("Белкнига")).findFirst().orElseThrow().getId(),
				books.stream().filter(x -> x.getTitle().equals("Чайка")).findFirst().orElseThrow().getId()));
		shopBooks.add(new ShopBook(
				shops.stream().filter(x -> x.getName().equals("Белкнига")).findFirst().orElseThrow().getId(),
				books.stream().filter(x -> x.getTitle().equals("Капитанская дочка")).findFirst().orElseThrow()
						.getId()));
		shopBooks.add(new ShopBook(
				shops.stream().filter(x -> x.getName().equals("Букинист")).findFirst().orElseThrow().getId(),
				books.stream().filter(x -> x.getTitle().equals("Анна Каренина")).findFirst().orElseThrow().getId()));
		shopBooks.add(new ShopBook(
				shops.stream().filter(x -> x.getName().equals("Букинист")).findFirst().orElseThrow().getId(),
				books.stream().filter(x -> x.getTitle().equals("Идиот")).findFirst().orElseThrow().getId()));
		shopBooks.add(new ShopBook(
				shops.stream().filter(x -> x.getName().equals("Букинист")).findFirst().orElseThrow().getId(),
				books.stream().filter(x -> x.getTitle().equals("Евгений Онегин")).findFirst().orElseThrow().getId()));
		shopBooks.add(
				new ShopBook(shops.stream().filter(x -> x.getName().equals("Oz")).findFirst().orElseThrow().getId(),
						books.stream().filter(x -> x.getTitle().equals("Преступление и наказание")).findFirst()
								.orElseThrow().getId()));
		shopBooks.add(new ShopBook(
				shops.stream().filter(x -> x.getName().equals("Oz")).findFirst().orElseThrow().getId(),
				books.stream().filter(x -> x.getTitle().equals("Вишнёвый сад")).findFirst().orElseThrow().getId()));
		return shopBooks;
	}
}
