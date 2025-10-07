package example.com.jdbc.lab.menus;

import example.com.jdbc.lab.statement.action.StatementAction;
import example.com.jdbc.lab.statement.model.Author;
import example.com.jdbc.lab.utils.InputManager;
import example.com.jdbc.lab.utils.MenuUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StatementMenu {
	private static final Logger logger = Logger.getLogger(StatementMenu.class.getName());
	private final StatementAction statementAction = new StatementAction();

	public void print() {
		List<String> options = new ArrayList<>();
		options.add("Очистить все таблицы");
		options.add("Просмотреть все записи");
		options.add("Добавить дефолтную информацию");
		options.add("Просмотреть автров с сортировкой");
		options.add("Посмотреть книги, выпущенные после определённого года");
		options.add("Изменить имя случайной книги в определённом магазине");
		final String header = MenuUtils.getHeader("Меню Statement", options);
		while (true) {
			System.out.print(header);
			try {
				switch (InputManager.getNextInt()) {
				case 1: {
					statementAction.deleteAllInfo();
					break;
				}
				case 2: {
					statementAction.getAllInfo();
					break;
				}
				case 3: {
					statementAction.addDefaultInfo();
					break;
				}
				case 4: {
					authorSortLoop:
					while(true) {
						System.out.println("1 - сортировка по имени\n2 - сортировка по фамилии");
						switch (InputManager.getNextInt()) {
							case 1:
								statementAction.getAuthorInfoSorted(Author.SortingType.BY_FIRST_NAME);
								break authorSortLoop;
							case 2:
								statementAction.getAuthorInfoSorted(Author.SortingType.BY_LAST_NAME);
								break authorSortLoop;
							default:
								System.out.println("1 или 2");
								break;
						}
					}
					break;
				}
				case 5: {
					startYearInputLoop:
					while(true){
						System.out.println("Выберите год, начиная с которого, вы хотите просмотрть книги\n");
						int startYear = InputManager.getNextInt();
						if (startYear < 1000){
							System.out.println("Введите более поздний год");
						} else {
							statementAction.getBookInfoFiltered(startYear);
							break startYearInputLoop;
						}
					}
					break;
				}
				case 6: {
					statementAction.changeRandomBookName();
					break;
				}
				case 0: {
					return;
				}
				default: {
					System.out.println("Такого варианта нет!");
					break;
				}
				}
			} catch (Exception e) {

				logger.severe("Ошибка при обработке меню Statement: " + e.getMessage());
			}
		}
	}
}
