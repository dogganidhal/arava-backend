-- INSERT DEFAULT LANGUAGES 

INSERT INTO language ("id", "created", "disabled", "updated", "code", "name") VALUES 
    ('zh_Hans', '2020-01-24', 'f', '2020-01-24', 'cn', '中文'),
    ('en', '2020-01-24', 'f', '2020-01-24', 'en', 'English'),
    ('fr', '2020-01-24', 'f', '2020-01-24', 'fr', 'Français') ON CONFLICT DO NOTHING;

-- INSERT DEFAULT USER ROLES

INSERT INTO role (
                  "id", "created", "disabled", "updated", "name"
) VALUES (
          'd9199aec-712a-4eac-b965-2c7096f9f123', '2020-01-21', 'f', '2020-01-21', 'ADMIN'
), (
          'd5694e36-1024-48ef-96f8-0a452054934e', '2020-01-21', 'f', '2020-01-21', 'USER'
) ON CONFLICT DO NOTHING;

-- INSERT DEFAULT APP VERSION CONFIGURATION

INSERT INTO app_version_configuration ("id", "created", "min_version")
VALUES ('dd3d4622-832d-4766-9efb-dba1d95f1ef9', NOW(), 1) ON CONFLICT DO NOTHING ;

-- INSERT DEFAULT ARCHIPELAGOS

INSERT INTO archipelago (
    id, name
) VALUES ('fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4', 'Archipel de la Société'),
         ('49b263b0-0326-496d-bfa1-3a3986797bba', 'Archipel Australes'),
         ('02cbe4a1-fef8-46ec-b89e-30ad801cfd25', 'Archipel des Tuamotu'),
         ('c75f6eeb-0ca7-4d79-911c-fbba6979a1f3', 'Archipel des Marquises'),
         ('32b915b5-6fbd-4ece-b660-acab06391247', 'Archipel des Gambiers') ON CONFLICT DO NOTHING;

-- INSERT DEFAULT ISLANDS

INSERT INTO island (id, name, archipelago_id, latitude, longitude, zoom)
VALUES (
          '59b830f3-ad37-41dd-bac9-93eddc0e39e0',
          'Huahine',
          'fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4',
          -23.3727082,-149.4984394,18
), (
          'b8407091-2517-49fa-b3d7-93c098460b36',
          'Tahaa',
          'fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4',
          -16.6307292,-151.5270121,13
), (
          'ac2391de-44b8-475d-b2fa-5b2afa7cc598',
          'Fakarava',
          'fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4',
          -16.2989906,-145.7326184,11
), (
          '5404491f-6eda-49da-848b-f582db00f6d7',
          'Tahiti',
          'fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4',
          -17.686893,-149.5128859,11
), (
          '8a57b307-99c7-4682-9614-2836d13ff67c',
          'Moorea',
          'fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4',
          -17.5332919,-149.9053552,12
), (
          'd2c54228-86d5-4aa8-8853-903d980a6ff9',
          'Raiatea',
          'fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4',
          -16.8192425,-151.4926665,12
) ON CONFLICT DO NOTHING;

INSERT INTO island (id, name, archipelago_id, latitude, longitude, zoom)
VALUES (
           '37e3d4de-6039-4116-ab9e-a2daef012611',
           'Rurutu',
           '49b263b0-0326-496d-bfa1-3a3986797bba',
           -22.4779831,-151.3666893,14
       ), (
           'be262740-43ef-4f08-afef-20e1eb663ac7',
           'Raivavae',
           '49b263b0-0326-496d-bfa1-3a3986797bba',
           -23.8595525,-147.6805238,13.66
       ), (
           '9c605bae-ca46-4d0c-b3cf-b01f8c674f0f',
           'Rapa',
           '49b263b0-0326-496d-bfa1-3a3986797bba',
           -27.6082255,-144.3780974,13
       ), (
           'd8a27f4d-4148-47b4-a0a8-d4f450874865',
           'Rimatara',
           '49b263b0-0326-496d-bfa1-3a3986797bba',
           -22.6584362,-152.8136029,15
       ), (
           'c3231656-5d96-454f-9654-9ccd7d667842',
           'Avera',
           '49b263b0-0326-496d-bfa1-3a3986797bba',
           -16.7853439,-151.4180472,16
       ) ON CONFLICT DO NOTHING;

INSERT INTO island (id, name, archipelago_id, latitude, longitude, zoom)
VALUES (
           '0b42af3d-ab5f-4edb-8b76-5af7686be36c',
           'Rangiroa',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -15.1202173,-147.8573821,10
       ),
       (
           'b5e6d5cd-accc-4cdc-aa97-a00746db5c34',
           'Fakarava',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -16.2989906,-145.7326184,11
       ),
       (
           '56b9ceed-4505-4b3b-8f1f-f2516d9b02a8',
           'Anaa',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -17.4121632,-145.568079,12
       ),
       (
           '9e7ea1f8-c81f-4131-924f-f9484b54a9ce',
           'Maketa',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -9.0254483,-158.0290615,12.29
       ),
       (
           'a7fb9f91-03bd-479b-a136-5e6895317ad5',
           'Hao',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -18.2459137,-141.0072814,11
       ),
       (
           'd923ea8b-cef5-4757-a446-dead52b69baf',
           'Tikehau',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -15.114511,-148.2470119,15
       ),
       (
           '8fdde036-fabc-4fa8-acc2-8520ce22c1dc',
           'Takume',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -15.7999464,-142.2665214,12
       ),
       (
           'c679fcf2-cb62-46bc-8b1a-4bc8e16e4a92',
           'Manihi',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -14.3977912,-146.0273996,12
       ),
       (
           '3b191b73-f68b-4744-9434-c9b8a0379934',
           'Takaroa',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -14.4509515,-145.0336198,12
       ),
       (
           'bd5dd894-f982-4561-a26b-0e0bbdbc2020',
           'Apataki',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -15.4499788,-140.7695878,15
       ),
       (
           'a24cc1d3-3af5-4660-b004-a2b9ea97ecc1',
           'Makemo',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -16.5934163,-143.9680859,10
       ) ON CONFLICT DO NOTHING;

INSERT INTO island (id, name, archipelago_id, latitude, longitude, zoom)
VALUES (
           'f035fc61-fa44-43df-a3eb-ebd8612b67e0',
           'Nuku Hiva',
           'c75f6eeb-0ca7-4d79-911c-fbba6979a1f3',
           -8.8731591,-140.1989633,12
       ),(
           '2bf6d470-766f-48b8-8782-aab49944ff95',
           'Hiva Oa',
           'c75f6eeb-0ca7-4d79-911c-fbba6979a1f3',
           -9.7790045,-139.1210563,11
       ),(
           '74e79406-82e9-4a49-8452-ceb5ce72f286',
           'Fatu Hiva',
           'c75f6eeb-0ca7-4d79-911c-fbba6979a1f3',
           -10.4860661,-138.6854358,13
       ),(
           'f91b77d1-4933-4642-bae3-e9f4f2573499',
           '‘Ua Pou',
           'c75f6eeb-0ca7-4d79-911c-fbba6979a1f3',
           -9.4008065,-140.1127682,13
       ),(
           '05c0fa55-66d2-4451-a540-3aa31fadcd1b',
           'Tahuata',
           'c75f6eeb-0ca7-4d79-911c-fbba6979a1f3',
           -9.9544785,-139.1183004,13
       ) ON CONFLICT DO NOTHING;

INSERT INTO island (id, name, archipelago_id, latitude, longitude, zoom)
VALUES (
           'c52168d1-bdb2-44e8-a3e0-28baa64fc658',
           'Mangareva',
           '32b915b5-6fbd-4ece-b660-acab06391247',
           -23.1044714,-135.0063776,13
       ),(
           'f927b3a0-b523-4a3d-a3fa-ad89342995b7',
           'Taravai',
           '32b915b5-6fbd-4ece-b660-acab06391247',
           -23.1454945,-135.031674,17
       ),(
           '43a4cc2a-faf1-41da-a0c3-7ab08137796a',
           'Akamaru',
           '32b915b5-6fbd-4ece-b660-acab06391247',
           -23.1889658,-134.9198228,15
       ),(
           '2e2972e9-60a3-4cf2-b9cc-c6c639310958',
           '‘Aukena',
           '32b915b5-6fbd-4ece-b660-acab06391247',
           -23.1295853,-134.9131012,15
       ),(
           '13b6d542-4311-468c-86bc-c580812cd57a',
           'Kamaka',
           '32b915b5-6fbd-4ece-b660-acab06391247',
           -23.2430563,-134.9609471,17
       ) ON CONFLICT DO NOTHING;