package com.projeto.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalControle {

    @GetMapping("/administrativo")
    public String acessarPrincipal() {
        return "administrativo/home";
    }


}


/*

Aqui está uma tabela com alguns dos comandos e atalhos úteis do IntelliJ
IDEA para Linux. Esses atalhos podem ajudá-lo a melhorar sua produtividade
ao trabalhar com este IDE:

| **Ação**                         | **Atalho**                  |
|----------------------------------|-----------------------------|
| **Navegação**                    |                             |
| Ir para a classe                 | `Ctrl + N`                  |
| Ir para o arquivo                | `Ctrl + Shift + N`          |
| Ir para a linha                  | `Ctrl + G`                  |
| Ir para a declaração              | `Ctrl + B` ou `Ctrl + Click`|
| Ir para a implementação           | `Ctrl + Alt + B`            |
| Navegar entre arquivos abertos    | `Ctrl + Tab`                |
| **Edição de Código**             |                             |
| Executar o código atual          | `Shift + F10`               |
| Duplicar linha                   | `Ctrl + D`                  |
| Mover linha para cima            | `Shift + Up`                |
| Mover linha para baixo           | `Shift + Down`              |
| Comentário em linha              | `Ctrl + /`                  |
| Comentar bloco                   | `Ctrl + Shift + /`          |
| Reformatar código                | `Ctrl + Alt + L`            |
| Completar código                 | `Ctrl + Space`              |
| **Busca e Pesquisa**             |                             |
| Encontrar na classe atual        | `Ctrl + F`                  |
| Encontrar em todo o projeto      | `Ctrl + Shift + F`          |
| Substituir na classe atual       | `Ctrl + R`                  |
| Substituir em todo o projeto     | `Ctrl + Shift + R`          |
| **Controle de Versão**           |                             |
| Commit alterações                | `Ctrl + K`                  |
| Atualizar do repositório         | `Ctrl + T`                  |
| **Debugging**                    |                             |
| Iniciar depuração                | `Shift + F9`                |
| Passo a passo                    | `F8`                        |
| Avaliar expressão                | `Alt + F8`                  |
| **Janela de Ferramentas**        |                             |
| Abrir terminal                   | `Alt + F12`                 |
| Abrir a janela de estrutura      | `Alt + 1`                   |
| Abrir a janela de problemas      | `Alt + 6`                   |
| **Geral**                        |                             |
| Salvar arquivo                   | `Ctrl + S`                  |
| Abrir configurações              | `Ctrl + Alt + S`            |
| Fechar janela                    | `Ctrl + F4`                 |
| **Atalhos de Navegação Rápida**  |                             |
| Alternar entre janelas           | `Ctrl + Shift + [` ou `Ctrl + Shift + ]` |

Você pode personalizar esses atalhos indo em File -> Settings -> Keymap
 para ajustar conforme sua preferência.
 */