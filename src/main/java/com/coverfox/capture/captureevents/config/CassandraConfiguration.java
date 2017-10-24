package com.coverfox.capture.captureevents.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

import com.coverfox.capture.captureevents.CaptureActivitiesConstant;

@Configuration
@EnableReactiveCassandraRepositories
public class CassandraConfiguration extends AbstractReactiveCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return CaptureActivitiesConstant.KEYSPACE_NAME;
    }
    
    @Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.RECREATE;
	}
    
}
