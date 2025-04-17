pub fn longitud_de_cadenas(v:&Vec<String>) -> Vec<usize>{
   let mut ans = Vec::new();

   for i in v{
      ans.push(i.len());
   }
   ans
}

#[test]
fn test_longitud_de_cadenas(){
   let vector = vec![String::from("one"), String::from("two"), String::from("three")];
   let correct_ans = vec![3,3,5];
   // otra opcion let vector2 = vec!["uno".to_string(),"dos".to_string(),"tres".to_string()];

   let ans = longitud_de_cadenas(&vector);
   assert_eq!(ans, correct_ans);
}