CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- INSERT DEFAULT LANGUAGES

INSERT INTO language ("id", "created", "disabled", "updated", "name")
VALUES ('zh_Hans', '2020-01-24', 'f', '2020-01-24', '中文'),
       ('en', '2020-01-24', 'f', '2020-01-24', 'English'),
       ('fr', '2020-01-24', 'f', '2020-01-24', 'Français')
ON CONFLICT DO NOTHING;

-- INSERT DEFAULT USER ROLES

INSERT INTO role ("id", "disabled", "name")
VALUES ('d9199aec-712a-4eac-b965-2c7096f9f123', FALSE, 'ADMIN'),
       ('d5694e36-1024-48ef-96f8-0a452054934e', FALSE, 'USER')
ON CONFLICT DO NOTHING;

-- INSERT DEFAULT APP VERSION CONFIGURATION

INSERT INTO app_version_configuration ("id", "created", "min_version")
VALUES ('dd3d4622-832d-4766-9efb-dba1d95f1ef9', NOW(), 1)
ON CONFLICT DO NOTHING;

-- INSERT DEFAULT THEMES

INSERT INTO poi_theme ("id", "created", "updated", "icon_id", "parent_id")
VALUES ('dfb4053f-e4ad-47a8-b2f1-321d92b3329c', NOW(), NOW(), NULL, NULL)
ON CONFLICT DO NOTHING;

INSERT INTO localized_resource (id, created, disabled, updated, resource, language_id)
VALUES
       ('e8defcc8-d4ec-4c3c-8369-b41a1815e9d5', NOW(), FALSE, NOW(), 'Things to do', 'en'),
       ('f942ad1b-c344-4c67-b62f-e128b12f8a69', NOW(), FALSE, NOW(), 'Choses à faire', 'fr'),
       ('62b97939-23d0-4027-b13b-024165d4da54', NOW(), FALSE, NOW(), '要做的事情', 'zh_Hans')
ON CONFLICT DO NOTHING;

INSERT INTO poi_theme_name (poi_theme_id, name_id)
VALUES
       ('dfb4053f-e4ad-47a8-b2f1-321d92b3329c', 'e8defcc8-d4ec-4c3c-8369-b41a1815e9d5'),
       ('dfb4053f-e4ad-47a8-b2f1-321d92b3329c', 'f942ad1b-c344-4c67-b62f-e128b12f8a69'),
       ('dfb4053f-e4ad-47a8-b2f1-321d92b3329c', '62b97939-23d0-4027-b13b-024165d4da54')
ON CONFLICT DO NOTHING;



-- INSERT DEFAULT ARCHIPELAGOS

INSERT INTO archipelago (id, name)
VALUES ('fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4', 'Archipel de la Société'),
       ('49b263b0-0326-496d-bfa1-3a3986797bba', 'Archipel Australes'),
       ('02cbe4a1-fef8-46ec-b89e-30ad801cfd25', 'Archipel des Tuamotu'),
       ('c75f6eeb-0ca7-4d79-911c-fbba6979a1f3', 'Archipel des Marquises'),
       ('32b915b5-6fbd-4ece-b660-acab06391247', 'Archipel des Gambiers')
ON CONFLICT DO NOTHING;

-- INSERT DEFAULT ISLAND IMAGES

INSERT INTO media (id, created, disabled, updated, media_type, url)
VALUES ( -- Huahine
    '76022841-2834-4cac-932b-07a57becba62', NOW(), FALSE, NOW(), 'image/jpg', 'https://lesdeuxpiedsdehors.com/wp-content/uploads/2018/09/que-faire-sur-li%CC%82le-de-Huahine-en-Polyne%CC%81sie.jpg'
),( -- Tahaa
    'd49d1c94-0b7a-4a8b-b2f1-318683915fd1', NOW(), FALSE, NOW(), 'image/jpg', 'https://img.ev.mu/images/villes/63194/960x640.jpg'
),( -- Fakarava
    '84ff22c5-6be0-44f6-aaf9-556583dacf6b', NOW(), FALSE, NOW(), 'image/jpg', 'http://blog.univ-angers.fr/leparadisdesiles/files/2015/04/la-plus-belle-plage-du-monde-fakarava.jpg'
),( -- Tahiti
    'f103f09b-6008-4ebb-8ab0-1301c8368959', NOW(), FALSE, NOW(), 'image/jpg', 'https://www.airtahitinui.com/sites/default/files/img-slides/mobile/bora-dji0468-667x556.jpg'
),( -- Moorea
    '04bc1823-0ee0-48da-a3be-b01925d0cab1', NOW(), FALSE, NOW(), 'image/jpg', 'https://www.service-public.pf/dpam/wp-content/uploads/sites/20/2019/07/Moorea-Aerial-2000x1200_126.jpg'
),( -- Raiatea
    'b9d80d93-219c-4236-8c7a-4f2a12845702', NOW(), FALSE, NOW(), 'image/jpg', 'https://pix10.agoda.net/geo/city/18162/1_18162_02.jpg?s=1920x822'
),( -- Rurutu
    '6ad2c490-414a-4c48-9d75-cecf50bb0843', NOW(), FALSE, NOW(), 'image/jpg', 'https://media-cdn.tripadvisor.com/media/photo-c/768x250/12/cc/4a/a1/photo1jpg.jpg'
),( -- Raivavae
    '48b00e90-925a-4b76-955e-7b1a982c2787', NOW(), FALSE, NOW(), 'image/jpg', 'https://tahitileblog.fr/wp-content/uploads/2017/12/Raivavae.jpg'
),( -- Rapa nui
    'e822523c-dfbe-4629-92a6-c06e3aedcc29', NOW(), FALSE, NOW(), 'image/jpg', 'https://cosmos-images2.imgix.net/file/spina/photo/16073/180814-rapanui-full.JPG?ixlib=rails-2.1.4&auto=format&ch=Width%2CDPR&fit=max&w=835'
),( -- Rimatara
    '27889f36-a10a-48a2-befc-b9aee2b148c3', NOW(), FALSE, NOW(), 'image/jpg', 'https://cdn.futura-sciences.com/buildsv6/images/mediumoriginal/a/2/b/a2b4ca7ef7_44032_3-rimatara-04jpg.jpg'
),( -- Avera
    'ed65f858-c226-4edb-b382-7508042f5b81', NOW(), FALSE, NOW(), 'image/jpg', 'https://www.soscroisiere.com/back/images/escales/petit/small_jpg-2018102412285261.jpg'
),( -- Rangiroa
    '69dd2792-136e-4167-9c91-8a3e97213e05', NOW(), FALSE, NOW(), 'image/jpg', 'https://d1k2jfc4wnfimc.cloudfront.net/assets/maitairangiroa/images/image-11.jpg'
),( -- Fakarava
    '805f3e0c-5ffb-4186-89e3-2b752b43b629', NOW(), FALSE, NOW(), 'image/jpg', 'https://roamfamilytravel.com/wp-content/uploads/2019/03/palapas-havaiki-feature.jpg'
),( -- Anaa
    '8e053ccf-3700-427e-9461-eadb9d80f89d', NOW(), FALSE, NOW(), 'image/jpg', 'https://lp-cms-production.imgix.net/image_browser/Idyllic%20Anaa%2C%20French%20Polynesia.jpg?auto=format&fit=crop&q=40&sharp=10&vib=20&ixlib=react-8.6.4'
),( -- Maketa
    'b9abc777-58a7-4978-af09-982e73e547f3', NOW(), FALSE, NOW(), 'image/jpg', 'https://scd.france24.com/fr/files_fr/imagecache/home_1024/images/afp/04167026206fa64439709b9c054ba91498021705.jpg'
),( -- Hao
    'ef37215c-acd4-42d5-8750-6f18ca29423e', NOW(), FALSE, NOW(), 'image/jpg', 'https://media-cdn.sygictraveldata.com/media/800x600/612664395a40232133447d33247d38313130373031313635'
),( -- Tikehau
    '72f4ed80-d083-4772-b319-104e2a84ea37', NOW(), FALSE, NOW(), 'image/jpg', 'https://q-cf.bstatic.com/images/hotel/max1024x768/177/177019538.jpg'
),( -- Takume
    '8bb01c40-948c-4d04-a5c9-f9a0b269fe26', NOW(), FALSE, NOW(), 'image/jpg', 'https://www.expeditions.com/globalassets/der-images/or/or81822/or082018.jpg?w=964'
),( -- Manihi
    '5fc69c7e-c757-49d2-8fae-0bf159c15b61', NOW(), FALSE, NOW(), 'image/jpg', 'https://brightyonder.com/img/locations/760.jpg'
),( -- Takaroa
    '8653d417-3913-478e-8734-f9fe58f8022a', NOW(), FALSE, NOW(), 'image/jpg', 'https://welcome-tahiti.com/wp-content/uploads/anaa-recif-lagon-portrait-bacchet.jpg.webp'
),( -- Apataki
    '962e9d7c-2d45-4ed8-aa04-9eede85d76ae', NOW(), FALSE, NOW(), 'image/jpg', 'https://i.ytimg.com/vi/ptQcC8roHsQ/maxresdefault.jpg'
),( -- Makemo
    'dfdb5776-46f1-4757-b956-9be099930045', NOW(), FALSE, NOW(), 'image/jpg', 'https://i.pinimg.com/474x/f7/fa/8e/f7fa8ed43fad4886452bc9583e2b1547.jpg'
),( -- Nuku Hiva
    'af99f882-309f-40dd-bda2-636c5e824e33', NOW(), FALSE, NOW(), 'image/jpg', 'https://xdaysiny.com/wp-content/uploads/2014/07/Grand-Canyon-Nuku-Hiva-Marquesas-Islands-French-Polynesia.jpg'
),( -- Hiva Oa
    'b73f0d8d-26ab-4924-9ab6-d807fdc73e6f', NOW(), FALSE, NOW(), 'image/jpg', 'https://xdaysiny.com/wp-content/uploads/2014/07/Road-trip-Hiva-Oa-Marquesas-Islands-French-Polynesia-Eiaone-Bay.jpg'
),( -- Fatu Hiva
    '700502c5-195f-46ad-a7a1-15084e4fae26', NOW(), FALSE, NOW(), 'image/jpg', 'https://xdaysiny.com/wp-content/uploads/2014/07/Hatiheu-Bay-village-Nuku-Hiva-Marquesas-Islands-French-Polynesia.jpg'
),( -- Ua pou
    '24ec3b85-6ccf-406d-9e46-2d3fe180a472', NOW(), FALSE, NOW(), 'image/jpg', 'https://img.ev.mu/images/attractions/2823/600x400/3882.jpg'
),( -- Tahuata
    'd6ca02cc-c4ff-45c6-bffe-f180b1e4c2d1', NOW(), FALSE, NOW(), 'image/jpg', 'https://img.ev.mu/images/attractions/2827/600x400/16932.jpg'
),( -- Mangareva
    '53a840ba-43af-42c3-9f85-0149ede31a2a', NOW(), FALSE, NOW(), 'image/jpg', 'https://www.traveller.com.au/content/dam/images/h/1/a/1/z/v/image.related.articleLeadwide.620x349.h199za.png/1561599252244.jpg'
),( -- Taravai
    'c96085e0-f78c-4d48-8cd3-8f14055674f8', NOW(), FALSE, NOW(), 'image/jpg', 'https://cdn.britannica.com/99/120899-004-61D13E00/Mangareva-Gambier-Islands-French-Polynesia.jpg'
),( -- Akamaru
    'd7a75c84-cf24-49ee-9c6a-8cccc68eb1f0', NOW(), FALSE, NOW(), 'image/jpg', 'https://welcome-tahiti.com/wp-content/uploads/gambier-vue-du-lagon-bacchet.jpg'
),( -- Aukena
    '925db785-6477-41cd-96c7-48e7077599e0', NOW(), FALSE, NOW(), 'image/jpg', 'https://xdaysiny.com/wp-content/uploads/2019/04/Aukena-beach-Gambier-Islands-French-Polynesia.jpg'
),( -- Kamaka
    '58dfe5cf-1cc8-40c0-8e36-893986fea1a1', NOW(), FALSE, NOW(), 'image/jpg', 'https://welcome-tahiti.com/wp-content/uploads/gambier-vue-lagon-ile-bacchet.jpg.webp'
)  ON CONFLICT DO NOTHING ;

-- INSERT DEFAULT ISLANDS

INSERT INTO island (id, name, archipelago_id, latitude, longitude, zoom, image_id)
VALUES (
          '59b830f3-ad37-41dd-bac9-93eddc0e39e0',
          'Huahine',
          'fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4',
          -23.3727082,-149.4984394,18,
          '76022841-2834-4cac-932b-07a57becba62'
), (
          'b8407091-2517-49fa-b3d7-93c098460b36',
          'Tahaa',
          'fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4',
          -16.6307292,-151.5270121,13,
          'd49d1c94-0b7a-4a8b-b2f1-318683915fd1'
), (
          'ac2391de-44b8-475d-b2fa-5b2afa7cc598',
          'Fakarava',
          'fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4',
          -16.2989906,-145.7326184,11,
          '84ff22c5-6be0-44f6-aaf9-556583dacf6b'
), (
          '5404491f-6eda-49da-848b-f582db00f6d7',
          'Tahiti',
          'fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4',
          -17.686893,-149.5128859,11,
          'f103f09b-6008-4ebb-8ab0-1301c8368959'
), (
          '8a57b307-99c7-4682-9614-2836d13ff67c',
          'Moorea',
          'fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4',
          -17.5332919,-149.9053552,12,
          '04bc1823-0ee0-48da-a3be-b01925d0cab1'
), (
          'd2c54228-86d5-4aa8-8853-903d980a6ff9',
          'Raiatea',
          'fb9bdda0-df9e-49f1-b737-4cf5bc5d6bf4',
          -16.8192425,-151.4926665,12,
          'b9d80d93-219c-4236-8c7a-4f2a12845702'
) ON CONFLICT DO NOTHING;

INSERT INTO island (id, name, archipelago_id, latitude, longitude, zoom, image_id)
VALUES (
           '37e3d4de-6039-4116-ab9e-a2daef012611',
           'Rurutu',
           '49b263b0-0326-496d-bfa1-3a3986797bba',
           -22.4779831,-151.3666893,14,
           '6ad2c490-414a-4c48-9d75-cecf50bb0843'
       ), (
           'be262740-43ef-4f08-afef-20e1eb663ac7',
           'Raivavae',
           '49b263b0-0326-496d-bfa1-3a3986797bba',
           -23.8595525,-147.6805238,13.66,
           '48b00e90-925a-4b76-955e-7b1a982c2787'
       ), (
           '9c605bae-ca46-4d0c-b3cf-b01f8c674f0f',
           'Rapa',
           '49b263b0-0326-496d-bfa1-3a3986797bba',
           -27.6082255,-144.3780974,13,
           'e822523c-dfbe-4629-92a6-c06e3aedcc29'
       ), (
           'd8a27f4d-4148-47b4-a0a8-d4f450874865',
           'Rimatara',
           '49b263b0-0326-496d-bfa1-3a3986797bba',
           -22.6584362,-152.8136029,15,
           '27889f36-a10a-48a2-befc-b9aee2b148c3'
       ), (
           'c3231656-5d96-454f-9654-9ccd7d667842',
           'Avera',
           '49b263b0-0326-496d-bfa1-3a3986797bba',
           -16.7853439,-151.4180472,16,
           'ed65f858-c226-4edb-b382-7508042f5b81'
       ) ON CONFLICT DO NOTHING;

INSERT INTO island (id, name, archipelago_id, latitude, longitude, zoom, image_id)
VALUES (
           '0b42af3d-ab5f-4edb-8b76-5af7686be36c',
           'Rangiroa',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -15.1202173,-147.8573821,10,
           '69dd2792-136e-4167-9c91-8a3e97213e05'
       ),
       (
           'b5e6d5cd-accc-4cdc-aa97-a00746db5c34',
           'Fakarava',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -16.2989906,-145.7326184,11,
           '805f3e0c-5ffb-4186-89e3-2b752b43b629'
       ),
       (
           '56b9ceed-4505-4b3b-8f1f-f2516d9b02a8',
           'Anaa',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -17.4121632,-145.568079,12,
           '8e053ccf-3700-427e-9461-eadb9d80f89d'
       ),
       (
           '9e7ea1f8-c81f-4131-924f-f9484b54a9ce',
           'Maketa',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -9.0254483,-158.0290615,12.29,
           'b9abc777-58a7-4978-af09-982e73e547f3'
       ),
       (
           'a7fb9f91-03bd-479b-a136-5e6895317ad5',
           'Hao',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -18.2459137,-141.0072814,11,
           'ef37215c-acd4-42d5-8750-6f18ca29423e'
       ),
       (
           'd923ea8b-cef5-4757-a446-dead52b69baf',
           'Tikehau',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -15.114511,-148.2470119,15,
           '72f4ed80-d083-4772-b319-104e2a84ea37'
       ),
       (
           '8fdde036-fabc-4fa8-acc2-8520ce22c1dc',
           'Takume',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -15.7999464,-142.2665214,12,
           '8bb01c40-948c-4d04-a5c9-f9a0b269fe26'
       ),
       (
           'c679fcf2-cb62-46bc-8b1a-4bc8e16e4a92',
           'Manihi',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -14.3977912,-146.0273996,12,
           '5fc69c7e-c757-49d2-8fae-0bf159c15b61'
       ),
       (
           '3b191b73-f68b-4744-9434-c9b8a0379934',
           'Takaroa',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -14.4509515,-145.0336198,12,
           '8653d417-3913-478e-8734-f9fe58f8022a'
       ),
       (
           'bd5dd894-f982-4561-a26b-0e0bbdbc2020',
           'Apataki',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -15.4499788,-140.7695878,15,
           '962e9d7c-2d45-4ed8-aa04-9eede85d76ae'
       ),
       (
           'a24cc1d3-3af5-4660-b004-a2b9ea97ecc1',
           'Makemo',
           '02cbe4a1-fef8-46ec-b89e-30ad801cfd25',
           -16.5934163,-143.9680859,10,
           'dfdb5776-46f1-4757-b956-9be099930045'
       ) ON CONFLICT DO NOTHING;

INSERT INTO island (id, name, archipelago_id, latitude, longitude, zoom, image_id)
VALUES (
           'f035fc61-fa44-43df-a3eb-ebd8612b67e0',
           'Nuku Hiva',
           'c75f6eeb-0ca7-4d79-911c-fbba6979a1f3',
           -8.8731591,-140.1989633,12,
           'af99f882-309f-40dd-bda2-636c5e824e33'
       ),(
           '2bf6d470-766f-48b8-8782-aab49944ff95',
           'Hiva Oa',
           'c75f6eeb-0ca7-4d79-911c-fbba6979a1f3',
           -9.7790045,-139.1210563,11,
           'b73f0d8d-26ab-4924-9ab6-d807fdc73e6f'
       ),(
           '74e79406-82e9-4a49-8452-ceb5ce72f286',
           'Fatu Hiva',
           'c75f6eeb-0ca7-4d79-911c-fbba6979a1f3',
           -10.4860661,-138.6854358,13,
           '700502c5-195f-46ad-a7a1-15084e4fae26'
       ),(
           'f91b77d1-4933-4642-bae3-e9f4f2573499',
           '‘Ua Pou',
           'c75f6eeb-0ca7-4d79-911c-fbba6979a1f3',
           -9.4008065,-140.1127682,13,
           '24ec3b85-6ccf-406d-9e46-2d3fe180a472'
       ),(
           '05c0fa55-66d2-4451-a540-3aa31fadcd1b',
           'Tahuata',
           'c75f6eeb-0ca7-4d79-911c-fbba6979a1f3',
           -9.9544785,-139.1183004,13,
           'd6ca02cc-c4ff-45c6-bffe-f180b1e4c2d1'
       ) ON CONFLICT DO NOTHING;

INSERT INTO island (id, name, archipelago_id, latitude, longitude, zoom, image_id)
VALUES (
           'c52168d1-bdb2-44e8-a3e0-28baa64fc658',
           'Mangareva',
           '32b915b5-6fbd-4ece-b660-acab06391247',
           -23.1044714,-135.0063776,13,
           '53a840ba-43af-42c3-9f85-0149ede31a2a'
       ),(
           'f927b3a0-b523-4a3d-a3fa-ad89342995b7',
           'Taravai',
           '32b915b5-6fbd-4ece-b660-acab06391247',
           -23.1454945,-135.031674,17,
           'c96085e0-f78c-4d48-8cd3-8f14055674f8'
       ),(
           '43a4cc2a-faf1-41da-a0c3-7ab08137796a',
           'Akamaru',
           '32b915b5-6fbd-4ece-b660-acab06391247',
           -23.1889658,-134.9198228,15,
           'd7a75c84-cf24-49ee-9c6a-8cccc68eb1f0'
       ),(
           '2e2972e9-60a3-4cf2-b9cc-c6c639310958',
           '‘Aukena',
           '32b915b5-6fbd-4ece-b660-acab06391247',
           -23.1295853,-134.9131012,15,
           '925db785-6477-41cd-96c7-48e7077599e0'
       ),(
           '13b6d542-4311-468c-86bc-c580812cd57a',
           'Kamaka',
           '32b915b5-6fbd-4ece-b660-acab06391247',
           -23.2430563,-134.9609471,17,
           '58dfe5cf-1cc8-40c0-8e36-893986fea1a1'
       ) ON CONFLICT DO NOTHING;