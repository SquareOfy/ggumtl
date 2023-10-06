export function extractNumber(inputData:string):number {
  const result = inputData.match(/\d+/g); // 숫자인 것만 골라서 잘라서 리스트로 받아옴
  return Number(result)
}