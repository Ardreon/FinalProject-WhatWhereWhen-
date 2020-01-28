DROP SCHEMA IF EXISTS `system` ;

CREATE SCHEMA IF NOT EXISTS `system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `system` ;

CREATE TABLE IF NOT EXISTS `system`.`configuration` (
                                                        `configuration_id` INT NOT NULL,
                                                        `time` TIME NULL DEFAULT NULL,
                                                        `players_count` INT NULL DEFAULT NULL,
                                                        `prompt_type` TEXT NOT NULL,
                                                        `question_count` INT NULL DEFAULT NULL,
                                                        PRIMARY KEY (`configuration_id`));

-- -----------------------------------------------------
-- Table `system`.`open_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `system`.`open_question` (
                                                        `question_id` INT NOT NULL,
                                                        `question` TEXT NULL DEFAULT NULL,
                                                        `answer` TEXT NULL DEFAULT NULL,
                                                        `prtompt` TEXT NULL DEFAULT NULL,
                                                        PRIMARY KEY (`question_id`));

-- -----------------------------------------------------
-- Table `system`.`variant_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `system`.`variant_question` (
                                                           `question_id` INT NOT NULL,
                                                           `question` TEXT NOT NULL,
                                                           `right_answer` TEXT NOT NULL,
                                                           `right_answer_procent` DOUBLE NOT NULL,
                                                           `wrong_answer1` TEXT NOT NULL,
                                                           `wrong_answer1_procent` DOUBLE NOT NULL,
                                                           `wrong_answer2` TEXT NOT NULL,
                                                           `wrong_answer2_procent` DOUBLE NOT NULL,
                                                           `wrong_answer3` TEXT NOT NULL,
                                                           `wrong_answer3_procent` DOUBLE NOT NULL,
                                                           PRIMARY KEY (`question_id`));



-- -----------------------------------------------------
-- Table `system`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `system`.`user` (
                                               `id` INT NOT NULL,
                                               `name` TEXT NOT NULL,
                                               `email` TEXT NOT NULL,
                                               `password` TEXT NOT NULL,
                                               PRIMARY KEY (`id`));



-- -----------------------------------------------------
-- Table `system`.`lobbi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `system`.`lobbi` (
                                                `lobbi_id` INT NOT NULL,
                                                `configuration_id` INT NOT NULL,
                                                `user_id` INT NOT NULL,
                                                `question_id` INT NOT NULL,
                                                PRIMARY KEY (`lobbi_id`),
                                                CONSTRAINT `ConfigurationBound`
                                                    FOREIGN KEY (`configuration_id`)
                                                        REFERENCES `system`.`configuration` (`configuration_id`),
                                                CONSTRAINT `OpenQuestionBound`
                                                    FOREIGN KEY (`question_id`)
                                                        REFERENCES `system`.`open_question` (`question_id`),
                                                CONSTRAINT `UserBound`
                                                    FOREIGN KEY (`user_id`)
                                                        REFERENCES `system`.`user` (`id`),
                                                CONSTRAINT `VariantQuestionBound`
                                                    FOREIGN KEY (`question_id`)
                                                        REFERENCES `system`.`variant_question` (`question_id`))
