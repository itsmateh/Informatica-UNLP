pub fn cantidad_en_rango(v:&Vec<i32>, start:i32, end:i32) -> usize{
   let mut v_sorted = v.clone();
   v_sorted.sort(); 
   
   let left = match v_sorted.binary_search(&start){
      Ok(value) => value,
      Err(value) => value, // value >= start
   };

   let right = match v_sorted.binary_search(&(end+1)){
      Ok(value) => value,
      Err(value) => value, // value > end
   };
   
   let ans = right - left;
   ans
}

#[test]
fn test_cantidad_en_rango(){
   let v:Vec<i32> = vec![1,2,3,4,5,6,7,8,9,10];
   let left:i32 = 3; let right:i32 = 9; 
   assert_eq!(7,cantidad_en_rango(&v, left, right));
}