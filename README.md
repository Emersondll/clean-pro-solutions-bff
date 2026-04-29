# Clean Pro Solutions - BFF (Backend For Frontend) 📱

## 🎯 Papel no Ecossistema
O **BFF Service** atua como a fachada única para as aplicações cliente (Mobile e Web). Suas responsabilidades incluem:
- Agregação de dados de múltiplos microserviços (Ex: Perfil + Agendamentos).
- Simplificação da interface para o frontend.
- Roteamento e balanceamento de carga básico via Eureka.
- Formatação de respostas específicas para a experiência do usuário.

## 🚀 Tecnologias
- **Java 21** & **Spring Boot 3.3.4**
- **Spring Cloud Gateway / OpenFeign**
- **Netflix Eureka** (Service Discovery)

## 🛠️ Como Executar

### 1. Execução Isolada (Individual)
Para rodar este serviço e suas dependências (Registry):
```bash
docker-compose up -d --build
```
O serviço estará disponível em `http://localhost:8080`.

### 2. Execução Integrada
Este serviço é orquestrado pelo projeto principal [Clean Pro Platform](../README.md).

---
© 2026 Clean Pro Solutions - Desenvolvido por Emerson.
