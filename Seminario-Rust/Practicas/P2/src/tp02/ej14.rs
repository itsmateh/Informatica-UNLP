pub fn incrementar(n : &mut f32){
   *n += 1.0;
}

#[test]
fn test_incrementar(){
   let mut number:f32 = 2.0;
   let check:f32 = 3.0; 
   incrementar(&mut number);
   assert_eq!(check, number); 
}
