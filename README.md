# Quarkus 3 / Hibernate inheritance / Kotlin 

https://github.com/quarkusio/quarkus/issues/33740

Sample project to prove that Quarkus 3.0.4.Final does not support Hibernate inheritance with Kotlin.

Quarkus 2.16.7.Final works fine:
```shell
git checkout quarkus2
./gradlew clean build --no-build-cache
```

Quarkus  3.0.4.Final fails:
```shell
git checkout quarkus3
./gradlew clean build --no-build-cache
```

Error message:
```text
ShapeTest > test() FAILED
    org.hibernate.exception.ConstraintViolationException: could not execute statement [ERROR: null value in column "name" of relation "shape" violates not-null constraint
      Detail: Failing row contains (b79524af-dbfb-4f0e-a252-91f9431b6a08, null, null, null, null).] [insert into shape (color,name,bar,foo,id) values (?,?,?,?,?)]
        at org.hibernate.exception.internal.SQLStateConversionDelegate.convert(SQLStateConversionDelegate.java:95)
        at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:56)
        at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:108)
        at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:278)
        at org.hibernate.engine.jdbc.mutation.internal.AbstractMutationExecutor.performNonBatchedMutation(AbstractMutationExecutor.java:108)
        at org.hibernate.engine.jdbc.mutation.internal.MutationExecutorStandard.lambda$performNonBatchedOperations$1(MutationExecutorStandard.java:217)
        at java.base@17.0.7/java.util.TreeMap.forEach(TreeMap.java:1282)
        at org.hibernate.engine.jdbc.mutation.internal.PreparedStatementGroupStandard.forEachStatement(PreparedStatementGroupStandard.java:90)
        at org.hibernate.engine.jdbc.mutation.internal.MutationExecutorStandard.performNonBatchedOperations(MutationExecutorStandard.java:217)
        at org.hibernate.engine.jdbc.mutation.internal.AbstractMutationExecutor.execute(AbstractMutationExecutor.java:53)
        at org.hibernate.persister.entity.mutation.InsertCoordinator.doStaticInserts(InsertCoordinator.java:170)
        at org.hibernate.persister.entity.mutation.InsertCoordinator.coordinateInsert(InsertCoordinator.java:112)
        at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:2656)
        at org.hibernate.action.internal.EntityInsertAction.execute(EntityInsertAction.java:102)
        at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:616)
        at org.hibernate.engine.spi.ActionQueue.lambda$executeActions$1(ActionQueue.java:487)
        at java.base@17.0.7/java.util.LinkedHashMap.forEach(LinkedHashMap.java:721)
        at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:484)
        at org.hibernate.event.internal.AbstractFlushingEventListener.performExecutions(AbstractFlushingEventListener.java:358)
        at org.hibernate.event.internal.DefaultAutoFlushEventListener.onAutoFlush(DefaultAutoFlushEventListener.java:55)
        at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:127)
        at org.hibernate.internal.SessionImpl.autoFlushIfRequired(SessionImpl.java:1375)
        at org.hibernate.query.sqm.internal.ConcreteSqmSelectQueryPlan.lambda$new$0(ConcreteSqmSelectQueryPlan.java:107)
        at org.hibernate.query.sqm.internal.ConcreteSqmSelectQueryPlan.withCacheableSqmInterpretation(ConcreteSqmSelectQueryPlan.java:302)
        at org.hibernate.query.sqm.internal.ConcreteSqmSelectQueryPlan.performList(ConcreteSqmSelectQueryPlan.java:243)
        at org.hibernate.query.sqm.internal.QuerySqmImpl.doList(QuerySqmImpl.java:521)
        at org.hibernate.query.spi.AbstractSelectionQuery.list(AbstractSelectionQuery.java:367)
        at org.hibernate.query.sqm.internal.QuerySqmImpl.list(QuerySqmImpl.java:1084)
        at org.hibernate.query.spi.AbstractSelectionQuery.getSingleResult(AbstractSelectionQuery.java:461)
        at org.hibernate.query.sqm.internal.QuerySqmImpl.getSingleResult(QuerySqmImpl.java:1110)
        at io.quarkus.hibernate.orm.panache.common.runtime.AbstractJpaOperations.count(AbstractJpaOperations.java:323)
        at org.acme.ShapeRepository.count(ShapeRepository.kt)
        at org.acme.ShapeRepository_ClientProxy.count(Unknown Source)
        at org.acme.ShapeTest.test(ShapeTest.kt:43)

        Caused by:
        org.postgresql.util.PSQLException: ERROR: null value in column "name" of relation "shape" violates not-null constraint
          Detail: Failing row contains (b79524af-dbfb-4f0e-a252-91f9431b6a08, null, null, null, null).
            at app//org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse(QueryExecutorImpl.java:2713)
            at app//org.postgresql.core.v3.QueryExecutorImpl.processResults(QueryExecutorImpl.java:2401)
            at app//org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:368)
            at app//org.postgresql.jdbc.PgStatement.executeInternal(PgStatement.java:498)
            at app//org.postgresql.jdbc.PgStatement.execute(PgStatement.java:415)
            at app//org.postgresql.jdbc.PgPreparedStatement.executeWithFlags(PgPreparedStatement.java:190)
            at app//org.postgresql.jdbc.PgPreparedStatement.executeUpdate(PgPreparedStatement.java:152)
            at io.agroal.pool.wrapper.PreparedStatementWrapper.executeUpdate(PreparedStatementWrapper.java:88)
            at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:275)
            ... 30 more
```