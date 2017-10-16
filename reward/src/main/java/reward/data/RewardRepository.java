package reward.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import reward.model.Reward;

public interface RewardRepository extends
        MongoRepository<Reward, String> {

}
