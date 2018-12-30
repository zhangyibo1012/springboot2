package cn.zyblogs.webflux2.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import cn.zyblogs.webflux2.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class AllRouters {

	@Bean
	RouterFunction<ServerResponse> userRouter(UserHandler handler) {
//		next 嵌套
		return nest(
				// 相当于类上面的 @RequestMapping("/user")
				path("/user2"),
				// 下面的相当于类里面的 @RequestMapping
				// 得到所有用户
				route(GET("/"), handler::getAllUser)
				// 创建用户
				.andRoute(POST("/").and(accept(MediaType.APPLICATION_JSON_UTF8)),
								handler::createUser)
				// 删除用户
				.andRoute(DELETE("/{id}"), handler::deleteUserById));
	}

}
