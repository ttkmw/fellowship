package beyondeyesight.chat.domain;

public class Id {
    private final String id;

    private Id(String id) {
        this.id = id;
    }

    static Id of(String id) {
        return new Id(id);
    }
}
