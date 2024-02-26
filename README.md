Optei por definir a API mínima como 21, uma vez que é a versão mais recente suportada pelo aplicativo da Sicredi. Embora em 2018 a escolha da API mínima 19 fosse justificável, em 2024 a base de dispositivos compatíveis não justifica mais essa opção. Ao adotar a API 21, abrangemos 99.6% dos dispositivos disponíveis na loja, evitamos a utilização de bibliotecas obsoletas, como a antiga biblioteca do OkHttp, e ganhamos acesso a recursos como o agendamento de tarefas (scheduling jobs).

Além disso, integrei uma biblioteca própria, a [Utility Tools Kit Android](https://github.com/SamueldaCostaAraujoNunes/utility-tools-kit-android), contendo utilitários que ajudam a reduzir códigos repetitivos.

Alguns desses utilitários estão documentados nos seguintes artigos:

- [Mapeando o corpo do erro com Retrofit: uma solução eficiente](https://medium.com/@samueldacostaaraujonunes/mapeando-body-de-erro-com-retrofit-uma-solu%C3%A7%C3%A3o-eficiente-e8e95698819c)
- [Observando a conectividade de rede no Android com Flow](https://medium.com/@samueldacostaaraujonunes/observando-a-conectividade-de-rede-no-android-com-flow-12394a863f51)

Por fim, é importante ressaltar que, embora a API de check-in indique sempre "Endpoint disabled", fiz a suposição de que, em caso de sucesso, o statusCode seria 200 e o corpo da resposta estaria vazio, já que não tinha acesso aos contratos da api. Apesar disso, implementei tratamentos de erro para todos os endpoints, bem como para situações em que o dispositivo estiver sem conexão com a internet.
