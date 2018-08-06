package main.models;

import javax.persistence.*;

@Entity
@Table(name = "userdatabase")
public class UserDatabase {
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "user-database-generator")
    public Long id;
    public String name;
    public String login;
    public String passhash;
    public int wins;
    public int losses;

    public UserDatabase() {}

    public UserDatabase(String name, String login, String passhash) {
        this.name = name;
        this.login = login;
        this.passhash = passhash;
        this.wins = 0;
        this.losses = 0;
    }
}
