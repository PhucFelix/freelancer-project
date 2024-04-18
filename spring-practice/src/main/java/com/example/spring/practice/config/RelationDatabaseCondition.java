package com.example.spring.practice.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class RelationDatabaseCondition implements Condition {
//    #
//    This method
//    returns true if
//    the MySQL
//    database driver

//    class is present in the classpath

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return isMySQlDatabase();
    }
//    #Evaluates if
//    the MySQL
//    driver
//
//    class is present in the classpath.
//    Availability of
//    the
//
//    class indicates that the MySQL database is being used in the application.We’
//    ve used
//    MySQL Driver for
//    demonstration purpose
//    only .

    private boolean isMySQlDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }


}

