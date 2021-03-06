package by.it.kashayed.jd03_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class C_Init {


    static final String DBURL = "jdbc:mysql://127.0.0.1:2016/";
    static final String DBUSER = "root";
    static final String PASS = "";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e);
        }
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DBURL, DBUSER, PASS);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP SCHEMA IF EXISTS `kashayed` ;");
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS `kashayed` DEFAULT CHARACTER SET utf8 ;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `kashayed`.`Owners` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Owner` VARCHAR(100) NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `kashayed`.`Owner` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Login` VARCHAR(45) NULL,\n" +
                    "  `email` VARCHAR(100) NULL,\n" +
                    "  `Password` VARCHAR(100) NULL,\n" +
                    "  `Name` VARCHAR(100) NULL,\n" +
                    "  `Owners_id` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  INDEX `fk_Owner_Owners_idx` (`Owners_id` ASC),\n" +
                    "  CONSTRAINT `fk_Owner_Owners`\n" +
                    "    FOREIGN KEY (`Owners_id`)\n" +
                    "    REFERENCES `kashayed`.`Owners` (`id`)\n" +
                    "    ON DELETE RESTRICT\n" +
                    "    ON UPDATE RESTRICT)\n" +
                    "ENGINE = InnoDB;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `kashayed`.`Cars` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Model` VARCHAR(45) NULL,\n" +
                    "  `Year` INT NULL,\n" +
                    "  `Price` INT NULL,\n" +
                    "  `Owner_id` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  INDEX `fk_Cars_Owner1_idx` (`Owner_id` ASC),\n" +
                    "  CONSTRAINT `fk_Cars_Owner1`\n" +
                    "    FOREIGN KEY (`Owner_id`)\n" +
                    "    REFERENCES `kashayed`.`Owner` (`id`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB;");

            statement.executeUpdate("INSERT INTO `kashayed`.`Owners` (`id`, `Owner`) VALUES (DEFAULT, 'admin');\n");
            statement.executeUpdate("INSERT INTO `kashayed`.`Owners` (`id`, `Owner`) VALUES (DEFAULT, 'user');\n");
            statement.executeUpdate("INSERT INTO `kashayed`.`Owners` (`id`, `Owner`) VALUES (DEFAULT, 'guest');\n");
            statement.executeUpdate("INSERT INTO `kashayed`.`Owner` (`id`, `Login`, `email`, `Password`, `Name`, `Owners_id`) VALUES (DEFAULT, 'Admin', 'admin@it.by', 'adminpass', 'ADMIN', 1);\n");
            statement.executeUpdate("INSERT INTO `kashayed`.`Owner` (`id`, `Login`, `email`, `Password`, `Name`, `Owners_id`) VALUES (DEFAULT, 'User', 'user@it.by', 'userpass', 'USER', 2);\n");
            statement.executeUpdate("INSERT INTO `kashayed`.`Cars` (`id`, `Model`, `Year`, `Price`, `Owner_id`) VALUES (DEFAULT, 'Pontiac', 1965, 90000, 2);\n");
            statement.executeUpdate("INSERT INTO `kashayed`.`Cars` (`id`, `Model`, `Year`, `Price`, `Owner_id`) VALUES (DEFAULT, 'Nissan', 2016, 60000, 2);\n");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
