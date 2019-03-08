package pl.blaszak.aws.logonservice.controller.domain;

import lombok.Data;

@Data
public class ApplicationResult {

    private final State state;
    private final String message;
    private final Object responseData;

    private ApplicationResult(State state, String message, Object responseData) {
        this.state = state;
        this.message = message;
        this.responseData = responseData;
    }

    public static ApplicationResult businessError(String message) { return new ApplicationResult(State.BUSINESS_ERROR, message, null); }
    public static ApplicationResult validationError(String message) { return new ApplicationResult(State.VALIDATION_ERROR, message, null); }
    public static ApplicationResult ok(String message) { return new ApplicationResult(State.OK, message, null); }
    public static ApplicationResult withResponseData(String message, Object data) { return new ApplicationResult(State.OK, message, data); }
}
