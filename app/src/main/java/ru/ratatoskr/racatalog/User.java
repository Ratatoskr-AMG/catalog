package ru.ratatoskr.racatalog;

public class User implements Comparable<User> {
    private String id;
    private String firstname;
    private String lastname;
    private int age;

    @Override
    public int compareTo(User o) {
        //из getLastname() вычитаем o.getLastname()
        int result = getLastname().compareTo(o.getLastname());
        //если 0 - то равны
        if(result == 0)
            //вовзращаем 0, если юзеры одинаковые
            return getFirstname().compareTo(o.getFirstname());
        //вовзращаем не 1 или -1, если юзеры не одинаковые
        return result;
    }

    public User(String id, String firstname, String lastname, int age) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
