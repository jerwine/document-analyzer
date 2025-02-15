use DOCUMENTS_SCHEMA;

/* sample team */
INSERT INTO teams(team_name) VALUES ('default team');

/* sample users */
INSERT INTO users(email, date_added) VALUES ('John.Doe@mockaddress.fake', TIMESTAMP '2024-10-10 10:00:00');
INSERT INTO user_teams(user_id, team_id) VALUES ((SELECT id from users WHERE email='John.Doe@mockaddress.fake'),(SELECT id from teams WHERE team_name='default team'));
INSERT INTO users(email, date_added) VALUES ('Jack.Smith@mockaddress.fake', TIMESTAMP '2024-10-10 10:00:00');
INSERT INTO user_teams(user_id, team_id) VALUES ((SELECT id from users WHERE email='Jack.Smith@mockaddress.fake'),(SELECT id from teams WHERE team_name='default team'));
INSERT INTO users(email, date_added) VALUES ('Ben.Carter@mockaddress.fake', TIMESTAMP '2024-10-10 10:00:00');
INSERT INTO user_teams(user_id, team_id) VALUES ((SELECT id from users WHERE email='Ben.Carter@mockaddress.fake'),(SELECT id from teams WHERE team_name='default team'));

/* sample documents */
INSERT INTO documents(name, body, word_count, uploaded_at, user_id) VALUES (
    'sample contract',
    'This Agreement is made on this day between Acme Corp, hereinafter referred to as "Provider," and John Doe, hereinafter referred to as "Client." The Provider agrees to deliver software development services, including but not limited to backend API development, testing, and deployment. The Client agrees to compensate the Provider as per the agreed pricing schedule. This Agreement shall remain in effect for a period of six (6) months unless terminated earlier by mutual written consent. Any disputes arising from this contract shall be resolved in accordance with the laws of [Your Jurisdiction].',
    92,
    TIMESTAMP '2024-11-11 11:00:00',
    (SELECT id from users WHERE email='John.Doe@mockaddress.fake'));

INSERT INTO documents(name, body, word_count, uploaded_at, user_id) VALUES (
    'sample agreement',
    'This Consulting Agreement ("Agreement") is entered into by and between ByteTech Solutions ("Consultant") and Alice Johnson ("Client"). The Consultant agrees to provide IT consulting services, including system analysis and performance optimization. The Client shall make payments according to the terms specified in the attached schedule. This Agreement shall remain in effect for a period of three (3) months unless terminated by either party with a written notice of 15 days. Any disagreements arising from this Agreement will be resolved through mediation before any legal action is pursued.',
    87,
    TIMESTAMP '2024-12-12 12:00:00',
    (SELECT id from users WHERE email='Jack.Smith@mockaddress.fake'));