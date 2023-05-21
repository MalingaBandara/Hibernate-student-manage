package bitlord.bo.custom;


import bitlord.dto.CustomRegistrationData;
import bitlord.dto.ProgramDto;

import java.sql.SQLException;
import java.util.List;

public interface ProgramBo {

    public void saveProgram(ProgramDto dto ) throws SQLException, ClassNotFoundException ;

     public List<Long> findAllStudentIds();

     public void register( long studentId, long programId );

     List<CustomRegistrationData> findAllRegistrations();

}
