package messages;

public abstract class Reply extends Message {

    public ReplyType replyType;

    public static enum ReplyType {

        TYPE1
    }

    @Override
    public void Encode(Byte[] bytes) {
        super.Encode(bytes);

        // encode reply stuff
    }

    @Override
    public void Decode(Byte[] bytes) {
        super.Decode(bytes);

        // decode reply stuff
    }
}
