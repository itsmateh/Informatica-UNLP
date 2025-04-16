pub fn duplicar_valores(v : &Vec<f32>) -> Vec<f32>{
   let mut ans:Vec<f32> = Vec::new(); 
   for value in v{ans.push(value * 2.0);}
   ans
}

#[test]
fn test_cantidad_impares(){
   let vector:Vec<f32> = vec![1.0, 2.0, 3.0]; 
   let test_vector:Vec<f32> = vec![2.0, 4.0, 6.0];
   let duplicate_vector:Vec<f32> = duplicar_valores(&vector); 
   assert_eq!(duplicate_vector, test_vector);
}