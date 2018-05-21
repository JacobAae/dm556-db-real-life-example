package net.grydeske.demo

import com.google.inject.AbstractModule
import com.google.inject.Provides

import javax.inject.Singleton
import javax.sql.DataSource

class DbModule  extends AbstractModule {

    @Override
    protected void configure() { }

    @Provides
    @Singleton
    DbRepository reportRepository(DataSource dataSource) {
        new DbRepository(dataSource)
    }
}
