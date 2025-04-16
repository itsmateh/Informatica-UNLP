use std::vec;
                  // & -> respetamos el ownership, pide prestado la ref. (read-only)
pub fn suma_pares(v:&Vec<i32>) -> i32{
   let mut ans = 0; 
   for val in v {if val & 1 == 0 {ans += val;}}
   ans  
}

#[test]
fn test_suma_pares(){
   let even_numbers:Vec<i32> = vec![2,4]; 
   let value = suma_pares(&even_numbers); 
   assert_eq!(6,value);
}