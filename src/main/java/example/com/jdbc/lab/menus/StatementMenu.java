package example.com.jdbc.lab.menus;

import example.com.jdbc.lab.statement.action.StatementAction;
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
