pub fn reemplazar_pares(v:&mut Vec<i32>){
   for value in v.iter_mut(){
      if *value & 1 == 0 {*value = -1;}
   }
}

#[test]
fn test_reemplazar_pares(){
   let mut v : Vec<i32> = vec![1,2,1]; 
   let check : Vec<i32> = vec![1,-1,1]; 
   reemplazar_pares(&mut v);
   assert_eq!(check, v);
}