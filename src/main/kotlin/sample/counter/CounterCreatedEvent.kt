package sample.counter

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CounterCreatedEvent(
        @TargetAggregateIdentifier val counterId: CounterId
)