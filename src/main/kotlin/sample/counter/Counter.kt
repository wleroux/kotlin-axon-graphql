package sample.counter

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.*
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.stereotype.Component

@Component
@Aggregate
class Counter {

    @AggregateIdentifier
    private lateinit var counterId: CounterId

    @CommandHandler
    constructor(cmd: CreateCounterCommand) {
        apply(CounterCreatedEvent(cmd.counterId))
    }

    @CommandHandler
    fun on(cmd: IncrementCounterCommand) {
        apply(CounterIncrementedEvent(cmd.counterId))
    }

    @EventSourcingHandler
    fun on(evt: CounterCreatedEvent) {
        counterId = evt.counterId
    }
}