package io.swagger.api.impl;

import java.io.IOException;
import java.io.Reader;

import io.swagger.model.mappers.CatalogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Datastore
{
    private static SqlSessionFactory factory;

    private Datastore() {
    }

    static
    {
        org.apache.ibatis.logging.LogFactory.useLog4JLogging();
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        factory = new SqlSessionFactoryBuilder().build(reader);
        try (SqlSession session = factory.openSession()) {
            CatalogMapper mapper = session.getMapper(CatalogMapper.class);
            mapper.createDatastoreObjectsIfAbsent();
            session.commit();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory()
    {
        return factory;
    }

    public static SqlSession openSession() {
        return factory.openSession();
    }
}