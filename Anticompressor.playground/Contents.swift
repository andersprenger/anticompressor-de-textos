//
// Pontifícia Universidade Católica do Rio Grande do Sul
//
// Trabalho 1 de Algoritmos e Estrutura de Dados 2
// Anderson Sprenger
// 26 de Abril de 2022
//

import Foundation

class AnticompressorDeArquivos {
    var letras: [Letra] = []
    var raiz: Letra?
    
    init(_ arquivoCaso: String = "caso10") {
        self.letras = carregaLetras(do: arquivoCaso)
        self.raiz = nil
        
        carregaDependencias()
        
        self.raiz = encontraRaiz()
        print(arquivoCaso, raiz?.encontraTamanho() ?? "")
    }
    
    ///
    /// Carrega as das letras a partir do arquivo caso e os insere no vetor de letras.
    ///
    func carregaLetras(do arquivoCaso: String) -> [Letra] {
        let caso = loadFile(named: arquivoCaso)
        var letras: [Letra] = []
        
        for linha in caso {
            let linhaDividida = linha.split(separator: " ")
            
            switch linhaDividida.count {
            case 2:
                letras.append(Letra(caractere: linhaDividida[0].first!, substituto: String(linhaDividida[1])))
            case 1:
                letras.append(Letra(caractere: linhaDividida[0].first!))
            default:
                continue
            }
        }
        
        return letras
    }
    
    ///
    /// Carrega as dependencias das letras, verificando se as letras estão contidas nos substitutos das outras letras.
    ///
    func carregaDependencias() {
        for letra in letras {
            for outraLetra in letras {
                // Se for a mesma letra, pula para a próxima.
                if letra.caractere == outraLetra.caractere { continue }
                
                // Se a letra contem a outra letra em seu substituto, ela vai para o array contem da letra.
                if letra.substituto != nil && letra.substituto!.contains(outraLetra.caractere) {
                    letra.contem.insert(outraLetra)
                    // TODO: usar weight
//                    adjacencyList.add(.directed, from: vertices[letra]!, to: vertices[outraLetra]!, weight: nil)
                }
                
                // Se a outa letra contem a letra em seu substituto, ela vai para o array contem da outra letra.
                if outraLetra.substituto != nil && outraLetra.substituto!.contains(letra.caractere) {
                    letra.contidaEm.insert(outraLetra)
                }
            }
        }
    }
    
    ///
    /// Encontra um nodo candidato a raiz da arvore.
    /// Para tal, ele não deve: nem ser uma ilha, nem estar contido em outro nodo.
    ///
    func encontraRaiz() -> Letra {
        for letra in letras where letra.contidaEm.isEmpty && !letra.contem.isEmpty {
            return letra
        }
        
        fatalError("raiz não encontrada!")
    }
}

AnticompressorDeArquivos("caso0")
AnticompressorDeArquivos("caso01")
AnticompressorDeArquivos("caso02")
AnticompressorDeArquivos("caso03")
AnticompressorDeArquivos("caso04")
AnticompressorDeArquivos("caso05")
AnticompressorDeArquivos("caso06")
AnticompressorDeArquivos("caso07")
AnticompressorDeArquivos("caso08")
AnticompressorDeArquivos("caso09")
AnticompressorDeArquivos("caso10")

