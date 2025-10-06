package example.com.jdbc.lab;

import example.com.jdbc.lab.connection.ConnectionManager;
import example.com.jdbc.lab.menus.MainMenu;

import java.util.logging.Logger;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		if (ConnectionManager.getConnection() == null) { // установка соединения с БД
			return;
		}
		try {
			new MainMenu().print();
		} catch (Exception e) {
			logger.severe("Ошибка при обработке главного меню: " + e.getMessage());
		}
		ConnectionManager.closeConnection();
	}
}
