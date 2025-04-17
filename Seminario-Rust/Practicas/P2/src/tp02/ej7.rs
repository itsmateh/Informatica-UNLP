pub fn cantidad_de_mayores(v: &Vec<i32>, limit: i32) -> i32 {
   let mut ans = 0;
   for value in v {
       if *value > limit {
           ans += 1;
       }
   }
   ans
}

#[test]
fn test_cantidad_de_mayores() {
   let v = vec![2, 4, 6, 8, 10];
   let limite = 5;
   let esperados = 3; // 6, 8, 10

   assert_eq!(esperados, cantidad_de_mayores(&v, limite));
}
