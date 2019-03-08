package pl.blaszak.aws.logonservice.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.blaszak.aws.logonservice.controller.domain.ApplicationResult;
import pl.blaszak.aws.logonservice.couchbase.model.User;
import pl.blaszak.aws.logonservice.exception.bucket.UserAlreadyExistException;
import pl.blaszak.aws.logonservice.exception.bucket.UserNotExistException;
import pl.blaszak.aws.logonservice.service.UserService;

@RestController
@RequestMapping("/logonService/users")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
        LOGGER.debug("UserController constructor started.");
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ApplicationResult register(@RequestBody User user) {
        String message = "User: " + user.getName();
        try {
            userService.register(user);
             message += " has been registered";
            LOGGER.debug(message);
            return ApplicationResult.ok(message);
        } catch (UserAlreadyExistException e) {
            LOGGER.info(e.getMessage());
            return ApplicationResult.businessError(message + " already exist!");
        }
    }

    @PostMapping(value = "/delete", produces = "application/json")
    public ApplicationResult deleteUser(@RequestBody String name) {
        String normalizedName = StringUtils.normalizeSpace(name);
        try {
            userService.delete(normalizedName);
            return ApplicationResult.ok("User: " + normalizedName + " deleted.");
        } catch (UserNotExistException e) {
            return ApplicationResult.businessError(e.getMessage());
        }
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ApplicationResult allUsers() {
        return ApplicationResult.withResponseData(null, userService.getAll());
    }
}
