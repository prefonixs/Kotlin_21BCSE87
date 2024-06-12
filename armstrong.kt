fun main() {
    var num=153
    var temp=num
    var length=0
    while(temp>0){
        temp/=10
        length++
    }
    temp=num
    var armNum=0
    while(temp>0){
        var tempVal=temp%10
        var digitVal=1
        for(i in 1..length){
            digitVal*=tempVal
        }
        armNum+=digitVal
        temp/=10
    }
    if(armNum==num){
        println("Armstrong number")
    }
    else{
        println("not an Armstrong number")
    }
}