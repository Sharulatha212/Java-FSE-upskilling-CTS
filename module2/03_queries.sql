USE event_management;

-- 1. User Upcoming Events
SELECT e.event_id, e.title, e.start_date, e.city
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
JOIN Users u ON r.user_id = u.user_id
WHERE e.status = 'upcoming' 
AND e.city = u.city
ORDER BY e.start_date;

-- 2. Top Rated Events
SELECT e.event_id, e.title, AVG(f.rating) as avg_rating, COUNT(f.feedback_id) as feedback_count
FROM Events e
JOIN Feedback f ON e.event_id = f.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(f.feedback_id) >= 10
ORDER BY avg_rating DESC;

-- 3. Inactive Users
SELECT u.*
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id 
AND r.registration_date >= DATE_SUB(CURDATE(), INTERVAL 90 DAY)
WHERE r.registration_id IS NULL;

-- 4. Peak Session Hours
SELECT e.event_id, e.title, COUNT(*) as peak_sessions
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
WHERE TIME(s.start_time) BETWEEN '10:00:00' AND '12:00:00'
GROUP BY e.event_id, e.title;

-- 5. Most Active Cities
SELECT u.city, COUNT(DISTINCT r.user_id) as user_count
FROM Users u
JOIN Registrations r ON u.user_id = r.user_id
GROUP BY u.city
ORDER BY user_count DESC
LIMIT 5;

-- Additional queries will be added in subsequent files to maintain readability 