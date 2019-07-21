package util.event;

/**
 * Created by TangHaiyang on 2019/7/21.
 */
public class TestEvent {
    private final int message;
    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:"+message);
    }
    public int getMessage() {
        return message;
    }
}
