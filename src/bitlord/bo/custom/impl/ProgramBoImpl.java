package bitlord.bo.custom.impl;

import bitlord.bo.custom.ProgramBo;
import bitlord.dao.DaoFactory;
import bitlord.dao.custom.ProgramDao;
import bitlord.dto.ProgramDto;
import bitlord.entity.Program;

import java.sql.SQLException;

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
}
