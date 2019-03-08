package pl.blaszak.aws.logonservice.exception.bucket;

public class UserNotExistException extends Exception {

    public UserNotExistException(String name) {
        super("User " + name + " not exist");
    }
}
