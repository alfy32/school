package messages;

public abstract class Request extends Message {

    public RequestType requestType;

    public static enum RequestType {

        TYPE1
    }

    @Override
    public void Encode(Byte[] bytes) {
        super.Encode(bytes);

        // encode request stuff
    }

    @Override
    public void Decode(Byte[] bytes) {
        super.Decode(bytes);

        // decode request stuff
    }
}
