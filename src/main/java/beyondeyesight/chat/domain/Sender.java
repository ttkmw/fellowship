package beyondeyesight.chat.domain;

public class Sender {
    private final Id id;

    private Sender(Id id) {
        this.id = id;
    }

    static Sender of(String id) {
        return new Sender(Id.of(id));
    }
}
