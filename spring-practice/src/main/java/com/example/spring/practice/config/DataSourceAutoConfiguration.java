package com.example.spring.practice.config;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.util.StringUtils;

public class DataSourceAutoConfiguration {
//    @Configuration(proxyBeanMethods = false)
//    @Conditional(EmbeddedDatabaseCondition.class)
//    @ConditionalOnMissingBean({ DataSource.class, XADataSource.class })
//    @Import(EmbeddedDataSourceConfiguration.class)
//    protected static class EmbeddedDatabaseConfiguration {
//
//    }
//
//    @Configuration(proxyBeanMethods = false)
//    @Conditional(PooledDataSourceCondition.class)
//    @ConditionalOnMissingBean({ DataSource.class, XADataSource.class })
//    @Import({ DataSourceConfiguration.Hikari.class, DataSourceConfiguration.Tomcat.class,
//            DataSourceConfiguration.Dbcp2.class, DataSourceConfiguration.OracleUcp.class,
//            DataSourceConfiguration.Generic.class, DataSourceJmxConfiguration.class })
//    protected static class PooledDataSourceConfiguration {
//
//        @Bean
//        @ConditionalOnMissingBean(JdbcConnectionDetails.class)
//        PropertiesJdbcConnectionDetails jdbcConnectionDetails(DataSourceProperties properties) {
//            return new PropertiesJdbcConnectionDetails(properties);
//        }
//
//    }

    /**
     * {@link AnyNestedCondition} that checks that either {@code spring.datasource.type}
     * is set or {@link PooledDataSourceAvailableCondition} applies.
     */
    static class PooledDataSourceCondition extends AnyNestedCondition {

        PooledDataSourceCondition() {
            super(ConfigurationPhase.PARSE_CONFIGURATION);
        }

        @ConditionalOnProperty(prefix = "spring.datasource", name = "type")
        static class ExplicitType {

        }

        @Conditional(PooledDataSourceAvailableCondition.class)
        static class PooledDataSourceAvailable {

        }

    }

    /**
     * {@link Condition} to test if a supported connection pool is available.
     */
    static class PooledDataSourceAvailableCondition extends SpringBootCondition {

        @Override
        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
            ConditionMessage.Builder message = ConditionMessage.forCondition("PooledDataSource");
            if (DataSourceBuilder.findType(context.getClassLoader()) != null) {
                return ConditionOutcome.match(message.foundExactly("supported DataSource"));
            }
            return ConditionOutcome.noMatch(message.didNotFind("supported DataSource").atAll());
        }

    }

    /**
     * {@link Condition} to detect when an embedded {@link DataSource} type can be used.
     * If a pooled {@link DataSource} is available, it will always be preferred to an
     * {@code EmbeddedDatabase}.
     */
    static class EmbeddedDatabaseCondition extends SpringBootCondition {

        private static final String DATASOURCE_URL_PROPERTY = "spring.datasource.url";

        private final SpringBootCondition pooledCondition = new PooledDataSourceCondition();

        @Override
        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
            ConditionMessage.Builder message = ConditionMessage.forCondition("EmbeddedDataSource");
            if (hasDataSourceUrlProperty(context)) {
                return ConditionOutcome.noMatch(message.because(DATASOURCE_URL_PROPERTY + " is set"));
            }
            if (anyMatches(context, metadata, this.pooledCondition)) {
                return ConditionOutcome.noMatch(message.foundExactly("supported pooled data source"));
            }

//            EmbeddedDatabaseType type = EmbeddedDatabaseConnection.get(context.getClassLoader()).getType();
            EmbeddedDatabaseType type = null;
            if (type == null) {
                return ConditionOutcome.noMatch(message.didNotFind("embedded database").atAll());
            }
            return ConditionOutcome.match(message.found("embedded database").items(type));
        }

        private boolean hasDataSourceUrlProperty(ConditionContext context) {
            Environment environment = context.getEnvironment();
            if (environment.containsProperty(DATASOURCE_URL_PROPERTY)) {
                try {
                    return StringUtils.hasText(environment.getProperty(DATASOURCE_URL_PROPERTY));
                }
                catch (IllegalArgumentException ex) {
                    // Ignore unresolvable placeholder errors
                }
            }
            return false;
        }

    }
}
