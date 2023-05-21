package bitlord.dao.custom.impl;

import bitlord.dao.custom.ProgramDao;
import bitlord.entity.Program;
import bitlord.entity.Registration;
import bitlord.entity.Student;
import bitlord.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramDaoImpl implements ProgramDao {
    @Override
    public void save(Program program) throws SQLException, ClassNotFoundException {

        try ( Session session = HibernateUtil.getInstance().openSession() ){

            session.beginTransaction();
            session.save( program );

            session.getTransaction().commit();

        }

    }

    @Override
    public void update(Program program) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Program find(Long aLong) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void delete(Long aLong) throws SQLException, ClassNotFoundException {

    }

    @Override
    public List<Program> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Long> findAllProgramsIds() {

        List<Long> list = new ArrayList<>();

         try ( Session session = HibernateUtil.getInstance().openSession() ) {

             String hql = "FROM Program";

             Query<Program> query = session.createQuery(hql, Program.class);

                for ( Program p : query.list() ) {
                    list.add( p.getId() ); // set ids to the arraylist
                }
         }

         return list;
    }

    @Override
    public void register(long studentId, long programId) {

        try (Session session = HibernateUtil.getInstance().openSession() ) {

                // ====================================================== Get Student data

                    Query<Student> studentQuery = session.createQuery("FROM Student WHERE student_id=:sId", Student.class ); // query for get student id

                    studentQuery.setParameter( "sId", studentId );

                    Student student = studentQuery.uniqueResult(); // assign result

                    if ( student == null ) {  // check the result is null
                        throw  new RuntimeException( "Student not found" );
                    }

                // ====================================================== Get Program data

                    Query<Program> programQuery = session.createQuery("FROM Program WHERE program_id=:pId", Program.class ); // query for get student id

                    programQuery.setParameter( "pId", programId );

                    Program program = programQuery.uniqueResult(); // assign result

                    if ( program == null ) {  // check the result is null
                        throw  new RuntimeException( "Program not found" );
                    }

        session.beginTransaction();
            Registration registration = new Registration();
            registration.setProgram( program );
            registration.setStudent( student );
            registration.setRegDate( new Date() );

        session.save( registration );
        session.getTransaction().commit();

        }

    }
}
