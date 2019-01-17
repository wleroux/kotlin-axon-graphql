package sample.currentcount

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver
import org.axonframework.queryhandling.QueryGateway
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import sample.counter.CounterId
import java.util.concurrent.CompletableFuture

@Component
class CurrentCountController: GraphQLQueryResolver, GraphQLSubscriptionResolver {

    @Autowired
    lateinit var queryGateway: QueryGateway

    fun counter(counterId: CounterId): CompletableFuture<Int> {
        return queryGateway
                .query(CurrentCountQuery(counterId), Int::class.java)
    }

    fun counterSubscription(counterId: CounterId): Publisher<Int> {
        val subscription = queryGateway.subscriptionQuery(CurrentCountQuery(counterId), Int::class.java, Int::class.java)
        return subscription.initialResult().concatWith(subscription.updates())
    }
}