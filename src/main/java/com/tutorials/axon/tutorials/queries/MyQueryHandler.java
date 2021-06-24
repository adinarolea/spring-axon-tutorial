package com.tutorials.axon.tutorials.queries;

import com.tutorials.axon.tutorials.events.CreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
@Service
@ProcessingGroup("events")
public class MyQueryHandler {
    List<UUID> ids = new ArrayList<>();

    //multiple handlers for the same event
    @EventSourcingHandler
    public void onEvent(CreatedEvent createdEvent) {
        ids.add(createdEvent.getId());
    }
    @QueryHandler
    public List<UUID> handle(FindAllIdsQuery query) {
        return Collections.unmodifiableList(ids);
    }
}
