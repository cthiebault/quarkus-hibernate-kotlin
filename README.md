# Quarkus 3 / Hibernate inheritance / Kotlin

https://github.com/quarkusio/quarkus/issues/33740

Sample project to prove that Quarkus 3.1.1.Final does not support Hibernate inheritance with Kotlin.

Quarkus 3.1.0.Final works fine if we annotate parent class's fields with `@Access(AccessType.PROPERTY)` 
(see [comment](https://github.com/quarkusio/quarkus/issues/33740#issuecomment-1571799478):

```shell
git checkout quarkus3-fix/access-property
./gradlew clean build --no-build-cache
```

Quarkus 3.1.1.Final fails:

```shell
git checkout quarkus-3.1.1
./gradlew clean build --no-build-cache
```

Error message:

```text
ShapeTest > test() FAILED
    org.hibernate.exception.ConstraintViolationException: could not execute statement [ERROR: null value in column "name" of relation "shape" violates not-null constraint
      Detail: Failing row contains (4db7480d-5875-4c2f-bf0c-3b52704cc0d4, null, null, null, null).] [insert into shape (color,name,bar,foo,id) values (?,?,?,?,?)]
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
        at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:2742)
        at org.hibernate.action.internal.EntityInsertAction.execute(EntityInsertAction.java:102)
        at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:606)
        at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:475)
        at org.hibernate.event.internal.AbstractFlushingEventListener.performExecutions(AbstractFlushingEventListener.java:358)
        at org.hibernate.event.internal.DefaultAutoFlushEventListener.onAutoFlush(DefaultAutoFlushEventListener.java:55)
        at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:127)
        at org.hibernate.internal.SessionImpl.autoFlushIfRequired(SessionImpl.java:1375)
        at org.hibernate.query.sqm.internal.ConcreteSqmSelectQueryPlan.lambda$new$0(ConcreteSqmSelectQueryPlan.java:108)
        at org.hibernate.query.sqm.internal.ConcreteSqmSelectQueryPlan.withCacheableSqmInterpretation(ConcreteSqmSelectQueryPlan.java:303)
        at org.hibernate.query.sqm.internal.ConcreteSqmSelectQueryPlan.performList(ConcreteSqmSelectQueryPlan.java:244)
        at org.hibernate.query.sqm.internal.QuerySqmImpl.doList(QuerySqmImpl.java:518)
        at org.hibernate.query.spi.AbstractSelectionQuery.list(AbstractSelectionQuery.java:367)
        at org.hibernate.query.spi.AbstractSelectionQuery.getSingleResult(AbstractSelectionQuery.java:473)
        at io.quarkus.hibernate.orm.panache.common.runtime.AbstractJpaOperations.count(AbstractJpaOperations.java:323)
        at org.acme.ShapeRepository.count(ShapeRepository.kt)
        at org.acme.ShapeRepository_ClientProxy.count(Unknown Source)
        at org.acme.ShapeTest.test(ShapeTest.kt:43)

        Caused by:
        org.postgresql.util.PSQLException: ERROR: null value in column "name" of relation "shape" violates not-null constraint
          Detail: Failing row contains (4db7480d-5875-4c2f-bf0c-3b52704cc0d4, null, null, null, null).
            at app//org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse(QueryExecutorImpl.java:2713)
            at app//org.postgresql.core.v3.QueryExecutorImpl.processResults(QueryExecutorImpl.java:2401)
            at app//org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:368)
            at app//org.postgresql.jdbc.PgStatement.executeInternal(PgStatement.java:498)
            at app//org.postgresql.jdbc.PgStatement.execute(PgStatement.java:415)
            at app//org.postgresql.jdbc.PgPreparedStatement.executeWithFlags(PgPreparedStatement.java:190)
            at app//org.postgresql.jdbc.PgPreparedStatement.executeUpdate(PgPreparedStatement.java:152)
            at io.agroal.pool.wrapper.PreparedStatementWrapper.executeUpdate(PreparedStatementWrapper.java:88)
            at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:275)
            ... 26 more
```