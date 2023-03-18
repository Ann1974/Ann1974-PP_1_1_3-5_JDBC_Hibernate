package jm.task.core.jdbc.model;

import javax.persistence.*;

@Entity// @Entity указывает, что класс является сущностью,
// которая может быть сохранена в базе данных
// потребовала явно указать конструктор

@Table(name = "user")// @Table определяет имя таблицы,
// с которой связана данная сущность
public class User {
    @Id// определяет уникальный ключ, у нас это id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//  к какому именно столбцу
    // привязываем конкретное поле класса

    @Column(name = "name" )
    private String name;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "age")
    private Byte age;


    public User() {

    }


    public User( String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

}

