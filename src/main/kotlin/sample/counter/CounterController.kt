package sample.counter

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class CounterController: GraphQLMutationResolver {
    @Autowired
    lateinit var commandGateway: CommandGateway

    fun createCounter(counterId: CounterId): CompletableFuture<Boolean> {
        return commandGateway
                .send<CounterId>(CreateCounterCommand(counterId))
                .thenApply { true }
                .exceptionally { false }
    }

    fun incrementCounter(counterId: CounterId): CompletableFuture<Boolean> {
        return commandGateway
                .send<Unit>(IncrementCounterCommand(counterId))
                .thenApply { true }
                .exceptionally { false }
    }
}