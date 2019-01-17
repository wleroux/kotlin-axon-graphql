package sample

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class VersionQueryResolver: GraphQLQueryResolver {
    fun version() = "0.1.0"
}