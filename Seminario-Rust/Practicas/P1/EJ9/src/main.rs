fn main() {
    let arr: [i32; 5] = [2,4,6,8,10]; 
    let mut ans = 0;  

    for num in arr{
        ans+=num;
    }
    
    println!("Arreglo: {:?}", arr);
    println!("Su suma: {}", ans);
}
