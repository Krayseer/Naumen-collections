package ru.naumen.collection.task2;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class Ticket {
 *     private long id;
 *     private String client;
 *     …
 * }</pre>
 * <p>Разработать программу для бармена в холле огромного концертного зала.
 * Зрители в кассе покупают билет (класс Ticket), на котором указан идентификатор билета (id) и имя зрителя.
 * При этом, есть возможность докупить наборы разных товаров (комбо-обед): нет товаров, напитки, еда и напитки.
 * Доп. услуги оформляются через интернет и привязываются к билету, но хранятся отдельно от билета
 * (нельзя добавить товары в класс Ticket).</p>
 * <p>Бармен сканирует билет и получает объект Ticket. По этому объекту нужно уметь
 * находить необходимые товары по номеру билета. И делать это нужно очень быстро,
 * ведь нужно как можно быстрее всех накормить.</p>
 * <p>
 * См. {@link Ticket}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2 {

    /**
     * Сложность выполнения программы O(n), т.к. scanTicket использует HashMap, который позволяет обращаться к каждому
     * элементу за O(1)
     */

    /**
     * Онлайн заказы
     */
    private final Map<Ticket, LUNCH> orders = new HashMap<>();

    /**
     * Тестовые билеты
     */
    private final List<Ticket> testTickets = List.of(
            new Ticket(1L, "eugene"),
            new Ticket(2L, "boris"),
            new Ticket(3L, "vasya")
    );

    /**
     * Сложность выполнения программы O(n), где n - колличество билетов, т.к. scanTicket использует HashMap,
     * который позволяет обращаться к каждому элементу за O(1)
     */
    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.initOrders();
        task2.processOrders();
    }

    /**
     * Проициниализировать заказы с тестовыми билетами и тестовыми заказами
     */
    private void initOrders() {
        testTickets.forEach(ticket -> addOrder(ticket, LUNCH.randomLunch()));
    }

    /**
     * Выдать заказы всем клиентам
     */
    private void processOrders() {
        testTickets.forEach(this::scanTicket);
    }

    /**
     * Добавить заказ в список заказов
     * @param ticket билет, которому нужно выдать заказ
     * @param lunch заказанный комбо-обед
     */
    private void addOrder(Ticket ticket, LUNCH lunch) {
        orders.put(ticket, lunch);
    }

    /**
     * Обработать билет и выдать комбо-обед
     */
    private void scanTicket(Ticket ticket) {
        LUNCH clientLunch = orders.get(ticket);
        System.out.printf("User with id: %s get lunch: %s%n", ticket.getId(), clientLunch);
    }

    /**
     * Комбо обед
     */
    private enum LUNCH {
        /**
         * Нет товаров
         */
        NOTHING,
        /**
         * Напитки
         */
        DRINK,
        /**
         * Еда и напитки
         */
        DRINK_AND_EAT;

        public static LUNCH randomLunch()  {
            return values()[new Random().nextInt(values().length)];
        }
    }

}
