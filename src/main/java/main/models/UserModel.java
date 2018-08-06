package main.models;

import main.pojos.UserPojo;
import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.*;

@Entity
@Table(name = "userdatabase")
public class UserModel {
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "user-database-generator")
    public Long id;
    public String name;
    public String login;
    public String passhash;
    public int wins;
    public int losses;
    public String bio;

//    public UserModel(String name, String password) {
//        this(DEFAULT_NAME, DEFAULT_PASS);
//    }

//    public UserModel(UserPojo user) {
//        this(user.name, user.login, user.password, user.wins, user.losses, user.bio);
//    }

//    public UserModel(String username, String login, String password, String bio) {
//        this(-1, username, login, password, bio);
//    }

    public UserModel() {}

    public UserModel(String name, String login, String password, String bio) {
        this.name = name;
        this.login = login;
        this.passhash = password;
        this.wins = 0;
        this.losses = 0;
        this.bio = bio;
    }

    public boolean checkPassword(String attempt) {
        boolean result = BCrypt.checkpw(attempt, this.passhash);
        return result;
    }
}
