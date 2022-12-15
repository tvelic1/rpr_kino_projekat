package ba.unsa.etf.rpr.exceptions;

public class filmoviException extends Exception{
    public filmoviException(String msg, Exception reason)
    {
        super(msg,reason);
    }
    public filmoviException(String msg)
    {
        super(msg);
    }
}
