# Corrigindo code smell Divergent Change na classe Game

## O quê é?
Na implementação original, a classe Game concentrava todas as responsabilidades do jogo em um único arquivo e por isso havia muitos motivos não relacionados para alterar essa mesma classe, causando Divergent Change

- **Mudança em Jogadores**: Se decidisse que um jogador passa a ter 3 vidas, você tinha que alterar o código interno de Game.
- **Mudança nas Perguntas**: Se quisesse adicionar uma nova categoria (ex: História), você tinha que alterar a classe Game.
- **Mudança nas Regras do Jogo**: Se quisesse mudar a condição de vitória de 6 para 10 moedas, alterar a regra do dado ou mudar o tamanho do tabuleiro, você alterava a classe Game.

## Por quê corrigir?

Manter tantas responsabilidades acopladas em um único arquivo faz com que uma simples alteração no sistema de perguntas  possa introduzir bugs acidentais na lógica dos jogadores. Isso viola o princípio da responsabilidade única (SPR), um dos pilares da orientação a objetos e gera frequentes conflitos de fusão no Git quando diferentes pessoas trabalham em tarefas distintas no mesmo arquivo, ao mesmo tempo.

## Como foi refatorado?
A refatoração separou as responsabilidades aplicando a técnica de Extract Class (Extrair Classe) da seguinte forma:

**Player**: 
- **Antes**: Game continha `players` (ArrayList), `places` (int[]), purses (int[]) e `inPenaltyBox` (boolean[]).
- **Depois**: Os dados e as regras individuais (como mover posições e somar moedas) foram encapsulados na classe Player.

**Question e QuestionDeck**:
- **Antes**: Game inicializava as 50 perguntas de cada categoria e gerenciava as filas (popQuestions, scienceQuestions, etc.).
- **Depois**: A classe Question representa o item, enquanto QuestionDeck armazena e fornece a próxima pergunta com base na categoria solicitada.

**Game**:
- **Antes**: A classe acumulava múltiplos papéis no mesmo arquivo, atuando ao mesmo tempo no cadastro de jogadores, repositório de perguntas, gerenciamento das regras do jogo e exibição das mensagens.
- **Depois**: A classe passa a agir como uma Controladora intermediária. Ela simplesmente pergunta ao QuestionDeck pela pergunta atual, pede para o Player se mover e verifica o resultado da jogada.

Ao separar cada responsabilidade em seu próprio módulo, o código passa a ter coesão alta e baixo acoplamento, garantindo que modificações em um domínio da aplicação não afetem partes não relacionadas. 
