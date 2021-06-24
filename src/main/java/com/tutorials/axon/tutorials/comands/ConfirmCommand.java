package com.tutorials.axon.tutorials.comands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class ConfirmCommand {

    @TargetAggregateIdentifier
    UUID id;
}
