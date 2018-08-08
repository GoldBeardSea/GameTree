package server.models;

import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.*;

@Entity
@Table(name = "userdatabase")
public class UserModel implements Comparable<UserModel> {
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "user-database-generator")
    public Long id;
    public String name;
    public String login;
    public String passhash;
    public int wins;
    public int losses;
    public int ties;
    public String bio;

    public UserModel() {}

    public UserModel(String name, String login, String password, String bio) {
        this.name = name;
        this.login = login;
        this.passhash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
        this.bio = bio;

    }

    public boolean checkPassword(String attempt) {
        boolean result = BCrypt.checkpw(attempt, this.passhash);
        return result;
    }

    @Override
    public int compareTo(UserModel o) {
        return o.wins - this.wins;
    }
}
