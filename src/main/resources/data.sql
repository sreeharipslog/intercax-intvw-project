-- Robot functions
INSERT INTO robot_function (id, code, description) VALUES ('1', 'LS', 'Light Sensing');
INSERT INTO robot_function (id, code, description) VALUES ('2', 'SS', 'Sound Sensing');
INSERT INTO robot_function (id, code, description) VALUES ('3', 'TS', 'Temperature Sensing');
INSERT INTO robot_function (id, code, description) VALUES ('4', 'PS', 'Pressure Sensing');
INSERT INTO robot_function (id, code, description) VALUES ('5', 'DF', 'Mobility Degrees of freedom');

-- Robot state
INSERT INTO robot_state (id, code, description) VALUES ('1', 'AWAITED', 'In production');
INSERT INTO robot_state (id, code, description) VALUES ('2', 'READY', 'Tested and Ready to ship');
INSERT INTO robot_state (id, code, description) VALUES ('3', 'DEPLOYED', 'Deployed to field');
INSERT INTO robot_state (id, code, description) VALUES ('4', 'END-OF-LIFE', 'Damaged beyond repair');