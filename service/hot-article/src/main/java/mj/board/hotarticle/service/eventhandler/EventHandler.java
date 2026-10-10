package mj.board.hotarticle.service.eventhandler;

import mj.board.common.event.Event;
import mj.board.common.event.EventPayload;

public interface EventHandler <T extends EventPayload>{
    void handle(Event<T> event);
    boolean supports(Event<T> event);
    Long findArticleId(Event<T> event);
}
