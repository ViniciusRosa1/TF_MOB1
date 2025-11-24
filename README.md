# 📱 FinApp – Gerenciador Financeiro Simples (Android)

Um aplicativo Android desenvolvido para auxiliar os usuários na organização financeira pessoal, permitindo o registro de **ganhos (créditos)** e **gastos (débitos)**. O projeto foi solicitado por uma **FinTech** como um protótipo funcional de um app simples para controle financeiro.

---

## 🎯 Objetivo do Projeto

O FinApp possibilita o cadastro de transações financeiras e a exibição de um extrato contendo todas as operações realizadas. Os registros são salvos **apenas em memória**, conforme os requisitos do trabalho — ou seja, **não permanecem após fechar o app**.

---

## 🧩 Funcionalidades Principais

| Activity / Tela        | Função                                                                 |
|------------------------|------------------------------------------------------------------------|
| **MainActivity**       | Dashboard com saldo, data da última alteração e botões de navegação. |
| **NovoLancamentoActivity** | Cadastro de transações financeiras (Crédito/Débito).                  |
| **ExtratoActivity**    | Lista todas as transações cadastradas.                               |
| **Botão Sair**         | Encerra completamente o aplicativo.                                   |

---

## 🖼️ Interface do Usuário

### 🟢 Dashboard (`MainActivity`)
- Exibe o **saldo total calculado** automaticamente.
- Mostra a **data da última modificação**.
- Possui 3 botões:
  - ➕ **Cadastrar nova transação**
  - 📜 **Visualizar extrato**
  - ❌ **Sair do aplicativo**

---

### ✍️ Cadastro de Transação (`NovoLancamentoActivity`)
- Escolha do tipo:
  - ✔ **Crédito**
  - ❌ **Débito**
- Campos preenchidos:
  - **Valor**
  - **Data**
- Ao salvar:
  - Cria uma instância da classe `Transacao`
  - Adiciona em uma **ArrayList em memória**
  - Retorna a lista para a `MainActivity` via `Intent`

---

### 📄 Extrato (`ExtratoActivity`)
- Utiliza **ListView** padrão.
- Exibe cada transação com:
  - **Valor (título)**
  - **Tipo e Data (subtítulo)**
- Botão **Voltar** retorna ao Dashboard.

