DROP SCHEMA IF EXISTS `system` ;

CREATE TABLE IF NOT EXISTS `system`.`open_question` (
     `questionId` INT NOT NULL AUTO_INCREMENT,
     `question` TINYTEXT NULL DEFAULT NULL,
     `answer` TINYTEXT NULL DEFAULT NULL,
     `prompt` TINYTEXT NULL DEFAULT NULL,
      PRIMARY KEY (`questionId`));

CREATE TABLE IF NOT EXISTS `system`.`prompt_type` (
     `promptId` INT NOT NULL AUTO_INCREMENT,
     `prompt` VARCHAR(8) NULL DEFAULT NULL,
     PRIMARY KEY (`promptId`));

CREATE TABLE IF NOT EXISTS `system`.`user` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `name` VARCHAR(45) NOT NULL,
     `email` VARCHAR(45) NOT NULL,
     `password` VARCHAR(45) NOT NULL,
     `score` TINYINT NULL DEFAULT NULL,
     PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `system`.`variant_question` (
     `questionId` INT NOT NULL AUTO_INCREMENT,
     `question` TINYTEXT NOT NULL,
     `rightAnswer` TINYTEXT NOT NULL,
     `rightAnswerProcent` DOUBLE NOT NULL,
     `wrongAnswer1` TINYTEXT NOT NULL,
     `wrongAnswer1Procent` DOUBLE NOT NULL,
     `wrongAnswer2` TINYTEXT NOT NULL,
     `wrongAnswer2Procent` DOUBLE NOT NULL,
     `wrongAnswer3` TINYTEXT NOT NULL,
     `wrongAnswer3Procent` DOUBLE NOT NULL,
     PRIMARY KEY (`questionId`));

CREATE TABLE IF NOT EXISTS `system`.`game` (
     `gameId` INT NOT NULL AUTO_INCREMENT,
     `time` TIMESTAMP NULL DEFAULT NULL,
     `openQuestionCount` VARCHAR(3) NULL DEFAULT NULL,
     `variantQuestionCount` VARCHAR(3) NULL DEFAULT NULL,
     `promptTypeId` INT NULL DEFAULT NULL,
     `userId` INT NULL DEFAULT NULL,
     `openQuestionId` INT NULL DEFAULT NULL,
     `variantQuestionId` INT NULL DEFAULT NULL,
     PRIMARY KEY (`gameId`),
  INDEX `promptId_idx` (`promptTypeId` ASC) VISIBLE,
  INDEX `userId_idx` (`userId` ASC) VISIBLE,
  INDEX `variantQuestionId_idx` (`variantQuestionId` ASC) VISIBLE,
  INDEX `openQuestionId_idx` (`openQuestionId` ASC) VISIBLE,
  CONSTRAINT `openQuestionId`
    FOREIGN KEY (`openQuestionId`)
    REFERENCES `system`.`open_question` (`questionId`),
  CONSTRAINT `promptId`
    FOREIGN KEY (`promptTypeId`)
    REFERENCES `system`.`prompt_type` (`promptId`),
  CONSTRAINT `userId`
    FOREIGN KEY (`userId`)
    REFERENCES `system`.`user` (`id`),
  CONSTRAINT `variantQuestionId`
    FOREIGN KEY (`variantQuestionId`)
    REFERENCES `system`.`variant_question` (`questionId`))