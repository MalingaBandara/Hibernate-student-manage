package bitlord.bo.custom;

import bitlord.dto.StudentDto;

import java.sql.SQLException;
import java.util.List;

public interface StudentBo {

    public void saveStudent ( StudentDto dto ) throws SQLException, ClassNotFoundException;
    public List<StudentDto> findAllStudents () throws SQLException, ClassNotFoundException;

}
