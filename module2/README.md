# Event Management System SQL Exercises

This module contains SQL exercises for an event management system using MySQL.

## File Structure

1. `01_schema.sql` - Database and table creation scripts
2. `02_sample_data.sql` - Sample data insertion scripts
3. `03_queries.sql` - First set of SQL queries (1-5)
4. `04_queries_part2.sql` - Second set of SQL queries (6-10)

## How to Use

1. Run the schema file first to create the database and tables:

```sql
mysql -u your_username -p < 01_schema.sql
```

2. Insert the sample data:

```sql
mysql -u your_username -p < 02_sample_data.sql
```

3. Execute the queries:

```sql
mysql -u your_username -p < 03_queries.sql
mysql -u your_username -p < 04_queries_part2.sql
```

## Database Schema

The database consists of six main tables:

- Users
- Events
- Sessions
- Registrations
- Feedback
- Resources

Each table is properly linked with foreign keys to maintain referential integrity.

## Query Categories

The exercises cover various aspects of SQL including:

- Basic SELECT queries
- JOINs (INNER, LEFT)
- Aggregations (COUNT, AVG)
- GROUP BY with HAVING
- Subqueries
- CASE statements
- Date/Time functions
