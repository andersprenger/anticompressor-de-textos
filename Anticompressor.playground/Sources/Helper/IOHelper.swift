import Foundation

public func loadFile(named fileName: String) -> [String] {
    guard let path = Bundle.main.path(forResource: fileName, ofType: ""), let content = try? String(contentsOfFile: path) else {
        fatalError("IO Error")
    }
    
    return content.components(separatedBy: .newlines)
}
