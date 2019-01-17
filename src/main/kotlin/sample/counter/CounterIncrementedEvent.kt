package sample.counter

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CounterIncrementedEvent(
        @TargetAggregateIdentifier val counterId: CounterId
)