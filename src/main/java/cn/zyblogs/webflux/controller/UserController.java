package cn.zyblogs.webflux.controller;

import cn.zyblogs.webflux.domain.User;
import cn.zyblogs.webflux.repository.UserRepository;
import cn.zyblogs.webflux.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @Class: UserController.java
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private  UserRepository userRepository;

    /**
     *  以数组形式一次性返回数据
     * @return
     */
    @GetMapping
    public Flux<User> getAll(){
        return userRepository.findAll();
    }

    /**
     *  以SSE形式多次返回数据 流  @Valid 校验参数
     * @return
     */
    @GetMapping(value = "stream/all" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll(){
        return userRepository.findAll();
    }

    @PostMapping
    public Mono<User> createUser( @Valid @RequestBody User user){
//        spring data jpa 新增和修改都是save 有id是修改 id为空是新增
//        根据实际情况是否置空id
        user.setId(null);
        CheckUtil.checkName(user.getName());
        return userRepository.save(user);
    }

    /**
     *  根据id删除用户  存在返回200 不存在返回404
     * @return
     */
    @DeleteMapping(value = "{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id){
//        deleteById 没有返回值 不能判断数据是否存在
//        userRepository.deleteById(id);

         return userRepository.findById(id)
//        当你要操作数据并返回一个Mono这个时候使用flatMap
//        如果不操作数据，只是转换数据使用map
        .flatMap(user -> userRepository.delete(user)
//                没有返回值时使用then 在里面直接返回一个Mono数据
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
//                defaultIfEmpty 假设数据从不存在 返回NOT_FOUND
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     *  修改数据 存在的时候返回200和修改后的数据 不存在时候返回404
     *  @Valid 效验参数 对象
     * @param id
     * @param user
     * @return
     */
    @PutMapping(value = "{id}")
    public Mono<ResponseEntity<User>> updateUser(
           @PathVariable("id") String id ,
           @Valid @RequestBody User user){
        CheckUtil.checkName(user.getName());
      return userRepository.findById(id)
//              flatMap 操作数据
              .flatMap(u -> {
                  u.setAge(user.getAge());
                  u.setName(user.getName());
                  return userRepository.save(u);
//                  map 转换数据 A 转换 B
              }).map(u -> new ResponseEntity<>(u, HttpStatus.OK))
//                defaultIfEmpty 假设数据从不存在 返回NOT_FOUND
              .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     *  根据id查找用户 存在返回用户信息 不存在返回404
     */
    @GetMapping(value = "{id}")
    public Mono<ResponseEntity<User>> findUserById(@PathVariable("id") String id){
        return userRepository.findById(id)
//                map 转换数据
                .map(u -> new ResponseEntity<>(u , HttpStatus.OK))
//                defaultIfEmpty 假设数据从不存在 返回NOT_FOUND
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据年龄查找用户
     *
     * @param start
     * @param end
     * @return
     */
    @GetMapping(value = "age/{start}/{end}")
    public Flux<User> findByAge(@PathVariable("start") int start,
                                @PathVariable("end") int end) {
        return this.userRepository.findByAgeBetween(start, end);
    }

    /**
     * 根据年龄查找用户 流
     * @param start
     * @param end
     * @return
     */
    @GetMapping(value = "stream/age/{start}/{end}" ,produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindByAge(
            @PathVariable("start") int start ,
            @PathVariable("end") int end){
        return userRepository.findByAgeBetween(start, end);
    }

    /**
     *  得到20-30用户
     * @return
     */
    @GetMapping(value = "old")
    public Flux<User> oldUser() {
        return userRepository.oldUser();
    }

    /**
     * 得到20-30用户
     *
     * @return
     */
    @GetMapping(value = "stream/old", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamOldUser() {
        return userRepository.oldUser();
    }

}
