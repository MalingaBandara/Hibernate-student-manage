package bitlord.dao.custom;

import bitlord.dao.CrudDao;
import bitlord.entity.Program;
import bitlord.entity.Registration;


import java.util.List;

public interface ProgramDao extends CrudDao<Program, Long > {


    public List< Long > findAllProgramsIds();

    public void register( long studentId, long programId);

    public List<Registration> findAllRegistrations();


}
