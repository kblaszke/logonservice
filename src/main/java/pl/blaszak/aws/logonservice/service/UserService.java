package pl.blaszak.aws.logonservice.service;

import pl.blaszak.aws.logonservice.couchbase.model.User;
import pl.blaszak.aws.logonservice.couchbase.repository.UserRepository;
import pl.blaszak.aws.logonservice.exception.bucket.UserAlreadyExistException;
import pl.blaszak.aws.logonservice.exception.bucket.UserNotExistException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) throws UserAlreadyExistException {
        if (userRepository.existsById(user.getName())) {
            throw new UserAlreadyExistException(user.getName());
        }
        userRepository.save(user);
    }

    public void delete(String name) throws UserNotExistException {
        User user = userRepository.findById(name).orElseThrow(() -> new UserNotExistException(name));
        userRepository.delete(user);
    }

    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        userList.sort(Comparator.comparing(User::getName));
        return userList;
    }
}
