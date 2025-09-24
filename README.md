
# 🛒 Shopping Cart - Ci/Cd



Este projeto é um mini sistema de carrinho de compras em Java com pipeline de CI/CD configurado no GitHub Actions.



## 🧪 Testes
- Desenvolvidos em JUnit 4.
- Incluem testes unitários e mocks
- Relatórios gerados em:
 ```bash
    Ci-Cd/target/surefire-reports/
 ```

 - Relatórios são enviados como artefatos no GitHub Actions.
## 🛠️ Build

- O build é feito via Maven:
```bash
    mvn -B -q -f Ci-Cd/pom.xml package
 ```
- O JAR resultante fica em:
```bash
    Ci-Cd/target/Ci-Cd-1.0-SNAPSHOT.jar
 ```

- Também é enviado como artefato no GitHub Actions.




##  ⚙️ Pipeline (GitHub Actions)
O workflow principal encontrado em .github/workflows/mvn.yml.

### Jobs do Pipeline
- Test → executa os testes e salva relatórios
- Static-checks → validações rápidas, roda em paralelo
- Build → empacota a aplicação e envia o JAR como artefato
- Notification → envia e-mail com resultado da execução

## 📧 Notificação por E-mail
O envio de e-mail é feito pelo script scripts/sendEmail.sh, utilizando mailutils.
- O endereço do destinatário vem de um secret configurado no GitHub Actions:
```bash
   env:
    NOTIFY_TO_EMAIL: ${{ secrets.NOTIFY_TO_EMAIL }}
 ```

## 📂 Artefatos Disponíveis
Após cada execução do pipeline ficam disponíveis no GitHub Actions:
 - 📄 Relatórios de testes (test-reports)
 - 📦 Aplicação empacotada (.jar) (app-jar)

 ## 🚀 Como rodar localmente
 Pré-requisitos
  - Java 21+
  - Maven
 
  __Passos__ 
  ```bash
    cd Ci-Cd
    mvn clean test     # executa os testes
    mvn package        # gera o JAR em target/
 ```
  