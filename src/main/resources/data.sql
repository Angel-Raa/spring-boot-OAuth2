

-- Insertar en la tabla client_app
insert into client_app (client_id, client_secret, duration_in_minutes, required_proof_key)
values ('client', '$2a$10$QHLrXJ8nlnDlOXEDUnV6iefGNOkKaW9pqJwZcpF.5HrO42PRPzjcO', 10, false);

-- Insertar en la tabla client_app_authorization_grant_types
insert into client_app_authorization_grant_types (client_app_id, authorization_grant_types)
values (client_app_id, 'authorization_code');

insert into client_app_authorization_grant_types (client_app_id, authorization_grant_types)
values (client_app_id, 'refresh_token');

insert into client_app_authorization_grant_types (client_app_id, authorization_grant_types)
values (client_app_id, 'client_credentials');

-- Continúa con las otras inserciones de datos para 'client'

-- Insertar en la tabla client_app_redirect_uris
insert into client_app_redirect_uris (client_app_id, redirect_uris)
values (client_app_id, 'https://oauthdebugger.com/debug');

-- Insertar en la tabla client_app_scopes
insert into client_app_scopes (client_app_id, scopes)
values (client_app_id, 'openid');

insert into client_app_scopes (client_app_id, scopes)
values (client_app_id, 'READ_ALL_PRODUCTS');

insert into client_app_scopes (client_app_id, scopes)
values (client_app_id, 'READ_ONE_PRODUCT');



-- Obtener el client_app_id para 'my-own-client'
SET my_own_client_app_id = (select id from client_app where client_id = 'my-own-client');

-- Insertar en la tabla client_app
insert into client_app (client_id, client_secret, duration_in_minutes, required_proof_key)
values ('my-own-client', '$2a$10$QHLrXJ8nlnDlOXEDUnV6iefGNOkKaW9pqJwZcpF.5HrO42PRPzjcO', 10, true);

-- Insertar en la tabla client_app_authorization_grant_types
insert into client_app_authorization_grant_types (client_app_id, authorization_grant_types)
values (my_own_client_app_id, 'authorization_code');

insert into client_app_authorization_grant_types (client_app_id, authorization_grant_types)
values (my_own_client_app_id, 'refresh_token');

insert into client_app_authorization_grant_types (client_app_id, authorization_grant_types)
values (my_own_client_app_id, 'client_credentials');

-- Continúa con las otras inserciones de datos para 'my-own-client'

-- Insertar en la tabla client_app_redirect_uris
insert into client_app_redirect_uris (client_app_id, redirect_uris)
values (my_own_client_app_id, 'https://oauthdebugger.com/debug');

-- Insertar en la tabla client_app_scopes
insert into client_app_scopes (client_app_id, scopes)
values (my_own_client_app_id, 'openid');

insert into client_app_scopes (client_app_id, scopes)
values (my_own_client_app_id, 'ALL');
