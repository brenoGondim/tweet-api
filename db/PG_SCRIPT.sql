CREATE TABLE "user" (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(50),
                        email VARCHAR(255) UNIQUE,
                        password VARCHAR(255),
                        role VARCHAR(50),
                        points INTEGER
);


CREATE TABLE "tweet" (
                         id SERIAL PRIMARY KEY,
                         payload TEXT,
                         post_date TIMESTAMP,
                         code_user INTEGER,
                         FOREIGN KEY (code_user) REFERENCES "user"(id)
);

CREATE TABLE "campaign" (
                            id SERIAL PRIMARY KEY,
                            slogan VARCHAR(255),
                            initial_date TIMESTAMP,
                            active BOOLEAN
);


CREATE TABLE "campaign_user" (
                                 code_user INTEGER,
                                 code_campaign INTEGER,
                                 FOREIGN KEY (code_user) REFERENCES "user"(id),
                                 FOREIGN KEY (code_campaign) REFERENCES "campaign"(id)
);


INSERT INTO public.tweet (payload,post_date,code_user) VALUES
                                                           ('Tweet sem nada de importante','2024-04-16 17:21:53.810817',5),
                                                           ('Tweet com a frase para match com campanhas: Campanha de aniversário!','2024-04-16 17:22:48.397031',5),
                                                           ('Tweet com a frase para match com campanhas: Campanha de aniversário!','2024-04-16 22:01:01.579225',5),
                                                           ('Tweet com a frase para match com campanhas: Campanha de aniversário!','2024-04-16 22:02:27.463012',5),
                                                           ('Tweet para o breno ganhar um coca cola com a frase ''Campanha de Coca-Cola''','2024-04-16 22:06:38.957292',5),
                                                           ('O joaozinho atualiza o Tweet e ver se ganha a ''Campanha de aniversário''','2024-04-18 12:26:35.981536',6),
                                                           ('O joaozinho atualiza o Tweet e ver se ganha a ''Campanha de aniversário!''','2024-04-18 12:25:31.828556',6);

