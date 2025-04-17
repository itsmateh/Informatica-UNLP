pub fn cantidad_de_cadenas_mayor_a(txt:&str, limit:i32) -> i32{
   let mut ans = 0; 

   for word in txt.split_whitespace(){
      if word.len() > limit as usize {ans +=1;}
   }
   ans
}

#[test]
fn test_cantidad_de_cadenas_mayor_a(){
   let txt:&str = "universidad nacional de la plata"; 
   let ans:i32 = 5; 
   let limit:i32 = 1; 
   assert_eq!(ans, cantidad_de_cadenas_mayor_a(&txt, limit))
}