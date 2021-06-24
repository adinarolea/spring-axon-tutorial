package com.tutorials.axon.tutorials.controllers;

import com.tutorials.axon.tutorials.comands.ConfirmCommand;
import com.tutorials.axon.tutorials.comands.CreateCommand;
import com.tutorials.axon.tutorials.queries.FindAllIdsQuery;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
public class TestController {
    CommandGateway commandGateway;
    QueryGateway queryGateway;

    @PostMapping("/commands")
    public CompletableFuture<Void> createCommand() {
        UUID id = UUID.randomUUID();
        return commandGateway.send(new CreateCommand(id))
                .thenCompose(result -> commandGateway.send(new ConfirmCommand(id)));
    }

    @GetMapping("/all")
    public CompletableFuture<List<UUID>> getIds() {
        return queryGateway.query(new FindAllIdsQuery(),
                ResponseTypes.multipleInstancesOf(UUID.class));
    }
}
