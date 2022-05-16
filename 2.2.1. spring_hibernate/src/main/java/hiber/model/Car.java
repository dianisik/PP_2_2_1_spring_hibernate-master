package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")

public class Car {

    public Car (String model, int series, User user) {
        this.model = model;
        this.series = series;
        this.user = user;
    }
    public Car (){}

    public String getModel() {
        return model;
    }
    public int getSeries() {
        return series;
    }
    public User getUser() {
        return user;
    }
    public int getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "model")
    private String model;

    @Column (name = "series")
    int series;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id", referencedColumnName = "id")
    private User user;


}
