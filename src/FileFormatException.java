/**
 * This is a custom exception, for checking file formats.
 */
public class FileFormatException extends Exception
{
    /**
     * Create an instance using the provided message.
     * @param msg a descriptive message about the error.
     */
    public FileFormatException(String msg)
    {
        super(msg);
    }
}