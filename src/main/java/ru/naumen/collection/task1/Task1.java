package ru.naumen.collection.task1;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно написать утилитный метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 *
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task1 {

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях<br/><br/>
     *
     * Делаю каст collA к Set, чтобы поиск по коллекции происходил за O(1), тем самым сложность всего алгоритма будет O(n),
     * где n - колличество элементов в коллекции collA
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        List<User> duplicateUsers = new ArrayList<>();
        Set<User> setCollA = new HashSet<>(collA);
        for (User user : setCollA) {
            if (collB.contains(user)) {
                duplicateUsers.add(user);
            }
        }
        return duplicateUsers;
    }

}
