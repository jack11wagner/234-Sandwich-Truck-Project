/**
 * This is a custom exception, for checking the format of addresses before they are converted.
 */
class InvalidAddressException extends Exception
{
    /**
     * Create an instance using the provided message.
     * @param msg a descriptive message about the error.
     */
    public InvalidAddressException(String msg)
    {
        super(msg);
    }
}
