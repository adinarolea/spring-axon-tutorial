package com.tutorials.axon.tutorials.events;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;
@Value
@Builder
public class CreatedEvent {

    UUID id;
}
