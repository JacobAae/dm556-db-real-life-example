import net.grydeske.demo.DbModule
import net.grydeske.demo.DbRepository
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.hikari.HikariModule

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
  bindings {
    module MarkupTemplateModule
    module(HikariModule) { config ->
      config.dataSourceClassName = 'org.postgresql.ds.PGSimpleDataSource'

      config.addDataSourceProperty("serverName", "localhost" )
      config.addDataSourceProperty("databaseName", "demodb" )
      config.addDataSourceProperty("portNumber", "5435" )
      config.addDataSourceProperty("user", "demo")
      config.addDataSourceProperty("password", "demopw")

      config.setMaximumPoolSize(10)
      config.setMinimumIdle(30000) // mill
      config.setIdleTimeout(1) // minutes
      config.setConnectionTimeout(1500) // mill
    }
    module(DbModule)

  }

  handlers {
    get { DbRepository repository ->
      repository.accessDb().then { String dbResult ->
        println dbResult
        render groovyMarkupTemplate("index.gtpl", result: dbResult)
      }
    }

    files { dir "public" }
  }
}
