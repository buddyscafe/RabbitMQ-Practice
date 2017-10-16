package mission.data;

import mission.model.Mission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MissionRepository extends
        MongoRepository<Mission, String> {

}
