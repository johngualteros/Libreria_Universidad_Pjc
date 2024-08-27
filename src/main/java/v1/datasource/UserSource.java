package v1.datasource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import v1.model.User;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UserSource {
    public static List<User> users = new ArrayList<>();

    public static void add(User user) {
        users.add(user);
    }

    public static void remove(User user) {
        users.remove(user);
    }

    public static User getById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public static void update(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                return;
            }
        }
    }
}
