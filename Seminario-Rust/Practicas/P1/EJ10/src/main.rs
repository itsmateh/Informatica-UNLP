fn main() {
    let arr: [i32; 5] = [1,3,5,7,9];
    let arr2: [i32; 5] = [2,4,6,8,10];

    let mut arr3 = [arr.as_slice(), arr2.as_slice()].concat();
    arr3.extend(arr2); 
    println!("{:?}", arr3);
}
