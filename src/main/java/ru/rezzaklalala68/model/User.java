package ru.rezzaklalala68.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotEmpty(message = "firstName should not be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "firstName should contain only letters")
    @Size(min = 2, max = 35, message = "firstName should be between 2 and 35 characters")
    private String firstName;


    @Column( nullable = false)
    @NotEmpty(message = "lastName should not be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "lastName should contain only letters")
    @Size(min = 2, max = 35, message = "lastName should be between 2 and 35 characters")
    private String lastName;

    @Column(nullable = false)
    @Min(value = 1, message = "Age should be greater than 0")
    private short age;

    public User(){

    }
    public User(long id, String firstName, String lastName, short age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public long getId() {return id;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age);
    }

    @Override
    public String toString() {
        return "User " +
                "id = " + id +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", age = " + age ;
    }
}
