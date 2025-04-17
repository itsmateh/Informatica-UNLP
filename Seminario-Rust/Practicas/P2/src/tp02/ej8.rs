pub fn sumar_arreglos(v1:&Vec<i32>, v2:&Vec<i32>) -> Vec<i32>{
   let mut ans = Vec::new();
   for index in 0..v1.len(){
      ans.push(v1[index] + v2[index]);
   }
   ans
}

#[test]
fn test_sumar_arreglos(){
   let v1 = vec![1,2,3];
   let v2 = vec![4,5,6];
   let ans = vec![5,7,9]; 
   assert_eq!(ans, sumar_arreglos(&v1, &v2));
}