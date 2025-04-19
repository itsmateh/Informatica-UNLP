pub fn ordenar_nombres(v : &mut Vec<String>){
   v.sort();
}

#[test]
fn test_ordenar_nombres(){
   let mut names_v :Vec<String> = vec!["dd".to_string(), "bb".to_string(), "aa".to_string(), "cc".to_string()];
   let check : Vec<String> = vec!["aa".to_string(), "bb".to_string(), "cc".to_string(), "dd".to_string()];
   ordenar_nombres(&mut names_v);
   assert_eq!(check, names_v);
}