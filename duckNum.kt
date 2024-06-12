fun main() {
    var num=1032
    var temp=num
    var isDuck=false
    while(temp>0){
        if(temp%10==0){
            isDuck=true
            break
        }
        temp/=10
    }
    if(isDuck){
        println("${num} is a Duck number")
    }
    else{
        println("${num} is not a Duck number")
    }
}