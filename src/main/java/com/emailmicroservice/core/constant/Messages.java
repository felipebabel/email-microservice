package com.emailmicroservice.core.constant;

public class Messages {

    public static final String FIELD_SUBJECT = "Subject";

    public static final String FIELD_TO = "To";

    public static final String FIELD_FROM = "From";

    public static final String FIELD_BODY = "Body";

    public static final String MSG_FIELD_ID_NOT_INFORMED = "%s not informed.";

    public static final String MSG_EMAIL_SENT_SUCCESSFUL = "Email sent successful";

    public static final String MSG_ERROR_WHEN_SEND_EMAIL = "Error when send email";

    public static final String SUCCESS_MSG = "{\"status\" : \"Success\" , \"message\" : \"";

    public static final String ERROR_MSG = "{\"status\" : \"Error\" , \"message\" : \"";

    public static final String END_MESSAGE = "\" }";

    public static final String MSG_INVALID_CREDENTIALS = "Error sending email:"
        + " Please check your AWS Secret Access Key, region, accessKeyId and check the identitys in the Amazon SES.";

    public static final String MSG_NETWORK = "Error sending email: "
        + "There was a problem connecting to the email server. Please check your internet connection and try again.";

    public static final String SUCCESS_MESSAGE = SUCCESS_MSG + MSG_EMAIL_SENT_SUCCESSFUL + END_MESSAGE;

    public static final String ERROR_MESSAGE = ERROR_MSG + MSG_ERROR_WHEN_SEND_EMAIL + END_MESSAGE;

    public static final String ERROR_FIELD_ID_NOT_INFORMED = ERROR_MSG + "[field] not informed." + END_MESSAGE;

    public static final String ERROR_INVALID_CREDENTIALS = ERROR_MSG + MSG_INVALID_CREDENTIALS + END_MESSAGE;

    public static final String ERROR_NETWORK = ERROR_MSG + MSG_NETWORK + END_MESSAGE;

}
