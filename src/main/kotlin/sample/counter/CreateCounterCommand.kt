package sample.counter

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateCounterCommand(
        @TargetAggregateIdentifier val counterId: CounterId
)
