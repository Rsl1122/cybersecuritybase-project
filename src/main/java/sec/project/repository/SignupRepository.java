package sec.project.repository;

import sec.project.domain.Signup;

import java.util.List;

public interface SignupRepository {

    Signup findOne(Long id);

    List<Signup> findAll();

    void save(Signup signup);

}
