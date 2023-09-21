export const changeDate = (date:string|undefined) :string => {
  if (date === undefined) {
    return "0"
  }
  const createdDate = new Date(date)
  const year = createdDate.getFullYear()
  let month :string = String(createdDate.getMonth() +1)
  let day :string = String(createdDate.getDate()
)
  if (Number(month) < 10) {
    month = `0${month}`
  }
  if (Number(day) < 10) {
    day = `0${day}`
  }

  return [year, month, day].join("/")
}