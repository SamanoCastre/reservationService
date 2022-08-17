use reservation_bdd;

--
-- Base de données : `reservation_bdd`
--

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `reservation_id` int NOT NULL AUTO_INCREMENT,
  `date_annulation` datetime DEFAULT NULL,
  `date_creation` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `hopital_id` int NOT NULL,
  `intervenant` varchar(255) DEFAULT NULL,
  `specialite_id` int NOT NULL,
  PRIMARY KEY (`reservation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`reservation_id`, `date_annulation`, `date_creation`, `date_fin`, `hopital_id`, `intervenant`, `specialite_id`) VALUES
(21, NULL, '2022-08-17 16:09:50', NULL, 2, 'test', 2),
(20, NULL, '2022-08-14 22:12:19', NULL, 3, 'Richard', 9),
(19, NULL, '2022-08-13 01:55:18', NULL, 3, 'test', 4),
(18, NULL, '2022-08-13 01:22:23', NULL, 1, 'TEST', 1);
COMMIT;

