package com.tasif.enrollservice.feign;

import com.tasif.enrollservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE",url = "http://localhost:9090/user")
public interface UserFeignClient {

    @GetMapping("/{username}")
    public User getUserByName(@PathVariable String username);
}
