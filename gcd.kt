fun main() {
    var num1=56
    var num2=98
    while(num2!=0){
        var temp=num2
        num2=num1%num2
        num1=temp
    }
    println(num1)
}