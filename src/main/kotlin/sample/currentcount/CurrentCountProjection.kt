package sample.currentcount

import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.axonframework.queryhandling.QueryUpdateEmitter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import sample.counter.CounterCreatedEvent
import sample.counter.CounterId
import sample.counter.CounterIncrementedEvent

@Component
class CurrentCountProjection {

    @Autowired
    lateinit var queryUpdateEmitter: QueryUpdateEmitter

    private val counters: MutableMap<CounterId, Int> = mutableMapOf()

    @EventHandler
    fun on(evt: CounterCreatedEvent) {
        counters[evt.counterId] = 0
        queryUpdateEmitter.emit(CurrentCountQuery::class.java, { query -> query.counterId == evt.counterId}, 0)
    }

    @EventHandler
    fun on(evt: CounterIncrementedEvent) {
        val count = (counters[evt.counterId] ?: 0) + 1
        queryUpdateEmitter.emit(CurrentCountQuery::class.java, { query -> query.counterId == evt.counterId}, count)
        counters[evt.counterId] = count
    }

    @QueryHandler
    fun request(query: CurrentCountQuery): Int {
        return counters[query.counterId] ?: 0
    }
}