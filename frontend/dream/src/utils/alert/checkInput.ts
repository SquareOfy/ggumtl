export function checkWrongInput(inputData:string):boolean {
  const blank_pattern = /^\s+|\s+$/g; // 엔터는 허용
  var special_pattern = /[`~@#$%^&*|\\\'\";:\/]/gi; // 특수문자 제거
  if (blank_pattern.test(inputData)){
    return false
  } else if( special_pattern.test(inputData)){
    return false
  }
  return true
}