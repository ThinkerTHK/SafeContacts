import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactBook contactBook = new ContactBook();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить контакт");
            System.out.println("2. Удалить контакт по имени");
            System.out.println("3. Найти контакт по имени");
            System.out.println("4. Показать все контакты");
            System.out.println("5. Сохранить контакты в файл");
            System.out.println("6. Загрузить контакты из файла");
            System.out.println("7. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Введите имя: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите номер телефона: ");
                    String phone = scanner.nextLine();
                    System.out.print("Введите email: ");
                    String email = scanner.nextLine();
                    Contact contact = new Contact(name, phone, email);
                    contactBook.addContact(contact);
                    System.out.println("Контакт добавлен.");
                    break;

                case "2":
                    System.out.print("Введите имя для удаления: ");
                    String removeName = scanner.nextLine();
                    boolean removed = contactBook.removeContactByName(removeName);
                    if (removed) {
                        System.out.println("Контакт(ы) удалены.");
                    } else {
                        System.out.println("Контакт с таким именем не найден.");
                    }
                    break;

                case "3":
                    System.out.print("Введите имя для поиска: ");
                    String searchName = scanner.nextLine();
                    List<Contact> foundContacts = contactBook.findContactsByName(searchName);
                    if (!foundContacts.isEmpty()) {
                        System.out.println("Найденные контакты:");
                        for (Contact c : foundContacts) {
                            System.out.println(c);
                        }
                    } else {
                        System.out.println("Контакты не найдены.");
                    }
                    break;

                case "4":
                    List<Contact> allContacts = contactBook.getAllContacts();
                    if (allContacts.isEmpty()) {
                        System.out.println("Список контактов пуст.");
                    } else {
                        System.out.println("Все контакты:");
                        for (Contact c : allContacts) {
                            System.out.println(c);
                        }
                    }
                    break;

                case "5":
                    System.out.print("Введите имя файла для сохранения: ");
                    String saveFile = scanner.nextLine();
                    try {
                        contactBook.saveToFile(saveFile);
                        System.out.println("Контакты успешно сохранены.");
                    } catch (IOException e) {
                        System.out.println("Ошибка при сохранении файла: " + e.getMessage());
                    }
                    break;

                case "6":
                    System.out.print("Введите имя файла для загрузки: ");
                    String loadFile = scanner.nextLine();
                    try {
                        contactBook.loadFromFile(loadFile);
                        System.out.println("Контакты успешно загружены.");
                    } catch (IOException e) {
                        System.out.println("Ошибка при загрузке файла: " + e.getMessage());
                    }
                    break;

                case "7":
                    running = false;
                    System.out.println("Выход из программы.");
                    break;

                default:
                    System.out.println("Некорректный ввод, попробуйте еще раз.");
            }
        }

        scanner.close();
    }
}
