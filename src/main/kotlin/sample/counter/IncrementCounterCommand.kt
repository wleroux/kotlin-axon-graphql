package sample.counter

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class IncrementCounterCommand(
        @TargetAggregateIdentifier val counterId: CounterId
)
