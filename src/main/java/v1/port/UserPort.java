package v1.port;

import v1.model.User;

import java.util.List;

public interface UserPort {
    void createUser();
    List<User> getUsers();
    User getUser();
    void returnBook();
    void initUsers();
}
