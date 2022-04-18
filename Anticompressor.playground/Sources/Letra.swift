import Foundation

public class Letra {
    public let caractere: Character
    public let substituto: String?
    
    public var contem: Set<Letra>
    public var contidaEm: Set<Letra>
    
    private var tamanho: Int?
    
    public init(caractere: Character, substituto: String? = nil) {
        self.caractere = caractere
        self.substituto = substituto
        
        self.contem = []
        self.contidaEm = []
        
        self.tamanho = nil
    }
    
    public func encontraTamanho() -> Int {
        ///Se já encontrou o tamanho, return tamanho.
        if let tamanho = self.tamanho {
            return tamanho
        }
        
        ///Se não houver substituto, return 1.
        guard let substituto = substituto else {
            return 1
        }

        var tamanhoLetras = [Character: Int]()
        var tamanho = 0
        
        for letra in contem {
            tamanhoLetras[letra.caractere] = letra.encontraTamanho()
        }
        
        for caractere in substituto {
            tamanho += tamanhoLetras[caractere]!
        }
        self.tamanho = tamanho
        return tamanho
    }
}
    
extension Letra: CustomStringConvertible {
    public var extenseDescription: String {
        var str: String = "\(caractere) --> Substituto: \(substituto ?? "nil")"

        str += "\nContem as letras: "
        contem.forEach { str.append(String("\($0.caractere),")) }
        str.removeLast()

        str += "\nEsta contido em: "
        contidaEm.forEach { str.append(String("\($0.caractere),")) }
        str.removeLast()

        return str
    }
    
    public var description: String {
        return "\(caractere)"
    }
}

extension Letra: Hashable {
    public static func == (lhs: Letra, rhs: Letra) -> Bool {
        lhs.caractere == rhs.caractere
    }
    
    public func hash(into hasher: inout Hasher) {
        hasher.combine(caractere)
    }
}
