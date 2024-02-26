Utilizei a API min 21, por ser a atual versão minima suportada pelo app da Sicredi.
Entendo que em 2018, era justificavel utilizar a api min 19, mas em 2024, a base de dispositivos já não se sustenta.
Utilizando a api 21, atingimos 99.6% dos dispositivos em loja, evitamos utilizar bibliotecas deprecadas, como por exemplo a antiga lib do OkHttp e passamos ter acesso a features como o scheduling jobs.

Utilizei uma biblioteca minha ("com.github.SamueldaCostaAraujoNunes:utility-tools-kit-android:1.8") com códigos utilitários, que uso para reduzir códigos boilerplates.
Segue o repo -> https://github.com/SamueldaCostaAraujoNunes/utility-tools-kit-android

Alguns desses códigos que estão no projeto, estão documentadas nos artigos abaixo:
https://medium.com/@samueldacostaaraujonunes/mapeando-body-de-erro-com-retrofit-uma-solu%C3%A7%C3%A3o-eficiente-e8e95698819c
https://medium.com/@samueldacostaaraujonunes/observando-a-conectividade-de-rede-no-android-com-flow-12394a863f51

PS: A API de checkin, sempre indica "Endpoint disabled", como não consegui testar o caso de sucesso e não tinha acesso ao contrato. Fiz a suposição de que em caso de sucesso, o statusCode seria 200 e o body viria vazio. De qualquer forma, fiz os tratamentos de erro para todos os endPoints.
