INSERT INTO user (id, name, email, password) VALUES (1, "Kira", "kira@gmail.ru", "12345");

INSERT INTO user (id, name, email, password) VALUES (2, "Serk", "seeer@gmail.ru", "2rasas");

INSERT INTO variant_question (question_id, question, right_answer, right_answer_procent, wrong_answer1, wrong_answer1_procent,
                              wrong_answer2, wrong_answer2_procent, wrong_answer3, wrong_answer3_procent)
                               VALUES (11, "The name of our planet is ... ?", "Earth", 66, "Moon", 14, "Mars", 10, "Sun", 10);

INSERT INTO variant_question (question_id, question, right_answer, right_answer_procent, wrong_answer1, wrong_answer1_procent,
                              wrong_answer2, wrong_answer2_procent, wrong_answer3, wrong_answer3_procent)
                               VALUES (11, "The name of Jobs is ... ?", "Steven", 75, "Mike", 15, "Bill", 5, "Snake", 5);

INSERT INTO open_question (question_id, question, answer, prtompt) VALUES (1, "Who discovered the Pi const?", "Eiler", "british scientist");

INSERT INTO open_question (question_id, question, answer, prtompt) VALUES (1, "What?Where?....finish the statement", "When?", "another question word");

INSERT INTO configuration (configuration_id, time, players_count, prompt_type, question_count) VALUES (1, 1, 3, "Time", 10);

