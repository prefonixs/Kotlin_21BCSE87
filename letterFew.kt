fun main() {
    var inputStr="Hello World"
    var feq=IntArray(58)
    for(char in inputStr){
        if(char!=' '){
            feq[char-'A']++
        }
    }
    for(i in 0..57){
        if(feq[i]>0){
            println("${'A'+i}:${feq[i]}")
        }
    }
}