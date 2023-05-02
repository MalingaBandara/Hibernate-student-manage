package bitlord.entity;

import javax.persistence.*;

@Entity
public class Program {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "program_id" )
    private long id;

    private String title;

    private int credit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Program() {
    }

    public Program(long id, String title, int credit) {
        this.id = id;
        this.title = title;
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", credit=" + credit +
                '}';
    }
}
