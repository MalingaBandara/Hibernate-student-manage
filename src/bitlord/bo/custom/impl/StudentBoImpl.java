package bitlord.bo.custom.impl;

import bitlord.bo.custom.StudentBo;
import bitlord.dao.DaoFactory;
import bitlord.dao.custom.StudentDao;
import bitlord.dto.StudentDto;
import bitlord.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBo {

    private final StudentDao studentDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.STUDENT);

    @Override
    public void saveStudent(StudentDto dto) throws SQLException, ClassNotFoundException {

        Student student = new Student();

        student.setName( dto.getName() );
        student.setContact( dto.getContact() );

        studentDao.save( student );

    }

    @Override
    public List<StudentDto> findAllStudents() throws SQLException, ClassNotFoundException {

        ArrayList< StudentDto > dtos = new ArrayList<>(); // array list to save student data

        for ( Student s: studentDao.findAll() ) {

            StudentDto dto = new StudentDto( s.getId() , s.getName(), s.getContact() ); // create dto object to assign for each values

            dto.setBooks( s.getBooks() ); // assign child table data
            dto.setLaptop( s.getLaptop() ); // assign child table data

            dtos.add( dto ); // assign dto to array list

        }

        return dtos; // return array list of dto

    }

}
