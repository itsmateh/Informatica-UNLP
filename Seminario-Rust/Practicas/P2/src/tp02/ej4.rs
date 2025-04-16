pub fn cantidad_impares(vector:&Vec<i32>) -> i32{ 
   let mut ans = 0; 
   for value in vector{if value & 1 == 1{ans +=1;}}
   ans
}

#[test]
fn test_cantidad_impares(){
   let vector:Vec<i32> = vec![1,3,5,10]; 
   let cnt_odd_numbers:i32 = cantidad_impares(&vector);
   assert_eq!(cnt_odd_numbers, 3); 
   // assert_eq!(cnt_odd_numbers, 4); 
}