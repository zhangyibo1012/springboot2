package cn.zyblogs.webflux2.handler;

import cn.zyblogs.webflux.domain.User;
import cn.zyblogs.webflux.repository.UserRepository;
import cn.zyblogs.webflux.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @Class: UserHandler.java
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@Component
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    public Mono<ServerResponse> getAllUser(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(userRepository.findAll(), User.class);
    }

    public Mono<ServerResponse> createUser(ServerRequest request){
        Mono<User> user = request.bodyToMono(User.class);
//        需要操作使用flatMap
        return user.flatMap(u ->{
//            效验代码
            CheckUtil.checkName(u.getName());
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(userRepository.save(u), User.class);
        });
    }

    public Mono<ServerResponse> deleteUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.userRepository.findById(id)
                .flatMap(
                        user -> this.userRepository.delete(user).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
