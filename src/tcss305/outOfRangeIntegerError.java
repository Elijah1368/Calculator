package tcss305;

/**
 *
Custom exception for when integer achieves underflow or overflow
 */
public class outOfRangeIntegerError extends Exception {
    public outOfRangeIntegerError(String message){
        super(message);
    }

    /**
     *
     * @return custom message
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
