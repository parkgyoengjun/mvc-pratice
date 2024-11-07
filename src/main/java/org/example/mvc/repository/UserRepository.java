package org.example.mvc.repository;

import org.example.mvc.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private static Map<String, User> users = new HashMap<>();

    public static void save(User user) {
        users.put(user.getUserId(), user);
        // UserId 를 키로 가지고 User를 저장
    }

    public static Collection<User> findAll() {
        return users.values();
    }
    /*  -> Collection<> 을 쓴 이유는 users 가 Map 이고 값들을 다 넘겨줘야해서 Map의 상위인 Collection<>을 씀
        Collection<>
        Set, List, Map 인터페이스의 상위인터페이스
     */
}
