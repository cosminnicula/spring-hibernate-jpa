Hibernate
* Simple CRUD

* EntityManager

* TypedQueries

* Named queries

* Native queries

* JPQL

* JPQL joins (inner, left, outer)

* CriteriaQuery

* JPA & Hibernate Annotations

* Relationships: one-to-one, one-to-many, many-to-many

* Transaction, Persistence Context

* Inheritance Hierarchies: single table, table per class, joined, mapped superclass

* Transactions
  * Database isolation:
  
    * Dirty Read (another transaction reads the modified values before a given transaction is commited)
      Transaction 1: transfer 50 from A to B
      Transaction 2: transfer 100 from A to C
      --------------------------------------------------------------------------------------------------------------
      |                        | Description                      | A's account   | B's account   | C's account    |
      |                        |                                  | 200           | 300           | 500            |
      --------------------------------------------------------------------------------------------------------------
      | Transaction 1 Step 1   | Deduct A's account by 50         | 150           |               |                |
      --------------------------------------------------------------------------------------------------------------
      | Transaction 2 Step 1   | Deduct A's account by 100        | 50            |                |               |
      --------------------------------------------------------------------------------------------------------------
      | Transaction 1 Step 2   | Deposit 50 into B's account      |               | 350            |               |
      --------------------------------------------------------------------------------------------------------------
      | Transaction 2 Step 2   | Deposit 100 into C's account     |               |                | 600           |
      --------------------------------------------------------------------------------------------------------------
      | No failing transactions                                   | 50            | 350            | 600           |
      --------------------------------------------------------------------------------------------------------------
      | Transaction 1 Step 2 fails                                | 200           | 300           | 600 -> problem |
      --------------------------------------------------------------------------------------------------------------
      Assumption: Transaction 1 Step 2 fails, so Transaction 1 is rolled back.
      The balance in account A before Transaction 1 started was 200, so the balance in account A will remain 200
      The balance in account B before Transaction 1 started was 300, so the balance in account B will remain 300
      Assumption: Transaction 2 succeded, so Transaction 2 is persisted.
      The balance in account C will remain 600.
  
      Dirty read is when Transaction 1 Step 2 reads value of A's account before the transaction is commited
    
    * Non-Repeatable Read (when reading the same column twice within a transaction, different values might be returned)
      ----------------------------------------------------------------------        ---------------------------------
      | Transaction and step   | Description                               |        | id | name            | age    |
      ----------------------------------------------------------------------        ---------------------------------
      | Transaction 1 Step 1   | SELECT age FROM person where id = 1       |        | 1  | person 1        | 40     |
      ----------------------------------------------------------------------        ---------------------------------
      | Transaction 2 Step 1   | UPDATE person SET age = 50 WHERE id = 1   |        | 2  | person 2        | 10     |
      ----------------------------------------------------------------------        ---------------------------------
      | Transaction 1 Step 2   | SELECT age FROM person where id = 1       |        | 3  | person 3        | 25     |
      ----------------------------------------------------------------------        ---------------------------------
      
      Non-Repeatable Read is when Transaction 1 Step 2 returns age 50
    
    * Phantom Read (when different number of rows are returned in two different points of the transaction)
      -------------------------------------------------------------------------------        ---------------------------------
      | Transaction and step   | Description                                        |        | id | name            | age    |
      -------------------------------------------------------------------------------        ---------------------------------
      | Transaction 1 Step 1   | SELECT * FROM person where age BETWEEN 1 AND 100   |        | 1  | person 1        | 40     |
      -------------------------------------------------------------------------------        ---------------------------------
      | Transaction 2 Step 1   | INSERT INTO person VALUES(4, 'person 4', 30)       |        | 2  | person 2        | 10     |
      -------------------------------------------------------------------------------        ---------------------------------
      | Transaction 1 Step 2   | SELECT * FROM person where age BETWEEN 1 AND 100   |        | 3  | person 3        | 25     |
      -------------------------------------------------------------------------------        ---------------------------------
                                                                                             | 4  | person 4        | 30     |
                                                                                             ---------------------------------
    
      Phantom Read is when Transaction 1 Step 2 returns 4 rows, instead of 3
      
  * Isolation levels:

    -----------------------------------------------------------------------------
    |                        | Dirty Read  | Non-Repeatable Read | Phantom Read |
    -----------------------------------------------------------------------------
    | Read Uncommitted       | Possible    | Possible            | Possible     |
    -----------------------------------------------------------------------------
    | Read Committed         | OK          | Possible            | Possible     |
    -----------------------------------------------------------------------------
    | Repeatable Read        | OK          | OK                  | Possible     |
    -----------------------------------------------------------------------------
    | Serializable           | OK          | OK                  | OK           |
    -----------------------------------------------------------------------------

    Read Committed (most used)
     - Dirty Read OK: data that is changed during a transaction will be available only when the transaction is committed

    Repeatable Read
     - Non-Repeatable Read OK: when data is read during a transaction, that specific row will be locked. only when transaction completes execution, the lock will be removed, which means that Transaction 2 Step 1 will be deferred until the end of transaction

    Serializable (lowest possible performance - e.g. id one transaction does SELECT *, all the other transactions running in parallel will wait for the current transaction to finish)
     - Phantom Read OK: when data is read during a transaction, the rows queried by a condition (e.g. age BETWEEN 1 AND 100) are locked - also called a table lock. create / update / delete operations will be deferred until the table lock is removed

* JPA @Transactional vs Hibernate @Transactional
  - JPA: manages transactions from a single database 
  - Spring: manages transactions from multiple databases and possible other systems like ActiveMQ

* JPA type of queries: 
  - JPQL Query (not type-safe)
  - JPQL TypedQuery (type-safe)
  - JPQL NamedQuery (not type-safe)
  - NativeQuery using EntityManager (not type-safe)
  - CriteriaQuery (either string based attributes -> not type-safe, or with JPA Metamodel -> type-safe)
or
* QueryDSL -> type safe
 - Q-types automatically generated from JPA entities 
 - Q-types manually written

* Spring Data JPA

* Spring Data JPA REST

* Hibernate cache (first level - cache per transaction, second level - cache per multiple transactions - local / distributed)

* JPA Entity Lifecycle Events

* For QueryDSL, run maven compile to generate Q entities; mark ./spring-jpa-querydsl/target/generated-sources as Source folder in IDE

Credits to Udemy - Master Hibernate and JPA with Spring Boot

Useful resources:
- http://jpwh.org/
- https://github.com/AnghelLeonard/Hibernate-SpringBoot