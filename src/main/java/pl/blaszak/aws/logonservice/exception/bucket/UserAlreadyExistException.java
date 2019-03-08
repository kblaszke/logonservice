package pl.blaszak.aws.logonservice.exception.bucket;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException(String name) {
        super("User: " + name + " already exist");
    }
}
