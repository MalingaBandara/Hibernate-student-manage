package bitlord.bo.custom.impl;

import bitlord.bo.custom.ProgramBo;
import bitlord.dao.DaoFactory;
import bitlord.dao.custom.ProgramDao;
import bitlord.dto.CustomRegistrationData;
import bitlord.dto.ProgramDto;
import bitlord.entity.Program;
import bitlord.entity.Registration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramBoImpl implements ProgramBo {

    private final ProgramDao programDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.PROGRAM);

    @Override
    public void saveProgram(ProgramDto dto) throws SQLException, ClassNotFoundException {

        // MapStruct, model mapper
        Program program = new Program();

        program.setTitle( dto.getTitle() );
        program.setCredit( dto.getCredit() );

        programDao.save( program );

    }

    @Override
    public List<Long> findAllStudentIds() {
        return programDao.findAllProgramsIds();
    }

    @Override
    public void register(long studentId, long programId) {
        programDao.register( studentId, programId );
    }

    @Override
    public List<CustomRegistrationData> findAllRegistrations() {

        List<CustomRegistrationData> data = new ArrayList<>();

                for ( Registration temp : programDao.findAllRegistrations() ) {

                    data. add(
                            new CustomRegistrationData(
                                    temp.getRegDate(),
                                    temp.getStudent().getName(),
                                    temp.getProgram().getTitle()
                                )
                    );
                }

        return data;

    }


}
