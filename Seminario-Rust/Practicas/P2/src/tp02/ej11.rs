pub fn multiplicar_valores(v:&mut Vec<i32>, factor:i32){
   for value in v.iter_mut(){
      // aca value es una refencia mutable, por lo que tengo que acceder a su valor y modifcarlo: desreferenciar 
      *value *= factor;
   }
}

#[test]
fn test_multiplicar_valores(){
   let mut v:Vec<i32> = vec![1,2,3];
   let check:Vec<i32> = vec![2,4,6];
   multiplicar_valores(&mut v, 2);
   assert_eq!(check, v);
}