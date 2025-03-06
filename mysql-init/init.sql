USE `crud_java`;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `user_id` char(36) NOT NULL,
                         `full_name` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `password_hash` varchar(255) NOT NULL,
                         `phone_number` varchar(20) NOT NULL,
                         `status` enum('ACTIVE','INACTIVE','BANNED') NOT NULL,
                         `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `email_UNIQUE` (`email`),
                         UNIQUE KEY `phone_number_UNIQUE` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `wallets`;
CREATE TABLE `wallets` (
                           `wallet_id` char(36) NOT NULL,
                           `user_id` char(36) NOT NULL,
                           `balance` decimal(18,2) DEFAULT '0.00',
                           `currency` varchar(10) NOT NULL DEFAULT 'VND',
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`wallet_id`),
                           KEY `fk_wallet_user` (`user_id`),
                           CONSTRAINT `fk_wallet_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `transfers`;
CREATE TABLE `transfers` (
                             `transfer_id` char(36) NOT NULL DEFAULT (uuid()),
                             `sender_wallet` char(36) NOT NULL,
                             `receiver_wallet` char(36) NOT NULL,
                             `amount` decimal(18,2) DEFAULT NULL,
                             `status` enum('PENDING','COMPLETED','FAILED') DEFAULT 'PENDING',
                             `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             `note` text,
                             PRIMARY KEY (`transfer_id`),
                             KEY `fk_sender` (`sender_wallet`),
                             KEY `fk_receiver` (`receiver_wallet`),
                             CONSTRAINT `fk_receiver` FOREIGN KEY (`receiver_wallet`) REFERENCES `wallets` (`wallet_id`) ON DELETE CASCADE,
                             CONSTRAINT `fk_sender` FOREIGN KEY (`sender_wallet`) REFERENCES `wallets` (`wallet_id`) ON DELETE CASCADE,
                             CONSTRAINT `transfers_chk_1` CHECK ((`amount` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
                                `transaction_id` char(36) NOT NULL,
                                `wallet_id` char(36) NOT NULL,
                                `type` enum('DEPOSIT','WITHDRAW','TRANSFER') NOT NULL,
                                `amount` decimal(18,2) NOT NULL,
                                `status` enum('PENDING','COMPLETED','FAILED') NOT NULL,
                                `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`transaction_id`),
                                KEY `fk_transaction_wallet` (`wallet_id`),
                                CONSTRAINT `fk_transaction_wallet` FOREIGN KEY (`wallet_id`) REFERENCES `wallets` (`wallet_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `security_auth`;
CREATE TABLE `security_auth` (
                                 `otp_id` char(36) NOT NULL DEFAULT (uuid()),
                                 `user_id` char(36) NOT NULL,
                                 `otp_code` varchar(6) NOT NULL,
                                 `expired_at` timestamp NOT NULL,
                                 `status` enum('UNUSED','USED','EXPIRED') DEFAULT 'UNUSED',
                                 PRIMARY KEY (`otp_id`),
                                 KEY `fk_user_otp` (`user_id`),
                                 CONSTRAINT `fk_user_otp` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
                        `log_id` char(36) NOT NULL DEFAULT (uuid()),
                        `user_id` char(36) NOT NULL,
                        `action` text NOT NULL,
                        `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`log_id`),
                        KEY `fk_user_log` (`user_id`),
                        CONSTRAINT `fk_user_log` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;