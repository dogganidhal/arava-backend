

-- INSERT DEFAULT USER ROLES

INSERT INTO role (
                  "id", "created", "disabled", "updated", "name"
) VALUES (
          'd9199aec-712a-4eac-b965-2c7096f9f123', '2020-01-21', 'f', '2020-01-21', 'ADMIN'
);
INSERT INTO role (
                  "id", "created", "disabled", "updated", "name"
) VALUES (
          'd5694e36-1024-48ef-96f8-0a452054934e', '2020-01-21', 'f', '2020-01-21', 'USER'
);

-- INSERT DEFAULT ISLANDS

INSERT INTO island (id, name) VALUES ('59b830f3-ad37-41dd-bac9-93eddc0e39e0', 'Maupiti');
INSERT INTO island (id, name) VALUES ('b8407091-2517-49fa-b3d7-93c098460b36', 'Huahine');
INSERT INTO island (id, name) VALUES ('ac2391de-44b8-475d-b2fa-5b2afa7cc598', 'Fakarava');
INSERT INTO island (id, name) VALUES ('5404491f-6eda-49da-848b-f582db00f6d7', 'Tahiti');
INSERT INTO island (id, name) VALUES ('8a57b307-99c7-4682-9614-2836d13ff67c', 'Moorea');
INSERT INTO island (id, name) VALUES ('d2c54228-86d5-4aa8-8853-903d980a6ff9', 'Rangiroa');
INSERT INTO island (id, name) VALUES ('d904bacf-a9a2-47c2-8f2e-1bee8218a111', 'Raiatea');
INSERT INTO island (id, name) VALUES ('81f9fefb-3552-4985-89f2-07f51c9a28b8', 'Bora Bora');
INSERT INTO island (id, name) VALUES ('50b0025c-1e19-442d-b90a-6737286f7f9f', 'Hiva Oa');
INSERT INTO island (id, name) VALUES ('4af8c3fa-deb7-4217-9375-8837db632768', 'Nuku Hiva');
