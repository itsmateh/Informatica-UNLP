pub fn es_par(num:i32) -> bool{return num%2 == 0}

#[test]
fn test_es_par(){
   assert_eq!(es_par(2), true);
   assert_eq!(es_par(328399232), true);
}