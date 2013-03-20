package co.ntier.example.asset.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ntier.example.asset.model.SimpleUser;

@Repository
public interface SimpleUserRepository extends JpaRepository<SimpleUser, Long>{

}
