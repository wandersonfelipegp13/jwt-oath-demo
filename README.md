# API REST usando JWT com OAuth2

Uma API REST, protegida com o Spring Security e que gera JWTs, 
para acesso aos endpoints.

## Como rodar

Gere uma chave pública e uma privada, que serão usadas na criação do JWT, 
em `src/main/resources/certs`, com os seguintes comandos:

```sh
# crie o par de chaves rsa
$ openssl genrsa -out keypair.pem 2048
# extraia a chave pública
$ openssl rsa -in keypair.pem -pubout -out public.pem
# extraia a chave privada no formato PKCS#8
$ openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
# podemos deletar o keypair agora
$ rm keypair.pem
```

Com as chaves geradas, podemos rodar o projeto com o Maven, com o seguinte comando:

```sh
$ mvn spring-boot:run
```

## Como testar

Existem 2 endpoints: 
 - `/token`, via POST, para obter um token de acesso, e 
 - `/`, via GET, para obter uma mensagem de exemplo.

Podemos rodar os testes, no diretorio `src/test`, nos quais são usados
o MockMvc. Podemos também usar uma ferramenta, como o 
[Postman](https://www.postman.com), [Insomnia](https://insomnia.rest), 
ou [HTTPie](https://httpie.io) para fazer as requisições:

```sh
# obter o token
$ http POST :8080/token --auth wande:password -v
# obter a mensagem com o token
$ http :8080 'Authorization: Bearer JWT_GERADO_AQUI'
```

