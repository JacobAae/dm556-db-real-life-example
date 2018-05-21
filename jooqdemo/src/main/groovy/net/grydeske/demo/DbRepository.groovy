package net.grydeske.demo

import groovy.util.logging.Slf4j
import org.jooq.impl.DSL
import ratpack.exec.Blocking
import ratpack.exec.Promise

import javax.sql.DataSource
import org.jooq.DSLContext
import org.jooq.SQLDialect

@Slf4j
class DbRepository {

    private final DSLContext database

    DbRepository(DataSource dataSource) {
        this.database = DSL.using(dataSource, SQLDialect.POSTGRES)
    }

    Promise<String> accessDb() {
        Blocking.get {
            try {
                database.selectOne().queryTimeout(100).fetch()
                "Here we can return results"
            } catch (Exception e) {
                log.error("Db access failed")
                throw e
            }
        }
    }


}
