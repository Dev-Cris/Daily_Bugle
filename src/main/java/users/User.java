package users;

import javax.persistence.*;

@MappedSuperclass

public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    protected User() {
    }

    protected User(String name) {
        if (nameValidator(name)) {
            this.name = name;
        }
    }

    private boolean nameValidator(String name) {
        boolean result = false;
        if (!name.isEmpty() && !name.isBlank()) {
            result = true;
        } else {
            System.out.println("The author's name cannot be empty");
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}