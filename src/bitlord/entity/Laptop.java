package bitlord.entity;

import javax.persistence.*;

@Entity
public class Laptop {

                        @Id
                        @GeneratedValue( strategy = GenerationType.IDENTITY)
                        @Column( name = "laptop_id" )
                        private long id;
                        private String brand;


// ---------------- Mapping --------------

    @OneToOne
    @JoinColumn ( name = "student_id",
                    unique = true
            )
    private Student student;

// ---------------- Mapping --------------


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


                        public long getId() {
                            return id;
                        }

                        public void setId(long id) {
                            this.id = id;
                        }

                        public String getBrand() {
                            return brand;
                        }

                        public void setBrand(String brand) {
                            this.brand = brand;
                        }

                        public Laptop() {}

                        public Laptop(long id, String brand) {
                            this.id = id;
                            this.brand = brand;
                        }

                        @Override
                        public String toString() {
                            return "Laptop{" +
                                    "id=" + id +
                                    ", brand='" + brand + '\'' +
                                    '}';
                        }
}
