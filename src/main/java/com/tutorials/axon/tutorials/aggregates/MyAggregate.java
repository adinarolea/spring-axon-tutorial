package com.tutorials.axon.tutorials.aggregates;

import com.tutorials.axon.tutorials.comands.ConfirmCommand;
import com.tutorials.axon.tutorials.comands.CreateCommand;
import com.tutorials.axon.tutorials.events.ConfirmedEvent;
import com.tutorials.axon.tutorials.events.CreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class MyAggregate {

    @AggregateIdentifier
    UUID id;
    boolean confirmed;
    @CommandHandler
    public MyAggregate(CreateCommand command) {
        AggregateLifecycle.apply(CreatedEvent.builder()
                .id(command.getId())
                .build());
    }

    @EventSourcingHandler
    public void onEvent(CreatedEvent createdEvent) {
        this.id = createdEvent.getId();
        confirmed = false;
    }

    @CommandHandler
    public void onCommand(ConfirmCommand command) {
        AggregateLifecycle.apply(CreateCommand.builder()
                .id(command.getId())
                .build());
    }

    @EventSourcingHandler
    public void onEvent(ConfirmedEvent confirmedEvent) {
        confirmed = true;
    }
}
