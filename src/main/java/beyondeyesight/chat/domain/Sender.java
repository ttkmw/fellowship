package beyondeyesight.chat.domain;

import java.util.Objects;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType("sender")
public class Sender {
    private final UUID id;

    private Sender(UUID id) {
        this.id = id;
    }

    public static Sender of(UUID id) {
        return new Sender(id);
    }
}
