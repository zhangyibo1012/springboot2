package cn.zyblogs.webflux.repository;

import cn.zyblogs.webflux.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @Class: UserRepository.java
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    /**
     *  根据年龄查找用户
     * @param start
     * @param end
     * @return
     */
    Flux<User> findByAgeBetween(int start , int end);

    /**
     *  大于等于22 小于等于30
     * @return
     */
    @Query("{'age':{ '$gte': 20, '$lte' : 30}}")
    Flux<User> oldUser();
}
