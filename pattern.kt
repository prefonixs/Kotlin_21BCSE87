fun main() {
    var height=5
    var temp=1
    for(i in 1..height){
        for(j in 1..height-i){
            print(" ")
        }
        for(j in 1..(2*i)-1){
            print("*")
        }
        println()
    }
    for(i in height downTo 1){
        for(j in 1..height-i){
            print(" ")
        }
        for(j in 1..(2*i)-1){
            print("*")
        }
        println()
    }
}